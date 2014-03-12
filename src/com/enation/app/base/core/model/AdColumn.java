package com.enation.app.base.core.model;

/**
 * 广告位
 * 
 * @author 李志富 lzf<br/>
 *         2010-2-4 下午03:24:48<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class AdColumn {
	private Integer acid;
	private String cname;
	private String width;
	private String height;
	private String description;
	private Integer anumber;
	private Integer atype;
	private Integer rule;
	private Integer userid;
	private Integer siteid;
	private String disabled;

	public Integer getAcid() {
		return acid;
	}

	public void setAcid(Integer acid) {
		this.acid = acid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAnumber() {
		return anumber;
	}

	public void setAnumber(Integer anumber) {
		this.anumber = anumber;
	}

	public Integer getAtype() {
		return atype;
	}

	public void setAtype(Integer atype) {
		this.atype = atype;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

}
