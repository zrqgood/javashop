package com.enation.app.base.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 问答实体
 * @author kingapex
 * 2010-8-6上午07:50:31
 */
public class Ask {
	private int askid;
	private String title;
	private String content;
	private long dateline;
	private int isreply;
	private Integer userid;
	private Integer siteid;
	private String domain;
	private String sitename;
	private String username; //提问人姓名
	
	//回复列表非数据库字段
	private List replyList;
	
	public int getAskid() {
		return askid;
	}
	public void setAskid(int askid) {
		this.askid = askid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getDatelineLong() {
		return dateline;
	}
	public void setDateline(long dateline) {
		this.dateline = dateline;
	}
	public int getIsreply() {
		return isreply;
	}
	public void setIsreply(int isreply) {
		this.isreply = isreply;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getSiteid() {
		return siteid;
	}
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotDbField
	public List getReplyList() {
		return replyList;
	}
	public void setReplyList(List replyList) {
		this.replyList = replyList;
	}
	
}
