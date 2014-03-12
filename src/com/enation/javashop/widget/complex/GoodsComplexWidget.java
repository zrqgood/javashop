package com.enation.javashop.widget.complex;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.IGoodsComplexManager;

/**
 * 相关商品挂件
 * 
 * @author lzf<br/>
 *         2010-4-1 下午01:52:17<br/>
 *         version 1.0<br/>
 */
public class GoodsComplexWidget extends AbstractWidget {
	
	private IGoodsComplexManager goodsComplexManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		Integer goods_id  = this.getGoodsId();
		List complexList = goodsComplexManager.listAllComplex(goods_id);
		boolean hasComplex = complexList==null ||complexList.isEmpty() ?false:true;
		this.putData("hasComplex", hasComplex);
		this.putData("complexList", complexList);
		this.putData("GoodsPic", new GoodsPicDirectiveModel());
		this.setPageName("complex");
	}
	
	private Integer getGoodsId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		
		if(url.startsWith("/widget")) return 0;
		
		String goods_id = this.paseGoodsId(url);
		
		return Integer.valueOf(goods_id);
	}

	private  static  String  paseGoodsId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}

	public IGoodsComplexManager getGoodsComplexManager() {
		return goodsComplexManager;
	}

	public void setGoodsComplexManager(IGoodsComplexManager goodsComplexManager) {
		this.goodsComplexManager = goodsComplexManager;
	}

}
