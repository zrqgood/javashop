package com.enation.javashop.widget.comments;

import com.enation.app.base.core.plugin.IOnSettingInputShow;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.page.JspPageTabs;

public class CommentsSettingPlugin extends AutoRegisterPlugin implements IOnSettingInputShow {
	
	/**
	 * 定义设置参数组的组名
	 */
	
	public String getSettingGroupName() {
		 
		return "comments";
	}

	
	/**
	 * 响应输入界面显示事件，返回页面名称为setting，即同目录下setting.html
	 */
	
	public String onShow() {
		 
		return "setting";
	}
	
	/**
	 * 注册设置组选项卡名
	 */
	
	public void register() {
		 JspPageTabs.addTab("setting",30, "评论设置");
		
	}

	
	public String getAuthor() {
		return "kingapex";
	}

	
	public String getId() {
		return "comments";
	}

	
	public String getName() {
		return "评论";
	}

	
	public String getType() {
		return "comments";
	}

	
	public String getVersion() {
		return "1.0";
	}

	
	public void perform(Object... params) {
		
	}

}
