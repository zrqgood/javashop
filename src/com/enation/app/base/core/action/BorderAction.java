package com.enation.app.base.core.action;

import java.util.List;

import com.enation.eop.resource.IBorderManager;
import com.enation.eop.resource.model.Border;
import com.enation.framework.action.WWAction;

/**
 * 读取用户站点边框列表
 * @author kingapex
 * 2010-1-28下午06:19:19
 */
public class BorderAction extends WWAction {
	
	private IBorderManager borderManager;
	private List<Border> borderList;
	
	public String execute(){
		borderList=borderManager.list();
		return "list";
	}
	public void setBorderManager(IBorderManager borderManager) {
		this.borderManager = borderManager;
	}
	public void setBorderList(List<Border> borderList) {
		this.borderList = borderList;
	}
	public IBorderManager getBorderManager() {
		return borderManager;
	}
	public List<Border> getBorderList() {
		return borderList;
	}
	
}
