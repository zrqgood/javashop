package com.enation.javashop.core.model;

/**
 * 订单扩展信息
 * 
 * @author kingapex
 * 
 */
public class OrderMeta {
	private Integer metaid;
	private int orderid;
	private String meta_key;
	private String meta_value;

	public Integer getMetaid() {
		return metaid;
	}

	public void setMetaid(Integer metaid) {
		this.metaid = metaid;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getMeta_key() {
		return meta_key;
	}

	public void setMeta_key(String meta_key) {
		this.meta_key = meta_key;
	}

	public String getMeta_value() {
		return meta_value;
	}

	public void setMeta_value(String meta_value) {
		this.meta_value = meta_value;
	}

}
