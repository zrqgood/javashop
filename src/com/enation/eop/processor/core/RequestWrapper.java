package com.enation.eop.processor.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author kingapex
 * @version 1.0
 * @created 11-十月-2009 16:20:08
 */
public class RequestWrapper implements Request {
	protected Log logger = LogFactory.getLog(getClass());
	protected Request request;


	/**
	 * 
	 * @param request
	 */
	public RequestWrapper(Request request) {
		this.request = request;
	}

	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		
		return this.request.execute(uri, httpResponse, httpRequest);
	}

	/**
	 * 
	 * @param uri
	 */
	public Response execute(String uri) {
		return this.request.execute(uri);
	}

	public Request getRequest(){
		return this.request;
	}

	
	public void setExecuteParams(Map<String, String> params) {
		 this.request.setExecuteParams(params);
		
	}
}