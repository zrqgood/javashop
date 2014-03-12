package com.enation.eop.processor.backend.support;

import com.enation.eop.processor.AbstractFacadePagetParser;
import com.enation.eop.processor.FacadePage;
import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;
import com.enation.eop.sdk.utils.JspUtil;

/**
 * 后台页面包装器<br>
 * 用/admin/main_frame.jsp包装业务页面 <br>
 * @author kingapex
 * 2010-9-13下午12:17:04
 */
public class BackTemplateWrapper extends AbstractFacadePagetParser {
	
	public BackTemplateWrapper(FacadePage page, Request request) {
		super(page, request);
	}

	
	protected Response parse(Response response) {
		httpRequest.setAttribute("content", response.getContent());
		String content  = JspUtil.getJspOutput("/admin/main_frame.jsp", httpRequest, httpResponse);
		response.setContent(content);
		return response;
	}

}
