package com.enation.javashop.core.service;

import java.util.List;

import com.enation.javashop.core.model.ArticleCat;

/**
 * 文章分类管理
 * 
 * @author lzf<br/>
 *         2010-4-1 下午05:00:40<br/>
 *         version 1.0<br/>
 */
public interface IArticleCatManager {
	
	public ArticleCat getById(int cat_id);
	
	public void saveAdd(ArticleCat cat);
	
	public void update(ArticleCat cat);
	
	public int delete(int cat_id);
	
	public void saveSort(int[] cat_ids, int[] cat_sorts);
	
	public List listChildById(Integer cat_id);
	
	public List listHelp(int cat_id);
}
