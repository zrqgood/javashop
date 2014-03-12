package com.enation.eop.processor.widget;

import java.util.Map;

import com.enation.eop.sdk.widget.IWidget;

/**
 * 挂件解析器接口
 * @author kingapex
 * 2010-2-8下午03:50:22
 */
public interface IWidgetPaser {
	
	/**
	 * 根据挂件参数 出挂件内容
	 * @param params
	 * @return
	 */
	public String pase(Map<String,String> params);
	
	

	
}
