package com.enation.eop.sdk.user;

import com.enation.app.base.core.model.Member;

/**
 * 用户服务接口<br/>
 * 提供判断当前用户是否登陆、读取当前用户id和读取当前操作站点的服务
 * @author kingapex
 *
 */
public interface IUserService {

	public static final String CURRENT_MEMBER_KEY="curr_member";
	public boolean isUserLoggedIn();

	public Integer getCurrentUserId();

	public Integer getCurrentSiteId();
	
	public Integer getCurrentManagerId();
	
	public Member getCurrentMember();
}
