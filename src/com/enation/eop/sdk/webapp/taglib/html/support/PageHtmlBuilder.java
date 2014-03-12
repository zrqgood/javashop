package com.enation.eop.sdk.webapp.taglib.html.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.enation.framework.util.RequestUtil;

public  class PageHtmlBuilder {
	
	protected String url;
	protected HttpServletRequest request ;
	private long pageNum;
	private long totalCount;
	private int pageSize;
	private long pageCount;
	private int showCount = 5;
	
	public PageHtmlBuilder(long _pageNum,long _totalCount,int _pageSize,HttpServletRequest _request){
		pageNum= _pageNum;
		totalCount= _totalCount;
		pageSize= _pageSize ;
		request = _request;
	}
	
	public String buildPageHtml() {
		this.init();
		StringBuffer pageStr = new StringBuffer("");
		pageStr.append("<div class=\"page\" >" );
		pageStr.append(this.getHeadString());
		pageStr.append(this.getBodyString());
		pageStr.append(this.getFooterString());
		pageStr.append("</div>");
		return pageStr.toString()+"\n<script>EopPager.init('"+this.url+"');</script>";
	}

	
	
	/**
	 * 初始化url,用于地址栏方式传
	 * <br/> 
	 * 将地址栏上的参数拼装
	 *
	 */
	protected  void initUrl() {
		url =request.getContextPath()+RequestUtil.getRequestUrl(request);
		url = url.replaceAll("(&||\\?)page=(\\d+)","");
		url = url.replaceAll("(&||\\?)rmd=(\\d+)","");
		//url =url.indexOf('?')>0?url+"&": url + "?";
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
		
		if(this.url==null)
		initUrl();
//		url = url.indexOf('?') >= 0 ? (url += "&") : (url += "?");
	}
	
	
	 /**
	  * 生成分页头字串
	  * @return
	  */
	protected String getHeadString() {

		StringBuffer headString = new StringBuffer("");
		headString.append("<span class=\"info\" >");
		headString.append("共");
		headString.append(this.totalCount);
		headString.append("条记录");
		headString.append("</span>\n");

		headString.append("<span class=\"info\">");
		headString.append(this.pageNum);
		headString.append("/");
		headString.append(this.pageCount);
		headString.append("</span>\n");

		headString.append("<ul>");
		if (pageNum > 1) {

			headString.append("<li><a " );
//			headString.append(getUrlStr(1));	
			headString.append(this.getUrlStr(1));
			headString.append("|&lt;");
			headString.append("</a></li>\n");

			headString.append("<li><a  ");
//			headString.append(getUrlStr(pageNum - 1));
			headString.append(this.getUrlStr(pageNum - 1));
//			headString.append("\">"); 
			headString.append("&lt;&lt;");
			headString.append("</a></li>\n");
		}

		return headString.toString();
	}

	/**
	 * 生成分页尾字串
	 * @return
	 */
	protected String getFooterString() {
		StringBuffer footerStr = new StringBuffer("");
		if (pageNum < pageCount) {

			footerStr.append("<li><a ");
//			footerStr.append(getUrlStr(pageNum + 1));
			footerStr.append(this.getUrlStr(pageNum + 1));
//			footerStr.append(" >");
			footerStr.append("&gt;&gt;");
			footerStr.append("</a></li>\n");

			footerStr.append("<li><a ");
			//footerStr.append(getUrlStr(pageCount)); 
			footerStr.append(this.getUrlStr(pageCount));
//			footerStr.append(">");
			footerStr.append("&gt;|");
			footerStr.append("</a></li>\n");

		}
		footerStr.append("</ul>");
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

		for (long i = start; i <= end; i++) {

			pageStr.append("<li><a ");
			if (i != pageNum) {
//				pageStr.append(" href=\"");
//				pageStr.append(getUrlStr(i));				
//				pageStr.append("\"");
				pageStr.append(this.getUrlStr(i));
			} else {
				pageStr.append(" class=\"selected\">");
			}

		 
			pageStr.append(i);
			pageStr.append("</a></li>\n");

		}

		return pageStr.toString();
	}

	/**
	 * 根据页数生成超级连接href的字串
	 * @param page
	 * @return
	 */
	protected   String getUrlStr(long page){
		StringBuffer linkHtml = new  StringBuffer();
		linkHtml.append("href='javascript:;'");
		linkHtml.append(" pageNo='");
		linkHtml.append(page);
		linkHtml.append("'>");
		return linkHtml.toString();
	} 
	
	public void setUrl(String _url){
		
		this.url= _url;
	}
	
}
