package com.enation.javashop.widget.cart;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;

/**
 * 支付挂件
 * @author kingapex
 *2010-4-12上午10:58:01
 */
public class PayWidget extends AbstractWidget {
	private IPaymentManager paymentManager;
	private IOrderManager orderManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
	//	this.setPageFolder("/widgets/cart");
		this.setPageName("pay");
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		String[] actions = this.getAction(url);
		if(null== actions ){
			this.showError("参数不正确");
		}else{
			String action= actions[0];
			String pluginid  =actions[1];
			
			if("to".equals(action)){
				this.pay();
			}else if("callback".equals(action)){
				this.callback(pluginid);
			}else if("return".equals(action)){
				 this.payReturn(pluginid);
			}
			
		}
		
	}

	private void pay(){
		Integer orderId = this.getIntParam("orderid"); 
		Integer paymentId = this.getIntParam("paymentid");
		 
		Order order  = this.orderManager.get(orderId);
		PayCfg payCfg=  this.paymentManager.get(paymentId);
		
		IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(payCfg.getType());
		String payhtml = paymentPlugin.onPay(payCfg, order);
		this.disableCustomPage();
		this.putData("payhtml", payhtml);		
	}
	
	
	private void callback(String pluginid){
		IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(pluginid);
		String payhtml  = paymentPlugin.onCallBack();
		this.showJson(payhtml);		
		
	}
	
	
	private void payReturn(String paluginid){
		try{
			IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(paluginid);
			String ordersn  = paymentPlugin.onReturn();
			this.showSuccess("支付成功","查看此订单","orderdetail_"+ordersn+".html");
		}catch(RuntimeException e){
			this.logger.error("支付发生错误", e);
			this.showError("支付失败"+e.getMessage());
		}
	}
	
	private String[] getAction(String url){
		String action = null;
		String pluginid = null;
		String pattern = "/(\\w+)_(\\w+)_(\\w+).html(.*)";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			action = m.replaceAll("$2");
			pluginid =m.replaceAll("$3");
			return new String[]{action,pluginid};
		}else{
			return null;
		}
		
	}
	
	private Integer getIntParam(String name){
		try{
			HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		 return Integer.valueOf( httpRequest.getParameter(name) );
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}

	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	

	public static void main(String [] args){
		String action = null;
		String pluginid = null;
		String pattern = "/pay(\\w+)_(\\w+).html";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher("/payReturn_alipay.html");
		if (m.find()) {
			action = m.replaceAll("$1");
			pluginid =m.replaceAll("$2");
		}else{
			
		}
		
		System.out.println(action);
		System.out.println(pluginid);
	}
	
}
