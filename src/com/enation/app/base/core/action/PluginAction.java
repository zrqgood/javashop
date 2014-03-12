package com.enation.app.base.core.action;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.framework.plugin.PluginContext;

public class PluginAction extends WWAction {
	private List plugins;
	public String list() {
		plugins = PluginContext.getPlugins();
		return "list";
	}
	public List getPlugins() {
		return plugins;
	}
	public void setPlugins(List plugins) {
		this.plugins = plugins;
	}
	
	
}
