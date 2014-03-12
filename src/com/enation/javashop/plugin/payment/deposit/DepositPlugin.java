package com.enation.javashop.plugin.payment.deposit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.plugin.payment.AbstractPaymentPlugin;
import com.enation.javashop.core.plugin.payment.IPaymentEvent;
import com.enation.javashop.core.service.IAdvanceLogsManager;
import com.enation.javashop.core.service.IMemberManager;

/**
 * 预存款支付
 * @author kingapex
 * 2010-5-26下午02:29:09
 */
public class DepositPlugin extends AbstractPaymentPlugin implements
		IPaymentEvent {
	
	private IMemberManager memberManager;
	private IAdvanceLogsManager advanceLogsManager;
	
	
	public String onCallBack() {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String ordersn = request.getParameter("ordersn");
		
		try{
			this.paySuccess(ordersn);
		}catch(RuntimeException e){
			if(this.logger.isDebugEnabled()){
				this.logger.debug(e.getMessage(),e);
			}
			return e.getMessage();
		}
		
		return "支付成功";
	}

	
	public String onPay(PayCfg payCfg, Order order) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member  = userService.getCurrentMember();
		if(member== null ){
			 
			String url="";
			try {
				url = URLEncoder.encode("widget?type=paywidget&orderid="+order.getOrder_id()+"&paymentid="+payCfg.getId(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		 
			return "<script>location.href='member_login.html?forward="+url+"'</script>";
		}
		
		return "<script>location.href='"+this.getReturnUrl(payCfg)+"&ordersn="+order.getSn()+"';</script>正在支付...";
	
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "deposit";
	}

	
	public String getName() {
		
		return "预存款支付";
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

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public IAdvanceLogsManager getAdvanceLogsManager() {
		return advanceLogsManager;
	}

	public void setAdvanceLogsManager(IAdvanceLogsManager advanceLogsManager) {
		this.advanceLogsManager = advanceLogsManager;
	}


	@Override
	public String onReturn() {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String ordersn = request.getParameter("ordersn");
		
		try{
			this.paySuccess(ordersn);
		}catch(RuntimeException e){
			if(this.logger.isDebugEnabled()){
				this.logger.debug(e.getMessage(),e);
			}
			return e.getMessage();
		}
		
		return ordersn;
	}


}
