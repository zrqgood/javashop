package com.enation.javashop.widget.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IMemberCouponsManager;

/**
 * 优惠卷兑换
 * @author kingapex
 *
 */
public class MemberCouponExchangeWidget extends AbstractMemberWidget {

	private IMemberCouponsManager memberCouponsManager;
	
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		if(action.equals("")){
		this.setPageName("member_couponExchange");
		String page = request.getParameter("page");
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;
		Page pageExchangeCoupons = memberCouponsManager.pageExchangeCoupons(Integer.valueOf(page), pageSize);
		Long pageCount = pageExchangeCoupons.getTotalPageCount();
		List listExchangeCoupons = (List)pageExchangeCoupons.getResult();
		this.putData("listExchangeCoupons", listExchangeCoupons);
		this.putData("pageSize", pageSize);
		this.putData("pageCount", pageCount);
		this.putData("page", page);
		}else if(action.equals("exchange")){
			String cpns_id = request.getParameter("cpns_id");
			try{
				memberCouponsManager.exchange(Integer.valueOf(cpns_id));
				this.showSuccess("兑换成功","我的优惠券", "member_coupons.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("兑换失败", "我的优惠券", "member_coupons.html");
			}
		}

	}

	public IMemberCouponsManager getMemberCouponsManager() {
		return memberCouponsManager;
	}

	public void setMemberCouponsManager(IMemberCouponsManager memberCouponsManager) {
		this.memberCouponsManager = memberCouponsManager;
	}

}
