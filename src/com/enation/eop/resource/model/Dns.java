package com.enation.eop.resource.model;

import java.io.Serializable;

/**
 * Eop 平台DNS
 * @author lzf
 * <p>2009-12-17 上午09:54:51</p>
 * @version 1.0
 */
public class Dns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7525130004L;
	
	private String domain;
	private EopSite site;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public EopSite getSite() {
		return site;
	}
	public void setSite(EopSite site) {
		this.site = site;
	}
	
	

}
