package com.enation.framework.taglib;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;


/**
 * 商城标签支撑类
 * @author apexking
 *
 */
public abstract class EnationTagSupport extends BodyTagSupport {

	protected Log logger = LogFactory.getLog(getClass());
	/**
	 * 得到当前访问网站id
	 * 
	 * @return
	 */
	protected Long getShopId() {
		return (Long)this.getRequest().getAttribute("shopid");
	 
	}
	
	
	/**
	 * 获取ServletContext
	 * @return
	 */
	protected ServletContext getServletContext() {
		ServletContext servletContext = this.pageContext.getServletContext();
	
		return servletContext;
	}
	
	
	/**
	 * 获取Request
	 * @return
	 */
	public  HttpServletRequest getRequest(){
		HttpServletRequest request =	 (HttpServletRequest)this.pageContext.getRequest();
		
		return request;
	}
	
	
	public HttpServletResponse getResponse(){
		HttpServletResponse response  = (HttpServletResponse)this.pageContext.getResponse();
		return response;
	}
	
	/**
	 * 获取JspWriter
	 * @return
	 */
	
	protected JspWriter getWriter() {
		return this.pageContext.getOut();
	}

	/**
	 * 向客户端输出字串
	 * @param s
	 */
	protected void print(String s) {
		try {
			this.getWriter().write(s);
		} catch (IOException e) {
			this.logger.error("taglib print error", e);
		}
	}
	
	/**
	 * 向客户端输出字串并且换行
	 * @param s
	 */
	protected void println(String s) {
		print(s  +"\n");
	}
	
 
	
	/**
	 * 获取spring WebApplicationContext
	 * @return Spring的context
	 * @throws RuntimeException 如果未得到bean 抛此运行期异常
	 */
	protected WebApplicationContext getWebApplicationContext()throws RuntimeException{
		ServletContext servletContext = this.getServletContext();
		Object ob = servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if (ob == null) {
			logger
					.error("Get WebApplicationContext from ServletContext."
							+ WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE
							+ " is null.");
			throw new RuntimeException(" get obj form spring is null ");
		}

		WebApplicationContext context = (WebApplicationContext) ob;
	 
		return context;

	}
	
	protected Object getBean(String name){
	 
		return this.getWebApplicationContext().getBean(name);
	}
	
	protected Object getBean(String name,Object[] args){
		 
		return this.getWebApplicationContext().getBean(name,args);
	}
	
	
	protected String getContextPath(){
		return this.getRequest().getContextPath();
		 
	}
	
	
}
