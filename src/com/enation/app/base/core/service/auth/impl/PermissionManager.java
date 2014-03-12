package com.enation.app.base.core.service.auth.impl;

import java.util.List;

import com.enation.app.base.core.model.AuthAction;
import com.enation.app.base.core.model.Role;
import com.enation.app.base.core.service.auth.IPermissionManager;
import com.enation.eop.sdk.database.BaseSupport;

/**
 * 权限管理
 * @author kingapex
 * 2010-11-4下午12:50:09
 */
public class PermissionManager extends BaseSupport implements IPermissionManager {
	
	/**
	 * 为某个用户赋予某些角色<br>
	 * 会清除此用户的前角色，重新赋予
	 * @param userid  用户id
	 * @param roleids 角色id数组
	 */
	public void giveRolesToUser(int userid, int[] roleids) {
		this.baseDaoSupport.execute("delete from user_role where userid=?", userid);
		if(roleids==null) return ;
		for(int roleid:roleids){
			this.baseDaoSupport.execute("insert into user_role(roleid,userid)values(?,?)", roleid,userid);
		}
	}
	
	/**
	 * 读取某用户的权限点
	 * @param userid 用户id
	 * @param acttype 权限类型
	 * @return
	 */
	public List<AuthAction> getUesrAct(int userid, String acttype) {
		
		//查询权限表acttype符合条记录
		String sql ="select * from "+ this.getTableName("auth_action")+" where type=? ";
		//并且 权限id在用户的角色权限范围内
		sql+=" and actid in(select authid from  "+this.getTableName("role_auth")+" where roleid in ";
		//查询用户的角色列表
		sql+=" (select roleid from "+this.getTableName("user_role")+" where userid=?)";
		sql+=" )";
	 
		return this.daoSupport.queryForList(sql,AuthAction.class,acttype,userid);
	}

	
	/**
	 * 读取某用户的角色集合
	 * @param userid
	 * @return 此用户的角色集合
	 */	
	public List<Role> getUserRoles(int userid) {
		return this.baseDaoSupport.queryForList("select roleid from  user_role where userid=?", userid);
	}
	
	
	/**
	 * 删除某用户的所有角色
	 * @param userid 要删除角色的用户
	 */
	public void cleanUserRoles(int userid){
		this.baseDaoSupport.execute("delete from user_role where userid=?", userid);
	}

}
