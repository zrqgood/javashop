package com.enation.eop.resource.model;

/**
 * 应用-站点视图，反映所有已接入应用与站点的关系
 * 
 * @author lzf
 *         <p>
 *         2009-12-29 上午09:52:40
 *         </p>
 * @version 1.0
 */
public class EopAppSiteView extends EopApp {
	private Integer installedsiteid;

	public Integer getInstalledsiteid() {
		return installedsiteid;
	}

	public void setInstalledsiteid(Integer installedsiteid) {
		this.installedsiteid = installedsiteid;
	}

}
