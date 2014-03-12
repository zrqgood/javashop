package com.enation.app.base.widget.header;

import java.util.Map;

import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;


/**
 * 网站头挂件
 * @author kingapex
 * 2010-6-22上午10:13:17
 */
public class HeaderWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {
		
	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("header");
		EopSite site = EopContext.getContext().getCurrentSite();
		
		String ctx = ThreadContextHolder.getHttpRequest().getContextPath();
		this.putData("ctx", ctx);
		
		if(this.getData(HeaderConstants.title)==null)
			this.putData(HeaderConstants.title, StringUtil.isEmpty(site.getTitle())?site.getSitename():site.getTitle());
		
		if(this.getData("keywords")==null)
			this.putData(HeaderConstants.keywords, site.getKeywords());
		
		if(this.getData("description")==null)
			this.putData(HeaderConstants.description, site.getDescript());
		
		this.putData("ico",  site.getIcofile());
		this.putData("logo", site.getLogofile());
	}


}
