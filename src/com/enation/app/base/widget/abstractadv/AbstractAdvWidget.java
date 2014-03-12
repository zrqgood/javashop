package com.enation.app.base.widget.abstractadv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.core.service.IAdColumnManager;
import com.enation.app.base.core.service.IAdvManager;
import com.enation.eop.sdk.widget.AbstractWidget;

public abstract class AbstractAdvWidget extends AbstractWidget {
	private IAdvManager advManager;
	private IAdColumnManager adColumnManager;

	
	protected void config(Map<String, String> params) {
		freeMarkerPaser.setClz( AbstractAdvWidget.class);
		this.setPageName("AbstractAdvWidget_config");
		List<AdColumn> adColumnList = adColumnManager.listAllAdvPos();
		adColumnList = adColumnList == null ? new ArrayList<AdColumn>():adColumnList;
		this.putData("adColumnList", adColumnList);
	}

	
	protected void display(Map<String, String> params) {
		String acid = params.get("acid");
		acid = acid == null ? "0" : acid;
		try{
			AdColumn adc = adColumnManager.getADcolumnDetail(Long.valueOf(acid));
			List<Adv> advList = advManager.listAdv(Long.valueOf(acid));
			advList = advList == null ? new ArrayList<Adv>():advList;
			
			if(advList.isEmpty()){
				freeMarkerPaser.setClz( AbstractAdvWidget.class);
				this.setPageName("notfound");
			}else{
				String width = params.get("width");
				String height = params.get("height");
				if(width!=null && !"".equals(width)) this.putData("width", width);
				if(height!=null && !"".equals(height)) this.putData("height", height);
				this.execute(adc, advList);
			} 
		}catch(RuntimeException e){
			if(this.logger.isDebugEnabled()){
				this.logger.error(e.getStackTrace());
			}
			freeMarkerPaser.setClz( AbstractAdvWidget.class);
			this.setPageName("notfound");
		}
		
	}

	
	abstract protected void execute(AdColumn adColumn,List<Adv> advList ) ;
	
	
	
	public IAdvManager getAdvManager() {
		return advManager;
	}

	public void setAdvManager(IAdvManager advManager) {
		this.advManager = advManager;
	}

	public IAdColumnManager getAdColumnManager() {
		return adColumnManager;
	}

	public void setAdColumnManager(IAdColumnManager adColumnManager) {
		this.adColumnManager = adColumnManager;
	}
	
	
	

}
