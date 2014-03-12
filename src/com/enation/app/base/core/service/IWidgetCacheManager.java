package com.enation.app.base.core.service;



/**
 * 挂件缓存业务
 * @author kingapex
 *
 */
public interface IWidgetCacheManager {
	
	/**
	 * 开启缓存
	 * 
	 */
	public void open();
	
	
	/**
	 * 关闭并刷新缓存
	 */
	public void close();
	
	
	/**
	 * 获取挂件缓存是否为打开状态
	 * @return 
	 */
	public boolean isOpen();
}
