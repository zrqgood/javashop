package com.enation.javashop.widget.goods.list;

import java.util.List;

public class Tab {
	private String id;
	private String title;
	private Term term;
	private List goodsList;
	private boolean selected;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public List getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List goodsList) {
		this.goodsList = goodsList;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
