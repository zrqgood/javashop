package com.enation.javashop.core.model;

import java.util.ArrayList;
import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 货品实体
 * @author kingapex
 *2010-3-9下午05:57:16
 */
public class Product {

	private Integer product_id;
	private Integer goods_id;
	private String name;
	private String sn;
	private Integer store;
	private Double price;
	private Double cost;
	private Double weight;
	private String specs;
	private List<GoodsLvPrice> goodsLvPrices; //商品会员价格
	
	//货品对应规格信息，在添加货品时用到了
	private List<SpecValue> specList;
	
	public Product(){
		specList= new ArrayList();
	}
	
	@NotDbField
	public List<SpecValue> getSpecList() {
		return specList;
	}
	public void setSpecList(List<SpecValue> specList) {
		this.specList = specList;
	}
	
	//添加一种规格
	public void addSpec(SpecValue spec){
		this.specList.add(spec);
	}
	
	/**
	 * 返回根据 specList得到的 specvid json
	 * @return
	 */
	@NotDbField
	public String getSpecsvIdJson(){
		String json="[";
		int i=0;
		for(SpecValue value :specList){
			if(i!=0)json+=",";
			json+=value.getSpec_value_id();
			i++;
		}
		json+="]";
		return json;
	}
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer productId) {
		product_id = productId;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goodsId) {
		goods_id = goodsId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotDbField
	public List<GoodsLvPrice> getGoodsLvPrices() {
		return goodsLvPrices;
	}

	public void setGoodsLvPrices(List<GoodsLvPrice> goodsLvPrices) {
		this.goodsLvPrices = goodsLvPrices;
	}
	

	
	
}
