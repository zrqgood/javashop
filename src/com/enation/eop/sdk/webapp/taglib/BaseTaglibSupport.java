package com.enation.eop.sdk.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class BaseTaglibSupport extends BodyTagSupport {
	private static final long serialVersionUID = -8939393391060656141L;
	
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
	public HttpServletRequest getRequest(){
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		
		return request;
	}
	
	public HttpServletResponse getResponse(){
		HttpServletResponse response = (HttpServletResponse)this.pageContext.getResponse();
		
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
			this.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 向客户端输出字串并且换行
	 * @param s
	 */
	protected void println(String s) {
		print(s  +"\n");
	}
	
	protected String getContextPath(){
		return this.getRequest().getContextPath();
		 
	}
}