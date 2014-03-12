package com.enation.javashop.core.model.support;

public class ParamDTO {
	private String[] paramnums; // 参数组中拥有参数的个数
	private String[] groupnames;// 参数组的名称
	private String[] paramnames;// 参数名称
	private String[] paramvalues;// 参数值
	public String[] getGroupnames() {
		return groupnames;
	}
	public void setGroupnames(String[] groupnames) {
		this.groupnames = groupnames;
	}
	public String[] getParamnames() {
		return paramnames;
	}
	public void setParamnames(String[] paramnames) {
		this.paramnames = paramnames;
	}
	public String[] getParamnums() {
		return paramnums;
	}
	public void setParamnums(String[] paramnums) {
		this.paramnums = paramnums;
	}
	public String[] getParamvalues() {
		return paramvalues;
	}
	public void setParamvalues(String[] paramvalues) {
		this.paramvalues = paramvalues;
	}
	
	
}
