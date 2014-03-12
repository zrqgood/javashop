package com.enation.framework.plugin;

import java.util.ArrayList;
import java.util.List;

public class PluginContext {
	
	 private static List<IPlugin> pluginContext;
	static{
		pluginContext =  new ArrayList<IPlugin>();
	}
	 public static void registerPlugin(IPlugin plugin){
		 pluginContext.add( plugin);
	 }
	 
	 public static List<IPlugin> getPlugins(){
		 return pluginContext;
	 }
	 
}
