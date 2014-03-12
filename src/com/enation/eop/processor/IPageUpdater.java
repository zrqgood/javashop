package com.enation.eop.processor;


/**
 * 页面更新接口
 * @author kingapex
 * 2010-2-10下午03:54:57
 */
public interface IPageUpdater {
	
	/**
	 * 给定页面url和挂件参数json字串，更新页面内容
	 * @param url
	 * @param paramJson
	 */
	public void update(String url,String pageContent,String paramJson);
	
}
