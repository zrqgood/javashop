package com.enation.javashop.core.plugin.promotion;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;

/**
 * 优惠规则插件桩
 * @author kingapex
 *2010-4-15下午03:50:35
 */
public class PromotionPluginBundle extends AutoRegisterPluginsBundle {

	
	public String getName() {
		 
		return "优惠规则插件桩";
	}
	
	public List<IPlugin> getPluginList(){
		
		return this.plugins;
	}
	
	

}
