package com.enation.eop.sdk.user.impl;

import org.apache.log4j.Logger;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;

public final class UserServiceImpl implements IUserService {
	private UserContext userContext;
	
	protected static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	public UserServiceImpl() {
//		if(logger.isDebugEnabled()){
//			logger.debug("create userservice impl ");
//		}
		WebSessionContext<UserContext> webSessionContext = ThreadContextHolder
				.getSessionContext();
		userContext = webSessionContext.getAttribute(UserContext.CONTEXT_KEY);
		
//		if(logger.isDebugEnabled()){
//			logger.debug("get  userContext is "+ userContext);
//		}
	}

	
	public Integer getCurrentSiteId() {
		if (isUserLoggedIn()) {
			return userContext.getSiteid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public Integer getCurrentUserId() {
		if (isUserLoggedIn()) {
			return userContext.getUserid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public boolean isUserLoggedIn() {
		if (userContext == null)
			return false;
		else
			return true;
	}

	
	public Integer getCurrentManagerId() {
		if (isUserLoggedIn()) {
			return userContext.getManagerid();
		}
		throw new IllegalStateException("The current user is not logged in.");
	}

	
	public Member getCurrentMember() {
		Member member = (Member)ThreadContextHolder.getSessionContext().getAttribute(IUserService.CURRENT_MEMBER_KEY);
/*	Member member = new Member();
		member.setMember_id(1);
		member.setName("bbb");
		member.setProvince_id(104);
		member.setCity_id(105);
		member.setRegion_id(106);
		member.setRegtime(1267572800000L);
		member.setBirthday(1267572800000L);
		member.setSex(1);
		member.setUname("测试");
		member.setEmail("1@2.com");
		member.setAddress("aaa");
		member.setZip("202020");
		member.setMobile("1391111234");
		member.setTel("119");
		member.setPw_question("Why?");
		member.setPw_answer("Oh!");
		member.setPassword("e4789cf2281e1d05a5685438d2cfad");//StringUtil.md5(752513)
		member.setPoint(1440);
		member.setLv_id(1);
		member.setAdvance(new Double(250));*/
		return member;
	}

}
