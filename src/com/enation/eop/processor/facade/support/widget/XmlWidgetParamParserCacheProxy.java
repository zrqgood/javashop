package com.enation.eop.processor.facade.support.widget;

import java.util.Map;

import com.enation.eop.processor.widget.IWidgetParamParser;
import com.enation.framework.cache.AbstractCacheProxy;

public class XmlWidgetParamParserCacheProxy extends AbstractCacheProxy implements
		IWidgetParamParser {
	private static String cacheName = "widget_key";
	private IWidgetParamParser xmlWidgetParamParserImpl;
	
	public XmlWidgetParamParserCacheProxy(IWidgetParamParser _xmlWidgetParamParserImpl) {
		super(cacheName);
		xmlWidgetParamParserImpl =  _xmlWidgetParamParserImpl;
	
	}

	public Map<String, Map<String, Map<String, String>>> parse() {
	
		Object obj = this.cache.get("obj");
		
		if(obj==null){
			  obj  =this.xmlWidgetParamParserImpl.parse();
			  this.cache.put("obj", obj);
		//	  System.out.println("read from disc");
		} else{
		//	System.out.println("read from cache");
		}
		
		return ( Map<String, Map<String, Map<String, String>>>) obj;
	}

}
