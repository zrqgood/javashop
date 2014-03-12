package com.enation.javashop.plugin.promotion;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;
import com.enation.javashop.core.service.promotion.PromotionConditions;
import com.enation.javashop.core.service.promotion.PromotionType;



/**
 * 购物车金额满多少就免运费
 * @author kingapex
 *
 */
public class EnoughPriceFreeDeliveryPlugin  extends AutoRegisterPlugin implements
IPromotionPlugin {

	
	public void register() {
		 
		
	}

	
	public String[] getConditions() {
		 
		return new String[]{ PromotionConditions.ORDER ,PromotionConditions.MEMBERLV};
	}

	
	public String getMethods() {
	 
		return "freeFreight";
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "enoughPriceFreeDeliveryPlugin";
	}

	
	public String getName() {
		
		return "免运费———购物车中商品总金额大于指定金额,免运费。";
	}

	
	public String getType() {
		
		return PromotionType.PMTTYPE_ORDER;
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public void perform(Object... params) {
		
		
	}
 

}
