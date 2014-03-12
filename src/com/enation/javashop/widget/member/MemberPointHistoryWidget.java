package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IPointHistoryManager;

/**
 * 我的积分
 * @author kingapex
 *
 */
public class MemberPointHistoryWidget extends AbstractMemberWidget {

	private IPointHistoryManager pointHistoryManager;
	private IMemberManager memberManager;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		
		this.setPageName("member_pointhistory");
		
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		member = this.memberManager.get(member.getMember_id()); 
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String page = request.getParameter("page");
		int pointType  = StringUtil.toInt(request.getParameter("pointtype"), true);
		
		page = (page == null || page.equals("")) ? "1" : page;
		int pageSize = 20;
		Page pointHistoryPage = pointHistoryManager.pagePointHistory(Integer
				.valueOf(page), pageSize,pointType);
		Long pageCount = pointHistoryPage.getTotalPageCount();
		List pointHistoryList = (List) pointHistoryPage.getResult();
		pointHistoryList = pointHistoryList == null ? new ArrayList()
				: pointHistoryList;
		
		Long consumePoint = pointHistoryManager.getConsumePoint(pointType);
		Long gainedPoint = pointHistoryManager.getGainedPoint(pointType);

		int point  = 0;
		if(pointType==0){
			point  = member.getPoint();
		}
		
		if(pointType==1){
			point  = member.getMp();
		}
		this.putData("pageCount", pageCount);
		this.putData("pageSize", pageSize);
		this.putData("page", page);
		this.putData("pointHistoryList", pointHistoryList);
		this.putData("point", point);
		this.putData("consumePoint", consumePoint);
		this.putData("gainedPoint", gainedPoint);
	}

	public IPointHistoryManager getPointHistoryManager() {
		return pointHistoryManager;
	}

	public void setPointHistoryManager(IPointHistoryManager pointHistoryManager) {
		this.pointHistoryManager = pointHistoryManager;
	}


	public IMemberManager getMemberManager() {
		return memberManager;
	}


	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	
}
