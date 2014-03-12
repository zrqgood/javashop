package com.enation.javashop.widget.goods.detail;

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.support.ParamGroup;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.plugin.standard.type.GoodsTypeUtil;

/**
 * 商品详细信息挂件
 * @author kingapex
 *
 */
public class GoodsIntroWidget extends AbstractWidget {
 
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		
		if(goods==null) throw new RuntimeException("参数显示挂件必须和商品详细显示挂件同时存在");
		String intro =(String)goods.get("intro");
		intro =intro==null?"":intro;
		this.putData("intro", intro);
		this.setPageName("intro");
	}

 
	

}
