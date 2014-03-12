package com.enation.app.base.widget.friendsLink;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.service.IFriendsLinkManager;
import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;

public class FriendsLinkWidget extends AbstractWidget {
	
	private IFriendsLinkManager friendsLinkManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("friendsLink");
 
		List listLink = this.friendsLinkManager.listLink();
		this.putData("listLink", listLink);
	 
		Nav nav = new Nav();
		nav.setTitle("友情链接 ");
		this.putNav(nav);
	}

	public IFriendsLinkManager getFriendsLinkManager() {
		return friendsLinkManager;
	}

	public void setFriendsLinkManager(IFriendsLinkManager friendsLinkManager) {
		this.friendsLinkManager = friendsLinkManager;
	}

}
