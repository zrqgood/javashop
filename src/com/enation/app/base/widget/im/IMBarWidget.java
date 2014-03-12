package com.enation.app.base.widget.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.util.StringUtil;



/**
 * 客服挂件
 * @author kingapex
 * 2010-9-15下午05:56:14
 */
public class IMBarWidget extends AbstractWidget {
	private ISettingService settingService ;
	private ISiteManager siteManager;
	
	protected void config(Map<String, String> params) {
		
	}

	
	protected void display(Map<String, String> params) {
		
		EopSite site = EopContext.getContext().getCurrentSite();
		
		//开启状态1为开启
		String state  = //settingService.getSetting("im", "state");
			site.getState().toString();
		
		//在线客服标题
		String title = "在线客服";//settingService.getSetting("im", "title");
		
		//是否开启qq
		Integer qq = site.getQq();//settingService.getSetting("im", "qq");
		String qqlist = site.getQqlist();
		qq = qq == null ? 0 : qq;
		
		//是否开启msn
		Integer msn = site.getMsn();//settingService.getSetting("im", "msn");
		String msnlist = site.getMsnlist();
		msn = msn == null ? 0 : msn;
		
		//是否开启旺旺
		Integer ww = site.getWw();//settingService.getSetting("im", "ww");
		String wwlist = site.getWwlist();
		ww = ww == null ? 0 : ww;
		
		//是否显示工作时间
		Integer wt = site.getWt();
		wt = wt == null ? 0 : wt;
		
		//是否显示电话
		Integer tel = site.getTel();
		tel = tel == null ? 0 : tel;
		
		//qq列表
		qqlist  = StringUtil.isEmpty(qqlist)?"":qqlist;
		

		
		//msn列表
//		String msnlist = settingService.getSetting("im", "msnlist");
		msnlist  = StringUtil.isEmpty(msnlist)?"":msnlist;
		
		//旺旺列表
//		String wwlist = settingService.getSetting("im", "wwlist");
		wwlist  = StringUtil.isEmpty(wwlist)?"":wwlist;
		
		//营业时间
		String worktime = site.getWorktime();//settingService.getSetting("im", "worktime");
		String tellist = site.getTellist();//settingService.getSetting("im", "tel");
		
		this.putData("state", state);
		this.putData("imtitle", title);//注意压入的key为imtitle
		this.putData("qq", qq);
		this.putData("msn", msn);
		this.putData("ww", ww);
		this.putData("wt", wt);
		this.putData("tel", tel);
		
		this.putData("qqlist", this.createList(qqlist));
		this.putData("msnlist",this.createList(msnlist));
		this.putData("wwlist", this.createList(wwlist));
		this.putData("worktime", worktime);
		this.putData("imtel", tellist);
		 
		this.setPageName("imbar");
	}
	
	/**
	 * 根据一个im信息串生成im map<br/>
	 * 
	 * @param im
	 * @return
	 */
	private Map<String,String> createMap(String im){
		im =StringUtil.isEmpty(im)?"":im;
		String[] imar  = im.split(":");
		
		Map<String,String> immap = new HashMap<String, String>();
		if(imar.length!=2) return null;
		
		immap.put("num", imar[0]);
		immap.put("text", imar[1]);
		return immap;
	}
	
	/**
	 * 根据一个im串创建imlist
	 * @param im
	 * @return
	 */
	private List createList(String im){
		List imlist  = new ArrayList();
		im =StringUtil.isEmpty(im)?"":im;
		String[] imar  = im.split(",");
		for(String s:imar){
			Map<String,String> immap = this.createMap(s);
			if(immap!=null)
				imlist.add( immap);
		}		
		return imlist;
	}
	
	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}


	public ISiteManager getSiteManager() {
		return siteManager;
	}


	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	

}
