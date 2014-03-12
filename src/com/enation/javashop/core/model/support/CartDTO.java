package com.enation.javashop.core.model.support;

import java.util.List;

public class CartDTO {
	private List itemList;
	private List ruleList;
	
	private Double orderPrice;
	public List getItemList() {
		return itemList;
	}
	public void setItemList(List itemList) {
		this.itemList = itemList;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public List getRuleList() {
		return ruleList;
	}
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
	
	
}
