package com.enation.javashop.plugin.promotion;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;
import com.enation.javashop.core.service.promotion.PromotionConditions;
import com.enation.javashop.core.service.promotion.PromotionType;

/**
 * 某商品翻倍积分
 * @author kingapex
 *2010-4-25下午10:39:13
 */
public class GoodsTimesPointPlugin extends AutoRegisterPlugin implements
		IPromotionPlugin {

	
	public void register() {

	}

	
	public String[] getConditions() {
		 
		return new String[]{ PromotionConditions.GOODS ,PromotionConditions.MEMBERLV};
	}

	
	public String getMethods() {
		return "timesPoint";
	}

	
	public String getAuthor() {
		return "kingapex";
	}

	
	public String getId() {
		return "goodsTimesPointPlugin";
	}

	
	public String getName() {
		return "积分翻倍——顾客购买指定的商品，可获得翻倍积分或者x倍积分";
	}

	
	public String getType() {
		return  PromotionType.PMTTYPE_GOODS;
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}

}
