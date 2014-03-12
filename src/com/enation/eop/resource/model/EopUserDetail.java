package com.enation.eop.resource.model;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-27 下午01:42:00
 *         </p>
 * @version 1.0
 */
public class EopUserDetail {
	private Integer id;
	private Integer userid;
	private String bussinessscope;
	private String regaddress;
	private Long regdate;
	private Integer corpscope;
	private String corpdescript;
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
	public String getBussinessscope() {
		return bussinessscope;
	}
	public void setBussinessscope(String bussinessscope) {
		this.bussinessscope = bussinessscope;
	}
	public String getRegaddress() {
		return regaddress;
	}
	public void setRegaddress(String regaddress) {
		this.regaddress = regaddress;
	}
	
	public Long getRegdate() {
		return regdate;
	}
	public void setRegdate(Long regdate) {
		this.regdate = regdate;
	}
	public Integer getCorpscope() {
		return corpscope;
	}
	public void setCorpscope(Integer corpscope) {
		this.corpscope = corpscope;
	}
	public String getCorpdescript() {
		return corpdescript;
	}
	public void setCorpdescript(String corpdescript) {
		this.corpdescript = corpdescript;
	}

}
