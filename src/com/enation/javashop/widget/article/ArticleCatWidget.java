package com.enation.javashop.widget.article;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.IArticleCatManager;

public class ArticleCatWidget extends AbstractWidget {
	
	private IArticleCatManager articleCatManager;

	
	protected void config(Map<String, String> params) {
		

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("articlecat");
		String cat_id = params.get("cat_id");
		List articleCatList = articleCatManager.listHelp(Integer.valueOf(cat_id));
		this.putData("articleCatList", articleCatList);
		this.putData("cat_id", cat_id);
	}

	public IArticleCatManager getArticleCatManager() {
		return articleCatManager;
	}

	public void setArticleCatManager(IArticleCatManager articleCatManager) {
		this.articleCatManager = articleCatManager;
	}

}
