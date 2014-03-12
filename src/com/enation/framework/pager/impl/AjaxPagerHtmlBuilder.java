package com.enation.framework.pager.impl;

import com.enation.framework.pager.AbstractPageHtmlBuilder;

public class AjaxPagerHtmlBuilder extends AbstractPageHtmlBuilder {

	public AjaxPagerHtmlBuilder(long _pageNum, long _totalCount, int _pageSize) {
		super(_pageNum, _totalCount, _pageSize);
	 
	}

	@Override
	protected String getUrlStr(long page) {
		StringBuffer linkHtml = new StringBuffer();
		linkHtml.append("href='javascript:;'");
		linkHtml.append("page='");
		linkHtml.append(page);
		linkHtml.append("'>");
		return linkHtml.toString();
	}

}
