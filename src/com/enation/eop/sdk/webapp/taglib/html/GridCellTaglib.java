package com.enation.eop.sdk.webapp.taglib.html;

import com.enation.eop.sdk.webapp.taglib.HtmlTaglib;
import com.enation.eop.sdk.webapp.taglib.html.support.GridCellProvider;

public class GridCellTaglib extends HtmlTaglib {
	private String sort;

	private String sortDefault;

	private String order;

	private int isSort;

	private int isAjax;

	private String style;

	private String width;

	private String height;

	private String align;

	private String plugin_type;
	
	private String clazz;  //为grid:cell 加上class by lzf

	private GridCellProvider cellProvider;

	public GridCellTaglib(){
		cellProvider = new GridCellProvider();
	}
	/**
	 * 这个html标签标记的开始段
	 */
	
	protected String postStart() {
		
		cellProvider.initCellProvider(this);
		
		return cellProvider.getStartHtml(); 
	} 

	/**
	 * 这个标记的结束串
	 */
	
	protected String postEnd() {

		return this.cellProvider.getEndHtml();
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortDefault() {
		return sortDefault;
	}

	public void setSortDefault(String sortDefault) {
		this.sortDefault = sortDefault;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getPlugin_type() {
		return plugin_type;
	}

	public void setPlugin_type(String plugin_type) {
		this.plugin_type = plugin_type;
	}

	public int getIsAjax() {
		return isAjax;
	}

	public void setIsAjax(int isAjax) {
		this.isAjax = isAjax;
	}

	public int getIsSort() {
		return isSort;
	}

	public void setIsSort(int isSort) {
		this.isSort = isSort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
}