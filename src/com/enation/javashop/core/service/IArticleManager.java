package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Article;

/**
 * 文章管理
 * 
 * @author lzf<br/>
 *         2010-4-1 下午05:00:10<br/>
 *         version 1.0<br/>
 */
public interface IArticleManager {
	
	public void saveAdd(Article article);
	
	public void saveEdit(Article article);
	
	public Article get(Integer id);
	
	public List listByCatId(Integer cat_id);
	
	public Page pageByCatId(Integer pageNo, Integer pageSize, Integer cat_id);
	
	public List topListByCatId(Integer cat_id, Integer top_num);
	
	public String getCatName(Integer cat_id);
	
	public void delete(String id);

}
