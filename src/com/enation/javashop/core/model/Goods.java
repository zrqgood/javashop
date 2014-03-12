package com.enation.javashop.core.model;

import java.util.Date;


/**
 * 商品实体
 * @author kingapex
 *2010-4-25下午09:40:24
 */
public class Goods  implements java.io.Serializable {

     private Integer goods_id;
     private String name;
     private String sn;
     private Integer brand_id;
     private Integer cat_id;
     private Integer type_id;
     private String goods_type;  //enum('normal', 'bind') default 'normal'
     private String unit;
     private Double weight;
     private Integer market_enable;
     private String image_default;
     private String image_file;
     private String brief;
     private String intro;
     private Double price;
     private Double mktprice;
     private Integer store;
     private String adjuncts;
     private String params;
     private String specs;
     private Long create_time;
     private Long last_modify;
     private Integer view_count;
     private Integer buy_count;
     private Integer disabled;
     private String page_title;
     private String meta_keywords;
     private String meta_description;
     private Integer point; //积分
     private Integer sord;
     
     
	public Integer getBrand_id() {
		if(brand_id == null ) brand_id =0;
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(Integer buy_count) {
		this.buy_count = buy_count;
	}

 
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getImage_default() {
		return image_default;
	}
	public void setImage_default(String image_default) {
		this.image_default = image_default;
	}
	public String getImage_file() {
		return image_file;
	}
	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getMarket_enable() {
		return market_enable;
	}
	public void setMarket_enable(Integer market_enable) {
		this.market_enable = market_enable;
	}
	public String getMeta_description() {
		return meta_description;
	}
	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}
	public String getMeta_keywords() {
		return meta_keywords;
	}
	public void setMeta_keywords(String meta_keywords) {
		this.meta_keywords = meta_keywords;
	}
	public Double getMktprice() {
		return mktprice;
	}
	public void setMktprice(Double mktprice) {
		this.mktprice = mktprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPage_title() {
		return page_title;
	}
	public void setPage_title(String page_title) {
		this.page_title = page_title;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goodsType) {
		goods_type = goodsType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getView_count() {
		return view_count;
	}
	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}
	public Double getWeight() {
		weight=weight==null?weight=0D:weight;
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getCat_id() {
		return cat_id;
	}
	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public Long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
	public Long getLast_modify() {
		return last_modify;
	}
	public void setLast_modify(Long last_modify) {
		this.last_modify = last_modify;
	}
	public String getSpecs() {
		return specs;
	}
	public void setSpecs(String specs) {
		this.specs = specs;
	}
	public String getAdjuncts() {
		return adjuncts;
	}
	public void setAdjuncts(String adjuncts) {
		this.adjuncts = adjuncts;
	}
	public Integer getPoint() {
		point  = point==null ?0:point;
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getSord() {
		return sord;
	}
	public void setSord(Integer sord) {
		this.sord = sord;
	}
	

}