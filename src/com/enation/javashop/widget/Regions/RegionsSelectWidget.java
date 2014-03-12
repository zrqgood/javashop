package com.enation.javashop.widget.Regions;

import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 地区联动下拉框挂件
 * @author kingapex
 *
 */
public class RegionsSelectWidget extends AbstractWidget {
	
	private IRegionsManager regionsManager;
	
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {
		if("showJson".equals(action)){
			String regionid = ThreadContextHolder.getHttpRequest().getParameter("regionid");
			this.getChildren(Integer.valueOf(regionid));
		}else{
			List provinceList  =this.regionsManager.listProvince();
			this.putData("provinceList",provinceList);
		}
	}
	
	private void getChildren(Integer regionid){
		String json =regionsManager.getChildrenJson(regionid);
		this.showJson(json);
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}

	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}
	

}