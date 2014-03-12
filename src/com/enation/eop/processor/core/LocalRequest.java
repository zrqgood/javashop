package com.enation.eop.processor.core;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.sdk.utils.JspUtil;
import com.enation.framework.util.StringUtil;

/**
 * @author kingapex
 * @version 1.0
 * @created 09-十月-2009 22:22:30
 */
public class LocalRequest implements Request {

	public LocalRequest(){

	}

	public void setExecuteParams(Map<String,String> params){
	}

	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		
		String content = JspUtil.getJspOutput(uri, httpRequest, httpResponse);
		//content= StringUtil.compressHtml(content);
		Response response = new StringResponse();
		response.setContent(content);
		
		return response;
	}

	/**
	 * 
	 * @param uri
	 */
	public Response execute(String uri){
		return null;
	}

}