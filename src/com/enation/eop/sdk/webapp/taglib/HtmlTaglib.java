package com.enation.eop.sdk.webapp.taglib;

import javax.servlet.jsp.JspException;

public abstract class HtmlTaglib extends BaseTaglibSupport {
	
	protected String startHtml = "";
	protected String endHtml = "";
	
	protected void startAppend(String html){
		startHtml += html;
	}
	protected void endAppend(String html){
		endHtml += html;
	}
	
	public int doStartTag() throws JspException {
		startHtml = "";
		this.print(this.postStart());		
		return this.EVAL_BODY_INCLUDE;
	}
	
	public int doAfterBody() {
		String content = this.postEnd();
		this.print(content) ;
		return this.EVAL_PAGE;
	}

	protected abstract String postStart();
	protected abstract String postEnd();
	
}

