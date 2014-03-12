package com.enation.javashop.core.service.promotion;
/**
 * 优惠规则条件
 * @author kingapex
 *2010-4-19下午04:12:47
 */
public class PromotionConditions {
	
	public static final String ORDER ="order";
	public static final String MEMBERLV ="memberLv";
	public static final String GOODS = "goods";
	private boolean  hasOrder=false;
	private boolean  hasMemberLv=false;
	private boolean hasGoods=false;
	
	public PromotionConditions(String[] conditions){
		if(conditions!=null){
			for(String  cond : conditions){
				if(ORDER.equals(cond)){
					this.hasOrder= true;
				}
				
				if(MEMBERLV.equals(cond)){
					this.hasMemberLv =true;
				}
				
				if(GOODS.equals(cond)){
					this.hasGoods =true;
				}
			}
		} 
	}
	
	public boolean getHasOrder(){
		return this.hasOrder;
	}


	public boolean getHasMemberLv() {
		return hasMemberLv;
	}


	public void setHasMemberLv(boolean hasMemberLv) {
		this.hasMemberLv = hasMemberLv;
	}


	public void setHasOrder(boolean hasOrder) {
		this.hasOrder = hasOrder;
	}


	public boolean getHasGoods() {
		return hasGoods;
	}


	public void setHasGoods(boolean hasGoods) {
		this.hasGoods = hasGoods;
	}
	
	
}
