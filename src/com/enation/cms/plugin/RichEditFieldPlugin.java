package com.enation.cms.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 富文本编辑器字段插件
 * @author kingapex
 * 2010-7-7上午10:28:06
 */
public class RichEditFieldPlugin extends AbstractFieldPlugin {

	
	public int getHaveSelectValue() {
		
		return 0;
	}

	/**
	 * 覆写数据保存事件默认响应<br>
	 * 逻辑为以name为字段为字段名，值为request.getParameter(fieldname);
	 */
	public void onSave(Map article, DataField field) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String value =request.getParameter(field.getEnglish_name());
		if(value!=null){
			//替换静态服务器域名为本地标识串(fs:)
			value = value.replaceAll( EopSetting.IMG_SERVER_DOMAIN +EopContext.getContext().getContextPath()+  "/attachment/", EopSetting.FILE_STORE_PREFIX+ "/attachment/");
		}
		article.put(field.getEnglish_name(),value);
	}
	
	
	

	/**
	 * 覆写父的数据显示响应事件<br>
	 * 逻辑为直接返回字段值<br>
	 * 如果为null返回空串
	 */
	public Object onShow(DataField field, Object value) {
		if(value!=null){
			value  =UploadUtil.replacePath( value.toString());
			return value;
		}
		else return "";
	}
		
	
	
	public String onDisplay(DataField field, Object value) {
		StringBuffer html = new StringBuffer();
		html.append("<textarea id=\""+field.getEnglish_name()+"\" name=\""+field.getEnglish_name()+"\">");
		if(value!=null){
			value  =UploadUtil.replacePath( value.toString());
			html.append(value);
		}
		html.append("</textarea>");
		html.append("<script type=\"text/javascript\">");
		html.append("$('#"+field.getEnglish_name()+"' ).ckeditor( );");
		html.append("</script>");
		
		return html.toString();
	}

	
	public String getDataType() {
		 
		return "text";
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "richedit";
	}

	
	public String getName() {
		
		return "富文本编辑器";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

}
