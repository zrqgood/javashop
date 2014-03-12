package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.ICommentsManager;
import com.enation.javashop.core.service.IMemberOrderManager;

/**
 * 我的评论
 * @author kingapex
 *
 */
public class MemberCommentsWidget extends AbstractMemberWidget {
	
	private ICommentsManager commentsManager;

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		String page  = request.getParameter("page");
		 page = (page == null || page.equals("")) ? "1" : page;
		 this.setPageName("mycomments");
		 int pageSize = 5;
		 
		 Page commentsPage = commentsManager.pageComments_Display(Integer.valueOf(page), pageSize);
		 Long totalCount = commentsPage.getTotalCount();
		 
		 List commentsList = (List)commentsPage.getResult();
		 commentsList = commentsList == null ? new ArrayList() : commentsList;
		 
		 this.putData("totalCount", totalCount);
		 this.putData("pageSize", pageSize);
		 this.putData("page", page);
		 this.putData("commentsList", commentsList);
	}

	
	protected void config(Map<String, String> params) {
		
	}

	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

}
