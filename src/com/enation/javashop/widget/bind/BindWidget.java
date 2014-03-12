package com.enation.javashop.widget.bind;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.IGoodsManager;

/**
 * 捆绑商品
 * 
 * @author lzf<br/>
 *         2010-4-8 上午09:43:49<br/>
 *         version 1.0<br/>
 */
public class BindWidget extends AbstractWidget {
	
	private IGoodsManager goodsManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("bind");
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String page  = request.getParameter("page");
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;
		Page bindPage = goodsManager.searchBindGoods(null, null, null, Integer.valueOf(page), pageSize);
		Long pageCount = bindPage.getTotalPageCount();
		List bindList = (List)bindPage.getResult();
		bindList = bindList == null ? new ArrayList() : bindList;
		this.putData("pageSize", pageSize);
		this.putData("page", page);
		this.putData("bindList", bindList);
		this.putData("pageCount", pageCount);
		
		Nav nav = new Nav();
		nav.setTitle("捆绑促销");
		this.putNav(nav);
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

}
