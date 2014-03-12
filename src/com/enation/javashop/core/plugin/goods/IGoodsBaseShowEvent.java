package com.enation.javashop.core.plugin.goods;

import java.util.Map;

/**
 * 商品基本信息显示事件
 * @author kingapex
 * 2010-2-16下午03:33:59
 */
public interface IGoodsBaseShowEvent {
	
	/**
	 * 在商品基本数据显示时激发此事件
	 * @param goods 对应品数据的map
	 * @return 返回此插件html代码片段
	 */
	public String onBaseShow(Map goods);
}
