package com.enation.cms.core.plugin;

import java.util.Map;

/**
 * 数据保存事件
 * @author kingapex
 * 2010-10-19下午04:27:49
 */
public interface IDataSaveEvent {
	
	
	/**
	 * 数据保存接口定义
	 * @param data
	 */
	public void onSave(Map data);
}
