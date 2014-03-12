package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IPackageProductManager;
import com.enation.javashop.core.service.IProductManager;

/**
 * 捆绑商品
 * 
 * @author lzf<br/>
 *         2010-4-7 下午06:11:01<br/>
 *         version 1.0<br/>
 */
public class BindAction extends WWAction {
	protected String name;
	protected String sn;
	protected String order;
	protected IGoodsManager goodsManager;
	protected int goods_id;
	protected Page productPage;
	protected IProductManager productManager;
	protected IPackageProductManager packageProductManager;
	protected int[] product_id;
	protected int[] pkgnum;
	protected Goods bind;
	protected List packageList;

	public String bindlist() {
		this.webpage = goodsManager.searchBindGoods(name, sn, order, this
				.getPage(), this.getPageSize());
		return "bindlist";
	}

	public String delete() {
		try {
			this.goodsManager.delete(new Integer[] { goods_id });
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String add() {
		//productPage = productManager.list(1, 10, null);
		return "add";
	}
	
	public String edit() {
		//productPage = productManager.list(1, 10, null);
		bind = goodsManager.getGoods(goods_id);
		packageList = packageProductManager.list(goods_id);
		return "edit";
	}

	public String addSave() {
		try {
			bind.setCreate_time(System.currentTimeMillis());
			bind.setLast_modify(System.currentTimeMillis());
			bind.setView_count(0);
			bind.setBuy_count(0);
			bind.setGoods_type("bind");
			bind.setDisabled(0);
			packageProductManager.add(bind, product_id, pkgnum);
			this.msgs.add("捆绑商品添加成功");
			this.urls.put("捆绑商品列表", "bind!bindlist.do");
		} catch (RuntimeException e) {
			this.msgs.add("捆绑商品添加失败");
			this.urls.put("捆绑商品列表", "bind!bindlist.do");
		}
		return this.MESSAGE;
	}
	
	public String editSave() {
		try {
			bind.setLast_modify(System.currentTimeMillis());
			packageProductManager.edit(bind, product_id, pkgnum);
			this.msgs.add("捆绑商品修改成功");
			this.urls.put("捆绑商品列表", "bind!bindlist.do");
		} catch (RuntimeException e) {
			this.msgs.add("捆绑商品修改失败");
			this.urls.put("捆绑商品列表", "bind!bindlist.do");
		}
		return this.MESSAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goodsId) {
		goods_id = goodsId;
	}

	public Page getProductPage() {
		return productPage;
	}

	public void setProductPage(Page productPage) {
		this.productPage = productPage;
	}

	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	public int[] getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int[] productId) {
		product_id = productId;
	}

	public int[] getPkgnum() {
		return pkgnum;
	}

	public void setPkgnum(int[] pkgnum) {
		this.pkgnum = pkgnum;
	}

	public Goods getBind() {
		return bind;
	}

	public void setBind(Goods bind) {
		this.bind = bind;
	}

	public IPackageProductManager getPackageProductManager() {
		return packageProductManager;
	}

	public void setPackageProductManager(
			IPackageProductManager packageProductManager) {
		this.packageProductManager = packageProductManager;
	}

	public List getPackageList() {
		return packageList;
	}

	public void setPackageList(List packageList) {
		this.packageList = packageList;
	}

	

}
