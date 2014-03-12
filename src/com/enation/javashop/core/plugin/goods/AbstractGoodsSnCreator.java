package com.enation.javashop.core.plugin.goods;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.IPlugin;

/**
 * 商品货号生成插件抽像类
 * @author kingapex
 *
 */
public abstract class AbstractGoodsSnCreator extends AutoRegisterPlugin implements IPlugin , IGoodsBeforeAddEvent,IGoodsBeforeEditEvent   {

 
	
	
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {
		goods.put( "sn", createSn(goods) );
	}
	
	
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
	 
		goods.put( "sn", createSn(goods) );
	}


	/**
	 * 生成货号的算法
	 * @param goods
	 * @return
	 */
	 public abstract String createSn(Map goods);
	 
	 
}
