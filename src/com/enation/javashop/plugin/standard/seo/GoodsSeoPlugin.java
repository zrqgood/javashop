package com.enation.javashop.plugin.standard.seo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;


/**
 * 商品SEO优化插件
 * @author enation
 *
 */
public class GoodsSeoPlugin extends AbstractGoodsPlugin{
	
 
	
	public void addTabs(){
		 this.addTags(4, "搜索优化");
	}
	
	public void registerPages(){	 
		//this.registerPage("goods_add.tabcontent", "/plugin/seo/seo.jsp?ajax=yes");
	}

 

	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {

	}
 
	
	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("seo");
		return freeMarkerPaser.proessPageContent();
	}

	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser = new FreeMarkerPaser(getClass());
		freeMarkerPaser.setPageName("seo");
		freeMarkerPaser.putData("goods", goods);
		return freeMarkerPaser.proessPageContent();
	}

	public void onAfterGoodsAdd(Goods goods, HttpServletRequest request) throws RuntimeException {
		
		
	}

	
	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		
		
	}

	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		
		
	}
	
	

	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		
		
	}

	public String getAuthor() {
		
		return "kingapex";
	}

	public String getId() {
		
		return "goodsseo";
	}

	public String getName() {
		 
		return "商品SEO优化插件";
	}

	public String getType() {
		
		return "";
	}

	public String getVersion() {
		
		return "1.0";
	}

	public void perform(Object... params) {
		
		
	}
	
}
