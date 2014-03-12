package com.enation.javashop.widget.comments;

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.ICommentsManager;

/**
 * 商品评价挂件
 * @author kingapex
 *
 */
public class GoodsGradeWidget extends AbstractWidget {
	private ICommentsManager commentsManager;
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		if(goods==null) throw new RuntimeException("商品评论挂件必须和商品详细显示挂件同时存在");
		Map grade = this.commentsManager.coutObjectGrade("goods", (Integer)goods.get("goods_id"));
		this.putData("grade", grade);
	}

	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

}
