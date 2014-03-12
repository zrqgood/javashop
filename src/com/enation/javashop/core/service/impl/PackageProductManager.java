package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.PackageProduct;
import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IPackageProductManager;
import com.enation.javashop.core.service.IProductManager;

/**
 * 捆绑商品列表
 * @author lzf<br/>
 * 2010-4-7 下午03:27:45<br/>
 * version 1.0<br/>
 */
public class PackageProductManager extends BaseSupport implements
		IPackageProductManager {
	
	private IGoodsManager goodsManager;
	private IProductManager productManager;
	
	
	public void add(PackageProduct packageProduct) {
		this.baseDaoSupport.insert("package_product", packageProduct);

	}

	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(Goods goods, int[] product_id, int[] pkgnum) {
		String sn = "P" + DateUtil.toString( new Date(System.currentTimeMillis()) ,"yyyyMMddhhmmss" );
		goods.setSn(sn);
		goods.setPoint(0);
		this.baseDaoSupport.insert("goods", goods);
		Integer goods_id = this.baseDaoSupport.getLastId("goods");
		for(int i=0;i<product_id.length;i++){
			PackageProduct product = new PackageProduct();
			product.setGoods_id(goods_id);
			product.setProduct_id(product_id[i]);
			product.setPkgnum(pkgnum[i]);
			this.add(product);
		}
		
		Product product = new Product();
		product.setGoods_id(goods_id);
		//product.setCost(  goods.get );
		product.setName(goods.getName());
		product.setPrice(   goods.getPrice() );
		product.setSn( sn);
		product.setStore(goods.getStore());
		product.setWeight(goods.getWeight());
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		this.productManager.add(productList);
		
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(Goods goods, int[] product_id,
			int[] pkgnum) {
		this.baseDaoSupport.update("goods", goods, "goods_id="+goods.getGoods_id());
		this.baseDaoSupport.execute("delete from package_product where goods_id = ?", goods.getGoods_id());
		for(int i=0;i<product_id.length;i++){
			PackageProduct product = new PackageProduct();
			product.setGoods_id(goods.getGoods_id());
			product.setProduct_id(product_id[i]);
			product.setPkgnum(pkgnum[i]);
			this.add(product);
		}

	 
		Product product = new Product();
		product.setGoods_id(goods.getGoods_id());
		product.setName(goods.getName());
		product.setPrice(   goods.getPrice() );
		product.setStore(goods.getStore());
		product.setWeight(goods.getWeight());
		product.setSn(goods.getSn());
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		this.productManager.add(productList);
		
	}

	
	public List list(int goods_id) {
		String sql = "select pp.*, p.product_id, p.sn, p.price, p.goods_id as pgoods_id, p.weight, g.name from " + this.getTableName("package_product") + " pp left join " + this.getTableName("product") + " p on p.product_id = pp.product_id left join " + this.getTableName("goods") + " g on g.goods_id = p.goods_id";
		sql += " where pp.goods_id = " + goods_id;
		List list = this.daoSupport.queryForList(sql);
		return list;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

}
