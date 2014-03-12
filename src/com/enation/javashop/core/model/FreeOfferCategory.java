package com.enation.javashop.core.model;

/**
 * 赠品分类
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-18 上午10:07:10<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class FreeOfferCategory {
	private Integer cat_id;
	private String cat_name;
	private Integer publishable;
	private Integer sorder;
	private Integer disabled;
	private Integer userid;
	private Integer siteid;

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer catId) {
		cat_id = catId;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String catName) {
		cat_name = catName;
	}

	public Integer getPublishable() {
		return publishable;
	}

	public void setPublishable(Integer publishable) {
		this.publishable = publishable;
	}

	public Integer getSorder() {
		return sorder;
	}

	public void setSorder(Integer sorder) {
		this.sorder = sorder;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
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
}
