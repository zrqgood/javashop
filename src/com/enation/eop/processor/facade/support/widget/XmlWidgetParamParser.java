package com.enation.eop.processor.facade.support.widget;

import java.util.Map;

import com.enation.eop.processor.widget.IWidgetParamParser;
import com.enation.eop.processor.widget.WidgetXmlUtil;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;

/**
 *  SAAS式的xml挂件参数解析器
 * @author kingapex
 * 2010-2-4下午04:05:18
 */
public class XmlWidgetParamParser implements IWidgetParamParser {

	 
	private IThemeManager themeManager;
	
	public Map<String, Map<String, Map<String,String>>> parse() {
		EopSite  site  =EopContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(site.getThemeid());
		String contextPath  = EopContext.getContext().getContextPath();
		String path =
		EopSetting.EOP_PATH	
		+contextPath
		+ EopSetting.THEMES_STORAGE_PATH+
		"/" + theme.getPath()  + "/widgets.xml"; 
		
		return WidgetXmlUtil.parse(path);
	}
	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}
	
	
}
