package com.enation.javashop.core.service.promotion;

import com.enation.javashop.core.model.Promotion;

/**
 * 商品直接打折
 * @author kingapex
 *2010-4-18上午10:04:08
 */
public interface IDiscountBehavior extends IPromotionBehavior {
	
	
	
	/**
	 * 计算一个商品打折后的价格
	 * @param promotion
	 * @param goodsPrice 商品价格
	 * @return 优惠后的商品价格
	 */
	public Double discount(Promotion promotion,Double  goodsPrice);

	
}
