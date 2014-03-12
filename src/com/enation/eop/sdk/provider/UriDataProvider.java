package com.enation.eop.sdk.provider;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.sdk.utils.JspUtil;

/**
 * @author kingapex
 * @version 1.0
 * @created 06-十一月-2009 13:14:40
 */
public  class UriDataProvider implements DataProvider {

	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private String url;

	/**
	 * 
	 * @param httpRequest
	 * @param httpResponse
	 */
	public UriDataProvider(HttpServletRequest httpRequest, HttpServletResponse httpResponse,String url){
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
		this.url = url;
	}
	
	

	/**
	 * 
	 * @param param
	 */
	public Object load(Map<String,String> params){
		
		String content = JspUtil.getJspOutput(this.url, this.httpRequest,this.httpResponse);
		
		return content;
	}

}