package com.enation.eop.resource.dto;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopSiteAdmin;
import com.enation.eop.resource.model.EopSiteDomain;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-12-10 下午06:21:25
 *         </p>
 * @version 1.0
 */
public class SiteDTO {
	private EopSite site;
	private EopSiteDomain domain;
	private EopSiteAdmin siteAdmin;
	
//	public SiteDTO(){
//		site = new EopSite();
//		domainList = new ArrayList<EopSiteDomain>();
//		listSiteApp = new ArrayList<EopSiteApp>();
//		siteAdminList = new ArrayList<EopSiteAdmin>();
//	}
	
	public void vaild(){
		if(this.domain==null){
			throw new IllegalArgumentException("站点至少要有一个域名！");
		}		
		
		if(this.siteAdmin==null){
			throw new IllegalArgumentException("站点至少应该指定一位管理员！");
		}
	}
	
	public void setUserId(Integer userid){
		site.setUserid(userid);
		domain.setUserid(userid);
		siteAdmin.setUserid(userid);
	}
	
	public void setSiteId(Integer siteid){
		domain.setSiteid(siteid);
		siteAdmin.setSiteid(siteid);
	}
	
	public void setManagerid(Integer managerid){
		siteAdmin.setManagerid(managerid);
	}

	public EopSite getSite() {
		return site;
	}

	public void setSite(EopSite site) {
		this.site = site;
	}

	public EopSiteDomain getDomain() {
		return domain;
	}

	public void setDomain(EopSiteDomain domain) {
		this.domain = domain;
	}

	public EopSiteAdmin getSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(EopSiteAdmin siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

}
