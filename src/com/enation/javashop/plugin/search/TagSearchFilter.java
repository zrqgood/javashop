package com.enation.javashop.plugin.search;

import java.util.List;
import java.util.Map;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.IPutWidgetParamsEvent;
import com.enation.javashop.core.plugin.search.SearchSelector;


/**
 * 商品标签搜索过虑器  
 * @author kingapex
 *
 */
public class TagSearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter ,IPutWidgetParamsEvent{

	public List<SearchSelector> createSelectorList(Cat cat, String url,
			String urlFragment) {
		
		return null;
	}


	public void filter(StringBuffer sql, Cat cat, String urlFragment) {
		if(!StringUtil.isEmpty(urlFragment))
		sql.append(" and goods_id in (select rel_id from es_tag_rel where tag_id in("+urlFragment +") )");
	}



	public void putParams(Map<String, Object> params, String urlFragment) {
		if(!StringUtil.isEmpty(urlFragment)){
			params.put("tag", urlFragment);
		}
	}
	
	
	public String getFilterId() {
		
		return "tag";
	}


	public String getAuthor() {
		
		return "kingapex";
	}


	public String getId() {
		
		return "tagSearchFilter";
	}


	public String getName() {
		
		return "商品标签搜索过虑器";
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
