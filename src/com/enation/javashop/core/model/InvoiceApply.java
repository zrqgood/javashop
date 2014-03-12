package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;

public class InvoiceApply {
	
   private Integer id;
   private String title;
   private String content;
   private Integer  orderid  ;
   private long add_time;
   private Double amount ; //发票金额，非数据库字段
   private String ordersn; //订单号非数据库字段
   private int state;
   private String stateStr; //状态字串，非数据库字段
   private String refuse_reson;
   
   
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(long addTime) {
		add_time = addTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@NotDbField
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@NotDbField
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	@NotDbField
	public String getStateStr() {
		if(state==0){
			stateStr = "等待审核";
		}
		
		if(state==1){
			stateStr = "审核通过";
		}
		if(state==2){
			stateStr = "申请被拒绝";
		}
		
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	public String getRefuse_reson() {
		return refuse_reson;
	}
	public void setRefuse_reson(String refuseReson) {
		refuse_reson = refuseReson;
	}
	   
	   
}
