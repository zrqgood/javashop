package com.enation.app.base.core.action;

import java.util.List;

import com.enation.app.base.core.model.SiteMenu;
import com.enation.app.base.core.service.ISiteMenuManager;
import com.enation.framework.action.WWAction;


/**
 * 站点菜单
 * @author kingapex
 *
 */
public class SiteMenuAction extends WWAction {
	private ISiteMenuManager siteMenuManager ;
	private List menuList;
	private Integer[] sortArray;
	private Integer[] menuidArray;
	private Integer menuid;
	private SiteMenu siteMenu;
	private boolean isEdit;
	
	
	public String list(){
		menuList  = siteMenuManager.list(0);
		return "list";
	}
	
	public String updateSort(){
		siteMenuManager.updateSort(menuidArray, sortArray);
		return this.list();
	}
	
	
	public String add(){
		isEdit =false;
		this.menuList = this.siteMenuManager.list(0);
		siteMenu= new SiteMenu();
		return this.INPUT;
	}
	
	public String edit(){
		isEdit=true;
		this.menuList = this.siteMenuManager.list(0);
		siteMenu  =siteMenuManager.get(menuid);
		return this.INPUT;
	}
	
	public String save(){
		if(menuid==null){
			this.siteMenuManager.add(siteMenu);
			this.msgs.add("菜单添加成功");
		}else{
			siteMenu.setMenuid(menuid);
			this.siteMenuManager.edit(siteMenu);
			this.msgs.add("菜单修改成功");
		}
		
		this.urls.put("菜单列表", "siteMenu!list.do");
		return this.MESSAGE;
	}
	
	public String delete(){
		
		this.siteMenuManager.delete(menuid);
		this.msgs.add("菜单删除成功");
		this.urls.put("菜单列表", "siteMenu!list.do");
		return this.MESSAGE;
	}
	
	public ISiteMenuManager getSiteMenuManager() {
		return siteMenuManager;
	}
	public void setSiteMenuManager(ISiteMenuManager siteMenuManager) {
		this.siteMenuManager = siteMenuManager;
	}
	public List getMenuList() {
		return menuList;
	}
	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public Integer[] getSortArray() {
		return sortArray;
	}

	public void setSortArray(Integer[] sortArray) {
		this.sortArray = sortArray;
	}

	public Integer[] getMenuidArray() {
		return menuidArray;
	}

	public void setMenuidArray(Integer[] menuidArray) {
		this.menuidArray = menuidArray;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public SiteMenu getSiteMenu() {
		return siteMenu;
	}

	public void setSiteMenu(SiteMenu siteMenu) {
		this.siteMenu = siteMenu;
	}

	public boolean getIsEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	
}
