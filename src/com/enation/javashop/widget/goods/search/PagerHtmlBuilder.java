/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.javashop.widget.goods.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.utils.UrlUtils;

public   class PagerHtmlBuilder {
	
	
	protected String url;
	private HttpServletRequest request ;
	private long pageNum;
	private long totalCount;
	private int pageSize;
	private long pageCount;
	private int showCount = 23;
	
	public PagerHtmlBuilder(long _pageNum,long _totalCount,int _pageSize){
		pageNum= _pageNum;
		totalCount= _totalCount;
		pageSize= _pageSize ;
		request =  ThreadContextHolder.getHttpRequest();
	}
	
	public String buildPageHtml() {
		this.init();
		StringBuffer pageStr = new StringBuffer("");
		pageStr.append("<table align=\"right\" class=\"pager\"><tbody><tr>" );
		pageStr.append(this.getHeadString());
		pageStr.append(this.getBodyString());
		pageStr.append(this.getFooterString());
		pageStr.append("</tr></tbody></table>");
		return pageStr.toString();
	}

	
	

	
	
	/**
	 * 计算并初始化信息
	 *
	 */
	private  void init() {	
 
		pageSize = pageSize<1? 1 :pageSize;
		
		pageCount = totalCount / pageSize;
		pageCount = totalCount % pageSize > 0 ? pageCount + 1 : pageCount;
	
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		pageNum = pageNum < 1 ? 1 : pageNum;
		
		 
		url =request.getServletPath();
		url = StringUtil.toUTF8(url);
		url=UrlUtils.getParamStr(url);
		url= UrlUtils.getExParamUrl(url, "page");
		
		//url = url.replaceAll("\\.html", ""); 
	 
	}
	
	
	 /**
	  * 生成分页头字串
	  * @return
	  */
	protected String getHeadString() {

		StringBuffer headString = new StringBuffer("");
		headString.append("<td>");
	


		if (pageNum > 1) { //不是第一页，有上一页

			headString.append("<a title=\"上一页\"");
			headString.append(" onmouseout=\"this.className = 'prev'\" ");
			headString.append("  onmouseover=\"this.className = 'onprev'\" ");
			headString.append( " class=\"prev\" " );			
			headString.append(" href=\"");
			headString.append( getUrlStr (this.pageNum-1));
			headString.append("\" >上一页");
			headString.append("</a>\n");
 
		}else{//第一页
			headString.append("<span title=\"已经是第一页\" ");
			headString.append( " class=\"prev\"> 已经是第一页</span>" );
		}
		headString.append("</td>");
		return headString.toString();
	}

	
	
	
	/**
	 * 生成分页尾字串
	 * @return
	 */
	protected String getFooterString() {
		StringBuffer footerStr = new StringBuffer("");
		footerStr.append("<td style=\"padding-right: 20px;\">");
		if (pageNum < pageCount) {

		
			footerStr.append("<a title=\"下一页\" onmouseout=\"this.className = 'next'\" onmouseover=\"this.className = 'onnext'\" class=\"next\" ");
			footerStr.append(" href=\"");
			footerStr.append(  getUrlStr (this.pageNum+1) );
			footerStr.append("\"");
			footerStr.append("下一页</a>");

		}else{
			footerStr.append("<span title=\"已经是最后一页\" class=\"next\">已经是最后一页</span>");
		}
		footerStr.append("</td>\n");
		return footerStr.toString();
	}

	
	
	
	/**
	 * 生成分页主体字串
	 * @return
	 */
	protected String getBodyString() {

		StringBuffer pageStr = new StringBuffer();

		long start = pageNum - showCount / 2;
		start = start <= 1 ? 1 : start;

		long end = start + showCount;
		end = end > pageCount ? pageCount : end;
		pageStr.append("<td>");
		
		for (long i = start; i <= end; i++) {

			
			if (i != pageNum) {
				pageStr.append("<a");
				pageStr.append(" href=\"");
			
				pageStr.append(  getUrlStr ( i ) );
				pageStr.append("\">");
				
				pageStr.append(i);
				pageStr.append("</a>\n");
			} else {
				pageStr.append(" <strong class=\"pagecurrent\">");
				pageStr.append(i);
				pageStr.append("</strong> ");
			}
 
		}
		pageStr.append("</td>");
		return pageStr.toString();
	}

 


	
	
	
	/**
	 * 根据页数生成超级连接href的字串
	 * @param page
	 * @return
	 */
	protected   String getUrlStr(long page){
		String page_url;
		page_url= "search-"+UrlUtils.getExParamUrl(url, "page");
		page_url+="-page-"+ page;
		
		
		 return page_url+".html" ;
	}
	
	public static void main(String[] args){
		String url = "/search-0";		
		String pattern = "p(\\d*)";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.COMMENTS);
		Matcher m = p.matcher(url);
		if (m.find()) {		
		url = url.replaceAll(pattern, "p4");
		}else{
			url = url+"p5";
		}
	 
	}
	
	private  String findUrl(String url){
		String pattern = "(.*)(p(\\d))(.*).html";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			String s = m.replaceAll("$1");
			return s+"-";
		}
		return null;
	}

 
	
}
