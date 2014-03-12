package com.enation.javashop.widget.gallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.javashop.core.model.Gallery;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * 商品相册挂件
 * @author kingapex
 *
 */
public class GoodsGalleryWidget extends AbstractGoodsDetailWidget {
	private ISettingService settingService;
	
	protected void config(Map<String, String> params) {
		 
	}

	
	protected void execute(Map<String, String> params,Map goods) {

		String image_file = goods.get("image_file")==null?"":goods.get("image_file").toString();
		String default_img  = (String)goods.get("image_default");
		int defaultindex = 0;
		List<Gallery> galleryList = new ArrayList<Gallery>();
		if(image_file!=null && !image_file.equals("")){
		
			String[] imgs= image_file.split(",");
			int i=0;
			for(String img : imgs){
				
				Gallery gallery = new Gallery();
				gallery.setSmall(UploadUtil.getThumbPath(img, "_small"));
				gallery.setBig(UploadUtil.getThumbPath(img, "_big"));
				gallery.setThumbnail(UploadUtil.getThumbPath(img, "_thumbnail") );
				galleryList.add(gallery);
				if(img.equals(default_img)){
					defaultindex = i;
				}
				i++;
			}
			
		}else{
			String img  = EopSetting.IMG_SERVER_DOMAIN +"/images/no_picture.jpg";
			Gallery gallery = new Gallery();
			gallery.setSmall(UploadUtil.getThumbPath(img, "_small"));
			gallery.setBig(img);
			gallery.setThumbnail(UploadUtil.getThumbPath(img, "_thumbnail") );
			galleryList.add(gallery);
		}
 
		String album_pic_width = this.settingService.getSetting("photo", "detail_pic_width");
		String album_pic_height = this.settingService.getSetting("photo", "detail_pic_height");
		
		this.putData("album_pic_width", album_pic_width);
		this.putData("album_pic_height", album_pic_height);
		
		putData("galleryList", galleryList);
		//setPageFolder("/plugin/kingapex_gallery");
		setPageName("gallery");
		putData("goods", goods);
		this.putData("GoodsPic",new  GoodsPicDirectiveModel());
		this.putData("defaultindex",defaultindex);
		this.putData("image_default",default_img);
		
	}



	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}
	

}
