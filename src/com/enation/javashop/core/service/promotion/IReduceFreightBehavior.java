package com.enation.javashop.core.service.promotion;


/**
 * 优惠-减免运费价行为接口
 * @author kingapex
 *2010-4-15下午04:40:57
 */
public interface IReduceFreightBehavior  extends IPromotionBehavior{
	
	
	/**
	 * 减价方法
	 * @param freight 原始费用
	 * @return 减免后的配送费用
	 */
	public Double reducedPrice(Double freight); 
	
	
}
