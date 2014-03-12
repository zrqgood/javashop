package com.enation.app.base.core.service;

import java.util.List;

import com.enation.app.base.core.model.SiteMenu;


/**
 * 站点菜单管理接口
 * @author kingapex
 * 2010-5-20下午02:54:53
 */
public interface ISiteMenuManager {

	/**
	 * 添加
	 * 
	 * @param siteMenu
	 */
	public void add(SiteMenu siteMenu);

	
	/**
	 * 读取菜单详细
	 * @param menuid
	 * @return
	 */
	public SiteMenu get(Integer menuid);
	
	
	
	/**
	 * 修改
	 * 
	 * @param siteMenu
	 */
	public void edit(SiteMenu siteMenu);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(Integer id);

	
	
	/**
	 * 读取子菜单列表，包括其所有子的
	 * 
	 * @param parentid
	 * @return
	 */
	public List<SiteMenu> list(Integer parentid);
	
	
	
	
	/**
	 * 更新排序
	 * @param menuid 菜单id数组
	 * @param sort	排序值数组
	 * menuid 和sort要一一对应
	 */
	public void updateSort(Integer[] menuid,Integer[] sort);	

}
