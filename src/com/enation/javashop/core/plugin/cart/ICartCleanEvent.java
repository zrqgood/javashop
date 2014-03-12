package com.enation.javashop.core.plugin.cart;

/**
 * session失效时购物车的清空事件
 * @author kingapex
 *
 */
public interface ICartCleanEvent {
	
	/**
	 * 购物车清空事件
	 * @param sessionid
	 * @param userid
	 * @param siteid
	 */
	public void clean(String sessionid, Integer userid, Integer siteid);
	
	
}
