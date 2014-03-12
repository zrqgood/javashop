package com.enation.javashop.core.service.impl;

import java.util.List;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Logi;
import com.enation.javashop.core.service.ILogiManager;
 

public class LogiManager extends BaseSupport<Logi> implements ILogiManager{
	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from logi_company where id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public Logi getLogiById(Integer id) {
		String sql  = "select * from logi_company where id=?";
		Logi a =  this.baseDaoSupport.queryForObject(sql, Logi.class, id);
		return a;
	}

	
	public Page pageLogi(String order, Integer page, Integer pageSize) {
		order = order == null ? " id desc" : order;
		String sql = "select * from logi_company";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public void saveAdd(String name) {
		Logi logi = new Logi();
		logi.setName(name);
		this.baseDaoSupport.insert("logi_company", logi);
		
	}

	
	public void saveEdit(Integer id, String name) {
		Logi logi = new Logi();
		logi.setName(name);
		logi.setId(id);
		this.baseDaoSupport.update("logi_company", logi, "id="+id);
	}

	
	public List list() {
		String sql = "select * from logi_company";
		return this.baseDaoSupport.queryForList(sql);
	}

	
}
