package com.enation.app.base.core.action;

import com.enation.app.base.core.plugin.SitemapPluginBundle;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.framework.action.WWAction;

/**
 * 后台重建站点地图
 * @author lzf<br/>
 * 2010-12-2 下午01:31:52<br/>
 * version 2.1.5
 */
public class CreateSitemapAction extends WWAction {

	private ISitemapManager sitemapManager;
	private SitemapPluginBundle sitemapPluginBundle;
	
	public String recreate(){
		this.clean();
		this.sitemapPluginBundle.onRecreateMap();
		this.msgs.add("站点地图创建成功");
		this.urls.put("访问站点地图", "/sitemap.xml");
		return this.MESSAGE;
	}
	private void clean(){
		this.sitemapManager.clean();
	}
	
	public ISitemapManager getSitemapManager() {
		return sitemapManager;
	}
	public void setSitemapManager(ISitemapManager sitemapManager) {
		this.sitemapManager = sitemapManager;
	}
	public SitemapPluginBundle getSitemapPluginBundle() {
		return sitemapPluginBundle;
	}
	public void setSitemapPluginBundle(SitemapPluginBundle sitemapPluginBundle) {
		this.sitemapPluginBundle = sitemapPluginBundle;
	}
}