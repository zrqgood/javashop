package com.enation.eop.processor.facade.support.widget;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.processor.facade.support.LocalWidgetPaser;
import com.enation.eop.processor.facade.support.WidgetEditModeWrapper;
import com.enation.eop.processor.widget.IWidgetHtmlGetter;
import com.enation.eop.processor.widget.IWidgetPaser;
import com.enation.eop.sdk.context.SaasEopContextIniter;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 挂件Html获取器</br>
 * 通过 IWidgetPaser接口 解析挂件html</br>
 * </br>
 * 分为两种模式：浏览模式和编辑模式</br>
 * 浏览模式
 * @see #IWidgetPaser
 * 
 * @author kingapex
 * 2010-2-8下午10:17:58
 */
public class WidgetHtmlGetter implements IWidgetHtmlGetter {

	
	public String process(Map<String, String> params,String page) {
		
		IWidgetPaser widgetPaser = new LocalWidgetPaser();
		widgetPaser = new BorderWrapper(widgetPaser);
		if(UserServiceFactory.getUserService().isUserLoggedIn() &&"edit".equals(params.get("mode"))){
			widgetPaser = new WidgetEditModeWrapper(widgetPaser); 
		}
		String html =widgetPaser.pase(params);
		return html;
	}



	

}
