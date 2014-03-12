package com.enation.javashop.plugin.search;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.IMultiSelector;
import com.enation.javashop.core.plugin.search.SearchSelector;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.core.utils.UrlUtils;


/**
 * 商品属性过滤器
 * @author kingapex
 *
 */
public class CustomPropertySearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter,IMultiSelector {
	private IGoodsTypeManager goodsTypeManager;
	
	public List<SearchSelector> createSelectorList(Cat cat, String url,
			String urlFragment) {
		
		return null;
	}
 
	public Map<String,List<SearchSelector>> createMultiSelector(Cat cat, String url,
			String urlFragment) {
		
		if(cat==null) return null; //未按分类搜索，无法进行属性过滤
//		url=url.replaceAll("prop\\{\\}", "");
//		if(!StringUtil.isEmpty(urlFragment ))
//				url=url+"prop{"+urlFragment+"}"; //拼合回原字串
		if(!StringUtil.isEmpty(urlFragment ))
		{
			url=UrlUtils.addUrl(url,"prop",urlFragment); //拼合回原字串
			url = "/search-" + UrlUtils.getParamStr(url);
		}
		
		List<Attribute> attrList = this.goodsTypeManager
		.getAttrListByTypeId(cat.getType_id()); // 这个类型的属性
		attrList= attrList== null ?new ArrayList<Attribute>():attrList;
	
			Map<String,List<SearchSelector>> map = new LinkedHashMap<String,List<SearchSelector>>();
			String[] s_ar = StringUtil.isEmpty(urlFragment )? new String[]{}:urlFragment.split(",") ;
			int i=0;
			
			for(Attribute attr: attrList){
				
				String attrName =attr.getName();
				
				if (attr.getType() == 3) { //递进式搜索的属性
					List<SearchSelector> selectorList = new ArrayList<SearchSelector>();
					String[] optionAr = attr.getOptionAr(); //此属性的选项
					int j=0;
					
					boolean haveSelected= false;
					SearchSelector allSelector = new SearchSelector();
					allSelector.setName("全部");
					allSelector.setSelected(false);
					allSelector.setUrl(UrlUtils.getPropExSelf(i, url) +".html");
					selectorList.add(allSelector);
					
					//为每个选项生成选择器
					for(String option:optionAr){
						
						
						SearchSelector selector = new SearchSelector();
						selector.setName(option);
						selector.setSelected(this.isSelected(s_ar, i, j));
						if(selector.getIsSelected()) haveSelected =true; //有选中的，标记
						
					 
						String propurl= UrlUtils.appendParamValue(url, this.getFilterId(), i+"_"+j);
						
//						selector.setUrl(propurl+".html");
						selector.setUrl(propurl);
						selectorList.add(selector);
						
						j++;
						//如果到最后也没有选中的属性,则选中全部
						if(j==optionAr.length && !haveSelected){
							allSelector.setSelected(true);
						}
						
					}
					
					map.put(attrName, selectorList);
				}
				
				i++;
			}
			return map;
	 
	}
	
	/***
	 * 判断某个属性项是否已经选中
	 * @param s_ar
	 * @param attrIndex
	 * @param optionIndex
	 * @return
	 */
	private boolean isSelected(String[] s_ar,int attrIndex,int optionIndex){
		for (int i = 0; i < s_ar.length; i++) {
			String[] value = s_ar[i].split("\\_");
			int attr_index = Integer.valueOf(value[0]).intValue();
			int option_index = Integer.valueOf(value[1]).intValue();
			
			if(attrIndex == attr_index && option_index== optionIndex){
				 return true;
			}
		}
		
		return false;
	}
	
	
	
	public void filter(StringBuffer sql,Cat cat, String urlFragment) {
		
		if(cat==null) return; //未按分类搜索，无法进行属性过滤
		if(StringUtil.isEmpty(urlFragment)) return;
		
		// 关于属性的过滤
		//属性值示例: 0_1,0_2
		if (urlFragment != null && !urlFragment.equals("")) {
			List<Attribute> prop_list = this.goodsTypeManager.getAttrListByTypeId(cat.getType_id()); // 这个类型的属性
			prop_list= prop_list== null ?new ArrayList<Attribute>():prop_list;			
			String[] s_ar = urlFragment.split(",");
	
			for (int i = 0; i < s_ar.length; i++) {
				String[] value = s_ar[i].split("\\_");
				int index = Integer.valueOf(value[0]).intValue();
				Attribute attr = prop_list.get(index);
				int type = attr.getType();
				if(type==2 || type==5 ) continue; //不可搜索跳过
				
				sql.append(" and g.p" + (index + 1));
								
				if(type==1){ //输入项搜索用like
					sql.append(" like'%");
					sql.append(value[1]);
					sql.append("%'");
				}
				if(type==3 || type==4){ //渐进式搜索直接=
					sql.append("='");
					sql.append(value[1]);
					sql.append("'");
				}				

			}
		} 

	}

	
	public String getFilterId() {
		
		return "prop";
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "goodsPropertySearchFilter";
	}

	
	public String getName() {
		
		return "商品属性过滤器";
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


	public IGoodsTypeManager getGoodsTypeManager() {
		return goodsTypeManager;
	}


	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}
	
}
