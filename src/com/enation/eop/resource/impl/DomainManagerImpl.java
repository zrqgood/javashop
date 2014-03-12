package com.enation.eop.resource.impl;

import java.util.List;

import com.enation.eop.resource.IDomainManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopSiteDomain;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.database.IDaoSupport;

/**
 * 域名管理
 * 
 * @author kingapex 2010-5-9下午08:14:11
 */
public class DomainManagerImpl implements IDomainManager {

	private IDaoSupport<EopSiteDomain> daoSupport;

	
	
	public EopSiteDomain get(Integer id) {
		String sql = "select * from eop_sitedomain where id = ?";
		return daoSupport.queryForObject(sql, EopSiteDomain.class, id);
	}

	
	public List<EopSiteDomain> listUserDomain() {
		Integer userid = EopContext.getContext().getCurrentSite().getUserid();
		String sql = "select * from eop_sitedomain where userid=?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class, userid);
	}

	
	public List<EopSiteDomain> listSiteDomain() {
		EopSite site = EopContext.getContext().getCurrentSite();
		String sql = "select * from eop_sitedomain where userid=? and siteid =?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class, site
				.getUserid(), site.getId());
	}
	
	public List<EopSiteDomain> listSiteDomain(Integer siteid) {
		String sql = "select * from eop_sitedomain where  siteid =?";
		return this.daoSupport.queryForList(sql, EopSiteDomain.class, siteid);
	}
	
	public void edit(EopSiteDomain domain) {
		this.daoSupport.update("eop_sitedomain", domain, " id = "
				+ domain.getId());
	}

	public IDaoSupport<EopSiteDomain> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopSiteDomain> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public int getDomainCount(Integer siteid) {
		String sql = "select count(0)  from eop_sitedomain where  siteid =?";
		return this.daoSupport.queryForInt(sql, siteid);
	}




}
