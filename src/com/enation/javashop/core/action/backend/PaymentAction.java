package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PaymentLog;
import com.enation.javashop.core.service.IOrderFlowManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;

/**
 * 退款\支付action
 * 
 * @author apexking
 * 
 */
public class PaymentAction extends WWAction {

	private PaymentLog payment;
	private Integer orderId;
	private IOrderManager orderManager;
	private IOrderFlowManager orderFlowManager;
	private IPaymentManager paymentManager;
	private Order ord;
	private List paymentList;
	
	/**
	 * 显示支付对话框
	 * @return
	 */
	public String showPayDialog(){
		this.ord = this.orderManager.get(orderId);
		this.paymentList  = this.paymentManager.list();
		return "pay_dialog";
	}
	
	/**
	 * 显示退款对话框
	 * @return
	 */
	public String showRefundDialog(){
		this.ord = this.orderManager.get(orderId);
		this.paymentList  = this.paymentManager.list();
		return "refund_dialog";
	}
	
	
	// 支付
	public String pay() {
		try{
			payment.setOrder_id(orderId);
			orderFlowManager.pay(payment,false);
			Order order = this.orderManager.get(orderId);
			this.json="{result:1,message:'订单["+order.getSn()+"]支付成功',payStatus:"+order.getPay_status()+"}";
		}catch(RuntimeException e){
			if(logger.isDebugEnabled()){
				logger.debug(e);
			}
			this.json="{result:0,message:\"支付失败："+e.getMessage()+"\"}";
		}
		return this.JSON_MESSAGE;
	}

	// 退款
	public String cancel_pay() {
		try{
			payment.setOrder_id(orderId);
			orderFlowManager.refund(payment);
			Order order = this.orderManager.get(orderId);
			this.json="{result:1,message:'订单["+order.getSn()+"]退款成功',payStatus:"+order.getPay_status()+"}";
		}catch(RuntimeException e){
			if(logger.isDebugEnabled()){
				logger.debug(e);
			}
			this.json="{result:0,message:\"退款失败："+e.getMessage()+"\"}";
		}
		return this.JSON_MESSAGE;	 
	}

	public PaymentLog getPayment() {
		return payment;
	}

	public void setPayment(PaymentLog payment) {
		this.payment = payment;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public IOrderFlowManager getOrderFlowManager() {
		return orderFlowManager;
	}

	public void setOrderFlowManager(IOrderFlowManager orderFlowManager) {
		this.orderFlowManager = orderFlowManager;
	}

	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	public Order getOrd() {
		return ord;
	}

	public void setOrd(Order ord) {
		this.ord = ord;
	}

	public List getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List paymentList) {
		this.paymentList = paymentList;
	}


}
