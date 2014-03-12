package com.enation.javashop.core.plugin.order;

import com.enation.javashop.core.model.Order;



/**
 * 订单创建事件
 * @author kingapex
 *
 */
public interface IOrderCreateEvent {
	
	
	public void onOrderCreate(Order order ,String sessionid);
	
	
}
