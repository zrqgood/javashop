package com.enation.javashop.core.model.support;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单价格信息实体
 * @author kingapex
 * 2010-5-30上午11:58:33
 */
public class OrderPrice {
	private Double originalPrice; //原始总价,未经过任何优惠的
	private Double goodsPrice; //商品价格，经过优惠过的
	private Double orderPrice;//订单总价，优惠过的，包含商品价格和配置费用
	private Double discountPrice; //优惠的价格
	private Double shippingPrice; //配送费用
	private  Double protectPrice; //保价费用
	private Double weight ; //商品重量
	private Integer point; //可获得积分
	private Map<String,Object> discountItem; //使用优惠的项目
	public OrderPrice(){
		discountItem = new HashMap<String, Object>();
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Double getDiscountPrice() {
		discountPrice= discountPrice==null?0:discountPrice;
		return discountPrice;
	}
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Double getProtectPrice() {
		return protectPrice;
	}
	public void setProtectPrice(Double protectPrice) {
		this.protectPrice = protectPrice;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(Double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	public Map<String, Object> getDiscountItem() {
		return discountItem;
	}
	public void setDiscountItem(Map<String, Object> discountItem) {
		this.discountItem = discountItem;
	}
	
	
}
