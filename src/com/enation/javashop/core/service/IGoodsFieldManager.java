package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.plugin.field.GoodsField;


/**
 * 商品字段管理
 * @author kingapex
 *
 */
public interface IGoodsFieldManager {
	
	
	/**
	 * 获取某个商品类型的商品字段
	 * @param catid
	 * @return
	 */
	public List<GoodsField> list(Integer typeid);
	
	
	
	/**
	 * 添加某字段
	 * @param dataField
	 * @return
	 */
	public Integer add(GoodsField goodsField );
	
	
	
	
	
	/**
	 * 修改某字段信息
	 * @param dataField
	 */
	public void edit(GoodsField goodsField );
	
 
	/**
	 * 获取某字段的字段详细
	 * @param fieldid
	 * @return
	 */
	public GoodsField  get(Integer fieldid);	
	
	
	
	/**
	 * 删除某个字段
	 * @param modelid
	 */
	public void delete(Integer fieldid);
	
	
	/**
	 *批量刪除某个
	 * @param typeids
	 */
	public void delete(String typeids);
	
}
