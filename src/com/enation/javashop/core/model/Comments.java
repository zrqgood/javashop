package com.enation.javashop.core.model;

import java.util.Date;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.database.NotDbField;

/**
 * 评论/咨询实体
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-26 下午02:17:29<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class Comments {
	private Integer comment_id;
	private Integer for_comment_id;
	private Integer object_id; // meens goods_id or article_id
	private String object_type; 
	private Integer author_id;
	private String author;
	private String levelname;
	private String contact;
	private String mem_read_status;
	private String adm_read_status;
	private Long time;
	private Long lastreply;
	private String reply_name;
	private String title;
	private String acomment;
	private String ip;
	private String display;
	private Integer p_index;
	private String disabled;
	private String commenttype;
	private int grade; //评分
	private String img;

	@NotDbField
	public Date getDate(){
		return new Date(this.getTime());
	}
	
	public String getCommenttype() {
		return commenttype;
	}

	public void setCommenttype(String commenttype) {
		this.commenttype = commenttype;
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer commentId) {
		comment_id = commentId;
	}

	public Integer getFor_comment_id() {
		return for_comment_id;
	}

	public void setFor_comment_id(Integer forCommentId) {
		for_comment_id = forCommentId;
	}

	public Integer getObject_id() {
		return object_id;
	}

	public void setObject_id(Integer objectId) {
		object_id = objectId;
	}

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String objectType) {
		object_type = objectType;
	}

	public Integer getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Integer authorId) {
		author_id = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMem_read_status() {
		return mem_read_status;
	}

	public void setMem_read_status(String memReadStatus) {
		mem_read_status = memReadStatus;
	}

	public String getAdm_read_status() {
		return adm_read_status;
	}

	public void setAdm_read_status(String admReadStatus) {
		adm_read_status = admReadStatus;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getLastreply() {
		return lastreply;
	}

	public void setLastreply(Long lastreply) {
		this.lastreply = lastreply;
	}

	public String getReply_name() {
		return reply_name;
	}

	public void setReply_name(String replyName) {
		reply_name = replyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAcomment() {
		return acomment;
	}

	public void setAcomment(String acomment) {
		this.acomment = acomment;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Integer getP_index() {
		return p_index;
	}

	public void setP_index(Integer pIndex) {
		p_index = pIndex;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@NotDbField
	public String getImage(){
		img  =UploadUtil.replacePath(img);
		return img;
	}
	
}
