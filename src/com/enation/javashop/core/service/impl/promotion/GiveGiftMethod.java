package com.enation.javashop.core.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.FreeOffer;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.promotion.IGiveGiftBehavior;
import com.enation.javashop.core.service.promotion.IPromotionMethod;

/**
 * 优惠方式--送赠品
 * @author kingapex
 *2010-4-15下午04:59:08
 */
public class GiveGiftMethod extends BaseSupport<FreeOffer> implements IPromotionMethod, IGiveGiftBehavior {
	
	private IFreeOfferManager freeOfferManager;
	
	public String getInputHtml(Integer pmtid, String solution) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(this.getClass());
		if(solution!=null){
			Object[] giftIdArray=   JSONArray.fromObject(solution).toArray();
			if(giftIdArray!=null){
				Integer[] giftIds = new Integer[giftIdArray.length];
				int i=0;
				for(Object giftId: giftIdArray){
					giftIds[i] =  Integer.valueOf(giftId.toString());
					i++;
				}
				List giftList  = freeOfferManager.list(giftIds);
				freeMarkerPaser.putData("giftList", giftList);
			}
				
		}

		return freeMarkerPaser.proessPageContent();
	}

	
	public String getName() {
		
		return "giveGift";
	}

	
	public String onPromotionSave(Integer pmtid) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String[] giveGift = request.getParameterValues("giftidArray");
		if(giveGift==null) throw new  IllegalArgumentException("失败，请添加赠品！");
		return JSONArray.fromObject(giveGift).toString();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public void giveGift(Promotion promotion,Integer orderId) {
		List<Map> giftList  = this.getGiftList(promotion);
		
		for(Map gift:giftList){
			this.baseDaoSupport.execute("insert into order_gift(order_id,gift_id,gift_name,point,num,shipnum,getmethod)values(?,?,?,?,?,?,?)", 
								orderId,gift.get("fo_id"),gift.get("fo_name"),gift.get("score"),1,0,"present");
		}
	 
	}

	
	public List getGiftList(Promotion promotion) {
		String solution =  promotion.getPmt_solution();
		if(solution== null || "".equals(solution)) return null;
		
		JSONArray jsonArray = JSONArray.fromObject( solution);  
	 	Object[] giftIdArray = jsonArray.toArray();
	 	
	 	String sql ="select * from freeoffer where fo_id in("+ StringUtil.arrayToString(giftIdArray, ",") +") ";
		return this.baseDaoSupport.queryForList(sql);
	}

	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}

	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}
	
	
}
