package com.enation.app.base.core.action;

import net.sf.json.JSONObject;

import com.enation.app.base.core.model.VersionState;
import com.enation.app.base.core.service.IUpdateManager;
import com.enation.framework.action.WWAction;


/**
 * 版本更新action
 * @author kingapex
 *
 */
public class UpdateAction extends WWAction {
	
	private IUpdateManager updateManager;
	
	 
	
	/**
	 * 检测是否有新版本
	 * 供ajax调用使用
	 * @return
	 */
	public String checkNewVersion(){
		VersionState versionState = null;
		try {
			versionState = updateManager.checkNewVersion();
		} catch(Exception e){
			versionState = new VersionState();
		}
		this.json=JSONObject.fromObject(versionState).toString();
		return this.JSON_MESSAGE;
	}
	
	public String update(){
		
		try{
			this.updateManager.update();
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.logger.error(e);
			e.printStackTrace();
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}	
		return this.JSON_MESSAGE;
	}
	
	
	
	public IUpdateManager getUpdateManager() {
		return updateManager;
	}
	public void setUpdateManager(IUpdateManager updateManager) {
		this.updateManager = updateManager;
	}
	
	
}
