package com.enation.javashop.widget.limitbuy;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.ILimitBuyManager;

/**
 * 限时购买列表挂件
 * @author kingapex
 *
 */
public class LimitBuyListWidget extends AbstractWidget {
	private ILimitBuyManager limitBuyManager;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		List limitBuyList  = limitBuyManager.listEnable();
		
		this.putData("limitBuyList", limitBuyList);
	}

	public ILimitBuyManager getLimitBuyManager() {
		return limitBuyManager;
	}

	public void setLimitBuyManager(ILimitBuyManager limitBuyManager) {
		this.limitBuyManager = limitBuyManager;
	}

}
