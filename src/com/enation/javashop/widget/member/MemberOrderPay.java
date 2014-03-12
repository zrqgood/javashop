package com.enation.javashop.widget.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;
import com.enation.javashop.widget.cart.checkout.CheckOutWidget;

/**
 * 会员订单支付
 * @author kingapex
 *2010-4-12下午02:42:37
 */
public class MemberOrderPay extends AbstractMemberWidget {
	 
	private IOrderManager orderManager;
	private  HttpServletRequest  request;
	private IPaymentManager paymentManager;
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		request  = ThreadContextHolder.getHttpRequest();
		Integer  orderId = this.getIntParam("orderId") ; 
		Order order  = orderManager.get(orderId);
		
		PayCfg payCfg = this.paymentManager.get(order.getPayment_id());
		this.putData("paypluginid", payCfg.getType());
		
		
		this.putData("order", order);
		this.freeMarkerPaser.setClz(CheckOutWidget.class);
		this.setPageName("orderPay");

	}
	
	
	

	
	public boolean getShowMenu() {
	 
		if(this.getCurrentMember() == null){
			return false;
		}else{
			return true;
		}
	}


	private Integer getIntParam(String name){
		try{
		 return Integer.valueOf( request.getParameter(name) );
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}

	
	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}


	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}


	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}
	

}
