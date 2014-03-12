package com.enation.javashop.widget.search;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;

public class SearchBarWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String keyword = (String)request.getAttribute("keyword");
		
		String encoding  = EopSetting.ENCODING;
		if(!StringUtil.isEmpty(encoding)){
			keyword = StringUtil.to(keyword,encoding);
		}
		
		this.putData("keyword", keyword);
		this.setPageName("search_bar");
	}

}
