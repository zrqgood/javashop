package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 易族易站我的站点
 * @author kingapex
 *
 */
public class MemberSitesWidget extends AbstractMemberWidget {
	
	private IAdminUserManager adminUserManager;
	private ISiteManager siteManager;
	private IUserManager userManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		action = action == null ? "" : action;
		if(action.equals("")){
		this.setPageName("member_sites");
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		
		
		List siteList = null;
		if(member!=null){
			EopUser user  = this.userManager.getCurrentUser();
			siteList = this.siteManager.list(user.getId());
		}
		
		siteList = siteList == null ? new ArrayList() : siteList;
		this.putData("siteList", siteList);
		}else if(action.equals("delete")){
			String id = request.getParameter("id");
			try{
				this.siteManager.delete(Integer.valueOf(id));
				this.showSuccess("删除成功","我的站点", "member_sites.html");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("删除失败", "我的站点", "member_sites.html");
			}
		}
	}


	public IAdminUserManager getAdminUserManager() {
		return adminUserManager;
	}


	public void setAdminUserManager(IAdminUserManager adminUserManager) {
		this.adminUserManager = adminUserManager;
	}


	public ISiteManager getSiteManager() {
		return siteManager;
	}


	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}


	public IUserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
}
