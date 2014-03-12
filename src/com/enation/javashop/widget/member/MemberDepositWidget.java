package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;
import com.enation.javashop.core.service.IPaymentManager;

/**
 * 会员预存款挂件
 * @author kingapex
 * 2010-9-25上午11:57:57
 */
public class MemberDepositWidget extends AbstractMemberWidget {
	private  IPaymentManager paymentManager;
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String action  = request.getParameter("action");
		if(StringUtil.isEmpty(action)){
			this.topay();
		}else if("pay".equals(action)){
			this.pay();
		}else if("callback".equals(action)){
			this.callback();
		}
	}
	
	/**
	 * 显示支付页面<br/>
	 */
	private void topay(){
		
		//读取所有支付方式挂件
		List payList  = paymentManager.list();
		this.putData("payList", payList);
		
		
	}

	/**
	 * 执行支付操作
	 */
	private void pay(){
		
		Integer paymentId = this.getIntParam("paymentid");
		
		PayCfg payCfg=  this.paymentManager.get(paymentId);
		Double money  = this.getDoubleParam("money");
		
		//模拟一个订单，以适应支付插件
		Order order  = new Order();
		order.setOrder_id(-1);
		order.setSn("预存款充值");
		order.setOrder_amount(money);
		
		
		IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(payCfg.getType());
		AbstractPaymentPlugin payPlugin = (AbstractPaymentPlugin)paymentPlugin;
		payPlugin.setCallBackUrl(this.getCallBackUrl());
		
		String payhtml = paymentPlugin.onPay(payCfg, order);
		this.freeMarkerPaser.setClz(getClass());
		this.setPageName("pay");
		this.putData("payhtml", payhtml);
		
	}
	
	private Integer getIntParam(String name){
		try{
			    HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		 return Integer.valueOf( request.getParameter(name) );
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}

	private void callback(){
		
		Integer paymentId = this.getIntParam("paymentid"); //因有的支付接口会全部转为小写故参数全部为小写
		PayCfg payCfg=  this.paymentManager.get(paymentId);		
		IPaymentEvent  paymentPlugin =  SpringContextHolder.getBean(payCfg.getType());
		String payhtml  = paymentPlugin.onCallBack();
		this.showSuccess("充值成功","查看我的预存款","member_advanceLogs.html");
	}
	
	private Double getDoubleParam(String name){
		try{
			    HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		 return Double.valueOf( request.getParameter(name) );
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}
	

	
	
	/**
	 * 供支付插件获取回调url
	 * @return
	 */
	protected String getCallBackUrl(){
		
		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		String serverName =request.getServerName();
		String paymentId = request.getParameter("paymentid");
		int port  = request.getLocalPort();
		String contextPath = request.getContextPath();
		String orderId = request.getParameter("orderid"); 
		return "http://"+serverName+":"+port+contextPath+"/member_deposit.html?action=callback&paymentid="+paymentId;
	}
	
	
	/**
	 * 供支付插件获取显示url
	 * @return
	 */
	protected String getShowUrl(){

		HttpServletRequest request  =  ThreadContextHolder.getHttpRequest();
		String paymentId = request.getParameter("paymentid");
		String serverName =request.getServerName();	
		int port  = request.getLocalPort();
		String contextPath = request.getContextPath();		
		return "http://"+serverName+":"+port+contextPath+"/widget?type=paywidget&action=show&paymentid="+paymentId;
	}
	
	
	
	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}
	

}
