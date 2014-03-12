package com.enation.app.base.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.enation.framework.database.NotDbField;
import com.enation.framework.util.DateUtil;

/**
 * 留言板实体对象
 * @author kingapex
 * 2010-8-14下午09:39:32
 */
public class GuestBook {
	private int id;
	private int parentid;
	private String title;
	private String content;
	private int issubject;
	private long dateline;
	private String username;
	private String email;
	private String  qq;
	private String tel;
	private int  sex;	
	private String ip;
	private String area;
	
	//非数据库字段：添加时间
	private String addtime;
	//非数据库字段：回复列表
	private List replyList;
	
	public GuestBook(){
		replyList  = new ArrayList();
	}
	
	//为此留言的回复列表添加一个回复
	public void addReply(GuestBook reply){
		this.replyList.add(reply);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
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
	public int getIssubject() {
		return issubject;
	}
	public void setIssubject(int issubject) {
		this.issubject = issubject;
	}
	public long getDateline() {
		return dateline;
	}
	public void setDateline(long dateline) {
		this.dateline = dateline;
	}
	@NotDbField
	public String getAddtime() {
		addtime  = DateUtil.toString(new Date(  ((long)this.dateline)*1000 ), "MM-dd hh:mm");
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	public static void main(String[] args){
		long sys = System.currentTimeMillis();
		int now  =(int)(sys /1000);
	}
	@NotDbField
	public List getReplyList() {
		return replyList;
	}
	public void setReplyList(List replyList) {
		this.replyList = replyList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
