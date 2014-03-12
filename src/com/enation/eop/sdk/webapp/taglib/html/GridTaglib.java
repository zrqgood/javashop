package com.enation.eop.sdk.webapp.taglib.html;

import com.enation.eop.sdk.webapp.bean.Grid;
import com.enation.eop.sdk.webapp.taglib.HtmlTaglib;
import com.enation.eop.sdk.webapp.taglib.html.support.GridAjaxPageHtmlBuilder;
import com.enation.framework.database.Page;
import com.enation.framework.pager.IPageHtmlBuilder;
import com.enation.framework.pager.impl.SimplePageHtmlBuilder;

/**
 * grid主标签
 * @author kingapex
 *
 */
public class GridTaglib extends HtmlTaglib {

	private String from;
	private String pager; //是否显示分页
	private String gridid;
	private String ajax; //是否异步
	
	protected String postStart() {
		//生成gridid
		gridid = ""+System.currentTimeMillis();
		return "<div class=\"gridbody\"  gridid='"+gridid+"' ><table>";
	}
	
	
	protected String postEnd() {
		StringBuffer str = new StringBuffer();
		str.append("</table>");
		if(pager==null || pager.equals("yes")){
			str.append(this.buildPageHtml());
		}
		str.append("</div>");
		return str.toString();
	}

	private String buildPageHtml(){

		/**读取并计算正确页数**/
		String tempPage = this.getRequest().getParameter("page");
		int pageNo = 1;
		if(tempPage!=null && !tempPage.toString().equals("")){
			pageNo = Integer.valueOf(tempPage.toString());
		}
		
		/**由上下文中取出分页对象，以便计算总数、分页数**/
		Object obj = this.pageContext.getAttribute(from);
		if(obj==null){
			obj = this.getRequest().getAttribute(from);
			if(obj==null){
				return "";
			}
			this.pageContext.setAttribute(from,obj);
		}
		
		//分析分页数据对象的类型
		Page page = null;
		if(obj instanceof Page)
			page = (Page)obj;
		else if(obj instanceof Grid)
			page = ((Grid)obj).getWebpage();
		else
			return "";

		int pageSize = page.getPageSize();
		long totalCount = page.getTotalCount();
		
		IPageHtmlBuilder pageHtmlBuilder =null;
		if("yes".equals(ajax) ){
			//采用异步分页构建器
			pageHtmlBuilder =new GridAjaxPageHtmlBuilder( pageNo, totalCount,  pageSize,gridid);
		}
		else{
			//采用正常跳转分页构建器
			pageHtmlBuilder = new SimplePageHtmlBuilder(pageNo, totalCount,  pageSize); 
		}

		return pageHtmlBuilder.buildPageHtml();

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	
}
