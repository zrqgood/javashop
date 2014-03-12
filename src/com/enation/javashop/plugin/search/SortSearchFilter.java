package com.enation.javashop.plugin.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.IGoodsSortFilter;
import com.enation.javashop.core.plugin.search.IPutWidgetParamsEvent;
import com.enation.javashop.core.plugin.search.SearchSelector;
import com.enation.javashop.core.utils.UrlUtils;

/***
 * 商品排序过滤器
 * @author kingapex
 *
 */
public class SortSearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter,IGoodsSortFilter,IPutWidgetParamsEvent {

	/**
	 * //价格从低到高    
            
//价格从高到低    
            
//评价从高到低    
            
//评价从低到高    
            
//销量从高到低    
            
//销量从低到高    
            
/上架时间从高到低   
            
//上架时间从低到高  
	 */
	private static final String[] sortItemList  =
		new String[]{"默认排序","价格从高到低","价格从低到高","评价从高到低","评价从低到高","销量从高到低","销量从低到高","上架时间从新到旧","上架时间从旧到新"};
	
	public List<SearchSelector> createSelectorList(Cat cat, String url,
			String urlFragment) {
		
		String sort  = urlFragment==null?"":urlFragment;
		
		List<SearchSelector>  selectorList  = new ArrayList<SearchSelector>();
		int i=0;
		for(String item:sortItemList){
			
			SearchSelector selector = new SearchSelector();
			selector.setName(item);
			selector.setUrl(UrlUtils.addUrl(url,"sort",""+(i+1)));
			
			if(sort.equals(String.valueOf( (i+1) ))){
				selector.setSelected(true);
				
			}
			selectorList.add(selector);
			i++;
		}
		
		return selectorList;
	}

	
	public void filter(StringBuffer sql, Cat cat, String urlFragment) {
		
		String order = urlFragment;
		if ("1".equals(order)) {  
			order = "sord desc";
		}  else if ("2".equals(order)) { //价格从高到低
			order = "price desc";
		}else if ("3".equals(order)) { //价格从低到高
			order = "price asc";
		} else if ("4".equals(order)) { //评价从高到低
			order = "grade desc";
		} else if ("5".equals(order)) { //评价从低到高
			order = "grade asc";
		} else if ("6".equals(order)) { //销量从高到低
			order = "buy_count desc";
		} else if ("7".equals(order)) { //销量从低到高
			order = "buy_count asc";
		}else if ("8".equals(order)) { //上架时间从高到低
			order = "last_modify desc";
		}else if ("9".equals(order)) {  //上架时间从低到高
			order = "last_modify asc";
		} else if (order == null || order.equals("") || order.equals("0")) {
			order = "sord desc";
		}

		sql.append(" order by "+ order);

	}

	
	public String getFilterId() {
		
		return "sort";
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "sortSearchFilter";
	}

	
	public String getName() {
		
		return "商品排序过滤器";
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


	public void putParams(Map<String, Object> params, String urlFragment) {
		params.put("sort", urlFragment);
	}
}
