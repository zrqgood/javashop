package com.enation.app.base.core.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.MultiSite;
import com.enation.app.base.core.service.IMultiSiteManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopSiteDomain;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.util.FileUtil;

public class MultiSiteManager extends BaseSupport<MultiSite> implements
		IMultiSiteManager {

	private IThemeManager themeManager;
	private ISiteManager siteManager;

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void open(String domain) {
		
		/**如果已开启，则不操作**/
		EopSite site  = EopContext.getContext().getCurrentSite();
		if(site.getMulti_site()==1) return ;
		
		
		/**更新此站点为开启多站点**/
		Integer siteid   = site.getId(); //主站点的站点id
		String  sql  ="update eop_site set multi_site=1 where id=?";
		this.daoSupport.execute(sql, siteid);
		site.setMulti_site(1); //更新缓存中的引用
		
		/**曾经开启过不添加子站数据**/
		sql  ="select count(0) from site";
		int count = this.baseDaoSupport.queryForInt(sql);
		if(count>0){
			sql="update site set domain=? where code=?";
			this.baseDaoSupport.execute(sql,domain,100000);
			return; 
		} 
		
		

		
		/**为站点树添加子站点**/
		MultiSite mainChildSite = new MultiSite();
		mainChildSite.setCode(100000); //站站代码100000
		mainChildSite.setLevel(1); //主站为第一级
		mainChildSite.setDomain(domain);
		mainChildSite.setName(site.getSitename());
		mainChildSite.setParentid(0);
		mainChildSite.setThemeid(site.getThemeid()); //设置主站的模板id为当前模板的id
		this.baseDaoSupport.insert("site", mainChildSite);
		
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void close() {
		EopSite site  = EopContext.getContext().getCurrentSite();
		Integer siteid   = site.getId(); //主站点的站点id
		String sql  ="update eop_site set multi_site=0 where id=?";
		this.daoSupport.execute(sql, siteid);
		
		site.setMulti_site(0); //更新缓存中的引用
	}

	private int createCode(int maxcode,int level){

		if(level==1){
			return maxcode+100000;
		}

		if(level==2){
			return maxcode+1000;
		}
		
		
		if(level==3){ 
			return maxcode+10;
		}		
		
		return 0;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(MultiSite site) {
		
		/**读取父**/
		MultiSite parent = this.get(site.getParentid());
		
		/**读取本级最大code**/
		String sql ="select max(code) code from site where parentid=? ";
		int maxcode =this.baseDaoSupport.queryForInt(sql, site.getParentid());//cat code
		maxcode=maxcode==0? maxcode = parent.getCode():maxcode;
		int level = parent.getLevel() +1;//级别
		site.setCode(this.createCode(maxcode, level)); 
		site.setLevel(level);
		
		this.baseDaoSupport.insert("site", site);
		int siteid = this.baseDaoSupport.getLastId("site");
		  
			Integer userid = EopContext.getContext().getCurrentSite().getUserid();
			EopSiteDomain eopSiteDomain = new EopSiteDomain();
			eopSiteDomain.setUserid(userid);
			eopSiteDomain.setDomain(site.getDomain());
			eopSiteDomain.setSiteid(EopContext.getContext().getCurrentSite()
					.getId());
			siteManager.addDomain(eopSiteDomain);
		try {
			/**
			 * 此处逻辑：先取出所指定的theme信息，取得其对应的文件目录，<BR/>
			 * 然后修改theme.path并插入theme，取得插入的themeid并写回到site
			 */
			site.setSiteid(siteid);
			Theme theme = this.themeManager.getTheme(site.getThemeid());
			String contextPath = EopContext.getContext().getContextPath();

			// 复制资源到静态资源服务器
			String basePath = EopSetting.IMG_SERVER_PATH + contextPath
					+ "/themes/" + theme.getPath();
			String targetPath = EopSetting.IMG_SERVER_PATH + contextPath
					+ "/themes/" + theme.getPath() + "_" + siteid;
			FileUtil.copyFolder(basePath, targetPath);
			// 复制theme
			basePath = EopSetting.EOP_PATH + contextPath + "/themes/"
					+ theme.getPath();
			targetPath = EopSetting.EOP_PATH + contextPath + "/themes/"
					+ theme.getPath() + "_" + siteid;
			FileUtil.copyFolder(basePath, targetPath);

			theme.setPath(theme.getPath() + "_" + siteid);
			theme.setSiteid(siteid);
			theme.setId(null);
			this.baseDaoSupport.insert("theme", theme);
			int themeid = this.baseDaoSupport.getLastId("theme");
			site.setThemeid(themeid);
			this.update(site);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建主题出错");
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int id) {
		MultiSite childsite  =this.get(id);
		List<Theme> list = themeManager.list(id); //读取此站点的主题
		String contextPath = EopContext.getContext().getContextPath();
		for (Theme theme : list) {
			/**删除模板静态资源文件**/
			String targetPath = EopSetting.IMG_SERVER_PATH + contextPath
					+ "/themes/" + theme.getPath() + "_" + id;
			FileUtil.removeFile(new File(targetPath));
			
			/**删除模板文件**/
			targetPath = EopSetting.EOP_PATH + contextPath
					+ "/themes/" + theme.getPath() + "_" + id;
			FileUtil.removeFile(new File(targetPath));			
			
		}
		
		/**删除域名*/
		this.siteManager.deleteDomain(childsite.getDomain());
		
		/**删除主题**/
		this.baseDaoSupport.execute("delete from theme where siteid = ?", id);
		
		/**删除站点*/
		this.baseDaoSupport.execute("delete from site where siteid = ?", id);
		
	}

	public List list() {
		
		List<Map> list  = this.baseDaoSupport.queryForList("select * from site ");
		List siteList  = new ArrayList();
		for (Map site : list) {
			Integer parentid = (Integer) site.get("parentid");
			if(parentid.intValue() ==0 ){
				this.putChildren(site, list);
				siteList.add(site);
			}
		}
		return siteList;
	}
	
	public void putChildren(Map site,List<Map> sitelist){
		List<Map> children  = new ArrayList<Map>();
		for(Map child:sitelist){
			Integer parentid = (Integer)child.get("parentid") ;
			Integer siteid = (Integer) site.get("siteid");
			if( parentid.compareTo(siteid)==0  ){
				this.putChildren(child, sitelist);
				children.add(child);
			}
		}
		site.put("children", children);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MultiSite site) {
		MultiSite site_old = this.get(site.getSiteid());
		String domain = site_old.getDomain();
		this.siteManager.editDomain(domain, site.getDomain());
		
		this.baseDaoSupport
				.update("site", site, "siteid = " + site.getSiteid());

	} 

	public MultiSite get(int id) {
		return this.baseDaoSupport.queryForObject(
				"select * from site where siteid = ?", MultiSite.class, id);
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public MultiSite get(String domain) {
		return this.baseDaoSupport.queryForObject(
				"select * from site where domain = ?", MultiSite.class, domain);
	}



}
