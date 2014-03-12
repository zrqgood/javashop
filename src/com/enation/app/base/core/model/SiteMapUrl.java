package com.enation.app.base.core.model;

/**
 * SiteMap中的URL实体
 * @author lzf<br/>
 * 2010-11-30 下午04:41:52<br/>
 * version 2.1.5
 */
public class SiteMapUrl implements java.io.Serializable{
	private String loc;
	private Long lastmod;
	private String changefreq;//："always", "hourly", "daily", "weekly", "monthly", "yearly"
	private String priority;
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public Long getLastmod() {
		return lastmod;
	}
	public void setLastmod(Long lastmod) {
		this.lastmod = lastmod;
	}
	public String getChangefreq() {
		return changefreq;
	}
	public void setChangefreq(String changefreq) {
		this.changefreq = changefreq;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
}
