package com.enation.eop.processor.facade.support.widget;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.enation.eop.processor.widget.IWidgetCfgHtmlParser;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.spring.SpringContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 本地挂件配置html解析器
 * @see com.enation.eop.sdk.widget.IWidget
 * @author kingapex
 * 2010-2-15下午01:48:18
 */
public class LocalWidgetCfgHtmlPaser implements IWidgetCfgHtmlParser{
 
	public String pase(Map<String,String> widgetParams){
		
		String type = widgetParams.get("type");
	
		//挂件类
		IWidget widget =SpringContextHolder.getBean(type);
		if(widget==null) throw new RuntimeException("widget["+type+"]not found");
 		//解析出挂件config的html
 		String content = widget.setting(widgetParams);
 		widgetParams.put("content", content);

		try {
			String fld = EopSetting.EOP_PATH + "/eop/";
			Configuration cfg = FreeMarkerUtil.getFolderCfg(fld);
			Template temp = cfg.getTemplate("widget_setting.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(widgetParams, out);
			out.flush();
			content = stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
 
 
	
	
}
