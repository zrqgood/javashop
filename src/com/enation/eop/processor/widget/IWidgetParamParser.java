package com.enation.eop.processor.widget;

import java.util.Map;

/**
 * 挂件参数解析器
 * @author kingapex
 * 2010-2-4下午04:03:26
 */
public interface IWidgetParamParser {
	
	/**
	 * 
	 * @return 解析后的参数，一个Map嵌套，格式如下:<br>
	 *  String[pageId]->Map
     *                  String[widgetId]->Map
     *                                     String[paramName]->String[paramValue] 
	 */
	public  Map<String, Map<String, Map<String,String>>> parse();
	
	
}
