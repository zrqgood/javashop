package com.enation.javashop.core.service.promotion;

/**
 * 优惠方式执行器
 * @author kingapex
 *2010-4-19下午03:31:41
 */
public interface IBehaviorExecutor {
	
	/**
	 * 执行一个优惠方式
	 * @param promotionBehavior
	 * @return
	 */
	public Double execute(IPromotionBehavior promotionBehavior);
	
	
}
