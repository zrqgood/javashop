package com.enation.javashop.core.service.impl.promotion;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.promotion.IPromotionMethod;
import com.enation.javashop.core.service.promotion.IReduceFreightBehavior;

/**
 * 免运费方法 
 * @author kingapex
 *
 */
public class FreeFreightMethod  implements IPromotionMethod,IReduceFreightBehavior {

	
	public String getInputHtml(Integer pmtid, String solution) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		freeMarkerPaser.putData("free",  solution );
		return freeMarkerPaser.proessPageContent();
	}

	
	public String getName() {
		
		return "free";
	}

	
	public String onPromotionSave(Integer pmtid) {
		
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String free = request.getParameter("free");
		return free==null?"":free;
	}


	
	public Double reducedPrice( Double freight) {
		return 0D;
	}

	
 

}
