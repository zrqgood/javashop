package com.enation.javashop.widget.adjunct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.javashop.core.model.AdjunctItem;
import com.enation.javashop.core.service.IGoodsAdjunctManager;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * 商品配件挂件
 * @author kingapex
 *2010-5-14上午07:36:05
 */
public class GoodsAdjunctWidget extends AbstractGoodsDetailWidget {

	private IGoodsAdjunctManager goodsAdjunctManager;
	
	protected void config(Map<String, String> params) {

	}

 

	
	protected void execute(Map<String, String> params, Map goods) {
		
		Integer goods_id = (Integer)goods.get("goods_id");
		List<Map> adjList = this.goodsAdjunctManager.list(goods_id);

		for(Map map:adjList){
			String json = String.valueOf(map.get("items"));
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<AdjunctItem> itemList = new ArrayList<AdjunctItem>();
			for (int i = 0; i < jsonArray.size(); i++) {
				Object o = jsonArray.get(i);
				JSONObject jsonObject = JSONObject.fromObject(o);
				AdjunctItem adjunctItem = (AdjunctItem) JSONObject.toBean(jsonObject, AdjunctItem.class  );
				
				//计算优惠价
				Double price  = adjunctItem.getPrice();
				String type=(String)map.get("set_price");
				if(  "discount".equals(type) ){
					Double discount = ((BigDecimal)map.get("price")).doubleValue();
					adjunctItem.setCoupPrice( adjunctItem.getPrice()*discount );
				}
				
				if(  "minus".equals(type) ){
					Double discount = ((BigDecimal)map.get("price")).doubleValue();
					adjunctItem.setCoupPrice( adjunctItem.getPrice()-discount );
				}

				itemList.add(adjunctItem);
			}
			map.put("itemList", itemList);
		}
		
		if(adjList==null || adjList.isEmpty()){
			this.putData("hasAdj", false);
		}else{
			this.putData("hasAdj", true);
		}
		this.putData("adjList", adjList);
		
		
	}



	public IGoodsAdjunctManager getGoodsAdjunctManager() {
		return goodsAdjunctManager;
	}



	public void setGoodsAdjunctManager(IGoodsAdjunctManager goodsAdjunctManager) {
		this.goodsAdjunctManager = goodsAdjunctManager;
	}

}
