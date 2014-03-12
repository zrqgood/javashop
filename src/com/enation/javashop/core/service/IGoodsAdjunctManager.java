package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.GoodsAdjunct;

/**
 * 商品配件
 * 
 * @author lzf<br/>
 *         2010-3-30 下午05:39:37<br/>
 *         version 1.0<br/>
 */
public interface IGoodsAdjunctManager {

	/**
	 * 商品配件组列表
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<Map> list(int goods_id);

	/**
	 * 更新商品配件
	 * 
	 * @param goods_id
	 * @param list
	 */
	public void save(int goods_id, List<GoodsAdjunct> list);
}
