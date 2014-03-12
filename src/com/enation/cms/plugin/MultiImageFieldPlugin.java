package com.enation.cms.plugin;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.AbstractFieldPlugin;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * 多图片上传插件
 * @author kingapex
 *
 */
public class MultiImageFieldPlugin extends AbstractFieldPlugin {

	@Override
	public int getHaveSelectValue() {
		
		return 0;
	}

	/**
	 * 在后台界面显示多图片上传的html(输入项 )
	 */
	public String onDisplay(DataField field, Object value) {
		try{
			Map data = new HashMap();
			data.put("fieldname", field.getEnglish_name());
			
			if(value!=null){
				value  =UploadUtil.replacePath( value.toString());
				 Collection imglist = JSONArray.toCollection(JSONArray.fromObject(value));
				data.put("imglist", imglist);
			}
			

			data.put("ext", EopSetting.EXTENSION);
			data.put("ctx", ThreadContextHolder.getHttpRequest().getContextPath()); 
			
			Configuration cfg = FreeMarkerUtil.getCfg();
		 
			cfg.setClassForTemplateLoading(this.getClass(), "");
			
			Template temp = cfg.getTemplate("MultiImageFieldPlugin.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			
			out.flush();
			String html = stream.toString();
			
			return html;
		}
		catch(Exception e){
			return "CMS多图片上传插件解析出错"+e.getMessage();
		}
	}
	
	
	/**
	 * 将字段值保存到库中
	 * 图片路径
	 */
	@Override
	public void onSave(Map article, DataField field) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String[] images = request.getParameterValues(field.getEnglish_name()+"_img");
		String[] titles = request.getParameterValues(field.getEnglish_name()+"_title");
		List<String> imgList  = new ArrayList();
		for(int i=0;i<images.length;i++){
			String image = images[i];
			String title = titles[i];
			image  = image.replaceAll( EopSetting.IMG_SERVER_DOMAIN +EopContext.getContext().getContextPath(), EopSetting.FILE_STORE_PREFIX );
			
			Map map = new HashMap(2);
			map.put("image", image);
			map.put("title", title);
			String imgObj  =   JSONObject.fromObject(map).toString();
			
			if(i==images.length-1) break;
			imgList.add(imgObj);
			
		}
		
		String path =StringUtil.arrayToString(images,",");
	
		article.put(field.getEnglish_name(), JSONArray.fromObject(imgList).toString());	
	}

	
	/**
	 * 图片在前台显示的处理
	 */
	@Override
	public Object onShow(DataField field, Object value) {
		if(value!=null){
			 value  =UploadUtil.replacePath( value.toString());
			 Collection imglist = JSONArray.toCollection(JSONArray.fromObject(value));
			 return imglist;
		}
		
		return  value;
	}
	
	public String getDataType() {
		 
		return "text";
	}

	public String getAuthor() {
		
		return "kingapex";
	}

	public String getId() {
		
		return "multiimage";
	}

	public String getName() {
		
		return "多图片上传";
	}

	public String getType() {
		
		return "field";
	}

	public String getVersion() {
		
		return "1.0";
	}


}
