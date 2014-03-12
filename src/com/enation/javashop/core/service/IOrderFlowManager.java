package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.ObjectNotFoundException;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;
import com.enation.javashop.core.model.OrderItem;
import com.enation.javashop.core.model.PaymentLog;

/**
 * 订单流程管理<br>
 * 负责订单后台管理中的：支付、退款、发货、退货、完成、作废操作<br>
 * 总体关系为：
 * <li>
 * 未支付不可以退款，支付后才能退款<br>
 * 未支付可以支付，支付完成后可以再支付
 * </li>
 * 
 * <li>
 * 未发货不可以退货，发货后可以退货<br>
 * 未发货可以发货，发货完成后不可以再发货
 * </li>
 * 
 *<li>
 * 订单标记为完成状态后不可以进行其它操作<br>
 * 订单标记为作废状态后不可以进行其它操作
 * </li>
 * @author kingapex
 * 2010-4-8上午09:07:00
 * @see com.enation.test.shop.order.OrderFlowTest
 */
public interface IOrderFlowManager {
	
	/**
	 * 为某订单付款<br/>
	 * 对订单状态有如下影响：<br>
	 * 		<li>如果全额付款订单状态为<b>已付款</b>，付款状态为已付款</li>
	 * 		<li>如果部分付款订单状态为<b>已付款</b>，付款状态为部分已付款</li>
	 * 如果订单为非匿名购买且支付方式(paymentLog.pay_method)为“预存款支付”则影响到会员预存款：<br>
	 * 订单预存款为当前预存款<b>减</b>去支付费用
	 * @param paymentLog 付款日志对象<br/>
	 * @throws IllegalArgumentException 下列情形之一抛出此异常:
	 * <li>paymentLog为null</li> 
	 * <li>order_id(订单id)为null</li> 
	 * <li>money(付款金额)为null</li> 
	 * @throws ObjectNotFoundException 如果要支付的订单不存在
	 * @throws IllegalStateException 如果订单支付状态为已支付
	 * @throws RuntimeException  当使用预存款支付，且用户预存款不够支付金额时
	 * @see com.enation.javashop.service.support.OrderStatus
	 */
	public void pay(PaymentLog paymentLog,boolean isOnline);
	
	
	
	
	/**
	 * 为某订单退款<br>
	 * 对订单状态有如下影响：<br>
	 * 		<li>如果全额退款订单状态为<b>已退款</b>，付款状态为<b>已退款</b></li>
	 * 		<li>如果部分退款订单状态为<b>已退款</b>，付款状态为<b>部分退付款</b></li>
	 * 如果订单为非匿名购买且支付方式(paymentLog.pay_method)为“预存款支付”则影响到会员预存款：<br>
	 * 订单预存款为当前预存款<b>加</b>支付费用
	 * @param paymentLog 退款日志对象
	 * @throws IllegalArgumentException 下列情形之一抛出此异常:
	 * <li>paymentLog为null</li> 
	 *  <li>order_id(订单id)为null</li> 
	 * <li>money(退款金额)为null</li>  
	 * @throws ObjectNotFoundException 如果要退款的订单不存在
	 * @throws IllegalStateException 如果订单支付状态为已退款
	 * @throws RuntimeException 当退款金额超过订单支付金额时
	 * @see com.enation.javashop.service.support.OrderStatus
	 */
	public void refund(PaymentLog paymentLog);
	
	
	
	/**
	 * 发货
	 * @param delivery 货运单对象</br>
	 * 如果不指定物流费用和保价费用，则其默认值为0
	 * @param itemList 本次发货的明细
	 * @throws ObjectNotFoundException 如果要发货的订单不存在
	 * @throws IllegalStateException 如果订单发货状态为已发货
	 * @throws IllegalArgumentException 下列情形之一抛出此异常:<br>
	 * <li>delivery为null</li> 
	 *  <li>delivery 对象的 order_id(订单id)为null</li> 
	 * <li>itemList为null 或为空</li> 
	 * <li>发货明细列表中的DeliveryItem对象下列属情有一个为空则抛出异常
	 *  goods_id, product_id、num
	 *  </li>
	 * @see com.enation.javashop.service.support.OrderStatus
	 */
	public void shipping(Delivery delivery ,List<DeliveryItem> itemList,List<DeliveryItem> giftItemList );
	
	
	
	/**
	 * 申请退换货
	 * @param orderid
	 * @param state 0为退货 1为换货 2为返修
	 * @param specids
	 */
	public void applyReturns(Integer orderid,int state,Integer[] specids);
	
	/**
	 * 拒绝退货或换货
	 */
	public void refuseRorC(Integer orderid);
	
	
	/**
	 * 退货
	 * @param delivery 货运单对象</br>type属性要指定为1(发货)
	 * @param itemList 本次发货的明细
	 * @throws ObjectNotFoundException 如果要发货的订单不存在
	 * @throws IllegalStateException 如果订单支付状态为已退货
	 * @throws IllegalArgumentException 下列情形之一抛出此异常:
	 * <li>delivery为null</li> 
	 *  <li>delivery 对象的 order_id(订单id)为null</li> 
	 * <li>itemList为null 或为空</li> 
	 * <li>发货明细列表中的DeliveryItem对象下列属情有一个为空则抛出异常
	 *  goods_id, product_id、num
	 *  </li>
	 *  @see  com.enation.javashop.service.support.OrderStatus
	 */
	public void returned(Delivery delivery,List<DeliveryItem> itemList,List<DeliveryItem> gifitemList);
	
	
	
	/**
	 * 换货
	 * @param delivery
	 * @param itemList
	 * @param gifitemList
	 */
	public void change(Delivery delivery,List<DeliveryItem> itemList,List<DeliveryItem> gifitemList);	
	
	
	
	/**
	 * 完成订单:
	 * 标记一个订单为完成状态
	 * @param  orderId 订单id
	 * @throws IllegalArgumentException 当orderId为null时
	 */
	public void complete(Integer orderId);
	
	
	
	
	
	/**
	 * 作废订单 
	 * 标记一个订单为作废状态
	 * @param  orderId 订单id
	 * @throws IllegalArgumentException 当orderId为null时
	 */
	public void cancel(Integer orderId);
	
	
	/**
	 * 读取某订单未发货的货物(商品)列表
	 * @param orderId 订单id
	 * @return 此订单未发货的货物(商品)列表
	 */
	public List<OrderItem> listNotShipGoodsItem(Integer orderId);
	
	
	/**
	 * 读取此订单未发货的赠品列表
	 * @param orderId 订单id
	 * @return 此订单未发货的赠品列表
	 */
	public List<Map> listNotShipGiftItem(Integer orderId);
	
	
	/**
	 * 读取某订单发货的商品明细列表
	 * @param orderId 订单id
	 * @return 此订单发货商品货物的列表
	 */
	public List<OrderItem> listShipGoodsItem(Integer orderId);	
	
	/**
	 * 读取某订单发货的赠品明细列表
	 * @param orderId 订单id
	 * @return 此订单发货赠品货物的列表
	 */
	public List<Map> listShipGiftItem(Integer orderId);	
	
	
}
