package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.PackageProduct;

/**
 * 捆绑商品
 * @author lzf<br/>
 * 2010-4-7 下午03:26:29<br/>
 * version 1.0<br/>
 */
public interface IPackageProductManager {
	
	public void add(PackageProduct packageProduct);
	
	public List list(int goods_id);
	
	public void add(Goods goods, int[] product_id, int[] pkgnum);
	
	public void edit(Goods goods, int[] product_id, int[] pkgnum);
	
}
