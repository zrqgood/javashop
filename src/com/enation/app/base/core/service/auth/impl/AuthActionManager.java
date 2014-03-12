package com.enation.app.base.core.service.auth.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.AuthAction;
import com.enation.app.base.core.service.auth.IAuthActionManager;
import com.enation.eop.sdk.database.BaseSupport;

/**
 * 权限点管理
 * @author kingapex
 * 2010-10-24下午10:38:33
 */
public class AuthActionManager extends BaseSupport<AuthAction> implements IAuthActionManager {

	@Transactional(propagation = Propagation.REQUIRED)
	public int add(AuthAction act) {
		this.baseDaoSupport.insert("auth_action", act);
		return this.baseDaoSupport.getLastId("auth_action");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int actid) {
		//删除角色权限表中对应的数据
		this.baseDaoSupport.execute("delete from role_auth where authid=?", actid);
		//删除权限基本数据
		this.baseDaoSupport.execute("delete from auth_action where actid=?", actid);
	}

	
	public void edit(AuthAction act) {
		this.baseDaoSupport.update("auth_action", act, "actid="+act.getActid());
	}

	
	public List<AuthAction> list() {
		
		return this.baseDaoSupport.queryForList("select * from auth_action", AuthAction.class);
	}


	public AuthAction get(int authid) {
		return this.baseDaoSupport.queryForObject("select * from auth_action where actid=?", AuthAction.class, authid);
	}

}
