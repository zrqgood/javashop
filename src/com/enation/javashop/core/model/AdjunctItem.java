package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;
import com.enation.javashop.core.model.support.AdjunctGroup;

/**
 * 配件列表实体
 * 
 * @author lzf<br/>
 *         2010-3-31 上午09:49:07<br/>
 *         version 1.0<br/>
 */
public class AdjunctItem  {
	
	private int productid;
	private int goodsid;
	private String name;
	private String specs;
	private Double price;
	private Double coupPrice; //优惠后的价格
 
	private int num;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getCoupPrice() {
		return coupPrice;
	}
	public void setCoupPrice(Double coupPrice) {
		this.coupPrice = coupPrice;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
 
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	 
}
