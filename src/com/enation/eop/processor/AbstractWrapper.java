package com.enation.eop.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.RequestWrapper;
import com.enation.eop.processor.core.Response;


/**
 * 抽像包装器
 * @author kingapex
 *
 */
public abstract class AbstractWrapper extends RequestWrapper {
	protected String uri;
	protected HttpServletResponse httpResponse;
	protected HttpServletRequest httpRequest;
	
	public AbstractWrapper(Request request) {
		super(request);
	}
	
	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		this.uri = uri;
		this.httpRequest =httpRequest;
		this.httpResponse=httpResponse;
		
		return wrap();
	}

	protected  Response wrap(){
		Response response  = super.execute(uri, httpResponse, httpRequest);
		return wrap(response);
	}
	
	
	protected abstract Response wrap(Response response);

	
}
