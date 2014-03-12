package com.enation.javashop.plugin.standard.album;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.plugin.page.JspPageTabs;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.plugin.goods.AbstractGoodsPlugin;


/**
 * 默认商品相册插件
 * @author enation
 *
 */
public class DefaultAlbumPlugin extends AbstractGoodsPlugin{
	
 
	public void addTabs(){
		 JspPageTabs.addTab("setting",2, "相册设置");
	}
	
 
	public void onBeforeGoodsAdd(Map goods, HttpServletRequest request) {
		String[] picnames =  request.getParameterValues("picnames");
		proessPhoto(picnames,goods); 
	}

	public void onBeforeGoodsEdit(Map goods, HttpServletRequest request)  {
		String[] picnames =  request.getParameterValues("picnames");
		proessPhoto(picnames,goods); 
		
	}
		
 
	
	/**
	 * 在修改时处理图片的信息
	 */
	public String onFillGoodsEditInput(Map goods, HttpServletRequest request) {
		
		String contextPath = request.getContextPath();
		//设置需要传递到页面的数据	
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		
		// 关于图片的处理
 		String image_default= null;
		if (goods.get("image_file") != null && goods.get("image_file")!="") {
			String image_file = goods.get("image_file").toString();
			String[] thumbnail_images = image_file.split(",");

			image_default= (String)goods.get("image_default");
			if(!StringUtil.isEmpty(image_default)){
				image_default = UploadUtil.replacePath(image_default);
				
			}
			
			freeMarkerPaser.putData("ctx", contextPath);
			freeMarkerPaser.putData("image_default", image_default );
			freeMarkerPaser.putData("thumbnail_images", thumbnail_images );	
		}
		
		freeMarkerPaser.setPageName("album");
		String html = freeMarkerPaser.proessPageContent();
		return html;
	}

	public String onFillGoodsAddInput(HttpServletRequest request) {
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.setPageName("album");		
		freeMarkerPaser.putData("image_default", null );
		freeMarkerPaser.putData("thumbnail_images", null );	
		String html = freeMarkerPaser.proessPageContent();
		return html;
	}
	

	public void onAfterGoodsAdd(Map goods, HttpServletRequest request)  {
		
	}
	

	public void onAfterGoodsEdit(Map goods, HttpServletRequest request)  {
		
	}

	protected void proessPhoto(String[] picnames,Map goods) {
		
	}
	



	public String getAuthor() {
		return "kingapex";
	}

	public String getId() {
		return "default_album";
	}

	public String getName() {
		 
		return "默认商品相册插件";
	}

	public String getType() {
		return "";
	}

	public String getVersion() {
		return "1.0";
	}

	public void perform(Object... params) {
		
	}
	
	
	
	
	
}
