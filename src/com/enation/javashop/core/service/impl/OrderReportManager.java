package com.enation.javashop.core.service.impl;

import java.util.List;

import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;
import com.enation.javashop.core.model.PaymentLog;
import com.enation.javashop.core.service.IOrderReportManager;

/**
 * 订单报表
 * 
 * @author lzf<br/>
 *         2010-4-12上午10:23:43<br/>
 *         version 1.0
 */
public class OrderReportManager extends BaseSupport implements
		IOrderReportManager {

	
	public Delivery getDelivery(Integer deliveryId) {
		String sql = "select l.*, m.uname as member_name, o.sn from " + this.getTableName("delivery") + " l left join " + this.getTableName("member") + " m on m.member_id = l.member_id left join " + this.getTableName("order") + " o on o.order_id = l.order_id where l.delivery_id = ?";
		Delivery delivery = (Delivery)this.daoSupport.queryForObject(sql, Delivery.class, deliveryId);
		return delivery;
	}

	
	public PaymentLog getPayment(Integer paymentId) {
		String sql = "select l.*, m.uname as member_name, o.sn from " + this.getTableName("payment_logs") + " l left join " + this.getTableName("member") + " m on m.member_id = l.member_id left join " + this.getTableName("order") + " o on o.order_id = l.order_id where l.payment_id = ?";
		PaymentLog paymentLog = (PaymentLog)this.daoSupport.queryForObject(sql, PaymentLog.class, paymentId);
		return paymentLog;
	}

	
	public List<DeliveryItem> listDeliveryItem(Integer deliveryId) {
		String sql = "select * from delivery_item where delivery_id = ?";
		return this.baseDaoSupport.queryForList(sql, DeliveryItem.class, deliveryId);
	}
	
	private Page list(int pageNo, int pageSize, String order, int type){
		order = order == null ? "payment_id desc" : order ;
		String sql = "select l.*, m.uname as member_name, o.sn from " + this.getTableName("payment_logs") + " l left join " + this.getTableName("member") + " m on m.member_id = l.member_id left join " + this.getTableName("order") + " o on o.order_id = l.order_id where l.type = " + type;
		sql += " order by " + order;
		return this.daoSupport.queryForPage(sql, pageNo, pageSize, PaymentLog.class);
	}

	
	public Page listPayment(int pageNo, int pageSize, String order) {
		return list(pageNo, pageSize, order, 1);
	}

	
	public Page listRefund(int pageNo, int pageSize, String order) {
		return list(pageNo, pageSize, order, 2);
	}
	
	private Page listDelivery(int pageNo, int pageSize, String order, int type){
		order = order == null ? "delivery_id desc" : order ;
		String sql = "select l.*, m.uname as member_name, o.sn from " + this.getTableName("delivery") + " l left join " + this.getTableName("member") + " m on m.member_id = l.member_id left join " + this.getTableName("order") + " o on o.order_id = l.order_id where l.type = " + type ;
		sql += " order by " + order;
		return this.daoSupport.queryForPage(sql, pageNo, pageSize, Delivery.class);
	}

	
	public Page listReturned(int pageNo, int pageSize, String order) {
		return listDelivery(pageNo, pageSize, order, 2);
	}

	
	public Page listShipping(int pageNo, int pageSize, String order) {
		return listDelivery(pageNo, pageSize, order, 1);
	}

	
	public List listDelivery(Integer orderId, Integer type) {
		return this.baseDaoSupport.queryForList("select * from delivery where order_id = ? and type = ?", orderId, type);
	}

	
	public List listPayLogs(Integer orderId, Integer type) {
		return this.baseDaoSupport.queryForList("select * from payment_logs where order_id = ? and type = ?", orderId, type);
	}

}
