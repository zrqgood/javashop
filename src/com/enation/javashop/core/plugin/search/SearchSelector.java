package com.enation.javashop.core.plugin.search;

/**
 * 选器实体
 * @author kingapex
 *
 */
public class SearchSelector {
	
	private String name;
	private String url;
	private boolean isSelected;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean getIsSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
