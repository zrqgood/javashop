package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.framework.database.Page;

public interface IGoodsSearchManager {
	
	
	public Page search( int page, int pageSize,Map<String,String> params);

	
	

	/**
	 * 读取某个类别下的属性列表<br/> 同时计算每个属性的商品数量
	 * 
	 * @param type_id
	 * @param cat_path
	 * @return
	 */
	public List[] getPropListByCat(final int type_id, String cat_path,
			String brand_str, String propStr,String attrStr);
	
	
	
	
}
