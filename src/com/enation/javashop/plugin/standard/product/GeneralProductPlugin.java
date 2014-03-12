package com.enation.javashop.plugin.standard.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.plugin.goods.IGoodsAfterAddEvent;
import com.enation.javashop.core.plugin.goods.IGoodsAfterEditEvent;
import com.enation.javashop.core.plugin.goods.IGoodsDeleteEvent;
import com.enation.javashop.core.service.IProductManager;


/**
 * 普通货品插件
 * @author kingapex
 * @date 2011-11-6 下午4:34:51 
 * @version V1.0
 */
public class GeneralProductPlugin extends AutoRegisterPlugin implements IGoodsAfterAddEvent,IGoodsAfterEditEvent,IGoodsDeleteEvent {
	 
	private IProductManager productManager;

	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)
			throws RuntimeException {
			if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
				Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
				Integer brandid =null;
				if(goods.get("brand_id")!=null){
					brandid = Integer.valueOf( goods.get("brand_id").toString() );
				}
				String sn=(String)goods.get("sn");
				
				Product product = new Product();
				product.setGoods_id(goodsId);
				product.setCost(  Double.valueOf( ""+goods.get("cost") ) );
				product.setPrice(   Double.valueOf( ""+goods.get("price"))  );
				product.setSn(sn);
				product.setStore(Integer.valueOf(""+goods.get("store")));
				product.setWeight(Double.valueOf( ""+goods.get("weight")));
				product.setName((String)goods.get("name"));
				 
				List<Product> productList = new ArrayList<Product>();
				productList.add(product);
				this.productManager.add(productList);
				
	}

	public void onAfterGoodsEdit(Map goods, HttpServletRequest request) {
				if(goods.get("goods_id")==null) throw new RuntimeException("商品id不能为空");
					Integer goodsId = Integer.valueOf(goods.get("goods_id").toString());
					Product product = this.productManager.getByGoodsId(goodsId);
					product.setGoods_id(goodsId);
					product.setCost(  Double.valueOf( ""+goods.get("cost") ) );
					product.setPrice(   Double.valueOf( ""+goods.get("price"))  );
					product.setSn((String)goods.get("sn"));
					product.setStore(Integer.valueOf(""+goods.get("store")));
					product.setWeight(Double.valueOf( ""+goods.get("weight")));
					product.setName((String)goods.get("name"));
					 
					List<Product> productList = new ArrayList<Product>();
					productList.add(product);
					this.productManager.add(productList);
					
	}

 

	public String getAuthor() {
		
		return "kingapex";
	}

	public String getId() {
		
		return "general_product";
	}

	public String getName() {
		
		return "一般货品插件";
	}

	public String getType() {
		
		return "goods";
	}

	public String getVersion() {
		
		return "1.0";
	}

	public void perform(Object... params) {
		

	}


	public IProductManager getProductManager() {
		return productManager;
	}


	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}


	public void onGoodsDelete(Integer[] goodsid) {
		 this.productManager.delete(goodsid);
	}

	@Override
	public void register() {
		
	}
	
	

}
