package com.enation.javashop.core.plugin.cart;

import java.util.List;

import com.enation.javashop.core.model.support.CartItem;

/**
 * 购物车项过滤器
 * @author kingapex
 *
 */
public interface ICartItemFilter {
	
	public void filter(List<CartItem>  itemlist,String sessionid);
}
