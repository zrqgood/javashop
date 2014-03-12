package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.Cat;

/**
 * 商品类别管理
 * @author kingapex
 * 2010-1-6上午12:39:09
 */
public interface IGoodsCatManager {
	
	
 
	/**
	 * 检测类名是否存在
	 * @param name
	 * @return 存在返回真，不存在返回假
	 */
	public boolean checkname(String name,Integer catid);
	
	
	
	/**
	 * 根据类别id获取类别
	 * @param cat_id
	 * @return
	 */
	public Cat getById(int cat_id);
	
	
	
	
	/**
	 * 添加商品类别
	 * @param cat
	 */
	public void saveAdd(Cat cat);
	
	
	
	
	/**
	 * 更新商品类别
	 * @param cat
	 */
	public void update(Cat cat);
	
	
	
	
	
	/**
	 * 删除商品类别
	 * @param cat_id
	 * @return 1删除失败有子类别，0删除成功
	 */
	public int delete(int cat_id);
	
	
	
	
	
	
	/**
	 * 获取某个类别的所有子类，所有的子孙
	 * @param cat_id
	 * @return
	 */
	public List<Cat> listAllChildren(Integer cat_id);
	
	
	
	
	/**
	 * 读取某类别下的子类别，只是儿子
	 * @param cat_id
	 * @return
	 */
	public List listChildren(Integer cat_id);
	
	
	
	
	
	/**
	 * 保存排序
	 * @param cat_ids
	 * @param cat_sorts
	 */
	public void saveSort(int[] cat_ids, int[] cat_sorts) ;
	
	
	public List getNavpath(int cat_id);
	 
	
}
