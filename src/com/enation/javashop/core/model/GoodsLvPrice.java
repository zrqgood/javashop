package com.enation.javashop.core.model;


/**
 * 商品会员价
 * @author kingapex
 *
 */
public class GoodsLvPrice {
	private Double price;
	private int lvid;
	private int productid;
	private int goodsid;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getLvid() {
		return lvid;
	}
	public void setLvid(int lvid) {
		this.lvid = lvid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	
}
