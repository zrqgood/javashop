package com.enation.javashop.core.plugin.field;

import java.util.Map;

/**
 * 商品数据保存事件
 * @author kingapex
 *
 */
public interface IFieldSaveEvent {
	/**
	 * 字段数据保存事件接口定义
	 * @param goods  商品数据 
	 * @param field  商品字段插件
	 */
	public void onSave(Map goods,GoodsField field );
}
