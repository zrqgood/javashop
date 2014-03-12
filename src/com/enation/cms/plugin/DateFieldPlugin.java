package com.enation.cms.plugin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.DateUtil;

/**
 * 日期控件字段插件
 * @author kingapex
 * 2010-7-7上午11:04:30
 */
public class DateFieldPlugin extends AbstractFieldPlugin {

	
	public int getHaveSelectValue() {
		
		return 0;
	}

	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer();
		 
		html.append("<input type=\"text\" name=\"");
		html.append(field.getEnglish_name());
		html.append("\" readonly=\"true\"");
		if(value!=null){
			html.append("value=\"");
			html.append(value);
			html.append("\"");
		}
		html.append(" class=\"dateinput\" ");
		html.append(">");
	 
		return html.toString();
	}

	
	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "dateinput";
	}

	
	public String getName() {
		
		return "日期控件";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

}
