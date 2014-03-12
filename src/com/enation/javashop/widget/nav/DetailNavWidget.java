package com.enation.javashop.widget.nav;

import java.util.Map;

import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.IGoodsCatManager;

/**
 * 美睛详细页导航
 * @author kingapex
 *
 */
public class DetailNavWidget extends AbstractWidget {
	
	private IGoodsCatManager goodsCatManager;
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		if(goods==null) throw new RuntimeException("参数显示挂件必须和商品详细显示挂件同时存在");
		
		StringBuffer navHtml = new StringBuffer();
		int catid  = (Integer)goods.get("cat_id");
		Integer brandid = (Integer)goods.get("brand_id");
		
		
		String catname = this.goodsCatManager.getById(catid).getName();
		String brandname =(String) goods.get("brand_name");
		
		
		String catstr  = this.getCatStr(catid);
		String brandstr  = this.getBrandStr(brandid);
		
		navHtml.append("<span><a href=\"index.html\">首页</a></span>&gt;");
		
		
		//类别
		navHtml.append("<span><a href='");
		navHtml.append(catstr);
		navHtml.append(".html'>");
		navHtml.append(catname);
		navHtml.append("</a></span>");
		
		navHtml.append("&gt;");
			
		if(brandid!=null){
			//品牌
			navHtml.append("<span><a href='");
			navHtml.append(catstr);
			navHtml.append("-");
			navHtml.append(brandstr);
			navHtml.append(".html'>");
			navHtml.append(brandname);
			navHtml.append("</a></span>&gt;");
		}
		
		navHtml.append("<span class=\"last\">");
		navHtml.append(goods.get("name"));
		navHtml.append("</span>");
		this.putData("navHtml",navHtml);
	}
	
	private String getCatStr(int catid){
		StringBuffer navHtml = new StringBuffer(); 
		navHtml.append("search-cat-");
		navHtml.append(catid);
		return navHtml.toString();
	}
	  
	
	private String getBrandStr(Integer brandid){
		if(brandid==null) return "";
		StringBuffer navHtml = new StringBuffer();
		navHtml.append("brand-");
		navHtml.append(brandid);
		return navHtml.toString();
	}
	
	
	
	

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	
}
