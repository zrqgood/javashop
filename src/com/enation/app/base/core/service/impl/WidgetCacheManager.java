package com.enation.app.base.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.enation.app.base.core.service.ISettingService;
import com.enation.app.base.core.service.IWidgetCacheManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.cache.CacheFactory;
import com.enation.framework.cache.ICache;

/**
 * 挂件缓存
 * @author kingapex
 *
 */
public class WidgetCacheManager implements IWidgetCacheManager {
	private ISettingService settingService;
	
	/**
	 * 更新挂件缓存为关闭
	 * 同时将挂件缓存清空
	 */
	public void close() {
		
		//清除挂件缓存
		if("2".equals(EopSetting.RUNMODE)){
			this.cleanSaasCache();
		}else{
			this.cleanSlCache();
		}
		
		Map<String,Map<String,String>> settings =new HashMap<String,Map<String,String>>();
		Map<String,String> value = new HashMap<String, String>();
		value.put("state", "close");
		settings.put(CacheFactory.WIDGET_CACHE_NAME_KEY, value);
		settingService.save(settings);
	}

	
	/**
	 * 清除saas站点的缓存
	 */
	private void cleanSaasCache(){
		
		ICache widgetCache = CacheFactory.getCache(CacheFactory.WIDGET_CACHE_NAME_KEY);
		//以当前站点作为整站挂件缓存的key
		//缓存的是一个map，map的key为当前页面uri和挂件id的组合
		EopSite site = EopContext.getContext().getCurrentSite();
		String site_key = "widget_"+ site.getUserid() +"_"+site.getId();
		
		//删除此站点的挂件集合
		widgetCache.remove(site_key);
		
		
	}
	
	/**
	 * 清除独立版的缓存
	 */
	private void cleanSlCache(){
		ICache widgetCache = CacheFactory.getCache(CacheFactory.WIDGET_CACHE_NAME_KEY);
		widgetCache.clear();
	}
	
	
	
	/**
	 * 更新挂件缓存状态为开启
	 */
	public void open() {
		Map<String,Map<String,String>> settings =new HashMap<String,Map<String,String>>();
		Map<String,String> value = new HashMap<String, String>();
		value.put("state", "open");
		settings.put(CacheFactory.WIDGET_CACHE_NAME_KEY, value);
		settingService.save(settings);
	}


	/**
	 * 获取挂件缓存是否为打开状态
	 */
	public boolean isOpen() {
		Map<String,Map<String,String>> settings =new HashMap<String,Map<String,String>>();
		String state = settingService.getSetting("widgetCache", "state");
		return "open".equals(state);
	}


	public ISettingService getSettingService() {
		return settingService;
	}


	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

}
