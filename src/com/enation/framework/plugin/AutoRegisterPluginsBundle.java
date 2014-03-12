package com.enation.framework.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 自动注册插件桩
 * 
 * @author apexking
 * 
 */
public abstract class AutoRegisterPluginsBundle implements IPluginBundle {
	protected static final Log loger = LogFactory
			.getLog(AutoRegisterPluginsBundle.class);

	protected List<IPlugin> plugins;

	public void registerPlugin(IPlugin plugin) {
		if (plugins == null) {
			plugins = new ArrayList<IPlugin>();
		}

		if (loger.isDebugEnabled()) {
			loger.debug("为插件桩" + getName() + "注册插件：" + plugin.getName()
					+ "，id：" + plugin.getId() + "，版本：" + plugin.getVersion()
					+ "，作者：" + plugin.getAuthor() + "。");
//			System.out.println("为插件桩" + getName() + "注册插件：" + plugin.getName()
//					+ "，id：" + plugin.getId() + "，版本：" + plugin.getVersion()
//					+ "，作者：" + plugin.getAuthor() + "。");
		}
		plugins.add(plugin);
	}

	abstract public String getName();

}
