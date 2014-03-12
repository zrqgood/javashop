package com.enation.eop.resource.model;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-27 下午02:10:37
 *         </p>
 * @version 1.0
 */
public class EopSiteDomain {
	private Integer id;
	private String domain;
	private Integer domaintype;
	private Integer siteid;
	private Integer userid;
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Integer getDomaintype() {
		return domaintype;
	}
	public void setDomaintype(Integer domaintype) {
		this.domaintype = domaintype;
	}
	public Integer getSiteid() {
		return siteid;
	}
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

}
