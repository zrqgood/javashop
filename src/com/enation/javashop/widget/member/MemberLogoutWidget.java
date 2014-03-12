package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.eop.sdk.user.IUserService;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 会员注销挂件
 * @author kingapex
 *2010-5-4上午11:03:58
 */
public class MemberLogoutWidget extends AbstractMemberWidget {

	
	protected void config(Map<String, String> params) {
			
	}

	
	protected void display(Map<String, String> params) {
		this.showMenu(false);
	   ThreadContextHolder.getSessionContext().removeAttribute(IUserService.CURRENT_MEMBER_KEY);
	   this.showSuccess("成功退出","商城首页", "index.html");
	}

}
