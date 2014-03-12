package com.enation.app.base.widget.adColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.core.service.IAdColumnManager;
import com.enation.app.base.core.service.IAdvManager;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.widget.AbstractWidget;

public class PageAdColumn extends AbstractWidget {
	
	private IAdvManager advManager;
	private IAdColumnManager adColumnManager;

	
	protected void config(Map<String, String> params) {
		this.setPageName("PageAdColumn_config");
		List<AdColumn> adColumnList = adColumnManager.listAllAdvPos();
		adColumnList = adColumnList == null ? new ArrayList<AdColumn>():adColumnList;
		this.putData("adColumnList", adColumnList);
	}

	
	protected void display(Map<String, String> params) {
		this.setPageName("PageAdColumn");
		String acid = params.get("acid");
		AdColumn adc = adColumnManager.getADcolumnDetail(Long.valueOf(acid));
		String adwidth = adc.getWidth();
		String adheight = adc.getHeight();
		int atype = adc.getAtype();
		List<Adv> advList = advManager.listAdv(Long.valueOf(acid));
		advList = advList == null ? new ArrayList<Adv>():advList;
		if(advList.size()!=0){
			int a = (int)((advList.size()) * Math.random());
			this.putData("adv", advList.get(a));
		}else{
			Adv adv = new Adv();
			adv.setAcid(0);
			adv.setAid(0);
			//adv.setUrl("/shop/adv/zhaozu.jsp");//设置为我们的一个招租地址
			adv.setAtturl(EopSetting.IMG_SERVER_DOMAIN + "/images/zhaozu.png");
			this.putData("adv", adv);
		}
		
		this.putData("wid", params.get("id"));
		this.putData("adwidth", adwidth);
		this.putData("adheight", adheight);
		this.putData("atype", atype);
	}
	
	public void setAdvManager(IAdvManager advManager) {
		this.advManager = advManager;
	}

	public void setAdColumnManager(IAdColumnManager adColumnManager) {
		this.adColumnManager = adColumnManager;
	}


}
