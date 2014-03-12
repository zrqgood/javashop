package com.enation.cms.plugin;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 图片字段插件
 * @author kingapex
 * 2010-7-6下午05:40:29
 */
public class ImageFieldPlugin extends AbstractFieldPlugin {

	@Override
	public int getHaveSelectValue() {
		
		return 0;
	}

 
	/**
	 * 覆盖默认的字段值显示，将本地存储的图片路径转换为静态资源服务器路径
	 */
	@Override
	public Object onShow(DataField field, Object value) {
		if(value!=null){
			value  =UploadUtil.replacePath( value.toString());
		}
		
		return  value;
	}

	/**
	 * 显示字段入项
	 */
	public String onDisplay(DataField field, Object value) {
		try{
			Map data = new HashMap();
			data.put("fieldname", field.getEnglish_name());
			if(value!=null){
				value  =UploadUtil.replacePath( value.toString());
			}
			data.put("value", value);
			data.put("ctx", ThreadContextHolder.getHttpRequest().getContextPath()); 
			data.put("ext", EopSetting.EXTENSION);
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());	
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
			
		 
			cfg.setClassForTemplateLoading(this.getClass(), "");
			Template temp = cfg.getTemplate("ImageFieldPlugin.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			
			out.flush();
			String html = stream.toString();		
			
			return html;
		}
		catch(Exception e){
			return "CMS插件解析出错"+e.getMessage();
		}
	}

	
	
	@Override
	public void onSave(Map article, DataField field) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String path  = request.getParameter(field.getEnglish_name());
		if(path!=null)
		path  = path.replaceAll( EopSetting.IMG_SERVER_DOMAIN +EopContext.getContext().getContextPath(), EopSetting.FILE_STORE_PREFIX );
		
		article.put(field.getEnglish_name(),path);		
		
	}


	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "image";
	}

	
	public String getName() {
		
		return "图片";
	}

	
	public String getType() {
		
		return "field";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

}
