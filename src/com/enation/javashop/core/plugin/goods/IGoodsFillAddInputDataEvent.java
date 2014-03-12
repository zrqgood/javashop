package com.enation.javashop.core.plugin.goods;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.plugin.IPlugin;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.service.IGoodsManager;

/**
 * 到商品添加前提供填充事件
 * @author kingapex
 *
 */
public  interface IGoodsFillAddInputDataEvent {
	 
	 public String onFillGoodsAddInput(HttpServletRequest request);
	
	
} 
