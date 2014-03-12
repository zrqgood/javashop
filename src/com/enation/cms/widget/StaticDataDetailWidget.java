package com.enation.cms.widget;

import java.util.Map;

import com.enation.app.base.widget.nav.Nav;
import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.eop.sdk.widget.AbstractWidget;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.user.UserServiceFactory;
/**
 *  静态数据详细挂件
 * @author kingapex
 * 2010-7-6上午11:33:11
 */
public class StaticDataDetailWidget extends AbstractWidget {

	private IDataManager dataManager;
	private IDataCatManager dataCatManager;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		
		String id = params.get("id");
		String catid = params.get("catid");
		String login = params.get("login");
		
		if("1".equals(login)){
			Member member = UserServiceFactory.getUserService().getCurrentMember();
			if(member==null){
				this.putData("isLogin", false);
			}else{
				this.putData("isLogin", true);
				this.putData("member", member);
			}
		}
		Map data = this.dataManager.get(Integer.valueOf(id), Integer.valueOf(catid),true);
		this.putData("data", data);

//		
//		DataCat cat  =this.dataCatManager.get(Integer.valueOf(catid));
//		
//		Nav nav = new Nav();
//		nav.setTitle("首页");
//		nav.setLink("index.html");
//		nav.setTips("首页");
//		this.putNav(nav);
//		
//		Nav nav1 = new Nav();
//		nav1.setTitle(cat.getName() );
//		nav1.setTips(cat.getName());
//		this.putNav(nav1);		
		
	}
	
	private int getNextId(){
		
		String sql="select min(id) from table where id>";
		return 0;
	}

	
	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}
	

}
