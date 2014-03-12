package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Article;
import com.enation.javashop.core.service.IArticleCatManager;
import com.enation.javashop.core.service.IArticleManager;
import com.enation.javashop.core.service.impl.ArticleRuntimeException;

/**
 * 文章action
 * 
 * @author apexking
 * 
 */
public class ArticleAction extends WWAction {
	private Article article;

	private IArticleCatManager articleCatManager;
	private IArticleManager articleManager;
	private Integer cat_id;
	private List catList;
	private List articleList;
	private int article_id;
	private String id; // 批量删除时用
	
	public String list(){
		articleList = articleManager.listByCatId(cat_id);
		return "list";
	}
	
	public String delete(){
		try {
			this.articleManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String add() {
		catList = this.articleCatManager.listChildById(0);
		return "add_input";
	}
	
	public String saveAdd(){
		if(article.getCat_id() == null){
			this.msgs.add("文章分类不能为空");
			this.urls.put("文章列表", "article!list.do?cat_id="+ article.getCat_id());
			return this.MESSAGE;
		}
		try{
		    articleManager.saveAdd(article);
		}catch(ArticleRuntimeException ex){
			this.msgs.add("文章分类不存在");
			this.urls.put("文章列表", "article!list.do?cat_id="+ article.getCat_id());
			return this.MESSAGE;
		}
		this.msgs.add("文章添加成功");
		this.urls.put("文章列表", "article!list.do?cat_id="+ article.getCat_id());
		return this.MESSAGE;
	}
	
	
	public String edit() {
		article = this.articleManager.get(article_id);
		catList = this.articleCatManager.listChildById(0);
		
		return "edit_input";
	}
	
	public String saveEdit(){
		if(article.getCat_id() == null){
			this.msgs.add("文章分类不能为空");
			this.urls.put("文章列表", "article!list.do?cat_id="+ article.getCat_id());
			return this.MESSAGE;
		}
		
		try{
			articleManager.saveEdit(article);
		}catch(ArticleRuntimeException ex){
			this.msgs.add("文章分类不存在");
			this.urls.put("文章列表", "article!list.do?cat_id="+ article.getCat_id());
			return this.MESSAGE;
		}
		this.msgs.add("文章修改成功");
		this.urls.put("文章列表", "article!list.do?cat_id="+ article.getCat_id());
		return this.MESSAGE;
	}
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	public IArticleCatManager getArticleCatManager() {
		return articleCatManager;
	}

	public void setArticleCatManager(IArticleCatManager articleCatManager) {
		this.articleCatManager = articleCatManager;
	}

	public List getCatList() {
		return catList;
	}

	public void setCatList(List catList) {
		this.catList = catList;
	}

	public List getArticleList() {
		return articleList;
	}

	public void setArticleList(List articleList) {
		this.articleList = articleList;
	}

	public IArticleManager getArticleManager() {
		return articleManager;
	}

	public void setArticleManager(IArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int articleId) {
		article_id = articleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
