package com.enation.eop.processor.widget;

import java.util.Map;

/**
 * 挂件处理器接口
 * @author kingapex
 * 2010-2-8下午03:50:22
 */
public interface IWidgetHtmlGetter {
	
	/**
	 * 根据挂件参数 出挂件内容
	 * 要进行不同的paser组合
	 * @param params
	 * @return
	 */
	public String process(Map<String,String> params,String page);
	
}
