package com.enation.eop.resource.model;

/**
 * @author lzf
 * <p>created_time 2009-12-9 下午03:09:51</p>
 * @version 1.0
 */
public class SiteManagerView {
	private Integer id;
	private Integer userid;
	private String sitename;
	private Integer siteid; //注意！只要此值不是null，则有效
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public Integer getSiteid() {
		return siteid;
	}
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

}
