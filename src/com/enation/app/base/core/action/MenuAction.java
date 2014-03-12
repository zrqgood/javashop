package com.enation.app.base.core.action;

import java.util.List;

import com.enation.eop.resource.IMenuManager;
import com.enation.eop.resource.model.Menu;
import com.enation.framework.action.WWAction;
/**
 * 菜单管理
 * @author kingapex
 * 2010-8-20下午12:36:03
 */
public class MenuAction extends WWAction {
	private IMenuManager menuManager;
	private List<Menu> menuList;
	private Menu menu;
	private Integer parentid;
	private Integer id;
	private Integer[] menu_ids;
	private Integer[] menu_sorts;
	
	public String list(){
		menuList  = this.menuManager.getMenuTree(0);
		return "list";
	}
	
	public String add(){
		menuList  = this.menuManager.getMenuTree(0);
		return "add";
	}
	
	public String saveAdd(){
		try{
			this.menuManager.add(menu);
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
	public String edit(){
		menuList  = this.menuManager.getMenuTree(0);
		menu = this.menuManager.get(id);
		return "edit";
	}
 
	
	public String saveEdit(){
		try{
			this.menuManager.edit(menu);
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
 
	
	public String updateSort(){
		try{
			this.menuManager.updateSort(menu_ids, menu_sorts);
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String delete(){
		try{
			this.menuManager.delete(id);
			this.json="{result:1}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}		
		return this.JSON_MESSAGE;
	}

	public IMenuManager getMenuManager() {
		return menuManager;
	}

	public void setMenuManager(IMenuManager menuManager) {
		this.menuManager = menuManager;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Integer[] getMenu_ids() {
		return menu_ids;
	}

	public void setMenu_ids(Integer[] menuIds) {
		menu_ids = menuIds;
	}

	public Integer[] getMenu_sorts() {
		return menu_sorts;
	}

	public void setMenu_sorts(Integer[] menuSorts) {
		menu_sorts = menuSorts;
	}
	
	
}
