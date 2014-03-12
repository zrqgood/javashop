package com.enation.javashop.core.model.support;

import java.util.List;

import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.GoodsType;

public class GoodsTypeDTO extends GoodsType {
	
	private List<Attribute> propList;
	private ParamGroup[] paramGroups;
	private List brandList;
	
	public ParamGroup[] getParamGroups() {
		return paramGroups;
	}
	public void setParamGroups(ParamGroup[] paramGroups) {
		this.paramGroups = paramGroups;
	}
	public List<Attribute> getPropList() {
		return propList;
	}
	public void setPropList(List<Attribute> propList) {
		this.propList = propList;
	}
	public List getBrandList() {
		return brandList;
	}
	public void setBrandList(List brandList) {
		this.brandList = brandList;
	}
	
	
 
	
	
	
	
}
