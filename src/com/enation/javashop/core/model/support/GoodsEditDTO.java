package com.enation.javashop.core.model.support;

import java.util.List;
import java.util.Map;

//商品编辑数据dto
public class GoodsEditDTO {
	
	
	private Map goods; //编辑的商品数据
	private List<String> htmlList;//编辑插件html代码列表
	public Map getGoods() {
		return goods;
	}
	public void setGoods(Map goods) {
		this.goods = goods;
	}
	public List<String> getHtmlList() {
		return htmlList;
	}
	public void setHtmlList(List<String> htmlList) {
		this.htmlList = htmlList;
	}
	
	
	
}
