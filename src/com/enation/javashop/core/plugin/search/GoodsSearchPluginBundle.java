package com.enation.javashop.core.plugin.search;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;

public class GoodsSearchPluginBundle extends AutoRegisterPluginsBundle {

	@Override
	public String getName() {
		 
		return "商品搜索插件桩";
	}
	
	public List getPluginList(){
		
		return this.plugins;
	}
	
 
	

}
