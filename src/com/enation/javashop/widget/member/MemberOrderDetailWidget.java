package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.OrderMeta;
import com.enation.javashop.core.service.IMemberOrderManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IOrderMetaManager;
/**
 * 会员订单详细
 * @author kingapex
 * 2010-9-27上午09:38:52
 */
public class MemberOrderDetailWidget extends AbstractMemberWidget {
	
	private IMemberOrderManager memberOrderManager;
	private IOrderManager orderManager;
	private IOrderMetaManager orderMetaManager;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("orderdetail");
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String sn  = this.parseSn(request.getServletPath());
		
		Order order = orderManager.get(sn);
		List orderLogList = memberOrderManager.listOrderLog(order.getOrder_id());
		List orderItemsList =orderManager.listGoodsItems(order.getOrder_id());
		List pgk = orderManager.listGiftItems(0);
		List giftList  = memberOrderManager.listGiftItems(order.getOrder_id());
		this.putData("order", order);
		this.putData("orderLogList", orderLogList);
		this.putData("orderItemsList", orderItemsList);
		this.putData("giftList", giftList);
		
		List<OrderMeta> metaList  =this.orderMetaManager.list(order.getOrder_id()) ;
		this.putData("metaList",metaList);
		
 
	}
	
	public boolean getShowMenu() {
	 
		if(this.getCurrentMember() == null){
			return false;
		}else{
			return true;
		}
	}

	
	private static String parseSn(String url){
		String pattern = "(.*)orderdetail_(\\w+)(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}
	
	public static void main(String[] args){
	 System.out.println(parseSn("/member_orderdetail_10010101.html") );
	 System.out.println(parseSn("/orderdetail_10010101.html") );
	}
	
	
	public IMemberOrderManager getMemberOrderManager() {
		return memberOrderManager;
	}

	public void setMemberOrderManager(IMemberOrderManager memberOrderManager) {
		this.memberOrderManager = memberOrderManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public IOrderMetaManager getOrderMetaManager() {
		return orderMetaManager;
	}

	public void setOrderMetaManager(IOrderMetaManager orderMetaManager) {
		this.orderMetaManager = orderMetaManager;
	}

}
