package com.enation.framework.plugin.page;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 商品维护时的选项卡上下文
 * @author kingapex
 *
 */
public class JspPageTabs {
	
	private static final Log loger = LogFactory.getLog(JspPageTabs.class);
	
	private static Map<String,Map> tabs;
	static{
		tabs = new HashMap<String, Map>();
		
	}
	
	
	public static void addTab(String plugintype,Integer tabid,String tabname){
		Map<Integer,String> plugin_tab = tabs.get(plugintype)==null?new LinkedHashMap<Integer, String>(): (Map)tabs.get(plugintype) ;
		plugin_tab.put(tabid, tabname);
		
		tabs.put(plugintype,plugin_tab);
		if(loger.isDebugEnabled()){
			loger.debug("添加"+ plugintype  +"选项卡" +  tabid + " tabname is  " + tabname);
		}
	}
	
	public static Map getTabs(String plugintype){
		
		return tabs.get(plugintype);
	}
	
	
	
}
