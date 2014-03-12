package com.enation.javashop.core.plugin.promotion;

import com.enation.framework.plugin.IPlugin;

/**
 * 促销规则插件
 * @author kingapex
 *2010-4-15下午03:19:42
 */
public interface IPromotionPlugin extends IPlugin {
	
	/**
	 * 定义促销条件集合
	 * @return
	 */
	public String[] getConditions();
	
	
	/**
	 * 定义优惠方式
	 * @return 优惠方式bean名字
	 */
	public String getMethods();
	
	
	
}
