package com.enation.app.base.core.model;

/**
 * 权限点实体
 * @author kingapex
 * 2010-10-24下午12:38:51
 */
public class AuthAction {
	private int actid;
	private String name;
	private String type;
	private String objvalue;
	public int getActid() {
		return actid;
	}
	public void setActid(int actid) {
		this.actid = actid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObjvalue() {
		return objvalue;
	}
	public void setObjvalue(String objvalue) {
		this.objvalue = objvalue;
	}
	
}
