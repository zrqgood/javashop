package com.enation.javashop.core.plugin.search;

import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.Cat;

/**
 * 多商品选择器接口
 * 实现此选择器接口，可创建多个商品过滤选择器
 * @author kingapex
 *
 */
public interface IMultiSelector {
	
	
	/**
	 * 创建多个选商品过滤选择器
	 * @param cat 当前搜索的商品分类
	 * @param url 当前的url
	 * @param urlFragment 当前属性的url片断
	 * @return
	 */
	public Map<String,List<SearchSelector>> createMultiSelector(Cat cat, String url,
			String urlFragment);
}
