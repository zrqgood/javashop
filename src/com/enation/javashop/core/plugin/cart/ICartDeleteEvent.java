package com.enation.javashop.core.plugin.cart;

/**
 * 购物车删除事件
 * @author kingapex
 *
 */
public interface ICartDeleteEvent {
	/**
	 * 购物车删除事件定义
	 * @param sessionid
	 * @param cartid
	 */
	public void delete(String sessionid,Integer cartid);
}
