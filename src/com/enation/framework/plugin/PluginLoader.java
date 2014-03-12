package com.enation.framework.plugin;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class PluginLoader implements BeanPostProcessor {

	
	public Object postProcessAfterInitialization(Object bean, String arg1)
			throws BeansException {
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		//System.out.println(" init " + beanName);
		if(bean instanceof AutoRegisterPlugin){
			AutoRegisterPlugin plugin =  (AutoRegisterPlugin)bean;
			if(plugin.getBundleList()==null){
			//	System.out.println( plugin.getName() +  " bundlelist is null " );
			}else{
				
				plugin.register();
				List<IPluginBundle> pluginBundelList = plugin.getBundleList();
				for( IPluginBundle bundle :pluginBundelList){
					bundle.registerPlugin(plugin);
				//	System.out.println(plugin.getName() + "注册在" + bundle.getClass().getName()+"桩中" ); 
				}
				PluginContext.registerPlugin(plugin); 
				
			}
		}
		
		return bean;
	}

}
