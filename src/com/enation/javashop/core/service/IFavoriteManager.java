package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;

/**
 * 商品收藏管理
 * 
 * @author lzf<br/>
 *         2010-3-24 下午02:39:25<br/>
 *         version 1.0<br/>
 */
public interface IFavoriteManager {

	/**
	 * 列表我的收藏
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(int pageNo, int pageSize);
	
	
	
	/**
	 * 读取会员的所有收藏商品
	 * @return
	 */
	public List list();

	/**
	 * 删除收藏
	 * 
	 * @param favorite_id
	 */
	public void delete(int favorite_id);
	
	
	
	/**
	 * 添加一个收藏
	 * @param goodsid
	 * @param memberid
	 */
	public void add(Integer goodsid);
	
	
}
