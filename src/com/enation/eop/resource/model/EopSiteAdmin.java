package com.enation.eop.resource.model;

/**
 * 网站管理员所管理的站列表
 * 
 * @author lzf
 *         <p>
 *         created_time 2009-11-27 下午01:41:29
 *         </p>
 * @version 1.0
 */
public class EopSiteAdmin {
	private Integer id;
	private Integer managerid;
	private Integer siteid;
	private Integer userid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getManagerid() {
		return managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
}
