package com.enation.eop.resource.model;

/**
 * 首页项
 * @author kingapex
 * 2010-10-12下午03:56:00
 */
public class IndexItem {
	
	private Integer id;
	private String title;
	private String url;
	private int sort;
 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
