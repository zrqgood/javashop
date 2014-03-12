package com.enation.javashop.core.plugin.payment;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;

public class PaymentPluginBundle extends AutoRegisterPluginsBundle {

	
	public String getName() {
		
		return "支付插件桩";
	}
	
	
	public List getPluginList(){
		
		return this.plugins;
	}
	
	
}
