package com.enation.app.base.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;
import com.enation.framework.model.Image;

/**
 * 数据日志实体
 * 
 * @author kingapex 2010-10-19下午03:29:02
 */
public class DataLog {
	
	private Integer id;
	private String content;
	private String url;
	private String pics;
	private String sitename;
	private String domain;
	private String logtype;
	private String optype;
	private long dateline;
	private int userid;
	private int siteid;
	private List<Image> picList; // 图片实体列表，由pics字串转换而来
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public String getOptype() {
		return optype;
	}

	public void setOptype(String optype) {
		this.optype = optype;
	}

	public long getDateline() {
		return dateline;
	}

	public void setDateline(long dateline) {
		this.dateline = dateline;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getSiteid() {
		return siteid;
	}

	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@NotDbField
	public List<Image> getPicList() {
		return picList;
	}

	public void setPicList(List<Image> picList) {
		this.picList = picList;
	}
	
}
