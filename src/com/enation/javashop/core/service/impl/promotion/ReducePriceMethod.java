package com.enation.javashop.core.service.impl.promotion;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.CurrencyUtil;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.promotion.IPromotionMethod;
import com.enation.javashop.core.service.promotion.IReducePriceBehavior;

/**
 * 减价优惠方式
 * @author kingapex
 *2010-4-19下午04:03:42
 */
public class ReducePriceMethod implements IPromotionMethod,
		IReducePriceBehavior {

	
	public String getInputHtml(Integer pmtid, String solution) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		freeMarkerPaser.putData("lessMoney", solution);
		return freeMarkerPaser.proessPageContent();
	}

	
	public String getName() {
		return "reducePrice";
	}

	
	public String onPromotionSave(Integer pmtid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String reducePrice = request.getParameter("lessMoney");
		return reducePrice==null?"":reducePrice;
	}

	
	public Double reducedPrice(Promotion pmt,Double price) {
		String solution = pmt.getPmt_solution();
		Double  lessMoney =  Double.valueOf(solution);
		return CurrencyUtil.sub(price, lessMoney);
	}

}
