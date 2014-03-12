package com.enation.javashop.core.action.backend;

import java.util.List;

import net.sf.json.JSONArray;

import com.enation.app.base.core.model.MemberLv;
import com.enation.framework.action.WWAction;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberPriceManager;

public class MemberPriceAction extends WWAction {
	
	private Double price;
	private int goodsid;
	private int productid;
	private IMemberLvManager memberLvManager;
	private IMemberPriceManager memberPriceManager;
	private List<MemberLv > lvList;
	
	/**
	 * 显示会员价格输入对话框
	 */
	public String execute(){
		this.processLv();
		return "list";
	}

	/**
	 * 显示会员价格input列表，在生成规格时填充所用
	 * @return
	 */
	public String disLvInput(){
		this.processLv();
		return "dis_lv_input";
	}
	
	public String getLvPriceJson(){
		
		try{
			List priceList  = this.memberPriceManager.listPriceByGid(goodsid);
			JSONArray jsonArray = JSONArray.fromObject( priceList );  
			this.json = "{result:1,priceData:"+jsonArray.toString()+"}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e.fillInStackTrace());
			this.json = "{result:0,message:"+e.getMessage()+"}";
		}
		return this.JSON_MESSAGE;
	}
	
	private void processLv(){
		lvList = memberLvManager.list();
		price= price==null?0:price;
		for(MemberLv lv:lvList){
			int discount = lv.getDiscount();
			 lv.setLvPrice(price*discount/100);
		}
		
		
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}

	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}

	public List<MemberLv> getLvList() {
		return lvList;
	}

	public void setLvList(List<MemberLv> lvList) {
		this.lvList = lvList;
	}

	public IMemberPriceManager getMemberPriceManager() {
		return memberPriceManager;
	}

	public void setMemberPriceManager(IMemberPriceManager memberPriceManager) {
		this.memberPriceManager = memberPriceManager;
	}
	
	
	
}
