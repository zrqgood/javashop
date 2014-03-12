package com.enation.app.base.core.action;

import java.util.List;

import net.sf.json.JSONArray;

import com.enation.app.base.core.model.MultiSite;
import com.enation.app.base.core.service.IMultiSiteManager;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.action.WWAction;

/**
 * 多站点管理
 * @author lzf<br/>
 * 2010-12-23 下午04:01:17<br/>
 * version 2.1.5
 */
public class MultiSiteAction extends WWAction {

	private IMultiSiteManager multiSiteManager;
	private EopSite eopSite; 
	private MultiSite site;
	private String parentname;
	private int siteid;
	private int multi_site;
	private IThemeManager themeManager;
	private List<Theme> themeList;
	private String previewBasePath;
	private String domain;
	 
	
	private List<MultiSite> siteList;
	
	
	public String execute(){
		//当前主站点的设置 
		eopSite  = EopContext.getContext().getCurrentSite();
		//读取所有子站
		siteList = multiSiteManager.list();
		return "main";
	}
	
	/**
	 * 保存多站点功能设置
	 * @return
	 */
	public String save(){
		try{
			if(multi_site==1){
				this.multiSiteManager.open(domain);
				this.json ="{result:1,message:'多站点功能开启成功'}";
			}else{
				this.multiSiteManager.close();
				this.json ="{result:1,message:'多站点功能关闭成功'}";
			}
			
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(),e.fillInStackTrace());
			this.json ="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
	
	/**
	 * 站点树json action 
	 * @return
	 */
	public String listJson(){
		try{
			JSONArray jsonArray1 = JSONArray.fromObject(multiSiteManager.list());
			this.json = "{'result':'0','data':"+jsonArray1.toString()+"}";
		}catch(Exception e){
			this.json = "{'result':'1'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String list(){
		siteList = multiSiteManager.list();
		return "list";
	}
	
	public String add(){
		themeList = this.themeManager.list(0); //获取主站模板列表
		String contextPath = EopContext.getContext().getContextPath();
		previewBasePath = EopSetting.IMG_SERVER_DOMAIN +contextPath+ "/themes/";
		return "add";
	}
	
	public String edit(){
		site = multiSiteManager.get(siteid);
		if( site.getParentid() !=0 )
			parentname = multiSiteManager.get(site.getParentid()).getName();
		else
			parentname="顶级站点";
		return "edit";
	}
	
	public String addSave(){
		try{
			this.multiSiteManager.add(site);
			this.msgs.add("新增子站点成功");
			
		}catch(RuntimeException e){
			this.msgs.add("操作失败："+e.getMessage());
		}
		this.urls.put("多站点管理", "multiSite.do");
		return this.MESSAGE;
	}
	
	public String editSave(){
		this.multiSiteManager.update(site);
		this.msgs.add("编辑子站点成功");
		this.urls.put("多站点管理", "multiSite.do");
		return this.MESSAGE;
	}
	
	public String delete(){
		try{
			this.multiSiteManager.delete(siteid);
			this.msgs.add("站点成功删除");
		
		}catch(Exception e){
			this.msgs.add("站点删除失败");
		}
		this.urls.put("多站点管理", "multiSite.do");
		return this.MESSAGE;
	}

	public IMultiSiteManager getMultiSiteManager() {
		return multiSiteManager;
	}

	public void setMultiSiteManager(IMultiSiteManager multiSiteManager) {
		this.multiSiteManager = multiSiteManager;
	}

	public MultiSite getSite() {
		return site;
	}

	public void setSite(MultiSite site) {
		this.site = site;
	}

	public int getSiteid() {
		return siteid;
	}

	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public List<Theme> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}

	public String getPreviewBasePath() {
		return previewBasePath;
	}

	public void setPreviewBasePath(String previewBasePath) {
		this.previewBasePath = previewBasePath;
	}

	public List<MultiSite> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<MultiSite> siteList) {
		this.siteList = siteList;
	}

	public EopSite getEopSite() {
		return eopSite;
	}

	public void setEopSite(EopSite eopSite) {
		this.eopSite = eopSite;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getMulti_site() {
		return multi_site;
	}

	public void setMulti_site(int multiSite) {
		multi_site = multiSite;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

}
