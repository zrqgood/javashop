package com.enation.javashop.core.plugin.goods;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品修改前事件
 * 
 * @author kingapex
 * 
 */
public interface IGoodsBeforeEditEvent {
 
	
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request);

}
