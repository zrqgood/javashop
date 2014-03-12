package com.enation.framework.pager.impl;

import com.enation.framework.pager.AbstractPageHtmlBuilder;

/**
 * 正常的页面跳转的翻页
 * @author apexking
 *
 */
public class SimplePageHtmlBuilder extends AbstractPageHtmlBuilder   {
	
	public SimplePageHtmlBuilder(long _pageNum, long _totalCount, int _pageSize) {
		super(_pageNum, _totalCount, _pageSize);
	}

	/**
	 * 生成href的字串
	 */
	
	protected String getUrlStr(long page) {
		
		StringBuffer linkHtml = new StringBuffer();
		linkHtml.append("href='");
		linkHtml.append(url);
		linkHtml.append("page=");
		linkHtml.append(page);
		linkHtml.append("'>");
		return linkHtml.toString();
		
		 
	}
	
 
}
