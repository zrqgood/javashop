package com.enation.javashop.core.model;

/**
 * 缺货登记表
 * 
 * @author lzf<br/>
 *         2010-3-19 下午02:29:50<br/>
 *         version 1.0<br/>
 */
public class Gnotify implements java.io.Serializable {
	private int gnotify_id;
	private int goods_id;
	private int member_id;
	private int product_id;
	private String email;
	private String status; // enum('ready','send','progress') not null default
							// 'ready'
	private Long send_time;
	private Long create_time;
	private String disabled;// enum('false','true') not null default 'false'
	private String remark;

	public int getGnotify_id() {
		return gnotify_id;
	}

	public void setGnotify_id(int gnotifyId) {
		gnotify_id = gnotifyId;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goodsId) {
		goods_id = goodsId;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int memberId) {
		member_id = memberId;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int productId) {
		product_id = productId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSend_time() {
		return send_time;
	}

	public void setSend_time(Long sendTime) {
		send_time = sendTime;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long createTime) {
		create_time = createTime;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
