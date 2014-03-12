package com.enation.javashop.widget.order;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IOrderMetaManager;

public class OrderMetaWidget extends AbstractWidget {
	private IOrderManager orderManager;
	private IOrderMetaManager orderMetaManager;
	@Override
	protected void display(Map<String, String> params) {
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String sn  = this.parseSn(request.getServletPath());
		Order order = orderManager.get(sn);
		
		
	}

	@Override
	protected void config(Map<String, String> params) {
	  
		
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
