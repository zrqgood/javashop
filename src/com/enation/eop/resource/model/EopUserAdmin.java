package com.enation.eop.resource.model;

import java.io.Serializable;

/**
 * 用户管理员
 * 
 * @author lzf
 *         <p>
 *         created_time 2009-11-27 下午01:40:54
 *         </p>
 * @version 1.0
 */
public class EopUserAdmin implements Serializable{
 
 
 
	private static final long serialVersionUID = -5111983361750207866L;
	
	
	private Integer id;
	private Integer userid;
	private String username;
	private String realname;
	private String password;
	private String tel;
	private String mobile;
	private String email;
	private String qq;
	private Integer defaultsiteid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getDefaultsiteid() {
		return defaultsiteid;
	}

	public void setDefaultsiteid(Integer defaultsiteid) {
		this.defaultsiteid = defaultsiteid;
	}

}
