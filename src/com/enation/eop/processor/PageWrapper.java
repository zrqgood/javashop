package com.enation.eop.processor;


/**
 * 页面包装器
 * @author kingapex
 * 2010-2-9上午12:58:42
 */
public class PageWrapper implements IPagePaser {
	protected IPagePaser pagePaser;
	public PageWrapper(IPagePaser paser){
		this.pagePaser = paser;
	}
	
	public String pase(String url) {
		 
		return this.pagePaser.pase(url);
	}

}
