package com.enation.app.base.core.service.impl.cache;

import java.util.List;

import com.enation.app.base.core.model.SiteMenu;
import com.enation.app.base.core.service.ISiteMenuManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.cache.AbstractCacheProxy;

public class SiteMenuCacheProxy extends AbstractCacheProxy<List<SiteMenu>> implements ISiteMenuManager {
	public  static final String MENU_LIST_CACHE_KEY = "siteMenuList";
	private ISiteMenuManager siteMenuManager;
	public SiteMenuCacheProxy(ISiteMenuManager siteMenuManager) {
		super(MENU_LIST_CACHE_KEY);
		this.siteMenuManager = siteMenuManager;
		 
	}

	private void cleanCache(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		this.cache.remove( MENU_LIST_CACHE_KEY+"_"+site.getUserid() +"_"+site.getId());
	}
	
	
	public void add(SiteMenu siteMenu) {
		this.siteMenuManager.add(siteMenu);
		this.cleanCache();
	}

	
	public void delete(Integer id) {
		this.siteMenuManager.delete(id);
		this.cleanCache();
	 
	}

	
	public void edit(SiteMenu siteMenu) {
		this.siteMenuManager.edit(siteMenu);
		this.cleanCache();
	}

	
	public SiteMenu get(Integer menuid) {
		return this.siteMenuManager.get(menuid);
	}

	
	public List<SiteMenu> list(Integer parentid) {
		EopSite site  = EopContext.getContext().getCurrentSite();
		List<SiteMenu> menuList  =  this.cache.get( MENU_LIST_CACHE_KEY+"_"+site.getUserid() +"_"+site.getId());
		
		
		if(menuList== null ){
			menuList = this.siteMenuManager.list(parentid);
			this.cache.put( MENU_LIST_CACHE_KEY+"_"+site.getUserid() +"_"+site.getId(),menuList);
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load sitemenu from database");
			}
		}else{
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load sitemenu from cache");
			}
		}
		
		return menuList;
	}

	
	public void updateSort(Integer[] menuid, Integer[] sort) {
		this.siteMenuManager.updateSort(menuid, sort);
		this.cleanCache();
	}

}
