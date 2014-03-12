package com.enation.javashop.widget.groupbuy;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.IGroupBuyManager;

/**
 * 团购列表挂件
 * @author kingapex
 *
 */
public class GroupBuyListWidget extends AbstractWidget {
	private IGroupBuyManager groupBuyManager;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		List list  = this.groupBuyManager.listEnable();
		this.putData("groupBuyList", list);
	}

	public final IGroupBuyManager getGroupBuyManager() {
		return groupBuyManager;
	}

	public final void setGroupBuyManager(IGroupBuyManager groupBuyManager) {
		this.groupBuyManager = groupBuyManager;
	}

}
