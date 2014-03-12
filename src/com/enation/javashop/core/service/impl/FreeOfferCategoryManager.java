package com.enation.javashop.core.service.impl;

import java.util.List;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.FreeOffer;
import com.enation.javashop.core.model.FreeOfferCategory;
import com.enation.javashop.core.service.IFreeOfferCategoryManager;

/**
 * 赠品分类管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-18 上午10:14:22<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class FreeOfferCategoryManager extends BaseSupport<FreeOfferCategory>
		implements IFreeOfferCategoryManager {

	
	public void clean(String bid) {
		if (bid == null || bid.equals(""))
			return;
		String sql = "delete  from  freeoffer_category   where cat_id in ("
				+ bid + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public void delete(String bid) {
		if (bid == null || bid.equals(""))
			return;
		String sql = "update freeoffer_category set disabled=1  where cat_id in ("
				+ bid + ")";
		this.baseDaoSupport.execute(sql);

	}

	
	public FreeOfferCategory get(int cat_id) {
		String sql = "select * from freeoffer_category where cat_id=?";
		return baseDaoSupport.queryForObject(sql, FreeOfferCategory.class,
				cat_id);
	}

	
	public List getFreeOfferCategoryList() {
		String sql="select * from freeoffer_category";
		return baseDaoSupport.queryForList(sql);
	}

	
	public Page pageTrash(String name, String order, int page, int pageSize) {
		order = order == null ? " cat_id desc" : order;
		String sql = "select * from freeoffer_category";
		name = name == null ? " disabled=1 ": " disabled=1 and cat_name like '%" + name+ "%'";
		sql += " where " + name;
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public void revert(String bid) {
		if (bid == null || bid.equals(""))
			return;
		String sql = "update freeoffer_category set disabled=0  where cat_id in ("
				+ bid + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public void saveAdd(FreeOfferCategory freeOfferCategory) {
		this.baseDaoSupport.insert("freeoffer_category", freeOfferCategory);
	}

	
	public Page searchFreeOfferCategory(String name, String order, int page,
			int pageSize) {
		order = order == null ? " cat_id desc" : order;
		String sql = "select * from freeoffer_category";
		name = name == null ? " disabled=0 ": " disabled=0 and cat_name like '%" + name+ "%'";
		sql += " where " + name;
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public void update(FreeOfferCategory freeOfferCategory) {
		this.baseDaoSupport.update("freeoffer_category",
				freeOfferCategory, "cat_id="
						+ freeOfferCategory.getCat_id());

	}

}
