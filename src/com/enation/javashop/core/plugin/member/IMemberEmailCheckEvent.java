package com.enation.javashop.core.plugin.member;

import com.enation.app.base.core.model.Member;

/**
 * 会员邮件验证事件
 * @author kingapex
 * @date 2011-10-20 下午3:31:10 
 * @version V1.0
 */
public interface IMemberEmailCheckEvent {
	
	/**
	 * 当会员通过邮件验证成功后激发此事件
	 * @param member
	 */
	public void onEmailCheck(Member member);
}
	