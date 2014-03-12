package com.enation.javashop.widget.jiushengcat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.utils.UrlUtils;

public class JiusshengCatWidget extends AbstractWidget {

	private IGoodsCatManager goodsCatManager;
	
	protected void display(Map<String, String> params) {
		
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String uri = request.getServletPath();
		
		String catidstr = UrlUtils.getParamStringValue(uri,"cat");
		if(StringUtil.isEmpty(catidstr)){
			catidstr =  "0";
		}
		List<Cat> cat_tree =  goodsCatManager.listAllChildren(0);
		
		String catimage = params.get("catimage");
		boolean showimage  = catimage!= null &&catimage.equals("on") ?true:false;
		this.putData("showimg", showimage);
		this.putData("cat_tree", cat_tree);
		this.putData("curr_cat_id",Integer.valueOf(catidstr));
		
	 
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
