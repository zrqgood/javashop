package com.enation.cms.core.model;

import com.enation.framework.database.NotDbField;


/**
 * 模型字段实体
 * @author kingapex
 * 2010-7-2下午03:39:00
 */
public class DataField {

    private Integer field_id;
    private String china_name;
    private String english_name;
 
    private String data_size;
    private String show_form;
    private String show_value;
    private Long add_time;
    private Integer model_id;
    private String save_value;
    private int is_validate; //0否 1是
    private Integer taxis;
    private Integer dict_id;
    private Integer is_show;
 
    // 输入项html，非数据库字段
    private String inputHtml;

    @NotDbField
	public String getInputHtml() {
		return inputHtml;
	}
	public void setInputHtml(String inputHtml) {
		this.inputHtml = inputHtml;
	}
	public Long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Long add_time) {
		this.add_time = add_time;
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
	public Integer getField_id() {
		return field_id;
	}
	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}
	public Integer getModel_id() {
		return model_id;
	}
	public void setModel_id(Integer model_id) {
		this.model_id = model_id;
	}
	
	public String getShow_value() {
		return show_value;
	}
	public void setShow_value(String show_value) {
		this.show_value = show_value;
	}
	public String getShow_form() {
		return show_form;
	}
	public void setShow_form(String show_form) {
		this.show_form = show_form;
	}
	public String getData_size() {
		return data_size;
	}
	public void setData_size(String data_size) {
		this.data_size = data_size;
	}
	
	public int getIs_validate() {
		return is_validate;
	}
	public void setIs_validate(int isValidate) {
		is_validate = isValidate;
	}
	public String getSave_value() {
		return save_value;
	}
	public void setSave_value(String save_value) {
		this.save_value = save_value;
	}
	public Integer getTaxis() {
		return taxis;
	}
	public void setTaxis(Integer taxis) {
		this.taxis = taxis;
	}
	public Integer getDict_id() {
		return dict_id;
	}
	public void setDict_id(Integer dict_id) {
		this.dict_id = dict_id;
	}
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer isShow) {
		is_show = isShow;
	}
 
	
}
