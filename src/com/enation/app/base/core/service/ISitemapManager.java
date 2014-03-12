package com.enation.app.base.core.service;

import com.enation.app.base.core.model.SiteMapUrl;

/**
 * Sitemap接口
 * 
 * @author lzf<br/>
 *         2010-11-30 下午04:46:56<br/>
 *         version 2.1.5 <br/>
 *         Usage: <br/>
 *         init(); <br/>
 *         add(url);//if need <br/>
 *         edit(url);// if need <br/>
 *         getsitemap();//最终的xml格式的串
 */
public interface ISitemapManager {

	/**
	 * 取得sitemap的文字串(xml格式)
	 * 
	 * @return
	 */
	public String getsitemap();

	/**
	 * 向sitemap中加入一个url
	 * 
	 * @param url
	 */
	public void addUrl(SiteMapUrl url);

	/**
	 * 修改sitemap中的url
	 * 
	 * @param url
	 */
	public void editUrl(String loc, Long lastmod);
	
	
	
	public int delete(String loc);
	
	public void clean();

	

}
