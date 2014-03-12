package com.enation.eop.resource.impl;

import java.util.List;

import com.enation.eop.resource.IWidgetBundleManager;
import com.enation.eop.resource.model.WidgetBundle;
import com.enation.eop.sdk.database.BaseSupport;

public class WidgetBundleManagerImpl extends BaseSupport<WidgetBundle> implements
		 IWidgetBundleManager {

	
	public WidgetBundle getWidgetBundle(String widgetType) {
		String sql ="select * from widgetbundle where widgettype=?";
		return this.baseDaoSupport.queryForObject(sql, WidgetBundle.class, widgetType);
	}

	
	public List<WidgetBundle> getWidgetbundleList() {
		String sql ="select * from widgetbundle";
		return baseDaoSupport.queryForList(sql, WidgetBundle.class);
	}

	
	public void add(WidgetBundle widgetBundle) {
		this.baseDaoSupport.insert("widgetbundle", widgetBundle);
	}

 

}
