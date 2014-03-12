package com.enation.javashop.widget.member;

import java.util.HashMap;
import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;

/**
 * 会员中心挂件基类
 * @author kingapex
 *2010-4-29下午02:13:09
 */
public abstract class AbstractMemberWidget extends AbstractWidget {
	
	
	/**
	 *会员中心挂件全部不缓存
	 */
	public boolean  cacheAble(){
		return false;
	}
	

	private boolean showMenu= true; 
	

	
	protected void showMenu(boolean show){
		showMenu = show;
	}
	
	public boolean getShowMenu(){
		return this.showMenu;
	}
	
	protected Member getCurrentMember(){
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		return member;
	}
}
