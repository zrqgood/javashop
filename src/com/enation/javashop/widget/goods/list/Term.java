package com.enation.javashop.widget.goods.list;

/**
 * 商品列表挂件显示条件
 * @author kingapex
 *2010-5-12下午05:40:03
 */
public class Term {
	
	private String tagid;
	private String catid;
	private String brandid;
	private Integer typeid;
	private Double  minprice;
	private Double maxprice;
	private String keyword;
	private Integer order;
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public Double getMinprice() {
		return minprice;
	}
	public void setMinprice(Double minprice) {
		this.minprice = minprice;
	}
	public Double getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(Double maxprice) {
		this.maxprice = maxprice;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	 
	
}
