package com.enation.cms.plugin;

import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.plugin.IRecreateMapEvent;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.plugin.IDataDeleteEvent;
import com.enation.cms.core.plugin.IDataSaveEvent;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.framework.plugin.AutoRegisterPlugin;

/**
 * CMS的sitemap生成插件
 * @author kingapex
 *
 */
public class CmsSitemapPlugin extends AutoRegisterPlugin implements
		IDataSaveEvent, IDataDeleteEvent, IRecreateMapEvent {
	
	private ISitemapManager sitemapManager;
	private IDataCatManager dataCatManager;
	private IDataManager dataManager;

	public void register() {

	}

	public void onSave(Map data) {
		DataCat cat = this.dataCatManager.get(Integer.valueOf(data.get("cat_id").toString()));
		if(cat.getTositemap()==1){ //要加入到Sitemap
			SiteMapUrl url = new SiteMapUrl();
			url.setLoc("/" + cat.getDetail_url() + "-" + cat.getCat_id() + "-" + data.get("id") + ".html");
			url.setLastmod(System.currentTimeMillis());
			this.sitemapManager.addUrl(url);
		}
	}
	

	public void onDelete(Integer catid, Integer articleid) {
		DataCat cat = this.dataCatManager.get(catid);
		this.sitemapManager.delete( "/" + cat.getDetail_url() + "-" + cat.getCat_id() + "-" + articleid + ".html");
		
	}
	
	public void onRecreateMap() {
		List<DataCat> listCat = this.dataCatManager.listAllChildren(0);
		for(DataCat cat:listCat){
			if(cat.getTositemap()==1){
				List<Map> list = this.dataManager.list(cat.getCat_id());
				for(Map map:list){
					SiteMapUrl url = new SiteMapUrl();
					url.setLoc("/" + cat.getDetail_url() + "-" + cat.getCat_id() + "-" + map.get("id") + ".html");
					url.setLastmod(System.currentTimeMillis());
					this.sitemapManager.addUrl(url);
				}
			}
		}
	}

	public String getAuthor() {
		return "lzf";
	}

	public String getId() {
		return "cms_sitemap";
	}

	public String getName() {
		return "cms数据sitemap插件";
	}

	public String getType() {
		return "cmssitemap";
	}

	public String getVersion() {
		return "v2.1.5";
	}

	public void perform(Object... params) {

	}

	public ISitemapManager getSitemapManager() {
		return sitemapManager;
	}

	public void setSitemapManager(ISitemapManager sitemapManager) {
		this.sitemapManager = sitemapManager;
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}

	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}

}
