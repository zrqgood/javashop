package com.enation.javashop.core.model;

import java.util.List;


/**
 * 配货单
 * @author kingapex
 *
 */
public class Allocation {
	private int orderid;
	private int shipDepotId; //发货仓库id
	private List<AllocationItem> itemList;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getShipDepotId() {
		return shipDepotId;
	}
	public void setShipDepotId(int shipDepotId) {
		this.shipDepotId = shipDepotId;
	}
	public List<AllocationItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<AllocationItem> itemList) {
		this.itemList = itemList;
	}
	
	
}
