package com.enation.javashop.core.plugin.search;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;

public class GoodsDataFilterBundle extends AutoRegisterPluginsBundle {

	public String getName() {
		 
		return "商品数据过滤插件桩";
	}

	
	public List getPluginList(){
		
		return this.plugins;
	}
	
	
	
}
