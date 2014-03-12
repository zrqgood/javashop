package com.enation.eop.processor.backend.support;

import com.enation.eop.processor.AbstractFacadePagetParser;
import com.enation.eop.processor.FacadePage;
import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;
/**
 * 输出获取积分对话框js的包装器
 * @author kingapex
 * 2010-9-13下午12:19:51
 */
public class GetPointJsWrapper extends AbstractFacadePagetParser {

	public GetPointJsWrapper(FacadePage page, Request request) {
		super(page, request);
	}

	protected Response parse(Response response) {
		String content  = response.getContent();
		content = content+"<script>$(function(){Eop.Point.init();});</script>";
		response.setContent(content);
		return response;
	}

}
