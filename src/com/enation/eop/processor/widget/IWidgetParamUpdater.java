package com.enation.eop.processor.widget;

import java.util.List;
import java.util.Map;

/**
 * 挂件参数更新
 * @author kingapex
 * 2010-2-9下午07:27:36
 */
public interface IWidgetParamUpdater {
	
	public void update(String pageId,List<Map<String,String>> params);
	
}
