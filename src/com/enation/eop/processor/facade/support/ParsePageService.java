package com.enation.eop.processor.facade.support;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.facade.support.widget.WidgetHtmlGetter;
import com.enation.eop.processor.widget.IWidgetHtmlGetter;
import com.enation.eop.processor.widget.IWidgetParamParser;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;

public class ParsePageService {
	
	public   void parse(){
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String uri = RequestUtil.getRequestUrl(request);
		this.pase(uri);
	} 

	
	private IWidgetParamParser widgetParamParser;

	private IThemeManager themeManager;
	private IThemeUriManager themeUriManager;
 
	private void putData(String key,String value){
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		request.setAttribute(key,value);
	}
	
	public void pase(String uri) {
 

		String mode = "no";
		// 判断是否是编辑状态
		if (uri.indexOf("?mode") > 0) {
			mode = "edit";
		}

		// 去掉uri问号以后的东西
		if (uri.indexOf('?') > 0)
			uri = uri.substring(0, uri.indexOf('?'));

		// 站点使用模板
		EopSite site = EopContext.getContext().getCurrentSite();
		Theme theme = themeManager.getTheme(site.getThemeid());
		String themePath = theme.getPath();

		ThemeUri themeUri  = themeUriManager.getPath(  uri);
		String tplFileName= themeUri.getPath();
	 
		
		// 此站点挂件参数集合
		Map<String, Map<String, Map<String, String>>> pages = this.widgetParamParser
				.parse();
		
		
		

		
 
		// 此页面的挂件参数集合
		Map<String, Map<String, String>> widgets = pages.get(tplFileName);
		
		if (widgets != null) {
			
			IWidgetHtmlGetter htmlGetter = new WidgetHtmlGetter();
			
			
			//检测是否有主挂件，如果有的话则先解析掉main挂件
			Map<String, String> mainparams = widgets.get("main");
			if(mainparams!=null){
				String content = htmlGetter.process(mainparams,tplFileName);
				putData("widget_" + mainparams.get("id"), content);		
				widgets.remove("main");
			}
			
			Set<String> idSet = widgets.keySet();
			
			for (String id : idSet) {
				Map<String, String> params = widgets.get(id);
				params.put("mode", mode);
			 
				boolean isCurrUrl =  matchUrl(uri,id);
				
				//对默认页的特殊处理
				if(tplFileName.equals("/default.html") && id.startsWith("/") &&  ! isCurrUrl ){
							 continue;
				} 
				
				String content = htmlGetter.process(params,tplFileName); 
				
				if(tplFileName.equals("/default.html") && id.startsWith("/") &&isCurrUrl){
					putData("widget_main" , content);
				} else{
					putData("widget_" + id, content);
				} 
				 
			}
		}
		
		//处理公用挂件
		Map<String, Map<String, String>> commonWidgets = pages.get("common");
		if (commonWidgets != null) {
			IWidgetHtmlGetter htmlGetter = new WidgetHtmlGetter();
			Set<String> idSet = commonWidgets.keySet();
			for (String id : idSet) {
				Map<String, String> params = commonWidgets.get(id);
				String content = htmlGetter.process(params,tplFileName);
				putData("widget_" + id, content);
			}
		}
		
		

		try {

			StringBuffer context = new StringBuffer();
			
			//静态资源分离和静态资源合并模式
			if(EopSetting.RESOURCEMODE.equals("1")){
			 context.append(EopSetting.IMG_SERVER_DOMAIN);
			}
			if(EopSetting.RESOURCEMODE.equals("2")){
				context.append(EopSetting.CONTEXT_PATH);
			}
			String contextPath  = EopContext.getContext().getContextPath();
			context.append(contextPath);
			context.append(EopSetting.THEMES_STORAGE_PATH);
			context.append("/");
			context.append(themePath+"/");
			putData("context", context.toString());
			putData("staticserver", EopSetting.IMG_SERVER_DOMAIN);
			
		
		 
		} catch (Exception e) {
			e.printStackTrace();
		 
		}
	}

	
	private boolean matchUrl(String uri,String targetUri){
		Pattern p = Pattern.compile(targetUri, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(uri); 
		return m.find();
	 
	}
	
	public static void main(String[] args) {
		String url = "/goods-1.html";
		if (url.indexOf('?') > 0)
			url = url.substring(0, url.indexOf('?'));
		System.out.println(url);
	}

	public void setWidgetParamParser(IWidgetParamParser widgetParamParser) {
		this.widgetParamParser = widgetParamParser;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}

	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}

	public IWidgetParamParser getWidgetParamParser() {
		return widgetParamParser;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}
	
	
}
