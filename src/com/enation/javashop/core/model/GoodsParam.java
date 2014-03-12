package com.enation.javashop.core.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 商品参数
 * @author apexking
 *
 */
public class GoodsParam {
	
	private String name; //参数名
	private String value; //参数值
	
	private List valueList; //多个商品的参数值，用于商品对比 
	
	
	public void addValue(String _value){
		if(valueList == null)  valueList  = new ArrayList();
		valueList.add(_value);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List getValueList() {
		return valueList;
	}
	public void setValueList(List valueList) {
		this.valueList = valueList;
	}
	
	
	
}
