package com.enation.eop.processor.facade.support.widget;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.enation.eop.processor.widget.IWidgetPaser;
import com.enation.eop.processor.widget.WidgetWrapper;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 挂件边框包装器
 * 根据参数给挂件加上合适的边框
 * @author kingapex
 * 2010-1-27下午01:35:30
 */
public class BorderWrapper extends WidgetWrapper {

	public BorderWrapper(IWidgetPaser paser) {
		 super(paser);
	}

	
	public String pase(Map<String, String> params) {
		
		String content = super.pase(params);		
		String border = params.get("border");
		String widgetid ="widget_"+ params.get("widgetid");
		if(border ==null ||border.equals("") || border.equals("none")){ 
			
			if("yes".equals(ThreadContextHolder.getHttpRequest().getParameter("ajax"))
			|| "widget_header".equals(widgetid)){
				return content;
			}else
			
			return "<div id=\""+widgetid+"\">"+content+"</div>";
			
		}
		
		EopSite site  =   EopContext.getContext().getCurrentSite();
		String contextPath  = EopContext.getContext().getContextPath();
		String borderPath =contextPath +"/themes/"+site.getThemepath()+"/borders/";
		borderPath= EopSetting.EOP_PATH+borderPath;
		try{
				
			Map<String,String> data = new HashMap<String, String>();
			
			data.put("widgetid", widgetid);
			data.put("body", content);
			data.put("title", params.get("bordertitle"));
			data.putAll(params);
			Configuration cfg  = FreeMarkerUtil.getFolderCfg(borderPath);
			Template temp = cfg.getTemplate(border+".html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		}catch(Exception e){
			e.printStackTrace();
			return content;
		}
		
	}

}
