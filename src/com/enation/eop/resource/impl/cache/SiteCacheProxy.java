package com.enation.eop.resource.impl.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.resource.IDomainManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.Dns;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopSiteDomain;
import com.enation.eop.resource.model.EopUser;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.cache.CacheFactory;
import com.enation.framework.database.Page;

/**
 * 站点缓存代理
 * @author kingapex
 * <p>2009-12-17 上午11:38:56</p>
 * @version 1.0
 */
public class SiteCacheProxy extends AbstractCacheProxy<EopSite> implements ISiteManager {
	
	private ISiteManager siteManager;
	private IDomainManager domainManager;
	private static final String SITE_LIST_CACHE_KEY ="eopDNS";
	public SiteCacheProxy(ISiteManager siteManager) {
		super(CacheFactory.SITE_CACHE_NAME_KEY);
		this.siteManager  = siteManager;
	}

	
	public int addDomain(EopSiteDomain eopSiteDomain) {
		EopSite site  =siteManager.get(eopSiteDomain.getSiteid());
		this.cache.put(eopSiteDomain.getDomain(),site);
		return siteManager.addDomain(eopSiteDomain);
	}
	
	public void deleteDomain(String domain) {
		
		siteManager.deleteDomain(domain);
		this.cache.remove(domain);
	}
	
	public Integer add(EopSite newSite,String domain) {
		
		EopSite  site  = cache.get(domain);
		if(site  == null){
			return siteManager.add(newSite,domain);
		}
		throw new EopException("域名:"+ domain +" 已经存在！");
	}

	
	public void deleteDomain(Integer domainid) {
		EopSiteDomain siteDomain = this.domainManager.get(domainid);
		siteManager.deleteDomain(domainid);
		this.cache.remove(siteDomain.getDomain());
	}

	
	public void delete(Integer id) {
		//移除缓存对应的site映射
		List<EopSiteDomain > list = domainManager.listSiteDomain(id);
		for(EopSiteDomain siteDomain :list){
			this.cache.remove(siteDomain.getDomain());
		}
		this.siteManager.delete(id);

		
	}

	
	
	/**
	 * 读取所有的dns列表并压入到Cache中<br/>
	 * 
	 */
	
	public List<Dns> getDnsList() {

		if(logger.isDebugEnabled()){
			logger.debug("query all DNS form datagae,then put them in cache ");
		}
		List<Dns>  dnsList = this.siteManager.getDnsList();
		for(Dns dns: dnsList){
			cache.put(dns.getDomain(), dns.getSite());
		}
		if(logger.isDebugEnabled()){
			logger.debug("DNS put in cache complete!");
		}		
		return dnsList;
	}

	
	
	
	public EopSite get(Integer id) {
		
		return this.siteManager.get(id);
	}

	public void editDomain(String domainOld, String domainNew) {
		EopSite site  =siteManager.get(domainOld);
		siteManager.editDomain(domainOld, domainNew);
		this.cache.remove(domainOld);
		this.cache.put(domainNew,site);
	}
	
	/**
	 * 由dns缓存中找到site
	 */
	
	public EopSite get(String domain) {
		
		
		if(logger.isDebugEnabled()){
			logger.debug("parse domain["+domain+"]...");
		}
		
		EopSite  site  = cache.get(domain);
		if(site  == null){
			
			if(logger.isDebugEnabled()){
				logger.debug("domain["+domain+"] not in cache, then query from database");
			}
			
			site  =  this.siteManager.get(domain);
			
		}
		
		if(site==null){
			if(logger.isDebugEnabled()){
				logger.debug("domain["+domain+"] not exist");
			}			
			throw new EopException("域名"+domain+"不存在");
		}
		
		
		
		if(logger.isDebugEnabled()){
			logger.debug("finded and siteid is : "+ site.getId());
		}
		cache.put(domain, site);
		return site;
	}
	
	
	public Boolean checkInDomain(String domain){
		return this.siteManager.checkInDomain(domain);
	}
 

