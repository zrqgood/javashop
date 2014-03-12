package com.enation.javashop.core.service.promotion;

import java.util.List;

import com.enation.javashop.core.model.Promotion;

/**
 * 优惠行为-送赠品
 * @author kingapex
 *2010-4-15下午04:49:32
 */
public interface IGiveGiftBehavior  extends IPromotionBehavior{
	
	/**
	 * 送出赠品 
	 */
	public void giveGift(Promotion promotion,Integer orderId);
	
	
	public List getGiftList(Promotion promotion);
}
