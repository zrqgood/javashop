package com.enation.app.base.core.model;

import java.util.List;

import com.enation.eop.sdk.context.EopSetting;

/**
 * 版本状态实体<br>
 * @author kingapex
 *
 */
public class VersionState {
	
	public VersionState(){
		this.haveNewVersion=false;
		this.productid = EopSetting.PRODUCTID;
	}
	
	//产品id
	private String productid;
	/**
	 * 是否有新版本
	 */
	private boolean haveNewVersion;
	
	
	
	/**
	 * 新版本号
	 */
	private String newVersion;

	
	
	
	/**
	 * 更新日志 
	 */
	private List<UpdateLog> updateLogList;
	
	
	
	
	public boolean getHaveNewVersion() {
		return haveNewVersion;
	}

	public void setHaveNewVersion(boolean haveNewVersion) {
		this.haveNewVersion = haveNewVersion;
	}

	public String getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}

	
	public List<UpdateLog> getUpdateLogList() {
		return updateLogList;
	}

	public void setUpdateLogList(List<UpdateLog> updateLogList) {
		this.updateLogList = updateLogList;
	}

	
	
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	/*
	 *                  ////////////////////////////////////////////
	 *                  下面属性已废弃，使用 updateLogList<UpdateLog>
	 *                  ////////////////////////////////////////////
	 * 
	 */
	/**
	 * 新版本更新日志
	 */
	@Deprecated
	private String updateLog;

	@Deprecated
	public String getUpdateLog() {
		return updateLog;
	}

	@Deprecated
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

}
