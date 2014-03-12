package com.enation.cms.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 多行文本框插件
 * @author kingapex
 * 2010-7-5下午03:10:04
 */
public class TextareaFieldPlugin extends AbstractFieldPlugin {
   
	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer();
		html.append("<textarea");
		html.append(" name=\"");
		html.append(field.getEnglish_name());
		html.append("\" style=\"width:250px;height:100px\" ");
		html.append(this.wrappValidHtml(field));
		html.append(">");
		if(value!=null){
			html.append(value);
		}
	
		html.append("</textarea>");
		
		return html.toString();
	}

	public String getDataType() {
		 
		return "text";
	}
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "textarea";
	}

	
	public String getName() {
		
		return "多行文本框";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public int getHaveSelectValue() {
		return 0;
	}

}
