package com.enation.eop.resource.impl;

import java.util.List;

import com.enation.eop.resource.IIndexItemManager;
import com.enation.eop.resource.model.IndexItem;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;


/**
 * 首页项管理实现
 * @author kingapex
 * 2010-10-12下午04:00:31
 */
public class IndexItemManager extends BaseSupport<IndexItem> implements IIndexItemManager {
	
	/**
	 * 添加首页项
	 */
	public void add(IndexItem item) {
		this.baseDaoSupport.insert("index_item", item);
	}

	
	/**
	 * 读取首页项列表
	 */
	public List<IndexItem> list() {
		String sql  ="select * from index_item order by sort";
		return this.baseDaoSupport.queryForList(sql, IndexItem.class);
	}
	
	public void clean() {
		this.baseDaoSupport.execute("truncate table index_item");
	}
	

 
}
