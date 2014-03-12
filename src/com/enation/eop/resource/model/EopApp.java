package com.enation.eop.resource.model;

import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-6 下午04:01:48
 *         </p>
 * @version 1.0
 */
public class EopApp {
	
	private Integer id;
	private String appid;
	private String app_name;
	private String author;
	private String descript;
	private int deployment; // 0:本地；1：远程
	private String path; // 对本地是目录，对远程是地址
	private String installuri; //安装地址 
	
	
	private String version; 
	private List<String> updateLogList;//应用的更新日志项
	
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private String authorizationcode; // 授权码

	/**
	 * @return
	 */
	public String getApp_name() {
		return app_name;
	}

	/**
	 * @param appName
	 */
	public void setApp_name(String appName) {
		app_name = appName;
	}

	/**
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return
	 */
	public String getDescript() {
		return descript;
	}

	/**
	 * @param descript
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}

	/**
	 * @return
	 */
	public int getDeployment() {
		return deployment;
	}

	/**
	 * @param deployment
	 */
	public void setDeployment(int deployment) {
		this.deployment = deployment;
	}

	/**
	 * @return
	 */
	public String getAuthorizationcode() {
		return authorizationcode;
	}

	/**
	 * @param authorizationcode
	 */
	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
	}

	public String getInstalluri() {
		return installuri;
	}

	public void setInstalluri(String installuri) {
		this.installuri = installuri;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@NotDbField
	public List<String> getUpdateLogList() {
		return updateLogList;
	}

	public void setUpdateLogList(List<String> updateLogList) {
		this.updateLogList = updateLogList;
	}


}
