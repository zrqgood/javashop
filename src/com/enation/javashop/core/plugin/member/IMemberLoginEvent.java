package com.enation.javashop.core.plugin.member;

import com.enation.app.base.core.model.Member;

/**
 * 会员登录事件
 * @author kingapex
 * @date 2011-10-20 下午3:30:10 
 * @version V1.0
 */
public interface IMemberLoginEvent {
	
	
	
	/**
	 * 登录成功后激发此事件
	 * @param member
	 */
	public void onLogin(Member member);
	
	
}
