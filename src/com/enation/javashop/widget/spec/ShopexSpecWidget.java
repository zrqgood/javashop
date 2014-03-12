package com.enation.javashop.widget.spec;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.service.IProductManager;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * shopex式商品挂件
 * @author kingapex
 * 2010-2-4上午11:19:03
 */
public class ShopexSpecWidget extends AbstractGoodsDetailWidget {
 
	private IProductManager productManager;
	
	protected void config(Map<String, String> params) {
		
	}

 
	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	
	protected void execute(Map<String, String> params, Map goods) {
//		if( (""+goods.get("have_spec")).equals("0")){
//			this.showHtml=false;
//			return ;
//		}
		
		Integer goods_id = Integer.valueOf( goods.get("goods_id").toString() );
		List<Product> productList  = this.productManager.list(goods_id);
		
		if( (""+goods.get("have_spec")).equals("0")){
			this.putData("productid", productList.get(0).getProduct_id());
			this.putData("productList", productList);
		}else{
			List<Specification> specList = this.productManager.listSpecs(goods_id);
		
			this.putData("productList", productList);
			this.putData("specList", specList);
		}
		this.putData("have_spec", goods.get("have_spec"));
		this.putData("goods",goods);
		this.setPageName("spec");
		
	}

}
