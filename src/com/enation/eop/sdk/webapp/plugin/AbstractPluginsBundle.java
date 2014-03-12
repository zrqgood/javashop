package com.enation.eop.sdk.webapp.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 可插件化业务基类,提供插件桩基本功能<br/> 本类实现了IPluginBundle接口，使其具有了插件桩化的能力。<br/>
 * 本类为抽像类，定义了一些插件桩公用的方法，将一插件按照type为key注册到一个map中,<br/> 执行某类多个插件的功能。<br/>
 * 一般性业务类需要插件桩化建议继承此类。
 * 
 * @author apexking
 * 
 */
public abstract class AbstractPluginsBundle implements IPluginBundle {

	/**
	 * 插件容器
	 */
	protected Map<String, List<IPlugin>> plugins;

	/**
	 * 注册插件
	 */
	public void registerPlugin(IPlugin plugin) {

		if (plugins == null) {
			plugins = new HashMap<String, List<IPlugin>>();
		}

		List<IPlugin> plugin_list = plugins.get(plugin.getType());

		if (plugin_list == null) {
			plugin_list = new ArrayList<IPlugin>();
		}

		plugin_list.add(plugin);
		plugins.put(plugin.getType(), plugin_list);

	}

	/**
	 * 执行插件功能
	 * 
	 * @param type
	 *            插件的类型
	 * @param param
	 *            传递给插件的参数
	 */
	protected void performPlugins(String type, Object... param) {
		List<IPlugin> plugin_list = plugins.get(type);
		if (plugin_list != null) {
			for (IPlugin plugin : plugin_list) {
				plugin.perform(param);
			}
		}
	}
}
