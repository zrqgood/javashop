package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IMemberManager;

public class MemberEmailCheckWidget extends AbstractWidget {
	private IMemberManager memberManager;
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {

		try{
			String s = ThreadContextHolder.getHttpRequest().getParameter("s");
			Member member  = memberManager.checkEmail(s);
			
			this.showSuccess(member.getUname() +"您好，您的邮箱验证成功!", "点此登陆", "member_login.html");
		}catch(RuntimeException e){
			this.showError("验证地址不正确");
		}
		
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

}
