package com.enation.eop.processor.facade.support;

import java.util.List;
import java.util.Map;

import com.enation.eop.processor.IPageUpdater;
import com.enation.eop.processor.widget.IWidgetParamUpdater;
import com.enation.eop.processor.widget.WidgetXmlUtil;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.util.FileUtil;

/**
 * 前台页面更新器
 * @author kingapex
 * 2010-2-10下午03:54:41
 */
public class FacadePageUpdater implements IPageUpdater {
	private IThemeManager themeManager ;
	private IWidgetParamUpdater widgetParamUpdater;
	
	
	public void update(String uri,String pageContent,String paramJson) {
		//去掉uri问号以后的东西
		if(uri.indexOf('?')>0)
			uri = uri.substring(0, uri.indexOf('?') );

		//站点使用模板
		EopSite site = EopContext.getContext().getCurrentSite();

		//rewrite url，即pageId
		IThemeUriManager themeUriManager =  SpringContextHolder.getBean("themeUriManager");
		ThemeUri themuri  =themeUriManager.getPath(uri);
		uri = themuri.getPath();
		
		//将json字串转为map
		List<Map<String,String>> mapList= WidgetXmlUtil.jsonToMapList(paramJson);	
		
		//调用接口更新挂件参数
		widgetParamUpdater.update(uri, mapList);
		
		//前当使用主题路径
		String themePath = themeManager.getTheme(site.getThemeid()).getPath();
		String contextPath  = EopContext.getContext().getContextPath();
		String pagePath = 
			 EopSetting.EOP_PATH
			+contextPath
			+"/"+EopSetting.THEMES_STORAGE_PATH
			+"/"+themePath
			+"/"+uri;
//		 
//		String oldContent  = FileUtil.read(pagePath, "UTF-8");
//		Pattern p = Pattern.compile("(.*)<div([^<|^>]*?)id=\"AllWrap\"([^<|^>]*?)>(.*)</div>(.*)", 2 | Pattern.DOTALL);
//		Matcher m = p.matcher(oldContent);  
//		if(m.find()){
//			pageContent=StringUtil.filterDollarStr(pageContent);
//			String s = m.replaceAll("$1<body>"+pageContent+"</body>$3");		
//			//更新模板文件内容
//			FileUtil.write(pagePath, s);		
//		}		
	
		pageContent ="<#include 'common/header.html' />"+pageContent+"<#include 'common/footer.html' />";
		FileUtil.write(pagePath, pageContent);
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public void setWidgetParamUpdater(IWidgetParamUpdater widgetParamUpdater) {
		this.widgetParamUpdater = widgetParamUpdater;
	}
	
	

}
