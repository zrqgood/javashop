package com.enation.javashop.widget.nav;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.utils.UrlUtils;

public class SearchNavWidget extends AbstractWidget {
	private IGoodsCatManager goodsCatManager;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String url= request.getServletPath();
		Cat cat  = null;
		String catidstr = UrlUtils.getParamStringValue(url,"cat");
		if(!StringUtil.isEmpty(catidstr) && !"0".equals(catidstr)){
			Integer catid= Integer.valueOf(catidstr);
			cat = goodsCatManager.getById(catid); 
			this.putData("cat",cat);
		}
	}

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

}
