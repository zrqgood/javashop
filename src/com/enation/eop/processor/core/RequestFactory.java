package com.enation.eop.processor.core;

import com.enation.eop.sdk.context.ConnectType;


/**
 * @author kingapex
 * @version 1.0
 * @created 09-十月-2009 23:08:48
 */
public abstract class RequestFactory {




	public static Request getRequest(int model){
		
		
		Request request = null;
		
		if(model==ConnectType.remote)
			request = new RemoteRequest();
		
		if(model==ConnectType.local)
			request = new LocalRequest();
		
		return request;
	}

}