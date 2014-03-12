package com.enation.javashop.core.service.impl;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.javashop.core.model.Seo;
import com.enation.javashop.core.service.ISeoManager;

/**
 * SEO管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-19 上午10:39:52<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class SeoManager extends BaseSupport<Seo> implements ISeoManager {

	
	public Seo getSeo() {
		String sql = "select * from seo where id=1";
		Seo seo = null;
		try {
			seo = baseDaoSupport.queryForObject(sql, Seo.class);
		} catch (Exception e) {
			seo = new Seo();
			seo.setId(1);
			baseDaoSupport.insert("seo", seo);
		}
		return seo;
	}

	
	public void update(Seo seo) {
		this.baseDaoSupport.update("seo", seo, "id=1");
	}

}
