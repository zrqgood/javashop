package com.enation.eop.processor.facade.support.widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.enation.eop.processor.widget.IWidgetParamParser;
import com.enation.eop.processor.widget.IWidgetParamUpdater;
import com.enation.eop.processor.widget.WidgetXmlUtil;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;

/**
 * saas式挂件参数更新
 * @author kingapex
 * 2010-2-10上午11:26:21
 */
public class XmlWidgetParamUpdater implements IWidgetParamUpdater {
	private IThemeManager themeManager;
	
	
	public void update(String pageId,List<Map<String,String>> params) {
		EopSite  site  =EopContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(site.getThemeid());
		String contextPath  = EopContext.getContext().getContextPath();
		String path =
		EopSetting.EOP_PATH	
		+contextPath
		+ EopSetting.THEMES_STORAGE_PATH+
		"/" + theme.getPath()  + "/widgets.xml"; 
			
		WidgetXmlUtil.save(path, pageId, params);		
	}
	
	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}
	

}
