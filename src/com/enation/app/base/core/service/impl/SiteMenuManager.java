package com.enation.app.base.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.SiteMenu;
import com.enation.app.base.core.service.ISiteMenuManager;
import com.enation.eop.sdk.database.BaseSupport;

public class SiteMenuManager extends BaseSupport<SiteMenu> implements ISiteMenuManager {

	
	public void add(SiteMenu siteMenu) {
		this.baseDaoSupport.insert("site_menu", siteMenu);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	
	public void delete(Integer id) {
		String sql  ="delete from site_menu where parentid =?";
		this.baseDaoSupport.execute(sql,  id );
		sql = "delete from  site_menu   where menuid=?";
		this.baseDaoSupport.execute(sql,  id );
	}
	
	public void edit(SiteMenu siteMenu) {
		this.baseDaoSupport.update("site_menu", siteMenu,"menuid="+siteMenu.getMenuid());
	}

	public List<SiteMenu> list(Integer parentid) {
		String sql  ="select * from site_menu order by parentid,sort";
		List<SiteMenu> menuList  = this.baseDaoSupport.queryForList(sql, SiteMenu.class);
		List<SiteMenu> topMenuList  = new ArrayList<SiteMenu>();
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找"+parentid+"的子...");
		}
		for(SiteMenu menu :menuList){
			if(menu.getParentid().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug("发现子["+menu.getName()+"-"+menu.getMenuid()+"]");
				}
				List<SiteMenu> children = this.getChildren(menuList, menu.getMenuid());
				menu.setChildren(children);
				topMenuList.add(menu);
			}
		}
		
		return topMenuList;
	}
	
	private List<SiteMenu> getChildren(List<SiteMenu> menuList ,Integer parentid){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找["+parentid+"]的子");
		}
		List<SiteMenu> children =new ArrayList<SiteMenu>();
		for(SiteMenu menu :menuList){
			if(menu.getParentid().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug(menu.getName()+"-"+menu.getMenuid()+"是子");
				}
			 	menu.setChildren(this.getChildren(menuList, menu.getMenuid()));
				children.add(menu);
			}
		}
		return children;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public void updateSort(Integer[] menuid, Integer[] sort) {
		
		if(menuid==null || sort == null )  throw new  IllegalArgumentException("menuid or sort is NULL");
		if(menuid.length != sort.length )  throw new  IllegalArgumentException("menuid or sort length not same");
		for(int i=0;i<menuid.length;i++){
			this.baseDaoSupport.execute("update site_menu set sort=? where menuid=?",sort[i],menuid[i]);
		}
		
	}
	
	public SiteMenu get(Integer menuid) {	 
		return this.baseDaoSupport.queryForObject("select * from site_menu where menuid=?", SiteMenu.class, menuid);
	}

}
