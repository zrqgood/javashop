package com.enation.eop.resource.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.resource.IMenuManager;
import com.enation.eop.resource.model.Menu;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;

/**
 * 菜单管理
 * @author kingapex
 *2010-5-10下午02:00:10
 */
public class MenuManagerImpl extends BaseSupport<Menu> implements IMenuManager {

	public void clean() {
		this.baseDaoSupport.execute("truncate table menu");
	}
	public List<Menu> getMenuList() {
		return this.baseDaoSupport.queryForList("select * from menu where deleteflag = '0' order by sorder asc", Menu.class);
	}

	
	public Integer add(Menu menu) {
		if(menu.getTitle()==null) throw new IllegalArgumentException("title argument is null");
		if(menu.getPid()==null) throw new IllegalArgumentException("pid argument is null");
		if(menu.getUrl() ==null) throw new IllegalArgumentException("url argument is null");
		if(menu.getSorder() ==null) throw new IllegalArgumentException("sorder argument is null");
		menu.setDeleteflag(0);
		this.baseDaoSupport.insert("menu", menu);
		return this.baseDaoSupport.getLastId("menu");
	}


	public List<Menu> getMenuTree(Integer menuid) {
		if(menuid==null)throw new IllegalArgumentException("menuid argument is null");
		List<Menu> menuList  = this.getMenuList();
		List<Menu> topMenuList  = new ArrayList<Menu>();
		for(Menu menu :menuList){
			if(menu.getPid().compareTo(menuid)==0){
				List<Menu> children = this.getChildren(menuList, menu.getId());
				menu.setChildren(children);
				topMenuList.add(menu);
			}
		}
		return topMenuList;
	}

	/**
	 * 在一个集合中查找子
	 * @param menuList 所有菜单集合
	 * @param parentid 父id
	 * @return 找到的子集合
	 */
	private List<Menu> getChildren(List<Menu> menuList ,Integer parentid){
		List<Menu> children =new ArrayList<Menu>();
		for(Menu menu :menuList){
			if(menu.getPid().compareTo(parentid)==0){
			 	menu.setChildren(this.getChildren(menuList, menu.getId()));
				children.add(menu);
			}
		}
		return children;
	}


	public Menu get(Integer id) {
		if(id==null)throw new IllegalArgumentException("ids argument is null");
		String sql ="select * from menu where id=?";
		return this.baseDaoSupport.queryForObject(sql, Menu.class, id);
	}


	public void edit(Menu menu) {
		if(menu.getId()==null) throw new IllegalArgumentException("id argument is null");
		if(menu.getTitle()==null) throw new IllegalArgumentException("title argument is null");
		if(menu.getPid()==null) throw new IllegalArgumentException("pid argument is null");
		if(menu.getUrl() ==null) throw new IllegalArgumentException("url argument is null");
		if(menu.getSorder() ==null) throw new IllegalArgumentException("sorder argument is null");
		menu.setDeleteflag(0);
		this.baseDaoSupport.update("menu", menu, "id="+ menu.getId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSort(Integer[] ids, Integer[] sorts) {
		if(ids==null)throw new IllegalArgumentException("ids argument is null");
		if(sorts==null)throw new IllegalArgumentException("sorts argument is null");
		if(sorts.length !=ids.length) throw new IllegalArgumentException("ids's length and sorts's length not same");
		for(int i=0;i<ids.length;i++){
			String sql ="update menu set sorder=? where id=?";
			this.baseDaoSupport.execute(sql, sorts[i],ids[i]);
		}
	}


	public void delete(Integer id) throws RuntimeException {
		if(id==null)throw new IllegalArgumentException("ids argument is null");
		String sql  ="select count(0) from menu where pid=?";
		int count  = this.baseDaoSupport.queryForInt(sql, id);
		if(count>0) throw new RuntimeException("菜单"+ id +"存在子类别,不能直接删除，请先删除其子类别。");
		sql ="delete from menu where id=?";
		this.baseDaoSupport.execute(sql, id);
	}
	
}
