package com.enation.javashop.core.plugin.order;

/**
 * 订单删除事件
 * @author kingapex
 *
 */
public interface IOrderDeleteEvent {
	public void delete(Integer[] orderId);
}
