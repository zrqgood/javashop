package com.enation.cms.plugin.video;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.cms.plugin.ImageFieldPlugin;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class VideoFieldPlugin extends AbstractFieldPlugin {

	private ImageFieldPlugin imageFieldPlugin;
	
	@Override
	public int getHaveSelectValue() {
		
		return 0;
	}

	@Override
	public String onDisplay(DataField field, Object value) {
		String fieldname = field.getEnglish_name();
		
		if(value!=null){
			value  =UploadUtil.replacePath( value.toString());
		}
		
		String img =null;
		Map data = new HashMap();
		
		data.put("fieldname", fieldname);

		
		if(value!=null){
			JSONObject videoData  = JSONObject.fromObject(value);
			
			String title =(String)videoData.get("title");
			String path =(String)videoData.get("path");
			img =(String)videoData.get("img");
			
			String width =(String)videoData.get("width");
			String height =(String)videoData.get("height");
			String autoplay =(String)videoData.get("autoplay");
			
			
			data.put("path", path);
			data.put("title", title);
			data.put("width", width);
			data.put("height", height);		
			data.put("autoplay", autoplay);	
		
		}
		
		field.setEnglish_name(  fieldname+"_img");
		String imgHtml =imageFieldPlugin.onDisplay(field, img);
		data.put("imgHtml", imgHtml);
		
		try{
			data.put("ctx", ThreadContextHolder.getHttpRequest().getContextPath()); 
			data.put("ext", EopSetting.EXTENSION);
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());	
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
			
		 
			cfg.setClassForTemplateLoading(this.getClass(), "");
			Template temp = cfg.getTemplate("VideoFieldPlugin.html");
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
		
		String fieldname = field.getEnglish_name();
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String title  = request.getParameter(fieldname+"_title");
		String path  = request.getParameter(fieldname+"_path");
		String width  = request.getParameter(fieldname+"_width");
		String height  = request.getParameter(fieldname+"_height");
		String autoplay  = request.getParameter(fieldname+"_autoplay");
		String img  = request.getParameter(fieldname+"_img");
		
		
		Map video =  new HashMap();
		video.put("title", title);
		video.put("path", path);
		video.put("width", width);
		video.put("height", height);
		video.put("autoplay", autoplay);
		video.put("img", img);
		
		
		
		String  value = JSONObject.fromObject(video).toString();
		article.put(fieldname, value);
		
	}
	
	@Override
	public Object onShow(DataField field, Object value) {
		String img =null;
		Map video = null;
		if(value!=null){
			video =new HashMap<String,String>();
			value  =UploadUtil.replacePath( value.toString());
			
				JSONObject videoData  = JSONObject.fromObject(value);
				
				String title =(String)videoData.get("title");
				String path =(String)videoData.get("path");
				img =(String)videoData.get("img");
				
				String width =(String)videoData.get("width");
				String height =(String)videoData.get("height");
				String autoplay =(String)videoData.get("autoplay");
				
				video.put("path", path);
				video.put("title", title);
				video.put("img", img);
				video.put("width", width);
				video.put("height", height);	
				video.put("autoplay", autoplay);	
				String videoHtml = this.getVideoPlayerHtml(video,field.getEnglish_name());
				video.put("playerHtml", videoHtml);	
				
		}
		
		return  video;
	}
	
	
	private String getVideoPlayerHtml(Map video,String fieldname){
		
		try{
			Map data = new HashMap();
			data.putAll(video);
			data.put("fieldname", fieldname);
			data.put("ctx", ThreadContextHolder.getHttpRequest().getContextPath()); 
			data.put("ext", EopSetting.EXTENSION);
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());	
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
		 
			cfg.setClassForTemplateLoading(this.getClass(), "");
			Template temp = cfg.getTemplate("VideoPlayer.html");
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

	
	public String getDataType() {
		 
		return "text";
	}
	
	@Override
	public String getAuthor() {
		
		return "kingapex";
	}

	@Override
	public String getId() {
		
		return "video";
	}

	@Override
	public String getName() {
		
		return "视频";
	}

	@Override
	public String getType() {
		
		return "field";
	}

	@Override
	public String getVersion() {
		
		return "1.0";
	}

	public ImageFieldPlugin getImageFieldPlugin() {
		return imageFieldPlugin;
	}

	public void setImageFieldPlugin(ImageFieldPlugin imageFieldPlugin) {
		this.imageFieldPlugin = imageFieldPlugin;
	}

 

}
