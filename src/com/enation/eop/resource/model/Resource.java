package com.enation.eop.resource.model;

import com.enation.framework.database.NotDbField;

/**
 * 资源实体抽象类,所有的资源都有id和对应的站点id
 * @author lzf
 * <p>created_time 2009-11-13 上午11:14:24</p>
 * @version 1.0
 */
public  class Resource {
	private Integer id;
 
	private Integer deleteflag = 0;
	
	private String productId;
	

	public Integer getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	
	@NotDbField
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
