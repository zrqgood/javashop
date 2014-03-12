package com.enation.eop.resource.dto;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-14 下午12:04:17
 *         </p>
 * @version 1.0
 */
public class SessionDTO {
	private Integer userid;
	private Integer defaultsiteid;
	private Integer managerid;
	

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getDefaultsiteid() {
		return defaultsiteid;
	}

	public void setDefaultsiteid(Integer defaultsiteid) {
		this.defaultsiteid = defaultsiteid;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

}
