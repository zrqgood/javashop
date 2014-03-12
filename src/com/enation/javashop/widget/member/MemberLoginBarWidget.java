package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;

/**
 * 会员登录bar
 * @author kingapex
 *
 */
public class MemberLoginBarWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if(member==null){
			this.putData("isLogin", false);
		}else{
			this.putData("isLogin", true);
			this.putData("member", member);
		}

	}

}
