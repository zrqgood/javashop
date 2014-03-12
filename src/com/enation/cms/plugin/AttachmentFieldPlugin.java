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
 * 附件上传控件
 * 
 * @author kingapex
 */
public class AttachmentFieldPlugin extends AbstractFieldPlugin {

	public int getHaveSelectValue() {
		return 0;
	}
	
	/**
	 * 控件html输出
	 */
	public String onDisplay(DataField field, Object value) {
		try {
			

			Map data = new HashMap();
			data.put("fieldname", field.getEnglish_name());
			
			if (value != null) {
				value = UploadUtil.replacePath(value.toString());
			}
			
			if(value!=null){
				String[] values =  value.toString().split(",");
				if(values.length!=2){
					data.put("name", "error");
					data.put("path", "error");
				}
				data.put("name", values[0]);
				data.put("path", values[1]);
			}
			
			
			data.put("ctx", ThreadContextHolder.getHttpRequest()
					.getContextPath());
			data.put("ext", EopSetting.EXTENSION);
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");

			cfg.setClassForTemplateLoading(this.getClass(), "");
			Template temp = cfg.getTemplate("AttachmentFieldPlugin.html");
			ByteOutputStream stream = new ByteOutputStream();

			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);

			out.flush();
			String html = stream.toString();

			return html;
		} catch (Exception e) {

			return "挂件解析出错" + e.getMessage();
		}
	}
	/**
	 * 覆盖默认的字段值显示，将本地存储的图片路径转换为静态资源服务器路径
	 */
	public Object onShow(DataField field, Object value) {
		
		Map<String,String> attr = new HashMap<String, String>(2); 
		if(value!=null){
			value  =UploadUtil.replacePath( value.toString());
			
			String[] values =  value.toString().split(",");
			if(values.length!=2){
				attr.put("name", "error");
				attr.put("path", "error");
			}else{
			attr.put("name", values[0]);
			attr.put("path", values[1]);
			}
		}		
		return  attr;
	}
	
	
	/**
	 * 保存字段值<br>
	 * 格式为name+","+path
	 */
	public void onSave(Map article, DataField field) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String path  = request.getParameter(field.getEnglish_name()+"_path");
		String name  = request.getParameter(field.getEnglish_name()+"_name");
		if(path!=null)
		path  = path.replaceAll( EopSetting.IMG_SERVER_DOMAIN +EopContext.getContext().getContextPath(), EopSetting.FILE_STORE_PREFIX );
		article.put(field.getEnglish_name(),name+","+path);		
	}
	
	

	public String getAuthor() {

		return "kingapex";
	}

	public String getId() {

		return "attachment";
	}

	public String getName() {

		return "附件";
	}

	public String getType() {

		return "field";
	}

	public String getVersion() {

		return "1.0";
	}

}
