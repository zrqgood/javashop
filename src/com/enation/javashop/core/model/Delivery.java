package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;

/**
 * 货运单
 * @author kingapex
 *2010-4-8上午09:13:33
 */
public class Delivery implements java.io.Serializable {

	private Integer delivery_id;

	private Integer order_id;
	
	private Integer type;

	private Integer member_id;
	
	//会员用户
	private String member_name;
	
	private String sn;

	private Double money;

	private String ship_type;

	private int is_protect;

	private Double protect_price;

	private Integer logi_id;

	private String logi_name;

	private String logi_no;

	private String ship_name;
	
	private int province_id;
	
	private int city_id;
	
	private int region_id;
	
	private String province;
	
	private String city;
	
	private String region;

	private String ship_addr;

	private String ship_zip;

	private String ship_tel;

	private String ship_mobile;

	private String ship_email;

	private String op_name;

	private String remark;

	private Long create_time;
	private String reason;
	public Integer getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(Integer delivery_id) {
		this.delivery_id = delivery_id;
	}
 

	public Integer getLogi_id() {
		return logi_id;
	}

	public void setLogi_id(Integer logi_id) {
		this.logi_id = logi_id;
	}

	public String getLogi_name() {
		return logi_name;
	}

	public void setLogi_name(String logi_name) {
		this.logi_name = logi_name;
	}

	public String getLogi_no() {
		return logi_no;
	}

	public void setLogi_no(String logi_no) {
		this.logi_no = logi_no;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getProtect_price() {
		return protect_price;
	}

	public void setProtect_price(Double protect_price) {
		this.protect_price = protect_price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int provinceId) {
		province_id = provinceId;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int cityId) {
		city_id = cityId;
	}

	public int getRegion_id() {
		return region_id;
	}

	public void setRegion_id(int regionId) {
		region_id = regionId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getShip_addr() {
		return ship_addr;
	}

	public void setShip_addr(String ship_addr) {
		this.ship_addr = ship_addr;
	}

	public String getShip_email() {
		return ship_email;
	}

	public void setShip_email(String ship_email) {
		this.ship_email = ship_email;
	}

	public String getShip_mobile() {
		return ship_mobile;
	}

	public void setShip_mobile(String ship_mobile) {
		this.ship_mobile = ship_mobile;
	}

	public String getShip_name() {
		return ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}

	public String getShip_tel() {
		return ship_tel;
	}

	public void setShip_tel(String ship_tel) {
		this.ship_tel = ship_tel;
	}

	public String getShip_type() {
		return ship_type;
	}

	public void setShip_type(String ship_type) {
		this.ship_type = ship_type;
	}

	public String getShip_zip() {
		return ship_zip;
	}

	public void setShip_zip(String ship_zip) {
		this.ship_zip = ship_zip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public int getIs_protect() {
		return is_protect;
	}

	public void setIs_protect(int isProtect) {
		is_protect = isProtect;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@NotDbField
	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String memberName) {
		member_name = memberName;
	}

	@NotDbField
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	
}