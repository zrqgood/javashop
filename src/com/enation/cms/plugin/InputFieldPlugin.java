package com.enation.cms.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.framework.context.webcontext.ThreadContextHolder;

import freemarker.template.utility.StringUtil;

/**
 * 单行文本框插件
 * @author kingapex
 * 2010-7-5下午02:55:26
 */
public class InputFieldPlugin extends AbstractFieldPlugin {

  
	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "input";
	}

	
	public String getName() {
		
		return "单行文本框";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

 

	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer("<input type=\"text\" style=\"width:450px\" name=\"");
		html.append(field.getEnglish_name());
		html.append("\""); 
		
		if(value!=null){
			html.append(" value=\"");
			value=StringUtil.HTMLEnc(value.toString());
			html.append(value);
			html.append("\"");
		}

		html.append(this.wrappValidHtml(field));
		
		html.append(" />");
		
		return html.toString();
	}

	
	public int getHaveSelectValue() {
		 
		return 0;
	}



}
