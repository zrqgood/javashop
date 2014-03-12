package com.enation.eop.processor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.service.ISitemapManager;
import com.enation.eop.processor.backend.BackgroundProcessor;
import com.enation.eop.processor.facade.FacadePageProcessor;
import com.enation.eop.processor.facade.SiteMapProcessor;
import com.enation.eop.processor.facade.WebResourceProcessor;
import com.enation.eop.processor.facade.WidgetProcessor;
import com.enation.eop.processor.facade.WidgetSettingProcessor;
import com.enation.eop.resource.IAppManager;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.sdk.context.ConnectType;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * @author kingapex
 * @version 1.0
 * @created 13-十月-2009 11:36:29 
 */
public abstract class ProcessorFactory {

	/**
	 * 
	 * @param uri
	 */
	public static Processor newProcessorInstance(String uri,HttpServletRequest httpRequest){
		Processor processor =null;
//		if(uri.endsWith(".action") || uri.endsWith(".do") ) return null;
		if(uri.startsWith("/statics")) return null;
		if(uri.startsWith("/install") && !uri.startsWith("/install.html")  ) return null;
		
		//sitemap生成
		if(uri.toLowerCase().equals( "/sitemap.xml")) { 
			return new SiteMapProcessor();
		}
		
		if(uri.toLowerCase().equals("/robots.txt")){return null;}
		
		IAppManager appManager = SpringContextHolder.getBean("appManager");
		List<EopApp> appList  =  appManager.list();
		String path =httpRequest.getServletPath();
		for(EopApp app:appList){
			if(app.getDeployment()==ConnectType.remote) continue;
			
			if(path.startsWith(app.getPath() +"/admin" )) {
				if( isExinclude (path)){return null;}
				
				processor = new BackgroundProcessor();
				return processor;
			} 
			if( path.startsWith( app.getPath()  ) ){
				return null;
			}
		}
		
		if(uri.startsWith("/validcode")) return null;
		if(uri.startsWith("/editor/")) return null;
		if(uri.startsWith("/eop/")) return null;
		if(uri.startsWith("/test/")) return null;
		if(uri.endsWith("favicon.ico")) return null;
		
		if (uri.startsWith("/resource/")) { 
			return new WebResourceProcessor();
		} 
		
		if(isExinclude(uri)) return null;
 
		if (uri.startsWith("/admin/")) { 
			if (!uri.startsWith("/admin/themes/")) {
					processor = new BackgroundProcessor();
			}
		} else if (uri.startsWith("/widget")) {
			
			if(uri.startsWith("/widgetSetting/")){
				processor = new WidgetSettingProcessor();
			}else if(uri.startsWith("/widgetBundle/")){
			//	processor = new WidgetBundleProcessor();
			}else{		 
				processor = new WidgetProcessor();
			}
		} else{
 
			if(uri.endsWith(".action") || uri.endsWith(".do") ) return null;
			if(EopSetting.TEMPLATEENGINE.equals("on"))
				processor = new FacadePageProcessor();
		}
		 
		return processor;
	}
	
 

	private static boolean isExinclude(String uri){
		
		String[] exts=new String[]{"jpg","gif","js","png","css","doc","xls","swf"};
		for(String ext:exts){ 
			if(uri.toUpperCase().endsWith(ext.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}

}