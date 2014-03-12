package com.enation.javashop.core.plugin.cart;

import com.enation.javashop.core.model.Cart;

/**
 * 购物车添加事件
 * @author kingapex
 *
 */
public interface ICartAddEvent {
	public void add(Cart cart);
}
