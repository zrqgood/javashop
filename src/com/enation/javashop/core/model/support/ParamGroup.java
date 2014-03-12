package com.enation.javashop.core.model.support;

import java.util.List;

import com.enation.javashop.core.model.GoodsParam;



/**
 * 参数组
 * @author apexking
 *
 */
public class ParamGroup {
	
	
	private String name; //参数组名称
	private List<GoodsParam> paramList;  //包含的参数列表
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GoodsParam> getParamList() {
		return paramList;
	}
	public void setParamList(List<GoodsParam> paramList) {
		this.paramList = paramList;
	}
	
	
	
	/**
	 * 参数的个数
	 * @return
	 */
	public int getParamNum(){
		if(paramList==null) return 0;
		return paramList.size();
	}
	
	
	
	
}
