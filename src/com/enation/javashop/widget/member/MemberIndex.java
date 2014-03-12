package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.javashop.core.service.IWelcomeInfoManager;

/**
 * 会员中心首页
 * @author kingapex
 *
 */
public class MemberIndex extends AbstractMemberWidget {
	
	private IWelcomeInfoManager welcomeInfoManager;

	
	protected void display(Map<String, String> params) {
		 this.setPageName("index");
		 IUserService userService = UserServiceFactory.getUserService();
		 Member member = userService.getCurrentMember();
		 Map wel = welcomeInfoManager.mapWelcomInfo();
		 
		 this.putData("member", member);
		 this.putData("wel", wel);
	}

	
	protected void config(Map<String, String> params) {
		
	}

	public IWelcomeInfoManager getWelcomeInfoManager() {
		return welcomeInfoManager;
	}

	public void setWelcomeInfoManager(IWelcomeInfoManager welcomeInfoManager) {
		this.welcomeInfoManager = welcomeInfoManager;
	}
	

}
