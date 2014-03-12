package com.enation.javashop.core.model;

import java.io.Serializable;

/**
 * 优惠券表
 * @author lzf<br/>
 * 2010-3-23 上午10:41:55<br/>
 * version 1.0<br/>
 */
public class Coupons implements Serializable {
	private int cpns_id;
	private String cpns_name;
	private int pmt_id;
	private String cpns_prefix;
	private int cpns_gen_quantity;
	private String cpns_key;
	private int cpns_status;
	private int cpns_type;
	private int cpns_point;
	private String disabled;//enum('true','false') default 'false'
	public int getCpns_id() {
		return cpns_id;
	}
	public void setCpns_id(int cpnsId) {
		cpns_id = cpnsId;
	}
	public String getCpns_name() {
		return cpns_name;
	}
	public void setCpns_name(String cpnsName) {
		cpns_name = cpnsName;
	}
	public int getPmt_id() {
		return pmt_id;
	}
	public void setPmt_id(int pmtId) {
		pmt_id = pmtId;
	}
	public String getCpns_prefix() {
		return cpns_prefix;
	}
	public void setCpns_prefix(String cpnsPrefix) {
		cpns_prefix = cpnsPrefix;
	}
	public int getCpns_gen_quantity() {
		return cpns_gen_quantity;
	}
	public void setCpns_gen_quantity(int cpnsGenQuantity) {
		cpns_gen_quantity = cpnsGenQuantity;
	}
	public String getCpns_key() {
		return cpns_key;
	}
	public void setCpns_key(String cpnsKey) {
		cpns_key = cpnsKey;
	}
	public int getCpns_status() {
		return cpns_status;
	}
	public void setCpns_status(int cpnsStatus) {
		cpns_status = cpnsStatus;
	}
	public int getCpns_type() {
		return cpns_type;
	}
	public void setCpns_type(int cpnsType) {
		cpns_type = cpnsType;
	}
	public int getCpns_point() {
		return cpns_point;
	}
	public void setCpns_point(int cpnsPoint) {
		cpns_point = cpnsPoint;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}
