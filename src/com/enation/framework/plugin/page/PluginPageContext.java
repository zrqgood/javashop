package com.enation.framework.plugin.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插件页面上下文件<br/> 用于存储插件页面及其插件类型的对应关系。
 * 
 * @author apexking
 * 
 */
public class PluginPageContext {
	private static Map<String, List<String>> plugin_pages;

	static {
		plugin_pages = new HashMap<String, List<String>>();
	}

	/**
	 * 向上下文件中加入一个页面路径。
	 * 
	 * @param type
	 *            插件页面类型
	 * @param page
	 *            插件页面路径
	 */
	public static void addPage(String type, String page) {
		List<String> pagelist = plugin_pages.get(type);
		if (pagelist == null)
			pagelist = new ArrayList<String>();
		pagelist.add(page);
		plugin_pages.put(type, pagelist);
		System.out.println("加载" + type + " 类页面： " + page);
	}

	/**
	 * 获取某插件类型的页面路径集合
	 * 
	 * @param page_type
	 *            插件页面类型
	 * @return
	 */
	public static List<String> getPages(String page_type) {
		return plugin_pages.get(page_type);
	}

}
