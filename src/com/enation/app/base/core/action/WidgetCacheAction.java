package com.enation.app.base.core.action;

import java.util.HashMap;
import java.util.Map;

import com.enation.app.base.core.service.ISettingService;
import com.enation.app.base.core.service.IWidgetCacheManager;
import com.enation.framework.action.WWAction;

/**
 * 挂件缓存action
 * @author kingapex
 *
 */
public class WidgetCacheAction extends WWAction {
	
	private IWidgetCacheManager widgetCacheManager;
	/**
	 * 开启挂件缓存
	 */
	public String open(){
		
		try{
			this.widgetCacheManager.open();
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
		
	}
	
	
	/**
	 * 关闭挂件缓存
	 */
	public String close(){
		try{
			this.widgetCacheManager.close();
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}


	public String getState(){
		try{
			boolean isOpen  = this.widgetCacheManager.isOpen();
			String state  = isOpen?"open":"close";
			this.json="{result:1,state:'"+state+"'}";
		}catch(RuntimeException e){
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
		
	}
	
	public IWidgetCacheManager getWidgetCacheManager() {
		return widgetCacheManager;
	}


	public void setWidgetCacheManager(IWidgetCacheManager widgetCacheManager) {
		this.widgetCacheManager = widgetCacheManager;
	}

	

	

}
