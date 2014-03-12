package com.enation.cms.core.model;

/**
 * 模型实体
 * @author kingapex
 * 2010-7-2下午02:12:09
 */
public class DataModel {


     private Integer model_id;
     private String china_name;
     private String english_name;
     private Long add_time;
     private String project_name;
     private String brief;
     private Integer if_audit;
     private String is_unlock;
     

	public Long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Long add_time) {
		this.add_time = add_time;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getChina_name() {
		return china_name;
	}
	public void setChina_name(String china_name) {
		this.china_name = china_name;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
	public Integer getModel_id() {
		return model_id;
	}
	public void setModel_id(Integer model_id) {
		this.model_id = model_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public Integer getIf_audit() {
		return if_audit;
	}
	public void setIf_audit(Integer if_audit) {
		this.if_audit = if_audit;
	}
	public String getIs_unlock() {
		return is_unlock;
	}
	public void setIs_unlock(String is_unlock) {
		this.is_unlock = is_unlock;
	}

}
