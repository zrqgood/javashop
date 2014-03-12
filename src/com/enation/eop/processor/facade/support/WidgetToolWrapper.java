package com.enation.eop.processor.facade.support;

import com.enation.eop.processor.AbstractFacadePageWrapper;
import com.enation.eop.processor.FacadePage;
import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;

/**
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:30
 */
public class WidgetToolWrapper extends AbstractFacadePageWrapper {

	private static final String toolsElement = "<div id=\"widget_setting\"></div><form id=\"pageForm\" method=\"POST\"><input type=\"hidden\" id=\"bodyHtml\" name=\"bodyHtml\"> </form></body>";

	/**
	 * 
	 * @param page
	 */
	public WidgetToolWrapper(FacadePage page, Request request) {
		super(page, request);
	}

	protected Response wrap(Response response) {
		String content = response.getContent();
		content = content.replaceAll("</body>", toolsElement);
		content = content.replaceAll("</BODY>", toolsElement);

		response.setContent(content);
		return response;
	}

}