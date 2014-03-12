package com.enation.eop.processor.facade.support;

import java.util.Map;

import com.enation.eop.processor.IPageParamJsonGetter;
import com.enation.eop.processor.widget.IWidgetParamParser;
import com.enation.eop.processor.widget.WidgetXmlUtil;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * 页面挂件json格式参数获取器
 * @author kingapex
 * 2010-2-10下午04:59:57
 */
public class PageParamJsonGetter implements IPageParamJsonGetter {
	private IWidgetParamParser widgetParamParser;
	 
	private IThemeManager themeManager ;
	
	
	
	public String getJson(String uri) {
		//去掉uri问号以后的东西
		if(uri.indexOf('?')>0)
			uri = uri.substring(0, uri.indexOf('?') );
		
		//站点使用模板
		EopSite site = EopContext.getContext().getCurrentSite();
	 
		
		//rewrite url = pageId
		IThemeUriManager themeUriManager =  SpringContextHolder.getBean("themeUriManager");
		ThemeUri themeUri  = themeUriManager.getPath(  uri);
		uri = themeUri.getPath();
		
		//此站点挂件参数集合
		Map<String, Map<String, Map<String, String>>> pages = this.widgetParamParser
				.parse();
		
		//此页面的挂件参数集合
		Map<String, Map<String,String>> params=pages.get(uri);
		String json = WidgetXmlUtil.mapToJson(params);
		json="{'pageId':'"+uri+"',params:"+json+"}";
		return json;
	}
	
	
	public void setWidgetParamParser(IWidgetParamParser widgetParamParser) {
		this.widgetParamParser = widgetParamParser;
	}
 

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

}
