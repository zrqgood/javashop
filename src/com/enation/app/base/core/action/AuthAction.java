package com.enation.app.base.core.action;

import com.enation.app.base.core.service.auth.IAuthActionManager;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;

/**
 * 权限点
 * @author kingapex
 * 2010-11-9下午05:55:11
 */
public class AuthAction extends WWAction {
	private String name;
	private int authid;
	private int isEdit;
	private IAuthActionManager authActionManager;
	private Integer[] menuids;
	private com.enation.app.base.core.model.AuthAction auth;
	public String add(){
		isEdit=0;
		return "input";
	} 
	
	public String edit(){
		isEdit=1;
		auth =this.authActionManager.get(authid);
		return "input";
	}
	
	public String save(){
		if(isEdit==0){
			return this.saveAdd();
		}else {
			return this.saveEdit();
		}
			
	}

	public String saveEdit(){
		
		try{
			com.enation.app.base.core.model.AuthAction act = new com.enation.app.base.core.model.AuthAction();
			act.setName(name);
			act.setType("menu");
			act.setActid(authid);
			act.setObjvalue(StringUtil.arrayToString(menuids, ","));
			this.authActionManager.edit(act);
			this.json ="{result:1,authid:'"+authid+"'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e.fillInStackTrace());
			this.json ="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String saveAdd(){
		try{
			com.enation.app.base.core.model.AuthAction act = new com.enation.app.base.core.model.AuthAction();
			act.setName(name);
			act.setType("menu");
			act.setObjvalue(StringUtil.arrayToString(menuids, ","));
			authid = this.authActionManager.add(act);
			this.json ="{result:1,authid:'"+authid+"'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e.fillInStackTrace());
			this.json ="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}

	public String delete(){
		try{
			this.authActionManager.delete(this.authid);
			this.json ="{result:1,authid:'"+authid+"'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e.fillInStackTrace());
			this.json ="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAuthid() {
		return authid;
	}

	public void setAuthid(int authid) {
		this.authid = authid;
	}

	public int getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}

	public IAuthActionManager getAuthActionManager() {
		return authActionManager;
	}

	public void setAuthActionManager(IAuthActionManager authActionManager) {
		this.authActionManager = authActionManager;
	}

	public Integer[] getMenuids() {
		return menuids;
	}

	public void setMenuids(Integer[] menuids) {
		this.menuids = menuids;
	}

	public com.enation.app.base.core.model.AuthAction getAuth() {
		return auth;
	}

	public void setAuth(com.enation.app.base.core.model.AuthAction auth) {
		this.auth = auth;
	}

 
	
}
