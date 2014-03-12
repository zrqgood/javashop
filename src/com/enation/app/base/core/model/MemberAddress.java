package com.enation.app.base.core.model;

import java.io.Serializable;

/**
 * 会员中心-接收地址
 * @author lzf<br/>
 * 2010-3-17 下午02:34:48<br/>
 * version 1.0<br/>
 */
public class MemberAddress implements Serializable {
	private int addr_id;
	private int member_id;
	private String name;
	private String country;
	private int province_id;
	private int city_id;
	private int region_id;
	private String province;
	private String city;
	private String region;
	private String addr;
	private String zip;
	private String tel;
	private String mobile;
	private int def_addr;
	public int getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(int addrId) {
		addr_id = addrId;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int memberId) {
		member_id = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getDef_addr() {
		return def_addr;
	}
	public void setDef_addr(int defAddr) {
		def_addr = defAddr;
	}
}