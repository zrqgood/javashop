package com.enation.eop.processor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.RequestFactory;
import com.enation.eop.processor.core.Response;

/**
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:27
 */
public abstract class AbstractFacadeProcessor implements Processor {

	protected FacadePage page;
	protected HttpServletRequest httpRequest;
	protected HttpServletResponse httpResponse;
	protected int mode;
	protected Request request;

	/**
	 * 
	 * @param page
	 */
	public AbstractFacadeProcessor(FacadePage page) {
		this.page = page;
	}

	/**
	 * 
	 * @param mode
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		
		this.mode = mode;
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
		request = RequestFactory.getRequest(mode);
		
		return process();
	}

	protected abstract Response process();

}