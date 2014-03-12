package com.enation.javashop.core.action.backend;

import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IOrderManager;

/**
 * 后台首页显示项
 * @author kingapex
 * 2010-10-12上午10:18:15
 */
public class ShopIndexItemAction extends WWAction {
	
	private IOrderManager orderManager;
	private IGoodsManager goodsManager;
	private Map orderss; //订单统计信息
	private Map goodsss ;//商品统计信息
	
	//订单统计信息
	public String order(){
		this.orderss  =this.orderManager.censusState();
		return "order";
	}
	
	
	//商品统计信息
	public String goods(){
		this.goodsss = this.goodsManager.census();
		return "goods";
	}


	public IOrderManager getOrderManager() {
		return orderManager;
	}


	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}


	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}


	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}


	public Map getOrderss() {
		return orderss;
	}


	public void setOrderss(Map orderss) {
		this.orderss = orderss;
	}


	public Map getGoodsss() {
		return goodsss;
	}


	public void setGoodsss(Map goodsss) {
		this.goodsss = goodsss;
	}

 



	
}
