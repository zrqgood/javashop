package com.enation.framework.plugin;

/**
 * 插件桩接口<br/>
 * 需要插件桩化的业务类实现此接口<br/>
 * 桩化后的业务类可以注册实现了IPlugin接口的插件
 * @see IPlugin
 * @author apexking
 */
public interface IPluginBundle {
	
	
	/**
	 * 为插件桩注册一个插件
	 * @param plugin 进行注册的插件
	 */
	public void registerPlugin(IPlugin plugin);
	
	
	
}
