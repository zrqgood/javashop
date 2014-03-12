package com.enation.javashop.core.model;

public class LimitBuyGoods {
	private Integer limitbuyid;
	private Integer goodsid;
	private Double price;
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getLimitbuyid() {
		return limitbuyid;
	}
	public void setLimitbuyid(Integer limitbuyid) {
		this.limitbuyid = limitbuyid;
	}
	
}
