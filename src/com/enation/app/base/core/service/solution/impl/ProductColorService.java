package com.enation.app.base.core.service.solution.impl;

import java.util.List;

import com.enation.app.base.core.model.ProductColor;
import com.enation.app.base.core.service.solution.IProductColorService;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;

public class ProductColorService extends BaseSupport<ProductColor> implements
		IProductColorService {

	public void add(ProductColor color) {
		if(StringUtil.isEmpty(color.getColorname()) ) throw new IllegalArgumentException("argument  color.colorname is null");
		this.daoSupport.insert("eop_product_color", color);

	}

	public int detete(int id) {
		String sql ="select count(0) from eop_product where colorid=?";
		int count  = this.daoSupport.queryForInt(sql, id);
		if(count>0){
			return 2;
		}
		sql  ="delete from eop_product_color where id=?";
		this.daoSupport.execute(sql, id);
		return 1;
	}

	public void edit(ProductColor color) {
		if(color.getId()==0) throw new IllegalArgumentException("argument  color.id is null");
		if(StringUtil.isEmpty(color.getColorname()) ) throw new IllegalArgumentException("argument  color.colorname is null");
		this.daoSupport.update("eop_product_color", color, "id="+color.getId());

	}

	public ProductColor get(int id) {
		return this.daoSupport.queryForObject("select * from eop_product_color where id=?", ProductColor.class, id);
	}

	public List<ProductColor> list() {
		String sql  ="select * from eop_product_color order by id";
		return this.daoSupport.queryForList(sql, ProductColor.class);
	}

	public Page list(int pageNo, int pageSize) {
		String sql  ="select * from eop_product_color order by id";
		return this.daoSupport.queryForPage(sql, pageNo, pageSize);
	}

}
