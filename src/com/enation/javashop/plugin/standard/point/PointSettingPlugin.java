package com.enation.javashop.plugin.standard.point;

import com.enation.app.base.core.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.page.JspPageTabs;

public class PointSettingPlugin extends AutoRegisterPlugin implements
		IOnSettingInputShow {

	
	public void register() {
		JspPageTabs.addTab("setting",3, "积分设置");
	}

	
	public String getSettingGroupName() {
	
		return "point";
	}

	
	public String onShow() {
	
		return "setting";
	}

	
	public String getAuthor() {
	
		return "kingapex";
	}

	
	public String getId() {
	
		return "point_setting";
	}

	
	public String getName() {
	
		return "积分设置";
	}

	
	public String getType() {
	
		return "setting";
	}

	
	public String getVersion() {
	
		return "1.0";
	}

	
	public void perform(Object... params) {
		
	}

}
