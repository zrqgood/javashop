package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.DlyArea;

public interface IAreaManager {
	/**
	 * 添加配送地区
	 * @param name
	 */
	public void saveAdd(String name);
	/**
	 * 编辑配送地区
	 * @param area_id
	 * @param name
	 */
	public void saveEdit(Integer area_id,String name);
	/**
	 * 读取所有配送地区
	 * @return
	 */
	public List getAll();
	
	/**
	 * 分页读取配送地区
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageArea(String order,int page,int pageSize);
    /**
     * 删除配送地区
     * @param id
     */
	public void delete(String id);
	/**
	 * 获取一个配送地区
	 * @param area_id
	 * @return
	 */
	public DlyArea getDlyAreaById(Integer area_id);

}
