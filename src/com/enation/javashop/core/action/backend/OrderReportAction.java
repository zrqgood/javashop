package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;
import com.enation.javashop.core.model.PaymentLog;
import com.enation.javashop.core.service.IOrderReportManager;

/**
 * @author lzf<br/>
 * 2010-4-12下午12:17:49<br/>
 * version 1.0
 */
public class OrderReportAction extends WWAction {
	
	private IOrderReportManager orderReportManager;
	private String order;
	private int id;
	private PaymentLog payment;
	private Delivery delivery;
	private List<DeliveryItem> listDeliveryItem;
	
	public String paymentList(){
		this.webpage = orderReportManager.listPayment(this.getPage(), this.getPageSize(), order);
		return "paymentList";
	}
	
	public String paymentDetail(){
		payment = orderReportManager.getPayment(id);
		return "paymentDetail";
	}
	
	public String refundList(){
		this.webpage = orderReportManager.listRefund(this.getPage(), this.getPageSize(), order);
		return "refundList";
	}
	
	public String refundDetail(){
		payment = orderReportManager.getPayment(id);
		return "refundDetail";
	}
	
	public String shippingList(){
		this.webpage = orderReportManager.listShipping(this.getPage(), this.getPageSize(), order);
		return "shippingList";
	}
	
	public String shippingDetail(){
		delivery = orderReportManager.getDelivery(id);
		listDeliveryItem = orderReportManager.listDeliveryItem(id);
		return "shippingDetail";
	}
	
	public String returnedList(){
		this.webpage = orderReportManager.listReturned(this.getPage(), this.getPageSize(), order);
		return "returnedList";
	}
	
	public String returnedDetail(){
		delivery = orderReportManager.getDelivery(id);
		listDeliveryItem = orderReportManager.listDeliveryItem(id);
		return "returnedDetail";
	}
	
	////
	

	public IOrderReportManager getOrderReportManager() {
		return orderReportManager;
	}

	public void setOrderReportManager(IOrderReportManager orderReportManager) {
		this.orderReportManager = orderReportManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PaymentLog getPayment() {
		return payment;
	}

	public void setPayment(PaymentLog payment) {
		this.payment = payment;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public List<DeliveryItem> getListDeliveryItem() {
		return listDeliveryItem;
	}

	public void setListDeliveryItem(List<DeliveryItem> listDeliveryItem) {
		this.listDeliveryItem = listDeliveryItem;
	}

}
