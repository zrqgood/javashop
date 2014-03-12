package com.enation.eop.sdk.webapp.taglib.html.support;

import com.enation.framework.pager.AbstractPageHtmlBuilder;

/**
 * grid异步分页构建器<br>
 * 依赖/WebContent/statics/js/admin/Grid.js<br>
 * 通过$(selector).gridAjaxPager(url)在客户端完成分页加载操作<br>
 * url为去掉page参数的url<br>
 * 每个分页的连接增加pageno属性，对应其页数
 * @author kingapex
 *
 */
public class GridAjaxPageHtmlBuilder extends AbstractPageHtmlBuilder {

	private String gridid;

	public GridAjaxPageHtmlBuilder(long pageNum, long totalCount, int pageSize,String _gridid) {
		super(pageNum, totalCount, pageSize);
		this.gridid = _gridid;
	}

	/**
	 * 覆写父类的生成html方法<br>
	 * 加入分页js调用
	 */
	@Override
	public String buildPageHtml() {
		return super.buildPageHtml() + "<script>$(function(){$(\".gridbody[gridid='"+gridid+"']>.page\").gridAjaxPager('"+url+"');});</script>";
	}

	/**
	 * 根据页数生成超级连接href的字串
	 * 
	 * @param page
	 * @return
	 */
	protected String getUrlStr(long page) {
		StringBuffer linkHtml = new StringBuffer();
		linkHtml.append("href='javascript:;'");
		linkHtml.append(" pageNo='");
		linkHtml.append(page);
		linkHtml.append("'>");
		return linkHtml.toString();
	}

}
