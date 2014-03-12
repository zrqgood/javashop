package com.enation.javashop.core.service;

import java.util.Map;

import com.enation.framework.database.Page;

public interface IGoodsSearchManager2 {
	
	
	public Page search(int pageNo,int pageSize ,String url) ;
	
	
	public Map<String,Object > getSelector(String url);
	
	
	public void putParams(Map<String,Object> params,String url);
	
	
}
