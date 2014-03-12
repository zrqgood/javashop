package com.enation.javashop.core.model;

/**
 * 商品配件实体
 * 
 * @author lzf<br/>
 *         2010-4-1 上午09:43:29<br/>
 *         version 1.0<br/>
 */
public class GoodsAdjunct implements java.io.Serializable {
	private int adjunct_id;
	private int goods_id;
	private String adjunct_name;
	private int min_num;
	private int max_num;
	private String set_price; //enum('discount','minus') DEFAULT NULL
	private Double price;
	private String items;
	public int getAdjunct_id() {
		return adjunct_id;
	}
	public void setAdjunct_id(int adjunctId) {
		adjunct_id = adjunctId;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goodsId) {
		goods_id = goodsId;
	}
	public String getAdjunct_name() {
		return adjunct_name;
	}
	public void setAdjunct_name(String adjunctName) {
		adjunct_name = adjunctName;
	}
	public int getMin_num() {
		return min_num;
	}
	public void setMin_num(int minNum) {
		min_num = minNum;
	}
	public int getMax_num() {
		return max_num;
	}
	public void setMax_num(int maxNum) {
		max_num = maxNum;
	}
	public String getSet_price() {
		return set_price;
	}
	public void setSet_price(String setPrice) {
		set_price = setPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	
}
