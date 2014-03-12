package com.enation.eop.resource;

import java.util.List;

import com.enation.eop.resource.model.WidgetBundle;

/**
 * 挂件管理
 * @author lzf
 *         <p>
 *         2009-12-16 上午11:33:25
 *         </p>
 * @version 1.0
 */
public interface IWidgetBundleManager {

	
	public void add(WidgetBundle widgetBundle);
	public List<WidgetBundle> getWidgetbundleList();
	public WidgetBundle getWidgetBundle(String widgetType);
}
