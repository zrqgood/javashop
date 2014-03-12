package com.enation.app.base.core.model;

import java.io.Serializable;

/**
 * 留言、短消息
 * 
 * @author lzf<br/>
 *         2010-3-18 上午11:18:35<br/>
 *         version 1.0<br/>
 */
public class Message implements Serializable {
	private int msg_id;
	private int for_id;
	private String msg_from;//发送者uname
	private int from_id;
	private int from_type;
	private int to_id;
	private String msg_to;//接收者uname
	private int to_type;
	private String unread; // enum('1','0') not null default '0'
	private String folder; // enum('inbox','outbox') not null default 'inbox'
	private String email;
	private String tel;
	private String subject;
	private String message;
	private int rel_order;
	private Long date_line;
	private String is_sec; // enum('true','false') not null default 'true'
	private String del_status; // enum('0','1','2') default '0', '0'=>正常, '1'=>收信人已删除，'2'=>发信人已删除
	private String disabled; // enum('true','false') not null default 'false'
	private String msg_ip;
	private String msg_type; // enum('default','payment') not null default
								// 'default'

	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msgId) {
		msg_id = msgId;
	}

	public int getFor_id() {
		return for_id;
	}

	public void setFor_id(int forId) {
		for_id = forId;
	}

	public String getMsg_from() {
		return msg_from;
	}

	public void setMsg_from(String msgFrom) {
		msg_from = msgFrom;
	}

	public int getFrom_id() {
		return from_id;
	}

	public void setFrom_id(int fromId) {
		from_id = fromId;
	}

	public int getFrom_type() {
		return from_type;
	}

	public void setFrom_type(int fromType) {
		from_type = fromType;
	}

	public int getTo_id() {
		return to_id;
	}

	public void setTo_id(int toId) {
		to_id = toId;
	}

	public String getMsg_to() {
		return msg_to;
	}

	public void setMsg_to(String msgTo) {
		msg_to = msgTo;
	}

	public int getTo_type() {
		return to_type;
	}

	public void setTo_type(int toType) {
		to_type = toType;
	}

	public String getUnread() {
		return unread;
	}

	public void setUnread(String unread) {
		this.unread = unread;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRel_order() {
		return rel_order;
	}

	public void setRel_order(int relOrder) {
		rel_order = relOrder;
	}

	public Long getDate_line() {
		return date_line;
	}

	public void setDate_line(Long dateLine) {
		date_line = dateLine;
	}

	public String getIs_sec() {
		return is_sec;
	}

	public void setIs_sec(String isSec) {
		is_sec = isSec;
	}

	public String getDel_status() {
		return del_status;
	}

	public void setDel_status(String delStatus) {
		del_status = delStatus;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMsg_ip() {
		return msg_ip;
	}

	public void setMsg_ip(String msgIp) {
		msg_ip = msgIp;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msgType) {
		msg_type = msgType;
	}

}
