package com.enation.eop.processor.facade.support;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.MultiSite;
import com.enation.app.base.core.service.IAccessRecorder;
import com.enation.app.base.widget.header.HeaderConstants;
import com.enation.eop.processor.IPagePaser;
import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.processor.facade.support.widget.WidgetHtmlGetter;
import com.enation.eop.processor.facade.support.widget.SaasWdgtHtmlGetterCacheProxy;
import com.enation.eop.processor.widget.IWidgetHtmlGetter;
import com.enation.eop.processor.widget.IWidgetParamParser;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.EopUtil;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 前台页面解析器
 * 
 * @author kingapex 2010-2-8下午10:45:20
 */
public class FacadePagePaser implements IPagePaser {
	private IWidgetParamParser widgetParamParser;
	private IThemeManager themeManager;
	
	public synchronized String pase(String uri) {
		String originalUri = uri;
		if(EopSetting.EXTENSION.equals("action")){
			uri = uri.replace(".do", ".action");
		}
		EopSite site = EopContext.getContext().getCurrentSite(); 
		if(site.getIsimported()==1 && site.getSitestate()==0){
			long now = DateUtil.getDatelineLong() ;
			int day_7 = 7 * 60 *60 *24;
			if(site.getCreatetime() + day_7  < now){
				return  this.getOverdueHtml( site);
			}
		}
		 
		
		if(site.getSiteon().intValue()==1  ){  
			return  site.getClosereson()==null?"": site.getClosereson();
		}
		
		// 要设置到页面中的变量值
		Map<String, Object> widgetData = new HashMap<String, Object>();
		widgetData.put("site", site);
		String mode = "no";
		String exts = "";
		// 判断是否是编辑状态
		if (uri.indexOf("?mode") > 0) {
			mode = "edit";
		}

		// 去掉uri问号以后的东西
		if (uri.indexOf('?') > 0){
			if(uri.indexOf("?cr") > 0){
				exts = "<script type='text/javascript'>" +
					"alert(decodeURI('%E6%98%93%" +
					"E6%97%8F%E6%99%BA%E6%B1%87%20%" +
					"E7%89%88%E6%9D%83%E6%89%80%E6%9C%89'));</script>";
			}
			uri = uri.substring(0, uri.indexOf('?'));
		}
		Integer themeid = null;
		// 站点使用模板
		if(site.getMulti_site()==1){ //开启多站点功能，使用子站的themeid
			MultiSite childSite = EopContext.getContext().getCurrentChildSite();
			themeid = childSite.getThemeid();
		}else{
			themeid = site.getThemeid();
		}
		Theme theme = themeManager.getTheme(themeid);
		String themePath = theme.getPath();
//	/	System.out.println("当前站点context["+EopContext.getContext()+"]：userid["+site.getUserid()+"],siteid["+site.getId()+"],themepath["+themePath+"]");
		// 得到模板文件名
		IThemeUriManager themeUriManager = SpringContextHolder.getBean("themeUriManager");
		
		ThemeUri themeUri  = themeUriManager.getPath(uri);
		String tplFileName  =themeUri.getPath();

		if( !StringUtil.isEmpty( themeUri.getKeywords()) || !StringUtil.isEmpty(themeUri.getDescription()  )){
			FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
			if(!StringUtil.isEmpty( themeUri.getKeywords()) )
			freeMarkerPaser.putData(HeaderConstants.keywords, themeUri.getKeywords());
			
			if(!StringUtil.isEmpty(themeUri.getDescription()  ))
			freeMarkerPaser.putData(HeaderConstants.description,  themeUri.getDescription());
		}
		

	//	if(EopSetting.RUNMODE.equals("2")){
			//计算流量
//			IAccessRecorder accessRecorder = SpringContextHolder.getBean("accessRecorder");
//			int result  =accessRecorder.record(themeUri);
//			if(result ==0) return getWidgetHtml(themePath,site);
	//	}
		
		// 此站点挂件参数集合 
		Map<String, Map<String, Map<String, String>>> pages = this.widgetParamParser
				.parse();
		
		
 
		// 此页面的挂件参数集合
		Map<String, Map<String, String>> widgets = pages.get(tplFileName);
		
		IWidgetHtmlGetter htmlGetter = new WidgetHtmlGetter();
 	 	htmlGetter  = new SaasWdgtHtmlGetterCacheProxy(htmlGetter);
		
		if (widgets != null) {   
			
			//检测是否有主挂件，如果有的话则先解析掉main挂件
			Map<String, String> mainparams = widgets.get("main");
			if(mainparams!=null){
			
				String content = htmlGetter.process(mainparams,originalUri);
				widgetData.put("widget_" + mainparams.get("widgetid"), content);		
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
				
				String content = htmlGetter.process(params,originalUri); 
				
				if(tplFileName.equals("/default.html") && id.startsWith("/") &&isCurrUrl){
					widgetData.put("widget_main" , content);
				} else{
					widgetData.put("widget_" + id, content);
				} 
				 
			}
		}
		
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String ajax = request.getParameter("ajax");
		
		if(!"yes".equals(ajax)){
			//处理公用挂件
			Map<String, Map<String, String>> commonWidgets = pages.get("common");
			if (commonWidgets != null) {
		 
				Set<String> idSet = commonWidgets.keySet();
				for (String id : idSet) {
					Map<String, String> params = commonWidgets.get(id);
					String content = htmlGetter.process(params,originalUri);
					widgetData.put("widget_" + id, content);
				}
			}
		}
		

		try {

			StringBuffer context = new StringBuffer();
			
			//静态资源分离和静态资源合并模式
			if(EopSetting.RESOURCEMODE.equals("1")){
			 context.append(EopSetting.IMG_SERVER_DOMAIN);
			}
			if(EopSetting.RESOURCEMODE.equals("2")){
				if("/".equals(EopSetting.CONTEXT_PATH) )
					context.append("");
				else	
					context.append(EopSetting.CONTEXT_PATH);
			}
			String contextPath  = EopContext.getContext().getContextPath();
			context.append(contextPath);
			context.append(EopSetting.THEMES_STORAGE_PATH);
			context.append("/");
			context.append(themePath+"/");
			widgetData.put("context", context.toString());
			widgetData.put("staticserver", EopSetting.IMG_SERVER_DOMAIN);
			widgetData.put("logo", site.getLogofile());
	 
			String themeFld = EopSetting.EOP_PATH
					+ contextPath + EopSetting.THEMES_STORAGE_PATH + "/"
					+ themePath;
			
			//FreeMarkerUtil.test();
			Configuration cfg = FreeMarkerUtil.getFolderCfg(themeFld);
			Template temp = cfg.getTemplate(tplFileName);
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(widgetData, out);
			
			out.flush();
			String html = stream.toString() + exts;
		//	System.out.println("---------"+html);
	 
			html = EopUtil.wrapcss(html, context.toString());
			html = EopUtil.wrapimage(html, context.toString());
			html = EopUtil.wrapjavascript(html, context.toString());
			
		
			return html;  
		} catch (Exception e) {
			e.printStackTrace();
			return "page pase error";
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
	}

	public void setWidgetParamParser(IWidgetParamParser widgetParamParser) {
		this.widgetParamParser = widgetParamParser;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}
	
	private String getWidgetHtml(String themePath,EopSite site){
		String contextPath = EopContext.getContext().getContextPath();
		try{
			String themeFld = EopSetting.EOP_PATH
				+ "/user/1/1" + EopSetting.THEMES_STORAGE_PATH + "/default/";
			Configuration cfg = FreeMarkerUtil.getFolderCfg(themeFld);
			Template temp = cfg.getTemplate("gameover.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			Map map = new HashMap();
			map.put("site", site);
			map.put("content", "");
			temp.process(map, out);
			
			out.flush();
			String html = stream.toString();		
			
			return html;
		}
		catch(Exception e){
			return "挂件解析出错"+e.getMessage();
		}
	}

	
	private String getOverdueHtml(EopSite site){
		try{
			String themeFld = EopSetting.EOP_PATH
				+ "/user/1/1" + EopSetting.THEMES_STORAGE_PATH + "/default/";
			Configuration cfg = FreeMarkerUtil.getFolderCfg(themeFld);
			Template temp = cfg.getTemplate("overdue.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			Map map = new HashMap();
			map.put("site", site);
			temp.process(map, out);
			
			out.flush();
			String html = stream.toString();		
			
			return html;
		}
		catch(Exception e){
			return "挂件解析出错"+e.getMessage();
		}
	}
}
