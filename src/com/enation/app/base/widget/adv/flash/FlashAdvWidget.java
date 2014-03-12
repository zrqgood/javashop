package com.enation.app.base.widget.adv.flash;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.widget.abstractadv.AbstractAdvWidget;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.webcontext.ThreadContextHolder;

public class FlashAdvWidget extends AbstractAdvWidget {

	
	protected void execute(AdColumn adColumn, List<Adv> advList) {
		StringBuffer imgs = new StringBuffer();
		StringBuffer urls = new StringBuffer();
		StringBuffer titles = new StringBuffer();
		for (Adv adv : advList) {
			if(imgs.length()!=0)imgs.append("|");
			imgs.append(adv.getAtturl());
			if(urls.length()!=0)urls.append("|");
			HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
			urls.append(request.getContextPath() +"/core/adv!click."+EopSetting.EXTENSION+"?advid="+adv.getAid());
			if(titles.length()!=0)titles.append("|");
			titles.append(adv.getAname());
		}
		this.putData("imgs", imgs.toString());
		this.putData("urls", urls.toString());
		this.putData("titles", titles.toString());
		this.putData("width", adColumn.getWidth().replaceAll("px", ""));
		this.putData("height", adColumn.getHeight().replaceAll("px", ""));
	}

}
