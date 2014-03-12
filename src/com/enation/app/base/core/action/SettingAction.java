package com.enation.app.base.core.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.service.ISettingService;
import com.enation.framework.action.WWAction;
import com.enation.framework.plugin.page.JspPageTabs;

/**
 * @author apexking
 *
 */
public class SettingAction extends WWAction {
	
	private String showtab;
	
	private ISettingService settingService;
 
	private List<String> htmlList;
 
	private String[] codes;
	private String[]  cfg_values;
	
	
	private Map tabs;
	
	public Map getTabs(){
		return this.tabs;
	}
	
	public static final String SETTING_PAGE_ID= "setting_input";
	
	/**
	 * 到设置页面
	 */
	public String edit_input(){
		this.pageId = SETTING_PAGE_ID;
		this.tabs = JspPageTabs.getTabs("setting");
		this.htmlList = this.settingService.onInputShow();
	
		return this.INPUT;
	}
	
	/**
	 * 保存配置
	 * @return
	 */
	public String save(){
		HttpServletRequest  request = this.getRequest();
		Enumeration<String> names = request.getParameterNames();
		Map<String,Map<String,String>> settings = new HashMap<String, Map<String,String>>();
		
	    while(names.hasMoreElements()){
	    	

	    	String name= names.nextElement();
	    	String[]name_ar = name.split("\\.");
	    	if(name_ar.length!=2) continue;
	    	
	    	String groupName = name_ar[0];
	    	String paramName  = name_ar[1];
	    	String paramValue = request.getParameter(name);
	    	
	    	Map<String,String> params = settings.get(groupName);
	    	if(params==null){
	    		params = new HashMap<String, String>();
	    		settings.put(groupName, params);
	    	}
	    	params.put(paramName, paramValue);
 
	    }
	    
		settingService.save( settings );
		this.msgs.add("配置修改成功");
		this.urls.put("系统设置", "setting!edit_input.do");
		return this.MESSAGE;
	}
	
	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}


	public String[] getCfg_values() {
		return cfg_values;
	}



	public void setCfg_values(String[] cfg_values) {
		this.cfg_values = cfg_values;
	}



	public String[] getCodes() {
		return codes;
	}



	public void setCodes(String[] codes) {
		this.codes = codes;
	}



	public void setTabs(Map tabs) {
		this.tabs = tabs;
	}

	public List<String> getHtmlList() {
		return htmlList;
	}

	public void setHtmlList(List<String> htmlList) {
		this.htmlList = htmlList;
	}

	public String getShowtab() {
		return showtab;
	}

	public void setShowtab(String showtab) {
		this.showtab = showtab;
	}

 
	
}
