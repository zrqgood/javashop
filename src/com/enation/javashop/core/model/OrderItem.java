package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;

/**
 * 
 * @author kingapex
 *2010-4-6下午04:11:42
 */
public class OrderItem  implements java.io.Serializable {

     private Integer item_id;
     private Integer order_id;
     private Integer goods_id;
     private Integer spec_id;
     private Integer num;
     private Integer ship_num;
     private String name;
     private String sn;
     private Integer store; //对应货品的库存
     private String addon;

     private Double price;
     private int gainedpoint;
     
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getShip_num() {
		return ship_num;
	}
	public void setShip_num(Integer ship_num) {
		this.ship_num = ship_num;
	}

	public Integer getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(Integer spec_id) {
		this.spec_id = spec_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getGainedpoint() {
		return gainedpoint;
	}
	public void setGainedpoint(int gainedpoint) {
		this.gainedpoint = gainedpoint;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	@NotDbField
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public String getAddon() {
		return addon;
	}
	public void setAddon(String addon) {
		this.addon = addon;
	}
	
	
}