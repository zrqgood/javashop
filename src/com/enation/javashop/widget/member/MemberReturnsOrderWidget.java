package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.ReturnsOrder;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IReturnsOrderManager;

/**
 * 会员退货挂件
 * 
 * @author kingapex
 * 
 */
public class MemberReturnsOrderWidget extends AbstractMemberWidget {

	private IReturnsOrderManager returnsOrderManager;
	private IOrderManager orderManager;

	protected void config(Map<String, String> params) {

	}

	protected void display(Map<String, String> params) {

 
		if (action.equals("list")) {
			this.list();
		}

		if (action.equals("apply")) {
			this.apply();
		}

		if (action.equals("add")) {
			this.add();
		}

	}

	private void list() {
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		List orderList = this.orderManager.listOrderByMemberId(member
				.getMember_id());
		List rorderList = this.returnsOrderManager.listMemberOrder();

		this.putData("orderList", orderList);
		this.putData("rorderList", rorderList);
	}

	private void apply() {

		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		Integer orderid = Integer.valueOf(request.getParameter("orderid"));
		Order order = this.orderManager.get(orderid);
		List goodsList = orderManager.listGoodsItems(orderid);

		this.putData("order", order);
		this.putData("goodsList", goodsList);

	}

	private void add() {

		HttpServletRequest request = ThreadContextHolder.getHttpRequest();

		String[] specids = request.getParameterValues("specid");
		
		if (specids == null || specids.length == 0) {
			this.showError("退货必须选择一个商品");
			return;
		}
		
		/**
		 * 将请求的参数转为integer数组
		 */
		Integer[] specidsInt= new Integer[specids.length];
		for(int i=0;i<specids.length;i++){
			specidsInt[i] = Integer.valueOf(specids[i]);
		}

		Integer orderid = Integer.valueOf(request.getParameter("orderid"));
		String ordersn  = request.getParameter("ordersn");
		int type = Integer.valueOf(request.getParameter("type"));
		String linkman = request.getParameter("linkman");
		String linktel = request.getParameter("linktel");
		String address = request.getParameter("address");
		String attachment = request.getParameter("attachment");
		Integer facade = Integer.valueOf(request.getParameter("facade"));
		Integer wrap = Integer.valueOf(request.getParameter("wrap"));
		int invoice = RequestUtil.getIntValue(request, "invoice");
		String shiptype = request.getParameter("shiptype");
		String remark = request.getParameter("remark");

		ReturnsOrder returnsOrder = new ReturnsOrder();
		returnsOrder.setOrderid(orderid);
		returnsOrder.setAddress(address);
		returnsOrder.setAttachment(attachment);
		returnsOrder.setFacade(facade);
		returnsOrder.setInvoice(invoice);
		returnsOrder.setShiptype(shiptype);
		returnsOrder.setWrap(wrap);
		returnsOrder.setRemark(remark);
		returnsOrder.setLinkman(linkman);
		returnsOrder.setAddress(address);
		returnsOrder.setType(type);
		returnsOrder.setLinktel(linktel);
		returnsOrder.setOrdersn(ordersn);

		this.returnsOrderManager.add(returnsOrder,specidsInt);
		this.showSuccess("申请已提交，我们会在2个工作日内处理您的请求。");

	}

	public IReturnsOrderManager getReturnsOrderManager() {
		return returnsOrderManager;
	}

	public void setReturnsOrderManager(IReturnsOrderManager returnsOrderManager) {
		this.returnsOrderManager = returnsOrderManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

}
