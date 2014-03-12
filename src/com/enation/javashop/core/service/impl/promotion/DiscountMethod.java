package com.enation.javashop.core.service.impl.promotion;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.CurrencyUtil;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.promotion.IDiscountBehavior;
import com.enation.javashop.core.service.promotion.IPromotionMethod;

/**
 * 对指定的商品直接打拆
 * @author kingapex
 *2010-4-18上午09:14:58
 */
public class DiscountMethod implements IPromotionMethod,IDiscountBehavior {

	
	public String getInputHtml(Integer pmtid, String solution) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		freeMarkerPaser.putData("discount",  solution );
		return freeMarkerPaser.proessPageContent();
	}

	
	public String getName() {
		return "discount";
	}

	
	public String onPromotionSave(Integer pmtid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String discount = request.getParameter("discount");
		return discount==null?"":discount;
	}

	
	public Double discount(Promotion promotion, Double  goodsPrice) {
		String solution = promotion.getPmt_solution();
		Double  discount =  Double.valueOf(solution);
		return CurrencyUtil.mul(goodsPrice, discount);
	}


}
