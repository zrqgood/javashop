package com.enation.javashop.core.action.backend;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Seo;
import com.enation.javashop.core.service.ISeoManager;

/**
 * SEO
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-19 上午10:41:04<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class SeoAction extends WWAction {
	
	private ISeoManager seoManager;
	private Seo seo;

	
	public String execute() throws Exception {
		seo = seoManager.getSeo();
		return this.SUCCESS;
	}
	
	public String save() throws Exception {
		seoManager.update(seo);
		this.msgs.add("SEO信息修改成功");
		this.urls.put("SEO信息", "seo.do");
		return this.MESSAGE;
	}

	public ISeoManager getSeoManager() {
		return seoManager;
	}

	public void setSeoManager(ISeoManager seoManager) {
		this.seoManager = seoManager;
	}

	public Seo getSeo() {
		return seo;
	}

	public void setSeo(Seo seo) {
		this.seo = seo;
	}

	
}
