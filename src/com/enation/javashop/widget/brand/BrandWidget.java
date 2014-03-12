package com.enation.javashop.widget.brand;

import java.util.List;
import java.util.Map;

import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.IBrandManager;

/**
 * 品牌
 * 
 * @author lzf<br/>
 *         2010-4-9下午05:29:09<br/>
 *         version 1.0
 */
public class BrandWidget extends AbstractWidget {
	
	private IBrandManager brandManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("brand");
		List listBrand = brandManager.list();
		this.putData("listBrand", listBrand);
		Nav nav = new Nav();
		nav.setTitle("品牌专区");
		this.putNav(nav);
	}

	public IBrandManager getBrandManager() {
		return brandManager;
	}

	public void setBrandManager(IBrandManager brandManager) {
		this.brandManager = brandManager;
	}

}
