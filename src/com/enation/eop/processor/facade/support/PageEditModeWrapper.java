package com.enation.eop.processor.facade.support;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.enation.eop.processor.IPagePaser;
import com.enation.eop.processor.PageWrapper;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.eop.sdk.utils.HtmlUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 前台页面编辑模式包装器
 * @author kingapex
 * 2010-2-9上午01:27:30
 */
public class PageEditModeWrapper extends PageWrapper {

	public PageEditModeWrapper(IPagePaser paser) {
		super(paser);
	}

	
	public String pase(String url) {
		String content  = super.pase(url);
		String script= this.getToolBarScript();
		String html = this.getToolBarHtml();
		
		content =wrapPageMain(content);
		content =HtmlUtil.appendTo(content, "head", script);
		content =HtmlUtil.insertTo(content, "body", html);
		//System.out.println(content);
		return content;
	}
	
	private String getToolBarScript(){
		String eopFld= EopSetting.EOP_PATH+"/eop/";
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("staticserver", EopSetting.IMG_SERVER_DOMAIN) ;	
			data.put("ctx", EopSetting.CONTEXT_PATH) ;
			EopSite site = EopContext.getContext().getCurrentSite();
			data.put("userid",""+site.getUserid() ) ;
			data.put("siteid",""+site.getId() ) ;
			
			Configuration cfg = FreeMarkerUtil.getFolderCfg(eopFld);
			Template temp = cfg.getTemplate("widget_tool_script.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	private String getToolBarHtml(){
		//widget_toolbar.html
		String eopFld= EopSetting.EOP_PATH+"/eop/";
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("staticserver", EopSetting.IMG_SERVER_DOMAIN) ;	
			data.put("ctx", EopSetting.CONTEXT_PATH) ;
			EopSite site = EopContext.getContext().getCurrentSite();
			data.put("userid",""+site.getUserid() ) ;
			data.put("siteid",""+site.getId() ) ;
			Configuration cfg = FreeMarkerUtil.getFolderCfg(eopFld);
			Template temp = cfg.getTemplate("widget_toolbar.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			return stream.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private String wrapPageMain(String content){
		content = HtmlUtil.insertTo(content, "body", "<div id=\"pagemain\">");
		content =HtmlUtil.appendTo(content, "body", "</div>");
		return content;
	}

}
