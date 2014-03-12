package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IMemberOrderManager;

/**
 * 会员是否购买过某商品的挂件
 * 必须和商品主挂件一起使用
 * @author kingapex
 *
 */
public class MemberGoodsIsBuyWidget extends AbstractWidget {

	private IMemberOrderManager memberOrderManager;
	protected void config(Map<String, String> params) {

	}

	protected void display(Map<String, String> params) {
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		if(goods==null) throw new RuntimeException("员是否购买过某商品挂件必须和商品详细显示挂件同时存在");
		boolean isBuy = this.memberOrderManager.isBuy((Integer)goods.get("goods_id"));
		this.putData("isBuy", isBuy);
	}

	public IMemberOrderManager getMemberOrderManager() {
		return memberOrderManager;
	}

	public void setMemberOrderManager(IMemberOrderManager memberOrderManager) {
		this.memberOrderManager = memberOrderManager;
	}
	
	

}
