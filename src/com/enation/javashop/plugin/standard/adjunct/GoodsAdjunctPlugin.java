package com.enation.javashop.plugin.standard.adjunct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.AdjunctItem;
import com.enation.javashop.core.model.GoodsAdjunct;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;
import com.enation.javashop.core.service.IGoodsAdjunctManager;
import com.enation.javashop.core.service.IProductManager;

public class GoodsAdjunctPlugin extends AbstractGoodsPlugin {
	
	private IGoodsAdjunctManager goodsAdjunctManager;
	private IProductManager productManager;

	
	public void addTabs() {
		this.addTags(5, "配件");

	}

	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();

		freeMarkerPaser.setPageName("adjunct");
		return freeMarkerPaser.proessPageContent();
	}

	
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request)
			  {

	}

	
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = FreeMarkerPaser.getInstance();
		Integer goods_id =  Integer.valueOf(goods.get("goods_id").toString());
		List<Map> listGoodsAdjunct = goodsAdjunctManager.list(goods_id);
		for(Map map:listGoodsAdjunct){
			String json = String.valueOf(map.get("items"));
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<AdjunctItem> listAdjunctItem = new ArrayList<AdjunctItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Object o = jsonArray.get(i);
				JSONObject jsonObject = JSONObject.fromObject(o);
				AdjunctItem adjunctItem = (AdjunctItem) JSONObject.toBean(jsonObject, AdjunctItem.class  );
				listAdjunctItem.add(adjunctItem);
			}
			map.put("listAdjunctItem", listAdjunctItem);
		}
		freeMarkerPaser.putData("listGoodsAdjunct", listGoodsAdjunct);

		freeMarkerPaser.setPageName("adjunct");
		return freeMarkerPaser.proessPageContent();
	}

	
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
		save(goods);
	}

	
	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)
		 {
		save(goods);
	}
	
	private void save(Map goods){
		if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
		Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
		HttpServletRequest httpRequest =ThreadContextHolder.getHttpRequest();
		String[] adjunct_name = httpRequest.getParameterValues("adjunct.adjunct_name");
		String[] adjunct_minnum = httpRequest.getParameterValues("adjunct.min_num");
		String[] adjunct_maxnum = httpRequest.getParameterValues("adjunct.max_num");
		String[] adjunct_setprice = httpRequest.getParameterValues("pricetype");
		String[] adjunct_price = httpRequest.getParameterValues("adjunct.price");
		String[] manual = httpRequest.getParameterValues("complex.manual");
		
		
		List<GoodsAdjunct> list = new ArrayList<GoodsAdjunct>();
		if(adjunct_name!=null ){ 
		for(int i=0;i<adjunct_name.length;i++){
			String[] productids = httpRequest.getParameterValues("productid_" + i);
			GoodsAdjunct adjunct = new GoodsAdjunct();
		
			adjunct.setGoods_id(goodsId);
			adjunct.setAdjunct_name(adjunct_name[i]);
			adjunct.setMin_num((adjunct_minnum[i] == null ? null : Integer.valueOf(adjunct_minnum[i])));
			adjunct.setMax_num((adjunct_maxnum[i] == null ? null : Integer.valueOf(adjunct_maxnum[i])));
			adjunct.setSet_price(adjunct_setprice[i]);
			adjunct.setPrice((adjunct_price[i] == null ? null : Double.valueOf(adjunct_price[i])));
			adjunct.setItems( this.getItemJson(productids));
			list.add(adjunct);
		}
		}
		goodsAdjunctManager.save(goodsId, list);
	}
	
	
	private String getItemJson(String[] productids){
		
		Integer[] productidArray  = new Integer[productids.length];
		for(int i=0;i<productids.length;i++){
			productidArray[i] = Integer.valueOf(productids[i]);
			
		}
		List<Map> proList = this.productManager.list(productidArray);
		
		List<AdjunctItem> itemList  = new ArrayList<AdjunctItem>();
		for(Map pro: proList){
			Integer productid  =  Integer.valueOf(pro.get("product_id").toString() );
			Integer goodsid = Integer.valueOf( pro.get("goods_id").toString());
			String name = (String)pro.get("name");
			String specs = (String)pro.get("specs");
			Double price  = Double.valueOf( pro.get("price").toString());
			AdjunctItem adjunctItem = new AdjunctItem();
			adjunctItem.setProductid(productid);
			adjunctItem.setGoodsid(goodsid);
			adjunctItem.setName(name);
			adjunctItem.setSpecs(specs);
			adjunctItem.setPrice(price);
			itemList.add(adjunctItem);
			
		}
		
		return JSONArray.fromObject(itemList).toString();
	}

	
	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)
			 {
		
	}

	
	public String getAuthor() {
		return "kingapex";
	}

	
	public String getId() {
		return "goodsadjunct";
	}

	
	public String getName() {
		return "商品配件";
	}

	
	public String getType() {
		return "";
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {

	}

	public IGoodsAdjunctManager getGoodsAdjunctManager() {
		return goodsAdjunctManager;
	}

	public void setGoodsAdjunctManager(IGoodsAdjunctManager goodsAdjunctManager) {
		this.goodsAdjunctManager = goodsAdjunctManager;
	}

	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

}
