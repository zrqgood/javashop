package com.enation.app.base.core.service.auth.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.MultiSite;
import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.app.base.core.service.auth.IPermissionManager;
import com.enation.eop.resource.model.AdminUser;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.UserContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;

/**
 * 管理员管理实现
 * @author kingapex 2010-11-5下午06:49:02
 */
public class AdminUserManagerImpl extends BaseSupport<AdminUser> implements
		IAdminUserManager {

	private IPermissionManager permissionManager;
	
	public void clean() {
		this.baseDaoSupport.execute("truncate table adminuser");
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer add(AdminUser adminUser) {
		adminUser.setPassword( StringUtil.md5(adminUser.getPassword()) );
		//添加管理员
		this.baseDaoSupport.insert("adminuser", adminUser);
		int userid =this.baseDaoSupport.getLastId("adminuser");
		
		//给用户赋予角色
		permissionManager.giveRolesToUser(userid, adminUser.getRoleids());
		return userid;
	}


	/**
	 * 为某个站点添加管理员
	 * @param userid
	 * @param siteid
	 * @param adminUser
	 * @return 添加的管理员id
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer add(int userid, int siteid, AdminUser adminUser) {
		adminUser.setState(1);
		this.baseDaoSupport.insert("adminuser", adminUser);
		return this.baseDaoSupport.getLastId("adminuser");
	}
	
	public int checkLast() {
		int count = this.baseDaoSupport.queryForInt("select count(0) from adminuser");
		return count;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		//如果只有一个管理员，则抛出异常
		if(this.checkLast()==1){
			throw new RuntimeException("必须最少保留一个管理员");
		}
		
		//清除用户角色
		permissionManager.cleanUserRoles(id);
		
		//删除用户基本信息
		this.baseDaoSupport.execute("delete from adminuser where userid=?",id);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(AdminUser adminUser) {
		
		//给用户赋予角色
		permissionManager.giveRolesToUser(adminUser.getUserid(), adminUser.getRoleids());
		
		//修改用户基本信息
		if( !StringUtil.isEmpty( adminUser.getPassword() ))
		adminUser.setPassword( StringUtil.md5(adminUser.getPassword()) );
		int userId = adminUser.getUserid();
		adminUser.setUserid(null);
		this.baseDaoSupport.update("adminuser", adminUser, "userid="+ userId);
	}

	public AdminUser get(Integer id) {
		return this.baseDaoSupport.queryForObject("select * from adminuser where userid=?", AdminUser.class, id);
	}

	public List list() {
		return this.baseDaoSupport.queryForList("select * from adminuser order by dateline", AdminUser.class);
	}
	
	
	public List<Map> list(Integer userid, Integer siteid) {
		String sql  ="select * from es_adminuser_"+ userid +"_"+ siteid ;
		return this.daoSupport.queryForList(sql);
	}
	
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password 未经过md5加密的密码
	 * @return 登录成功返回管理员
	 * @throws RuntimeException 当登录失败时抛出此异常，登录失败的原因可通过getMessage()方法获取
	 */
	public int login(String username, String password) {
		return this.loginBySys(username, StringUtil.md5(password));
	}

	
	/**
	 * 系统登录
	 * @param username
	 * @param password 此处为未经过md5加密的密码
	 * @return 返回登录成功的用户id
	 * @throws RuntimeException 登录失败抛出此异常，登录失败原因可通过getMessage()方法获取
	 */
	public int loginBySys(String username, String password) {
		String sql ="select * from adminuser where username=?";
		List<AdminUser> userList =  this.baseDaoSupport.queryForList(sql,AdminUser.class, username);
		if(userList == null || userList.size()==0){
			throw new RuntimeException("此用户不存在");
		}
		AdminUser user = userList.get(0);
		
		
		if(! password.equals(  user.getPassword() )){
			throw new RuntimeException("密码错误");
		}
		
		if(user.getState()==0){
			throw new RuntimeException("此用户已经被禁用");
		} 
		
		EopSite site  = EopContext.getContext().getCurrentSite(); 
		
		// 开启多站点功能
		if(site.getMulti_site()==1){
			if(user.getFounder()!=1){ //非超级管理员检测是否是站点管理员
				 MultiSite childSite = EopContext.getContext().getCurrentChildSite();
				 if(user.getSiteid()==null || childSite.getSiteid()!=user.getSiteid()){
					 throw new RuntimeException("非此站点管理员");
				 }
			}
		}
		
		//更新月登录次数和此站点的最后登录时间
		int logincount = site.getLogincount();
		long lastlogin  =( (long)site.getLastlogin() )* 1000;
		Date today = new Date();
		if(DateUtil.toString(new Date(lastlogin), "yyyy-MM").equals(DateUtil.toString(today, "yyyy-MM"))){//与上次登录在同一月内
			logincount++;
		}else{
			logincount = 1;
		}
		
		site.setLogincount(logincount);
		site.setLastlogin(DateUtil.getDatelineLong());
		this.daoSupport.execute("update eop_site set logincount=?, lastlogin=? where id=?", logincount,site.getLastlogin(),site.getId());
		
		//记录session信息
		WebSessionContext  sessonContext = ThreadContextHolder
		.getSessionContext();			
		UserContext userContext = new UserContext(site.getUserid(),
				site.getId(), user.getUserid());
		sessonContext.setAttribute(UserContext.CONTEXT_KEY, userContext);
		sessonContext.setAttribute("admin_user_key", user);
		
		return user.getUserid();
	}	
	
	
	public AdminUser getCurrentUser(){
		WebSessionContext<AdminUser>  sessonContext = ThreadContextHolder
		.getSessionContext();			
		return  sessonContext.getAttribute("admin_user_key");
	}
	
	
	public IPermissionManager getPermissionManager() {
		return permissionManager;
	}

	public void setPermissionManager(IPermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}
	



}
