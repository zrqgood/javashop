package com.enation.javashop.core.plugin.cart;

import com.enation.javashop.core.model.support.OrderPrice;

/**
 * 计算价格事件
 * @author kingapex
 *
 */
public interface ICountPriceEvent {
	
	
	public OrderPrice countPrice(OrderPrice orderprice);
	
}
