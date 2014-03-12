package com.enation.app.base.core.plugin;

/**
 * 设置页面显示事件
 * @author kingapex
 *2010-3-3上午11:02:04
 */
public interface IOnSettingInputShow {
	
	/**
	 * 响应输入界面显示事件
	 * @return 对应插件输入界面名称
	 */
	public String onShow();
	
	
	
	/**
	 * 得到插件设置的组名
	 * @return
	 */
	public String getSettingGroupName();
	
	
}
