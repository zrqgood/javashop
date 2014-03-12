package com.enation.javashop.core.plugin.member;

import com.enation.app.base.core.model.Member;

/**
 * 会员注销事件
 * @author kingapex
 *
 */
public interface IMemberLogoutEvent {
	 
	public void onLogout(Member member);
	
	
}
