package com.enation.javashop.widget.article;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.model.Article;
import com.enation.javashop.core.model.ArticleCat;
import com.enation.javashop.core.service.IArticleCatManager;
import com.enation.javashop.core.service.IArticleManager;

public class ArticleWidget extends AbstractWidget {
	
	private IArticleManager articleManager;
	private IArticleCatManager articleCatManager; 
	
	protected void  display(Map<String, String> params) {
		this.setPageName("articledetail");
		Integer article_id = this.getArticleId();
		Article article = articleManager.get(article_id);
		this.putData("article", article);
		
		ArticleCat cat  = this.articleCatManager.getById(article.getCat_id());
		Nav nav = new Nav();
		nav.setTitle("首页");
		nav.setLink("index.html");
		nav.setTips("首页");
		this.putNav(nav);
		
		Nav nav1 = new Nav();
		nav1.setTitle(cat.getName() );
		nav1.setTips(cat.getName());
		this.putNav(nav1);	
		
		Nav nav2 = new Nav();
		nav2.setTitle(article.getTitle());
		nav2.setTips(article.getTitle());
		this.putNav(nav2);
		
	}
	
	protected void config(Map<String, String> params) {
		
		
	}
	
	private Integer getArticleId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		
		if(url.startsWith("/widget")) return 0;
		
		String article_id = this.paseArticleId(url);
		
		return Integer.valueOf(article_id);
	}

	private  static  String  paseArticleId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}
	public IArticleManager getArticleManager() {
		return articleManager;
	}
	public void setArticleManager(IArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	public IArticleCatManager getArticleCatManager() {
		return articleCatManager;
	}

	public void setArticleCatManager(IArticleCatManager articleCatManager) {
		this.articleCatManager = articleCatManager;
	}
	
}
