package com.enation.javashop.core.service;

/**
 * 订单状态
 * 
 * @author apexking
 * 
 */
public interface OrderStatus {

	//订单状态
	
	public static final int ORDER_CHANGED=-7;//已换货
	
	public static final int ORDER_CHANGE_REFUSE=-6;//换货被拒绝
	public static final int ORDER_RETURN_REFUSE=-5;//退货被拒绝
	
	public static final int ORDER_CHANGE_APPLY=-4;//申请换货	
	public static final int ORDER_RETURN_APPLY=-3; // 申请退货
	
	public static final int ORDER_CANCEL_SHIP = -2; // 退货

	public static final int ORDER_CANCEL_PAY = -1; // 退款

	public static final int ORDER_NOT_PAY = 0; // 未付款

	public static final int ORDER_PAY = 1; // 已支付

	public static final int ORDER_SHIP = 2; // 已发货

	public static final int ORDER_COMPLETE = 3; // 已完成

	public static final int ORDER_CANCELLATION = 4; // 作废

	//付款状态
	public static final int PAY_NO= 0;  
	public static final int PAY_YES= 1;
	public static final int PAY_CANCEL =2; //已经退款
	public static final int PAY_PARTIAL_REFUND = 3; //部分退款
	public static final int PAY_PARTIAL_PAYED =4 ;//部分付款
	
	//货运状态
	public static final int SHIP_NO= 0;  //	0未发货
	public static final int SHIP_YES= 1;//	1已发货
	public static final int SHIP_CANCEL= 2;//	2.已退货
	public static final int SHIP_PARTIAL_SHIPED= 4; //	4 部分发货
	public static final int SHIP_PARTIAL_CANCEL= 3;// 3 部分退货
	public static final int SHIP_PARTIAL_CHANGE= 5;// 5部分换货	
	public static final int SHIP_CHANED= 6;// 6已换货	

	
	
	
}
