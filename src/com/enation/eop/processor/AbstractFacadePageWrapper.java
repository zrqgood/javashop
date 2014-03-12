package com.enation.eop.processor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.RequestWrapper;
import com.enation.eop.processor.core.Response;

/**
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 18:12:27
 */
public abstract class AbstractFacadePageWrapper extends AbstractWrapper {

	protected FacadePage page;

	/**
	 * 
	 * @param page
	 */
	public AbstractFacadePageWrapper(FacadePage page,Request request){
		 super(request);
		 this.page = page;
	}


}