package com.enation.app.base.widget.im;

import com.enation.app.base.core.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.page.JspPageTabs;

/**
 * 在线客服务设置插件
 * @author kingapex
 * 2010-9-15下午02:47:58
 */
public class IMSettingPlugin extends AutoRegisterPlugin  implements IOnSettingInputShow {

	public String getSettingGroupName() {
		
		return "im";
	}

	public String onShow() {
		
		return "setting";
	}

	
	public void register() {
		
		 JspPageTabs.addTab("setting",1, "在线客服");
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "imSettingPlugin";
	}

	
	public String getName() {
		
		return "imSettingPlugin";
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
