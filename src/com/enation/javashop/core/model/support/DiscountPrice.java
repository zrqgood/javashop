package com.enation.javashop.core.model.support;

/**
 * 价格优惠实体
 * @author kingapex
 * 2010-5-30下午06:31:38
 */
public class DiscountPrice {
	private Double orderPrice,shipFee;
	private Integer point;
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Double getShipFee() {
		return shipFee;
	}
	public void setShipFee(Double shipFee) {
		this.shipFee = shipFee;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	
}
