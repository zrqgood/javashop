package com.enation.javashop.plugin.standard.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.GoodsLvPrice;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberPriceManager;

/***
 * 标准的商品价格插件
 * @author kingapex
 *
 */
public class StandardGoodsPricePlugin extends AbstractGoodsPlugin {

	private IMemberLvManager memberLvManager;
	private IMemberPriceManager memberPriceManager;
	public void addTabs() {
		this.addTags(1, "价格");
	}

	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
 
		freeMarkerPaser.setPageName("goods_price_input");
		return freeMarkerPaser.proessPageContent();
	 
	}

	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
	 
		List lvList  = this.memberLvManager.list();
		freeMarkerPaser.putData("lvList", lvList);
 
		freeMarkerPaser.setPageName("goods_price_input");
		return freeMarkerPaser.proessPageContent();
	}
	
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {
		HttpServletRequest  httpRequest = ThreadContextHolder.getHttpRequest();
		goods.put("cost", httpRequest.getParameter("cost") );
		goods.put("price", httpRequest.getParameter("price") );
		goods.put("weight", httpRequest.getParameter("weight") );
		goods.put("store", httpRequest.getParameter("store") );
		
		if(  StringUtil.isEmpty((String)goods.get("cost")) ){ goods.put("cost", 0);}
		if(StringUtil.isEmpty((String)goods.get("price"))){ goods.put("price", 0);}
		if(StringUtil.isEmpty((String)goods.get("weight"))){ goods.put("weight", 0);}
		if(StringUtil.isEmpty((String)goods.get("store"))){ goods.put("store", 0);}		

	}
	
	private  void processprice(Integer goodsid){
	
		HttpServletRequest  httpRequest = ThreadContextHolder.getHttpRequest();
		String[] lvPriceStr = httpRequest.getParameterValues("lvPrice");
		String[] lvidStr = httpRequest.getParameterValues("lvid");
		
		//生成会员价list
		if(lvidStr!=null && lvidStr.length>0){   
			List<GoodsLvPrice> priceList  =  createGoodsLvPrices(lvPriceStr, lvidStr, goodsid);
			this.memberPriceManager.save(priceList);
			 
		}  
		
	}
	
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
		Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
		this.processprice(goodsId);
	}

	
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request) {
		if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
		Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
		this.processprice(goodsId);	

	}
	
	 
	/**
	 * 根据会员级别id和会员价信息生成会员价list<br>
	 * 会员价格如果无空则不插入数据，即不生成会员价，而是按此会员级别的默认折扣计算会员价格,以减少冗余数据。
	 * 
	 * @param lvPriceStr 会员价数组，数组的值类型为字串，考虑到一般由request中获取。
	 * @param lvidStr 会员级别id数组，数组的值类型为字串，考虑到一般由request中获取。
	 * @param goodsid 当前商品id，没有填充productid,此插件只支持商品统一的会员价格
	 */
	/**
	 * 根据会员级别id和会员价信息生成会员价list<br>
	 * 会员价格如果无空则不插入数据，即不生成会员价，而是按此会员级别的默认折扣计算会员价格,以减少冗余数据。
	 * 
	 * @param lvPriceStr 会员价数组，数组的值类型为字串，考虑到一般由request中获取。
	 * @param lvidStr 会员级别id数组，数组的值类型为字串，考虑到一般由request中获取。
	 * @param goodsid 当前商品id，没有填充productid,此插件只支持商品的统一会员价格
	 * @return 生成的List<GoodsLvPrice>
	 */
	private List<GoodsLvPrice> createGoodsLvPrices(String[] lvPriceStr,String[] lvidStr,int goodsid ){
		List<GoodsLvPrice> goodsLvPrices = new ArrayList<GoodsLvPrice>();
		for(int i=0;i<lvidStr.length;i++){
			int lvid =StringUtil.toInt( lvidStr[i] );
			Double  lvPrice = StringUtil.toDouble( lvPriceStr[i] );
			
			if( lvPrice.doubleValue()!=0 ){//输入了会员价格
				GoodsLvPrice goodsLvPrice = new GoodsLvPrice();
				goodsLvPrice.setGoodsid(goodsid);
				goodsLvPrice.setPrice(lvPrice);
				goodsLvPrice.setLvid(lvid);
				goodsLvPrices.add(goodsLvPrice);
			}
			
		}
		return goodsLvPrices;
	}
	
	
	

	
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request) {
		HttpServletRequest  httpRequest = ThreadContextHolder.getHttpRequest();
		goods.put("cost", httpRequest.getParameter("cost") );
		goods.put("price", httpRequest.getParameter("price") );
		goods.put("weight", httpRequest.getParameter("weight") );
		goods.put("store", httpRequest.getParameter("store") );
		
		if(  StringUtil.isEmpty((String)goods.get("cost")) ){ goods.put("cost", 0);}
		if(StringUtil.isEmpty((String)goods.get("price"))){ goods.put("price", 0);}
		if(StringUtil.isEmpty((String)goods.get("weight"))){ goods.put("weight", 0);}
		if(StringUtil.isEmpty((String)goods.get("store"))){ goods.put("store", 0);}		
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "goods_price";
	}

	
	public String getName() {
		
		return "标准商品价格插件";
	}

	
	public String getType() {
		
		return "goods";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public void perform(Object... params) {
		

	}


	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}


	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}


	public IMemberPriceManager getMemberPriceManager() {
		return memberPriceManager;
	}


	public void setMemberPriceManager(IMemberPriceManager memberPriceManager) {
		this.memberPriceManager = memberPriceManager;
	}

}
