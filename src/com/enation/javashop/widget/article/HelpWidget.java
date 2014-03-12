package com.enation.javashop.widget.article;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.model.Article;
import com.enation.javashop.core.service.IArticleManager;

public class HelpWidget extends AbstractWidget {
	
	private IArticleManager articleManager;

	
	protected void  display(Map<String, String> params) {
		this.setPageName("helpdetail");
		Integer article_id = this.getArticleId();
		Article article = articleManager.get(article_id);
		this.putData("article", article);
		 
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
}
