package com.enation.app.base.core.service.auth.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.AuthAction;
import com.enation.app.base.core.model.Role;
import com.enation.app.base.core.service.auth.IRoleManager;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.util.StringUtil;

/**
 * 角色管理
 * @author kingapex
 * 2010-10-24下午11:08:12
 */
public class RoleManager extends BaseSupport<Role> implements IRoleManager {

	/**
	 * 添加一个角色
	 * @param role 角色实体
	 * @param acts 此角色的权限集合
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Role role, int[] authids) {
		
		//添加角色并
		this.baseDaoSupport.insert("role", role);
		
		//不赋予权限则直接返回
		if(authids==null) return ;
		
		//获取角色id
		int roleid =  this.baseDaoSupport.getLastId("role");
		
		
		//为这个角色 赋予权限点，写入角色权限对照表
		for(int authid:authids){
			this.baseDaoSupport.execute("insert into role_auth(roleid,authid)values(?,?)", roleid,authid);
		}
		
	}

	/**
	 * 删除某角色
	 * @param roleid
	 */	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int roleid) {
		
		//删除用户角色
		this.baseDaoSupport.execute("delete from user_role where roleid=?", roleid);
		
		//删除角色权限
		this.baseDaoSupport.execute("delete from role_auth where roleid =?", roleid);
		
		//删除角色 
		this.baseDaoSupport.execute("delete from role where roleid =?", roleid);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(Role role, int[] authids) {
		//校验参数 
		if(role.getRoleid()==0) throw new IllegalArgumentException("编辑角色时id不可为空");
		if(StringUtil.isEmpty( role.getRolename())) throw new IllegalArgumentException("编辑角色时名称不可为空");
		
		//清除角色的权限
		this.baseDaoSupport.execute("delete from role_auth where roleid=?", role.getRoleid());

		//为这个角色 赋予权限点，写入角色权限对照表
		for(int authid:authids){
			this.baseDaoSupport.execute("insert into role_auth(roleid,authid)values(?,?)", role.getRoleid(),authid);
		}		
		//更新角色基本信息
		this.baseDaoSupport.update("role", role, "roleid="+role.getRoleid());
	}

	/**
	 * 读取所有角色列表 
	 * @return
	 */
	public List<Role> list() {
		
		return this.baseDaoSupport.queryForList("select * from role", Role.class);
		
	}

	
	/**
	 * 读取某个角色信息，同时读取此角色权限
	 * @param roleid
	 * @return 权限id存于role.actids数组中
	 */
	public Role get(int roleid){
		String sql ="select * from "+ this.getTableName("auth_action") +" where actid in(select authid from "+this.getTableName("role_auth")+" where roleid =?)";
		List  actlist = this.daoSupport.queryForList(sql,AuthAction.class, roleid);
		Role role = this.baseDaoSupport.queryForObject("select * from role where roleid=?", Role.class, roleid);
		
		if(actlist!=null){
			int[] actids = new int[ actlist.size()];
			for(int i=0;i<actlist.size();i++){
				actids[i] =( (AuthAction)actlist.get(i)).getActid();
			}
			role.setActids(actids);
		}
		return  role;
	}

}
