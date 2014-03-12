package com.enation.app.base.widget.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 网站 导航挂件
 * @author kingapex
 *
 */
public class SiteNavWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		List<Nav> navList  =(List<Nav>)request.getAttribute("site_nav_list");
		navList=navList == null?new ArrayList<Nav>():navList;
		this.putData("navList", navList);
		request.removeAttribute("site_nav_list");
	}

}
