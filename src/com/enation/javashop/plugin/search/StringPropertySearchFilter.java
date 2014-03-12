package com.enation.javashop.plugin.search;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.SearchSelector;

/**
 * 字串属性搜索过虑器
 * @author kingapex
 *
 */
public class StringPropertySearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter {

	
	public void register() {

	}

	
	public List<SearchSelector> createSelectorList(Cat cat, String url,
			String urlFragment) {
		return null;
	}

	
	public void filter(StringBuffer sql, Cat cat, String urlFragment) {

	}

	
	public String getFilterId() {
		
		return "sprop";
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "stringPropertySearchFilter";
	}

	
	public String getName() {
		
		return "字串属性搜索过虑器";
	}

	
	public String getType() {
		
		return "searchFilter";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public void perform(Object... params) {
		

	}

}
