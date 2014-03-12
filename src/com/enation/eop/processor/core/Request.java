package com.enation.eop.processor.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kingapex
 * @version 1.0
 * @created 09-十月-2009 22:22:30
 */
public interface Request {



	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse, HttpServletRequest httpRequest);

	/**
	 * 
	 * @param uri
	 */
	public Response execute(String uri);

	
	/**
	 * 设置请求时的参数
	 * @param params
	 */
	public void setExecuteParams(Map<String,String> params);
	
}