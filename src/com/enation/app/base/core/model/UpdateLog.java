package com.enation.app.base.core.model;

import java.util.List;


/**
 * 应用更新日志
 * @author kingapex
 *
 */
public class UpdateLog {
	//应用ID
	private String appId;
	//更新日志
	private List<String> logList;

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public List<String> getLogList() {
		return logList;
	}
	public void setLogList(List<String> logList) {
		this.logList = logList;
	}
	
	
}
