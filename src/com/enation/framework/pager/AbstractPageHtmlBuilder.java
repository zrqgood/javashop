package com.enation.framework.pager;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;

/**
 * 抽像的分页html代码生成器基类
 * 提供基本的逻辑支持
 * @author kingapex
 *
 */
public abstract class AbstractPageHtmlBuilder implements IPageHtmlBuilder {
	
	protected String url;
	protected HttpServletRequest request ;
	private long pageNum;
	private long totalCount;
	private int pageSize;
	private long pageCount;
	private int showCount = 5;
	
	public AbstractPageHtmlBuilder(long _pageNum,long _totalCount,int _pageSize){
		pageNum= _pageNum;
		totalCount= _totalCount;
		pageSize= _pageSize ;
		request = ThreadContextHolder.getHttpRequest();
	}
	
	public String buildPageHtml() {
		this.init();
		StringBuffer pageStr = new StringBuffer("");
		pageStr.append("<div class=\"page\" >" );
		pageStr.append(this.getHeadString());
		pageStr.append(this.getBodyString());
		pageStr.append(this.getFooterString());
		pageStr.append("</div>");
		return pageStr.toString();
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
		url =url.indexOf('?')>0?url+"&": url + "?";
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
			headString.append(this.getUrlStr(1));
			headString.append("|&lt;");
			headString.append("</a></li>\n");

			headString.append("<li><a  ");
			headString.append(this.getUrlStr(pageNum - 1));
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
			footerStr.append(this.getUrlStr(pageNum + 1));
			footerStr.append("&gt;&gt;");
			footerStr.append("</a></li>\n");

			footerStr.append("<li><a ");
			footerStr.append(this.getUrlStr(pageCount));
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
				pageStr.append(" class=\"unselected\"");
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
	abstract protected   String getUrlStr(long page);

 

}
