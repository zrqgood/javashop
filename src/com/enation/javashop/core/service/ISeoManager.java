package com.enation.javashop.core.service;

import com.enation.javashop.core.model.Seo;

/**
 * SEO管理
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-19 上午10:21:57<br/>
 *         version 1.0<br/>
 * <br/>
 */
public interface ISeoManager {

	public Seo getSeo();
	public void update(Seo seo);
}
