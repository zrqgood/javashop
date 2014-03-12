package com.enation.javashop.core.model;

import java.util.List;

public class ImportDataSource {
	private List<Brand> brandList;
	private List<Attribute> propList;
	private String datafolder; //注意要包含商品号
	private int goodsNum; //商品的id号
	private boolean isNewGoods; //是否是新商品
	public List<Brand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}
	public List<Attribute> getPropList() {
		return propList;
	}
	public void setPropList(List<Attribute> propList) {
		this.propList = propList;
	}
	public String getDatafolder() {
		return datafolder;
	}
	public void setDatafolder(String datafolder) {
		this.datafolder = datafolder;
	}
	public int getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	public boolean isNewGoods() {
		return isNewGoods;
	}
	public void setNewGoods(boolean isNewGoods) {
		this.isNewGoods = isNewGoods;
	}
	
	 
	
	
}
