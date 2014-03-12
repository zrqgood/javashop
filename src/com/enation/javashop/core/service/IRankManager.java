package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

/**
 * 排名
 * 
 * @author lzf<br/>
 *         2010-3-10 上午10:41:24<br/>
 *         version 1.0<br/>
 */
public interface IRankManager {

	/**
	 * 取得销售量（额）排名
	 * 
	 * @param page
	 * @param pageSize
	 * @param condition
	 * @param sort
	 * @return
	 */
	public List rank_goods(int page, int pageSize, String condition, String sort);

	/**
	 * 取得会员购物量（额）排名
	 * 
	 * @param page
	 * @param pageSize
	 * @param condition
	 * @param sort
	 * @return
	 */
	public List rank_member(int page, int pageSize, String condition,
			String sort);

	/**
	 * 商品访问/购买次数<br/>
	 * 因为其访问次数并未详细记录明细，故不做时间段查询
	 * 
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	public List rank_buy(int page, int pageSize, String sort);
	
	/**
	 * 销售指标分析
	 * @return
	 */
	public Map rank_all();

}
