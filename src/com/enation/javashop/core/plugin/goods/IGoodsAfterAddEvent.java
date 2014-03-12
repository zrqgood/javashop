package com.enation.javashop.core.plugin.goods;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品添加完成事件
 * 
 * @author kingapex
 * 
 */
public interface IGoodsAfterAddEvent {

	/**
	 * 商品表基本数据入库完成后激发此事件 <br/>响应此事件的方法可接收到添加成功后在商品数据。
	 * 
	 * @param goods
	 *            商品基本数据实体，其goods_id属性已经被赋为插入数据库的id
	 * @param request
	 *            HttpServletRequest 对象，可通过此对象获取用户的输入数据
	 * @param RuntimeException
	 *            事件如果抛出此异常，框架会处理此异常，中断商品的添加，并回滚之前的数据操作
	 */
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException;
	
	

}
