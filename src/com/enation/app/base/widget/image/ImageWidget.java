package com.enation.app.base.widget.image;

import java.util.Map;

import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.widget.AbstractWidget;

public class ImageWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		String src = params.get("src");
		src = src == null ? "" : src;
		String contextPath = EopContext.getContext().getContextPath();
		src = EopSetting.IMG_SERVER_DOMAIN + contextPath+ "/"+src; 
		this.putData("src", src);

	}

}
