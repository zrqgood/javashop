package com.enation.javashop.widget.goods.detail;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.widget.header.HeaderConstants;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.plugin.cart.ICartAddEvent;
import com.enation.javashop.core.service.IGoodsManager;

public class GoodsDetailMainWidget extends AbstractWidget {

	private IGoodsManager goodsManager;
	private ICartAddEvent testGroovy;
	protected void config(Map<String, String> params) {

	}

	protected void  display(Map<String, String> params) {
		 
		try{ 
		 Integer goods_id=this.getGoodsId();
		 Map goodsMap = goodsManager.get(goods_id);
		 if("1".equals(params.get("shownav")))
			 goodsManager.getNavdata(goodsMap);
		 ThreadContextHolder.getHttpRequest().setAttribute("goods", goodsMap);
		 
		 if(goodsMap.get("page_title")!=null &&!goodsMap.get("page_title").equals("") )
			this.putData(HeaderConstants.title, goodsMap.get("page_title"));
		 else
			 this.putData(HeaderConstants.title, goodsMap.get("name") );
		 
		 if(goodsMap.get("meta_keywords")!=null &&!goodsMap.get("meta_keywords").equals("")  )
			this.putData(HeaderConstants.keywords,goodsMap.get("meta_keywords"));
		 
		 if(goodsMap.get("meta_description")!=null &&!goodsMap.get("meta_description").equals("") )
			this.putData(HeaderConstants.description,goodsMap.get("meta_description"));
		  
		  
		 this.freeMarkerPaser.setClz(this.getClass());
			
			
		}catch(ObjectNotFoundException e){
			
			this.setPageName("notfound");
		}
	}
	
	private Integer getGoodsId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		String goods_id = this.paseGoodsId(url);
		
		return Integer.valueOf(goods_id);
	}

	private  static String  paseGoodsId(String url){
		String pattern = "/(.*)goods-(\\d+)(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}

	
	public static void main(String[] args){
		String uri = "/yxgoods-1.html";
		String goodsid  = paseGoodsId(uri);
		System.out.println(goodsid);
	}
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public ICartAddEvent getTestGroovy() {
		return testGroovy;
	}

	public void setTestGroovy(ICartAddEvent testGroovy) {
		this.testGroovy = testGroovy;
	}

 
	
}
