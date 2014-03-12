package com.enation.eop.resource.model;

import java.io.Serializable;

public class Access implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4339848792738875940L;
	private Integer id;
	private String ip;
	private String url; // 具体的url
	private String page; //访问的页面名
	private String area; //访问者地区
	private int access_time; //访问时间
	private int stay_time; //停留时间
	private int point ;//消耗积分
	private String membername; //会员名称，如果为空则为访客
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAccess_time() {
		return access_time;
	}
	public void setAccess_time(int accessTime) {
		 
		access_time = accessTime;
	}
	public int getStay_time() {
		return stay_time;
	}
	public void setStay_time(int stayTime) {
		stay_time = stayTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	
}