	public Page list(String keyword,int pageNo,int pageSize){
		Page page = this.siteManager.list(keyword,pageNo, pageSize);
		List<Map> listsite = (List<Map>) (page.getResult());
		
		List<Dns>  dnsList = this.getDnsList();
		for (Map site : listsite) {
			List<Dns> domainList = new ArrayList<Dns>();
//			String logofile  = site.get("logofile").toString();
//			if(logofile!=null ) logofile = logofile.replaceAll(EopSetting.FILE_STORE_PREFIX,EopSetting.IMG_SERVER_DOMAIN+"/user/"+ site.get("userid").toString() +"/"+site.get("id").toString());
//				site.put("logofile", logofile);
			for (Dns siteDomain : dnsList) {
				if (site.get("id").toString().equals(
						siteDomain.getSite().getId().toString())) {
					domainList.add(siteDomain);
				}
			}
			site.put("eopSiteDomainList", domainList);
		}
		return page;
	}
	
	public Page list(int pageNo, int pageSize, String order,
			String search) {
		return siteManager.list(pageNo, pageSize, order, search);
	}

	
	public void edit(EopSite eopSite) {
		siteManager.edit(eopSite);
		EopSite site = siteManager.get(eopSite.getId());
		refreshCache(site);
		this.siteManager.edit(eopSite);
	}

	
	private void refreshCache(EopSite site){
		List<EopSiteDomain > list = domainManager.listSiteDomain();
		for(EopSiteDomain siteDomain :list){
			this.cache.put( siteDomain.getDomain() ,site);
		}
	}
	
	public void updatePoint(Integer id,int point,int sitestate){
		this.siteManager.updatePoint(id, point,sitestate);
		
		//更新缓存中的point
		EopSite site  =this.get(id);
		List<EopSiteDomain> list= this.domainManager.listSiteDomain( id);
		if(list!=null && !list.isEmpty()){
			String domain  = list.get(0).getDomain();
			site  =this.get(domain);
			if(site!=null) {
				if(point ==-1)
					site.setPoint(point);
				else{
					site.setPoint(site.getPoint()+point);
				}
				
				site.setSitestate(sitestate);
			}
		}
		
	}	
		

	public int getPoint(Integer id, int point) {

		int result  =  this.siteManager.getPoint(id, point);
		
		if(result ==1){
			//更新缓存中的point
			EopSite site  =this.get(id);
			List<EopSiteDomain> list= this.domainManager.listSiteDomain( id);
			if(list!=null && !list.isEmpty()){
				String domain  = list.get(0).getDomain();
				site  =this.get(domain);
				if(site!=null) {
					if(point ==-1)
						site.setPoint(point);
					else{
						site.setPoint(site.getPoint()+point);
					}
				}
			}
		}
		return result ;
	}
	
	public List<EopApp> getSiteApps() {
		return this.siteManager.getSiteApps();
	}	
	
	
	public void setSiteProduct(Integer userid, Integer siteid, String productid) {
		this.siteManager.setSiteProduct(userid, siteid, productid);
	}

	
	public void changeAdminTheme(Integer themeid) {
		this.siteManager.changeAdminTheme(themeid);
	}

	
	public void changeTheme(Integer themeid) {
		this.siteManager.changeTheme(themeid);
	}

	
	public List listDomain(Integer userid, Integer siteid) {
		 
		return this.siteManager.listDomain(userid, siteid);
	}

	public IDomainManager getDomainManager() {
		return domainManager;
	}

	public void setDomainManager(IDomainManager domainManager) {
		this.domainManager = domainManager;
	}


	public void initData() {
		this.siteManager.initData();
	}


	public List list() {
		return this.siteManager.list();
	}


	public List list(Integer userid) {
		return this.siteManager.list(userid);
	}


	@Override
	public Integer add(EopUser user, EopSite site, String domain) {
		return this.siteManager.add(user, site, domain);
	}

}
