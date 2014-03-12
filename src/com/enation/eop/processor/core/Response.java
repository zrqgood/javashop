package com.enation.eop.processor.core;

import java.io.InputStream;

/**
 * @author kingapex
 * @version 1.0
 * @created 09-十月-2009 22:22:30
 */
public interface Response {

	public String getContent();
	
	public InputStream getInputStream();

	public String getStatusCode();

	public String getContentType();
	
	

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content);

	/**
	 * 
	 * @param code
	 */
	public void setStatusCode(String code);

	/**
	 * 
	 * @param contentType
	 */
	public void setContentType(String contentType);

}