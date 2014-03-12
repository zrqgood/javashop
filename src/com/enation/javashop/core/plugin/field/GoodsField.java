package com.enation.javashop.core.plugin.field;

import com.enation.framework.database.NotDbField;

/**
 * 商品字段实体
 * @author kingapex
 *
 */
public class GoodsField {
	
	/**
	 * ===================
	 * 数据源类型
	 * ===================
	 */
	 public static final String DATA_SOURCE_MANUAL="manual";//手动输入 
	 public static final String DATA_SOURCE_DICT="dict"; //来自于字典
	 
	 
	 private Integer field_id;      
	 private String china_name;
	 private String english_name;  
	 private String pluginid;      
	 private String config;        
	 private long add_time;      
	 private Integer type_id;        
	 private int  is_validate;   
	 private int  field_sort;    
	 private int is_show;
	// private String data_source;//数据来源
	// private Integer source_id; //数据来源id,如果为手工输入,则为null
	 // 输入项html，非数据库字段
	 private String inputHtml;
	    
	public Integer getField_id() {
		return field_id;
	}
	public void setField_id(Integer fieldId) {
		field_id = fieldId;
	}
	public String getChina_name() {
		return china_name;
	}
	public void setChina_name(String chinaName) {
		china_name = chinaName;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String englishName) {
		english_name = englishName;
	}
	public String getPluginid() {
		return pluginid;
	}
	public void setPluginid(String pluginid) {
		this.pluginid = pluginid;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(long addTime) {
		add_time = addTime;
	}


	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer typeId) {
		type_id = typeId;
	}
	public int getIs_validate() {
		return is_validate;
	}
	public void setIs_validate(int isValidate) {
		is_validate = isValidate;
	}
 
	public int getIs_show() {
		return is_show;
	}
	public void setIs_show(int isShow) {
		is_show = isShow;
	}
	public int getField_sort() {
		return field_sort;
	}
	public void setField_sort(int fieldSort) {
		field_sort = fieldSort;
	}
	
	@NotDbField
	public String getInputHtml() {
		return inputHtml;
	}
	public void setInputHtml(String inputHtml) {
		this.inputHtml = inputHtml;
	}
/*	public String getData_source() {
		return data_source;
	}
	public void setData_source(String dataSource) {
		data_source = dataSource;
	}
	public Integer getSource_id() {
		return source_id;
	}
	public void setSource_id(Integer sourceId) {
		source_id = sourceId;
	}*/
	 
	 

}
