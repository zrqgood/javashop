package com.enation.javashop.widget.goodscat;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.IGoodsCatManager;

public class GoodsCat extends AbstractWidget {
	 
	private IGoodsCatManager goodsCatManager;
	
	protected void display(Map<String, String> params) {
		List<Cat> cat_tree =  goodsCatManager.listAllChildren(0);
		String catimage = params.get("catimage");
		boolean showimage  = catimage!= null &&catimage.equals("on") ?true:false;
		this.putData("showimg", showimage);
		this.putData("cat_tree", cat_tree);
	 
	}
	
	
	protected void config(Map<String, String> params) {
		
	}

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}
	
}
