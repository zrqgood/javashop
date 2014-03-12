package com.enation.javashop.widget.goods.search2;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.IGoodsSearchManager2;
import com.enation.javashop.core.utils.UrlUtils;
import com.enation.javashop.widget.goods.search.SearchPagerDirectiveModel;

public class GoodsSearchWidget2 extends AbstractWidget {
	private IGoodsSearchManager2 goodsSearchManager2;
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String uri = request.getServletPath();
		//uri = StringUtil.decode(uri);
		//uri= StringUtil.toUTF8(uri);
		
		String pagesizes = params.get("pagesize");
		Integer pageSize = pagesizes == null ? 20 : Integer.valueOf(pagesizes);
		
		
		String page_str = UrlUtils.getParamStringValue(uri, "page");
		int page=1;
		if(page_str!=null && !page_str.equals("")){
			page= Integer.valueOf(page_str);
		}
		
		Page webpage = 	goodsSearchManager2.search(page, pageSize, uri);
		Map selectorMap = goodsSearchManager2.getSelector(uri);

		Map<String,Object> pluginParams = new HashMap<String, Object>();
		goodsSearchManager2.putParams(pluginParams, uri);
		
		this.putData("uri",uri);
		this.putData(pluginParams);
		this.putData("pager", new SearchPagerDirectiveModel());
		this.putData("GoodsPic",new  GoodsPicDirectiveModel());
		this.putData("goodsSelector", selectorMap);
		this.putData("webpage", webpage);
		this.putData("pageno", page); //当分页页码
		this.putData("pagesize", pageSize); //分页大小
		this.putData("total", webpage.getTotalCount());
	}

	public IGoodsSearchManager2 getGoodsSearchManager2() {
		return goodsSearchManager2;
	}

	public void setGoodsSearchManager2(IGoodsSearchManager2 goodsSearchManager2) {
		this.goodsSearchManager2 = goodsSearchManager2;
	}

}
