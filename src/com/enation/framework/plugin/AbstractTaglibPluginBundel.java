package com.enation.framework.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enation.framework.taglib.EnationTagSupport;

public class AbstractTaglibPluginBundel extends EnationTagSupport {
	
	protected static final Log loger = LogFactory
	.getLog(AbstractTaglibPluginBundel.class);
	
	
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
		
		if(loger.isDebugEnabled()){
			loger.debug("注册插件：" + plugin.getName() + "，id：" + plugin.getId()
					+ "，版本：" + plugin.getVersion() + "，作者：" + plugin.getAuthor()
					+ "。");
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
