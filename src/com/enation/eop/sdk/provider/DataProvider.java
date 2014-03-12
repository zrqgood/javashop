package com.enation.eop.sdk.provider;

import java.util.Map;

/**
 * @author kingapex
 * @version 1.0
 * @created 06-十一月-2009 13:14:39
 */
public interface DataProvider {

	/**
	 * 
	 * @param param
	 */
	public Object load(Map<String,String> params);

}