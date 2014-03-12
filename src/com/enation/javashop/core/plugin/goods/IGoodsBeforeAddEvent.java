package com.enation.javashop.core.plugin.goods;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品添加前事件
 * 
 * @author kingapex
 * 
 */
public interface IGoodsBeforeAddEvent {

	/**
	 * 在商品数据入库之前激发此事件 <br/>为商品数据入库前提供更改、补充商品数据的机会。
	 * 
	 * @param goods
	 *            商品数据
	 * @param request
	 *            HttpServletRequest 对象，可通过此对象获取用户的输入数据
	 * @throws GoodsRuntimeException
	 *             事件如果抛出此异常，框架会处理此异常，中断商品的添加，并回滚之前的数据操作
	 */
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request);

}
