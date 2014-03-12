package com.enation.cms.core.plugin;

import com.enation.cms.core.model.DataField;

/**
 * 数据字段控件html显示事件，即输出控件的html
 * @author kingapex
 * 2010-7-5下午02:24:35
 */
public interface IFieldDispalyEvent {
	
	
	/**
	 * 字段控件展示事件响应<br>
	 * 此方法的实现应该返回控件的显示html，如:<input type="text" .... />
	 * @param field
	 * @param value
	 * @return
	 */
	public String onDisplay(DataField field,Object value);
	
	
}
