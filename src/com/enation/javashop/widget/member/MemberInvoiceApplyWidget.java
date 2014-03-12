package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.model.InvoiceApply;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.service.IInvoiceApplyManager;
import com.enation.javashop.core.service.IOrderManager;

public class MemberInvoiceApplyWidget extends AbstractMemberWidget {

	private IInvoiceApplyManager  invoiceApplyManager;
	private IOrderManager orderManager ;
	
	protected void config(Map<String, String> params) {
		
	}

	
	protected void display(Map<String, String> params) {
		
		
		
		if(action.equals("apply")){
			 HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
			 String orderid  = request.getParameter("orderid");
			 Order order  = orderManager.get(Integer.valueOf(orderid));
			 
			 this.putData("order", order);
		}
		
		
		if(action.equals("list")){
			Member member = UserServiceFactory.getUserService().getCurrentMember();
			List orderList = this.orderManager.listOrderByMemberId(member
					.getMember_id());
			List invoiceList  = this.invoiceApplyManager.listMember();
			this.putData("invoiceList", invoiceList);
			this.putData("orderList", orderList);
		}
		
		if(action.equals("add")){
			this.add();
		}
		
	}
	
	
	
	
	private void add(){
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String title  = request.getParameter("title");
		String content  = request.getParameter("content");
		Integer orderid = RequestUtil.getIntegerValue(request, "orderid");
		 
		
		InvoiceApply invoiceApply = new InvoiceApply();
		invoiceApply.setTitle(title);
		invoiceApply.setContent(content);
		invoiceApply.setOrderid(orderid);
		
		this.invoiceApplyManager.add(invoiceApply);
		this.showSuccess("申请已经提交，请等待我们的审核");
		
	}


	public IInvoiceApplyManager getInvoiceApplyManager() {
		return invoiceApplyManager;
	}


	public void setInvoiceApplyManager(IInvoiceApplyManager invoiceApplyManager) {
		this.invoiceApplyManager = invoiceApplyManager;
	}


	public IOrderManager getOrderManager() {
		return orderManager;
	}


	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
	
	

}
