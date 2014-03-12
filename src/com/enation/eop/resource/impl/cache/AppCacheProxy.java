package com.enation.eop.resource.impl.cache;

import java.util.List;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.resource.IAppManager;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.resource.model.EopAppSiteView;
import com.enation.eop.resource.model.EopSiteApp;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.cache.CacheFactory;

/**
 * App Manager的缓存代理
 * @author kingapex
 * <p>2009-12-16 下午05:15:28</p>
 * @version 1.0
 */
public class AppCacheProxy extends AbstractCacheProxy<List<EopApp>> implements IAppManager {
	
	
	private IAppManager appManager;
	private static final String APP_LIST_CACHE_KEY = "applist";
	
	public  AppCacheProxy(IAppManager appManager){
		super(CacheFactory.APP_CACHE_NAME_KEY  );
		this.appManager = appManager;
	}
	
	
	
	public void add(EopApp app) {
		cache.clear();
	    appManager.add(app);
	}

	
	public EopApp get(String appid) {
		
		if(logger.isDebugEnabled()){
			logger.debug("get app : "+ appid);
		}
		List<EopApp> appList = this.list();
	 
		for(EopApp app :appList){
			if(app.getAppid().equals(appid)){
				return app;
			 
			}
		}
		
		throw new  EopException("App not found");
	}

	
	public List<EopApp> list() {
		
		List<EopApp> appList = this.cache.get(APP_LIST_CACHE_KEY);
		
		if(appList==null){
			if(logger.isDebugEnabled()){
				logger.debug("get applist from database");
			}
			appList = appManager.list();
			 this.cache.put(APP_LIST_CACHE_KEY, appList);
		}else{
			if(logger.isDebugEnabled()){
				logger.debug("get applist from cache");
			}
		}
		return appList;
	}
	 
}
