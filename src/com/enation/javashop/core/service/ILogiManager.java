package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Logi;

public interface ILogiManager {
	/**
	 * 添加物流公司
	 * @param name
	 */
	public void saveAdd(String name);
	
	/**
	 * 编辑物流公司
	 * @param id
	 * @param name
	 */
	public void saveEdit(Integer id,String name);
	
	/**
	 * 分页读取物流公司
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageLogi(String order ,Integer page,Integer pageSize);
	
	/**
	 * 读取所有物流公司列表
	 * @return
	 */
	public List list();
	
	
	/**
	 * 删除物流公司
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 获取物流公司
	 * @param id
	 * @return
	 */
	public Logi getLogiById(Integer id);
	
}
