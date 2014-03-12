package com.enation.javashop.core.plugin.search;

import java.util.List;

import com.enation.javashop.core.model.Cat;

/**
 * 
 * 商品搜索过滤器
 * @author kingapex
 *
 */
public interface IGoodsSearchFilter {
	
 
	/**
	 * 生成 选择器列表
	 * @param catid 当前搜索的分类，如果为null则搜索全部类别
	 * @param url
	 * @param urlFragment
	 * @return
	 */
	public List<SearchSelector> createSelectorList(Cat cat,String url,String urlFragment);
	
	
	/**
	 * 对搜索条件进行过滤
	 * @param sql 要过滤的sql语句
	 * @param cat 当前搜索的分类，如果为null则搜索全部类别
	 * @pa urlFragment 当前属性的地址栏字串片断,如brand{1}
	 */
	public void filter(StringBuffer sql,Cat cat,String urlFragment);
	
	
	/**
	 * 定义过滤器id，和url中的名字匹配，如brand{1}的 为brand
	 * @return
	 */
	public String getFilterId();
	
	
	
}
