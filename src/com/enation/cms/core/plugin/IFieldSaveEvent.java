package com.enation.cms.core.plugin;

import java.util.Map;

import com.enation.cms.core.model.DataField;

/**
 * 字段数据保存事件
 * 在CMS业务中，当数据进行保存时激发此事件，会被调用多次，要判断field的类型
 * @author kingapex
 * 2010-7-5下午02:20:36
 */
public interface IFieldSaveEvent {
	
	/**
	 * 字段数据保存事件接口定义
	 * @param article 
	 * @param field
	 */
	public void onSave(Map data,DataField field );
}
