package com.enation.javashop.core.model;

/**
 * 预存款日志
 * 
 * @author lzf<br/>
 *         2010-4-14上午09:41:36<br/>
 *         version 1.0
 */
public class AdvanceLogs implements java.io.Serializable {
	private int log_id;
	private int member_id;
	private Double money;
	private String message;
	private Long mtime;
	private int payment_id;
	private int order_id;
	private String paymethod;
	private String memo;
	private Double import_money;
	private Double explode_money;
	private Double member_advance;
	private Double shop_advance;
	private String disabled;//enum('true','false') NOT NULL DEFAULT 'false'
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int logId) {
		log_id = logId;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int memberId) {
		member_id = memberId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getMtime() {
		return mtime;
	}
	public void setMtime(Long mtime) {
		this.mtime = mtime;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int paymentId) {
		payment_id = paymentId;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int orderId) {
		order_id = orderId;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Double getImport_money() {
		return import_money;
	}
	public void setImport_money(Double importMoney) {
		import_money = importMoney;
	}
	public Double getExplode_money() {
		return explode_money;
	}
	public void setExplode_money(Double explodeMoney) {
		explode_money = explodeMoney;
	}
	public Double getMember_advance() {
		return member_advance;
	}
	public void setMember_advance(Double memberAdvance) {
		member_advance = memberAdvance;
	}
	public Double getShop_advance() {
		return shop_advance;
	}
	public void setShop_advance(Double shopAdvance) {
		shop_advance = shopAdvance;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
}

