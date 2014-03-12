package com.enation.javashop.core.plugin.order;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.javashop.core.model.AllocationItem;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;

/**
 * 订单发退货事件
 * @author kingapex
 *
 */
public interface IOrderShipEvent {
	
	/**
	 * 订单某个货物项发货事件
	 * @param deliveryItem
	 * @param allocationItem
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void itemShip(DeliveryItem deliveryItem,AllocationItem allocationItem);
	
	
	/**
	 * 订单发货事件
	 * @param delivery
	 * @param itemList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void ship(Delivery delivery ,List<DeliveryItem> itemList);
	
	
	/**
	 * 订单退货事件
	 * @param delivery
	 * @param itemList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void returned(Delivery delivery ,List<DeliveryItem> itemList);
	
	
	/**
	 * 定义事件是否被执行 
	 * @param catid 商品分类id
	 * @return
	 */
	public boolean canBeExecute(int catid);
}
