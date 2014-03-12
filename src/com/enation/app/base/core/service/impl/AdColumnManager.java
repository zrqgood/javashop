package com.enation.app.base.core.service.impl;

import java.util.List;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.service.IAdColumnManager;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;

/**
 * 广告位管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-2-4 下午03:56:01<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class AdColumnManager extends BaseSupport<AdColumn> implements
		IAdColumnManager {

	
	public void addAdvc(AdColumn adColumn) {
		this.baseDaoSupport.insert("adcolumn", adColumn);
	}

	
	public void delAdcs(String ids) {
		if (ids == null || ids.equals(""))
			return;
		String sql = "delete from adcolumn where acid in (" + ids
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public AdColumn getADcolumnDetail(Long acid) {
		AdColumn  adColumn = this.baseDaoSupport.queryForObject("select * from adcolumn where acid = ?", AdColumn.class, acid);
		return adColumn;
	}

	
	public List listAllAdvPos() {
		List<AdColumn> list = this.baseDaoSupport.queryForList("select * from adcolumn", AdColumn.class);
		return list;
	}

	
	public Page pageAdvPos(int page, int pageSize) {
		String sql = "select * from adcolumn";
		Page rpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return rpage;
	}

	
	public void updateAdvc(AdColumn adColumn) {
		this.baseDaoSupport.update("adcolumn", adColumn, "acid = " + adColumn.getAcid());
	}

}
