package com.enation.cms.plugin;

import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.plugin.IRecreateMapEvent;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.model.DataModel;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.framework.database.Page;
import com.enation.framework.plugin.AutoRegisterPlugin;

public class CmsRecreateMapPlugin extends AutoRegisterPlugin implements
		IRecreateMapEvent {
	
	private ISitemapManager sitemapManager;
	private IDataCatManager dataCatManager;
	private IDataManager dataManager;

	public void register() {

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
		return "CmsRecreateMap";
	}

	public String getName() {
		return "内容发布-重建站点地图";
	}

	public String getType() {
		return "CmsRecreateMap";
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
