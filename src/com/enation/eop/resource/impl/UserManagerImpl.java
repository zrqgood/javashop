package com.enation.eop.resource.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.user.UserContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;

/**
 * 易族易站用户管理 
 * @author kingapex
 * 2010-11-8下午02:22:24
 */
public class UserManagerImpl implements IUserManager {
	private IDaoSupport daoSupport;
	private ISiteManager siteManager;
	private IAdminUserManager adminUserManager;
	protected final Logger logger = Logger.getLogger(getClass());
	
	public void changeDefaultSite(Integer userid, Integer managerid,
			Integer defaultsiteid) {
		UserUtil.validUser(userid);
		String sql  ="update eop_user set defaultsiteid=? where id=?";
		  daoSupport.execute(sql, defaultsiteid,userid);
	}

	/**
	 * 创建用户
	 * @param user 创建一个用户
	 * @return 此用户的用户id
	 * @throws RuntimeException 用户名已经存在
	 */ 
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer createUser(EopUser user) {
		//检测用户是否已经存在
		String sql ="select count(0) from eop_user where username=?";
		int count = this.daoSupport.queryForInt(sql, user.getUsername());
		if(count>0) throw new RuntimeException("用户"+ user.getUsername()+"已存在");
		user.setPassword(StringUtil.md5(user.getPassword()));
		
		this.daoSupport.insert("eop_user", user);
		Integer userid = this.daoSupport.getLastId("eop_user");

		return userid;
	}

	
	
	/**
	 * 检测用户名是否存在
	 * @param username
	 * @return 存在返回true，不存在返回false
	 * @author kingapex
	 */
	private boolean checkUserName(String username){
		String sql ="select * from eop_user where username=?";
		List list  = this.daoSupport.queryForList(sql, username);
		if(list== null || list.isEmpty() || list.size()==0) 
			return false;
		else
			return true;
	}
	
	
	/**
	 * 平台用户登录
	 */
	public int login(String username, String password) {
		int result=0;
		try{
			EopUser user = this.get(username);
			if(user.getPassword().equals( StringUtil.md5(password) )){
				result =1;
				WebSessionContext<EopUser> sessonContext = ThreadContextHolder
				.getSessionContext();	
				sessonContext.setAttribute(IUserManager.USER_SESSION_KEY, user);

			}else{
				result =0;
			}
		}catch(RuntimeException e){
			this.logger.error(e.fillInStackTrace());
		}
	
		return result;
	}
 
 
	public int checkIsLogin() {
		WebSessionContext<EopUser> sessonContext = ThreadContextHolder
		.getSessionContext();	
		EopUser user = sessonContext.getAttribute(IUserManager.USER_SESSION_KEY);
		if(user!=null)
			return 1;
		else
			return 0;
	}
	
	public void logout() {
		
		WebSessionContext<UserContext> sessonContext = ThreadContextHolder.getSessionContext();		
		sessonContext.removeAttribute(IUserManager.USER_SESSION_KEY);
		
		ThreadContextHolder.getSessionContext().removeAttribute("userAdmin");	
		
	}
	
	
	/**
	 * 获取当前登录用户
	 * @return 当前登录的用户
	 * @throws RuntimeException 用户未登录抛出此异常
	 */
	public EopUser getCurrentUser() {
		WebSessionContext<EopUser> sessonContext = ThreadContextHolder.getSessionContext();	
		EopUser user = sessonContext.getAttribute(IUserManager.USER_SESSION_KEY);
		return user;
	}
	
	
	public EopUser get(Integer userid) {
		String sql ="select * from eop_user where deleteflag = 0 and id = ?";
		return (EopUser)this.daoSupport.queryForObject(sql, EopUser.class, userid);
	}
	
	

	public IDaoSupport<EopUser> getDaoSupport() {
		return daoSupport;
	}



	public void setDaoSupport(IDaoSupport<EopUser> daoSupport) {
		this.daoSupport = daoSupport;
	}



	public ISiteManager getSiteManager() {
		return siteManager;
	}



	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}



	public IAdminUserManager getAdminUserManager() {
		return adminUserManager;
	}



	public void setAdminUserManager(IAdminUserManager adminUserManager) {
		this.adminUserManager = adminUserManager;
	}


	
	public void edit(EopUser user) {
		this.daoSupport.update("eop_user", user, "id = "+user.getId());
	}




	public EopUser get(String username) {
		return  (EopUser)this.daoSupport.queryForObject("select * from eop_user where username=?", EopUser.class, username);
	}


	public Page list(String keyword, int pageNo, int pageSize) {
		String sql ="select u.*,d.regdate  regdate from eop_user u left join eop_userdetail d on  u.id= d.userid";
		if(!StringUtil.isEmpty(keyword)){
			sql+=" where  u.username like '%" + keyword +"%'";
		
		}
		sql+=" order by  d.regdate desc";
		return this.daoSupport.queryForPage(sql, pageNo, pageSize);
	}
	
	
	
	/**
	 * 删除某用户信息，会删除 此用户的所有站点 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer userid){
		
		//删除此用户的所有站点
		List<Map> siteList  = this.siteManager.list(userid);
		for(Map site: siteList){
			siteManager.delete((Integer)site.get("id"));
		}
		
		this.daoSupport.execute("delete from eop_userdetail where userid = ?", userid); //删除用户详细表
		this.daoSupport.execute("delete from eop_useradmin where userid = ?", userid); //删除管理员表
		this.daoSupport.execute("delete from eop_user where id = ?", userid); //删除用户信息
	}

}
