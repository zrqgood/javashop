package com.enation.eop.processor;


/**
 * 页面参数获取器
 * @author kingapex
 * 2010-2-10下午04:58:13
 */
public interface IPageParamJsonGetter {
	
	/**
	 * 给定一个页面uri,得到此页面挂件参数的json格式字串
	 * @param uri
	 * @return
	 */
	public String getJson(String uri);
	
}
