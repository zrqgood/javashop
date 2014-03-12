package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Brand;

/**
 * 品牌管理
 * @author kingapex
 * 2010-1-6上午12:39:09
 */
public interface IBrandManager {
	
	/**
	 * 检测品牌是否已经被商品使用
	 * @param ids id列表
	 * @return 已经被使用返回真，否则返回假
	 */
	public boolean checkUsed(String ids);
	
	
	
	/**
	 *  检测品牌名称是否存在
	 * @param name 品牌名称
	 * @param brandid 要排除的brandid,用于修改时的检测
	 * @return 存在返回true 不存在返回false
	 */
	public boolean checkname(String name,Integer brandid);
	
	public  void add(Brand brand);
	
	public void update(Brand brand);
	/**
	 * 分页读取品牌
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page list(String order,int page,int pageSize);
	
	/**
	 * 分页读取回收站列表
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page listTrash(String order,int page,int pageSize);
	
	
	/**
	 * 将回收站中的品牌还原
	 * @param bid
	 */
	public void revert(String bid);
	
	
	/**
	 * 将品牌放入回收站
	 * @param bid
	 */
	public void delete(String bid);
	
	/**
	 * 品牌删除,真正的删除。
	 * @param bid
	 * @param files
	 */
	public void clean(String bid);
	
	
	
	
	/**
	 * 读取所有品牌
	 * @return
	 */
	public List<Brand> list() ;
	
	
	/**
	 * 读取某个类型的所有品牌列表
	 * @param typeid
	 * @return
	 */
	public List<Brand> listByTypeId(Integer typeid);
	
	
	/**
	 * 读取某类别下的品牌列搁
	 * @param catid
	 * @return
	 */
	public List listByCatId(Integer catid);
	
	/**
	 * 按商品分类的第一级进行分组显示<br/>
	 * lzf add<br/>
	 * 20101210
	 * @return
	 */
	public List groupByCat();
	
	
	/**
	 * 读取品牌详细
	 * @param brand_id
	 * @return
	 */
	public Brand get(Integer brand_id);

	
	/**
	 * 分页读取某个品牌下的商品
	 * @param brand_id
	 * @return
	 */
	public Page getGoods(Integer brand_id,int pageNo,int pageSize);
}
