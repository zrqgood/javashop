package com.enation.javashop.widget.goods;

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 抽象的商品详细挂件
 * @author kingapex
 *
 */
public abstract class AbstractGoodsDetailWidget extends AbstractWidget {

 

	
	protected void display(Map<String, String> params) {
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		if(goods==null) throw new RuntimeException("参数显示挂件必须和商品详细显示挂件同时存在");
		this.execute(params, goods);
	}
	
	abstract protected void execute(Map<String, String> params,Map goods);

}
