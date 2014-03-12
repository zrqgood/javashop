package com.enation.javashop.plugin.standard.complex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.GoodsComplex;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;
import com.enation.javashop.core.service.IGoodsComplexManager;
import com.enation.javashop.core.service.IGoodsManager;

public class GoodsComplexPlugin extends AbstractGoodsPlugin {
	
	private IGoodsComplexManager goodsComplexManager;
	private IGoodsManager goodsManager;

	
	public void addTabs() {
		this.addTags(4, "相关商品");

	}

	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("complex");
//		Page pageGoods = goodsManager.searchGoods(null,null, null, null, 1, 20);
//		List listGoods = (List)pageGoods.getResult();
//		freeMarkerPaser.putData("listGoods", listGoods);
		return freeMarkerPaser.proessPageContent();
	}



	
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		List listGoodsComplex = goodsComplexManager.listComplex(goods_id);
		freeMarkerPaser.putData("listGoodsComplex", listGoodsComplex);
	//	Page pageGoods = goodsManager.searchGoods(null,null, null, null, 1, 20);
	//	Long pageCount = pageGoods.getTotalPageCount();
		//List listGoods = (List)pageGoods.getResult();
//		freeMarkerPaser.putData("listGoods", listGoods);
//		freeMarkerPaser.putData("pageCount", pageCount);
//		freeMarkerPaser.putData("page", 1);
//		freeMarkerPaser.putData("pageSize", 20);
		freeMarkerPaser.setPageName("complex");
		return freeMarkerPaser.proessPageContent();
	}
	
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request)
			{

	}
	
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
		Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
		HttpServletRequest httpRequest =ThreadContextHolder.getHttpRequest();
		String[] goods_2 = httpRequest.getParameterValues("complex.goods_2");
		String[] manual = httpRequest.getParameterValues("complex.manual");
		
		if(goods_2==null ) return ;
		
		List<GoodsComplex> list = new ArrayList<GoodsComplex>();
		for(int i=0;i<goods_2.length;i++){
			GoodsComplex complex = new GoodsComplex();
			complex.setGoods_1(goodsId);
			complex.setGoods_2(Integer.valueOf(goods_2[i]));
			complex.setManual(manual[i]);
			complex.setRate(100);
			list.add(complex);
		}
		goodsComplexManager.globalGoodsComplex(goodsId, list);
	}

	
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)
			  {
		if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
		Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
		HttpServletRequest httpRequest =ThreadContextHolder.getHttpRequest();
		String[] goods_2 = httpRequest.getParameterValues("complex.goods_2");
		String[] manual = httpRequest.getParameterValues("complex.manual");
		if(goods_2==null ) return ;
		List<GoodsComplex> list = new ArrayList<GoodsComplex>();
		for(int i=0;i<goods_2.length;i++){
			GoodsComplex complex = new GoodsComplex();
			complex.setGoods_1(goodsId);
			complex.setGoods_2(Integer.valueOf(goods_2[i]));
			complex.setManual(manual[i]);
			complex.setRate(100);
			list.add(complex);
		}
		goodsComplexManager.globalGoodsComplex(goodsId, list);
	}

	
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)
 {

	}

	
	public String getAuthor() {
		return "lzf";
	}

	
	public String getId() {
		return "goodscomplex";
	}

	
	public String getName() {
		return "相关商品插件";
	}

	
	public String getType() {
		return "";
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}

	public IGoodsComplexManager getGoodsComplexManager() {
		return goodsComplexManager;
	}

	public void setGoodsComplexManager(IGoodsComplexManager goodsComplexManager) {
		this.goodsComplexManager = goodsComplexManager;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

}
