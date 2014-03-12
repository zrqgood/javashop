package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;


/**
 * 支付/退款日志
 * @author kingapex
 *2010-4-8上午09:09:43
 */
public class PaymentLog implements java.io.Serializable {

	private Integer payment_id;

	private Integer order_id;

	private Integer member_id;

	private String account;

	private String bank;

	private String pay_user;

	private Double money;

	private Double pay_cost;

	private String pay_type;

	private String pay_method;

	private String remark;

	private Integer op_id;

	private Integer type;

	private Integer status;
	private Long create_time;
	
	//支付、退款会员名
	private String member_name;
	
	private String sn;
	
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getOp_id() {
		return op_id;
	}

	public void setOp_id(Integer op_id) {
		this.op_id = op_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getPay_cost() {
		return pay_cost;
	}

	public void setPay_cost(Double pay_cost) {
		this.pay_cost = pay_cost;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
 
	public String getPay_user() {
		return pay_user;
	}

	public void setPay_user(String pay_user) {
		this.pay_user = pay_user;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	@NotDbField
	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String memberName) {
		member_name = memberName;
	}

	@NotDbField
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}


	
}