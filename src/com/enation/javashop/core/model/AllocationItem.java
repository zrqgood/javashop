package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;

/**
 * 配货项
 * @author kingapex
 *
 */
public class AllocationItem {
   private Integer allocationid;
   private int itemid; //所属的购物项id
   private int cat_id; //所属分类id 非数据库字段
   private int  orderid;             
   private int  depotid;             
   private int goodsid;             
   private int productid;           
   private int num;
   private String other;
   private int iscmpl;//0为未完成1为完成
   
   
	public int getIscmpl() {
	return iscmpl;
}
public void setIscmpl(int iscmpl) {
	this.iscmpl = iscmpl;
}
	public Integer getAllocationid() {
		return allocationid;
	}
	public void setAllocationid(Integer allocationid) {
		this.allocationid = allocationid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getDepotid() {
		return depotid;
	}
	public void setDepotid(int depotid) {
		this.depotid = depotid;
	}
	public int getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@NotDbField
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
 
	   
}
