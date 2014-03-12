package com.enation.app.base.core.model;

import java.io.Serializable;

public class MultiSite  implements Serializable{
	private int siteid;
	private int parentid;
	private Integer code;
	private String name;
	private String domain;
	private Integer themeid;
	private int level; //站点级别
	
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}


	public Integer getThemeid() {
		return themeid;
	}
	public void setThemeid(Integer themeid) {
		this.themeid = themeid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}