package com.enation.javashop.widget.comments2;

import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.ICommentsManager;

public class CommentNumWidget extends AbstractWidget {

	private ICommentsManager commentsManager;
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {
		Map goods  = (Map)ThreadContextHolder.getHttpRequest().getAttribute("goods");
		if(goods==null) throw new RuntimeException("商品评论挂件必须和商品详细显示挂件同时存在");
		String commenttype = params.get("commenttype");
		Map commentNum = this.commentsManager.coutObejctNum(commenttype, (Integer)goods.get("goods_id"));
		this.putData("commentNum", commentNum);
	}

	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}
}
