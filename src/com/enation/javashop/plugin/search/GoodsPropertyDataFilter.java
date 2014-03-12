package com.enation.javashop.plugin.search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.plugin.search.IGoodsDataFilter;

/**
 * 商品属性数据过滤器
 * @author kingapex
 *
 */
public class GoodsPropertyDataFilter extends AutoRegisterPlugin implements
		IGoodsDataFilter {

	
	public void filter(Map<String, Object> goods, ResultSet rs) {
		//lzf add
		Map propMap = new HashMap();
		
		for(int i=0;i<20;i++){
			String value;
			try {
				value = rs.getString("p" + (i+1));
				propMap.put("p"+(i+1),value);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		goods.put("propMap", propMap);
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "goodsPropertyDataFilter";
	}

	
	public String getName() {
		
		return "商品属性数据过滤器";
	}

	
	public String getType() {
		
		return "searchFilter";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public void perform(Object... params) {
		

	}
	public void register() {
		

	}
}
