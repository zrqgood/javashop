package com.enation.javashop.widget.member;

import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;

/**
 * 判断会员是否登陆的挂件
 * @author kingapex
 *
 */
public class MemberIsLoginWidget extends AbstractWidget {

	@Override
	protected void config(Map<String, String> params) {
		
	}

	@Override
	protected void display(Map<String, String> params) {
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if(member==null){
			this.putData("isLogin", false);
		}else{
			this.putData("isLogin", true);
		}
	}

}
