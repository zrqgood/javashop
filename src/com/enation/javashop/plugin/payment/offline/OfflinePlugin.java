package com.enation.javashop.plugin.payment.offline;

import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;

/**
 * 线下支付插件
 * @author kingapex
 * 2010-5-26上午10:06:13
 */
public class OfflinePlugin extends AbstractPaymentPlugin implements
		IPaymentEvent {


	
	public String onCallBack() {
		return "";
	}

	
	public String onPay(PayCfg payCfg, Order order) {
		
		return "";
	}

	
	public String getAuthor() {
		return "kingapex";
	}

	
	public String getId() {
		return "offline";
	}

	
	public String getName() {
		return "线下支付";
	}

	
	public String getType() {
		return "payment";
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}

	
	public void register() {

	}


	@Override
	public String onReturn() {
		// TODO Auto-generated method stub
		return "";
	}
}
