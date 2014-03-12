package com.enation.javashop.widget.goods.detail;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.widget.header.HeaderConstants;
import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.plugin.goods.GoodsPluginBundle;
import com.enation.javashop.core.service.IGoodsManager;

/**
 * 商品详细挂件</br>
 * @author kingapex
 */
public class GoodsDetailWidget extends AbstractWidget {
	 

	protected void  display(Map<String, String> params) {
		
		try{
		 Map goodsMap=  (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		 
		 if(goodsMap.get("page_title")!=null &&!goodsMap.get("page_title").equals("") )
			this.putData(HeaderConstants.title, goodsMap.get("page_title"));
		 else
			 this.putData(HeaderConstants.title, goodsMap.get("name") );
		 
		 if(goodsMap.get("meta_keywords")!=null &&!goodsMap.get("meta_keywords").equals("")  )
			this.putData(HeaderConstants.keywords,goodsMap.get("meta_keywords"));
		 
		 if(goodsMap.get("meta_description")!=null &&!goodsMap.get("meta_description").equals("") )
			this.putData(HeaderConstants.description,goodsMap.get("meta_description"));
		  
		}catch(ObjectNotFoundException e){
			
			this.setPageName("notfound");
		}
	}
	
	protected void config(Map<String, String> params) {
		this.showHtml=false;
	}


	
	public static void main(String[] args){
	}

	
}
