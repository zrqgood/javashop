package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;

/**
 * 赠品
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-15 上午10:23:56<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class FreeOffer {
	private Integer fo_id;
	private Integer fo_category_id;
	private String cat_name; //所属类别名，非数据库字段
	private String fo_name;
	private Integer publishable;
	private Integer recommend;
	private Integer sorder;
	private Integer limit_purchases;
	private Long startdate;
	private Long enddate;
	private String lv_ids;
	private Double price;
	private String synopsis;
	private String list_thumb;
	private String pic;
	private Integer score;
	private Double weight;
	private Integer repertory;
	private String descript;
	private Integer disabled;
	
	
	public Integer getFo_id() {
		return fo_id;
	}
	public void setFo_id(Integer foId) {
		fo_id = foId;
	}
	public Integer getFo_category_id() {
		return fo_category_id;
	}
	public void setFo_category_id(Integer foCategoryId) {
		fo_category_id = foCategoryId;
	}
	public String getFo_name() {
		return fo_name;
	}
	public void setFo_name(String foName) {
		fo_name = foName;
	}
	public Integer getPublishable() {
		return publishable;
	}
	public void setPublishable(Integer publishable) {
		this.publishable = publishable;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getSorder() {
		return sorder;
	}
	public void setSorder(Integer sorder) {
		this.sorder = sorder;
	}
	public Integer getLimit_purchases() {
		return limit_purchases;
	}
	public void setLimit_purchases(Integer limitPurchases) {
		limit_purchases = limitPurchases;
	}
	public Long getStartdate() {
		return startdate;
	}
	public void setStartdate(Long startdate) {
		this.startdate = startdate;
	}
	public Long getEnddate() {
		return enddate;
	}
	public void setEnddate(Long enddate) {
		this.enddate = enddate;
	}
	
	public String getLv_ids() {
		return lv_ids;
	}
	public void setLv_ids(String lvIds) {
		lv_ids = lvIds;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getList_thumb() {
		return list_thumb;
	}
	public void setList_thumb(String listThumb) {
		list_thumb = listThumb;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getScore() {
		if(score==null)
			score=0;
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getRepertory() {
		return repertory;
	}
	public void setRepertory(Integer repertory) {
		this.repertory = repertory;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
  
	public Integer getDisabled() {
		return disabled;
	}
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	@NotDbField
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String catname) {
		this.cat_name = catname;
	}
	
}