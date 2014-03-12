package com.enation.javashop.core.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.image.IThumbnailCreator;
import com.enation.framework.image.ThumbnailCreatorFactory;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.ImageMagickMaskUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.service.IGoodsAlbumManager;
import com.enation.javashop.core.service.IGoodsManager;

/**
 * 商品相册管理
 * @author kingapex
 * 2010-2-21上午12:40:05
 */
public final class GoodsAlbumManager extends BaseSupport<Goods> implements IGoodsAlbumManager {
	private IGoodsManager goodsManager;
	/**
	 * 删除指定的图片<br>
	 * 将本地存储的图片路径替换为实际硬盘路径<br>
	 * 不会删除远程服务器图片
	 */
	public void delete(String photoName) {
		 if(photoName!=null && !photoName.startsWith( "http://static.enationsoft.com/attachment/") ){
					 photoName  =UploadUtil.replacePath(photoName);
					 photoName =photoName.replaceAll(EopSetting.IMG_SERVER_DOMAIN , EopSetting.IMG_SERVER_PATH);
					 FileUtil.delete(photoName);
					 FileUtil.delete(UploadUtil.getThumbPath(photoName, "_thumbnail"));
					 FileUtil.delete(UploadUtil.getThumbPath(photoName, "_small"));
					 FileUtil.delete(UploadUtil.getThumbPath(photoName, "_big"));
		 }
	}
	
	public void delete(Integer[] goodsid){
		String id_str = StringUtil.arrayToString(goodsid, ",");
		String sql = "select image_file from goods where goods_id in ("+ id_str +")";
		List<Map> goodsList  = this.baseDaoSupport.queryForList(sql);
		for(Map goods:goodsList){
			String name =(String)goods.get("image_file");
			if(name!=null &&  !name.equals("")){
				String[] pname = name.split(",");
				for(String n:pname)
					this.delete(n);
			}
		}  
		
	}
	private ISettingService settingService ;
	/**
	 * 上传商品图片<br>
	 * 生成商品图片名称，并且在用户上下文的目录里生成图片<br>
	 * 返回以静态资源服务器地址开头+用户上下文路径的全路径<br>
	 * 在保存入数据库时应该将静态资源服务器地址替换为fs:开头，并去掉上下文路径,如:<br>
	 * http://static.enationsoft.com/user/1/1/attachment/goods/1.jpg，存库应该为:
	 * fs:/attachment/goods/1.jpg
	 */
	public String[] upload(File file, String fileFileName) {
		String fileName = null;
		String filePath = "";

		String[] path = new String[2];
		
		if (file != null && fileFileName != null) {
			String ext = FileUtil.getFileExt(fileFileName);
			fileName = DateUtil.toString(new Date(), "yyyyMMddHHmmss") + StringUtil.getRandStr(4) + "." + ext;
			filePath = EopSetting.IMG_SERVER_PATH + getContextFolder()+ "/attachment/goods/";
			path[0] = EopSetting.IMG_SERVER_DOMAIN + getContextFolder()+ "/attachment/goods/"+fileName;
			filePath += fileName;
			FileUtil.createFile(file, filePath);
			
			String watermark = settingService.getSetting("photo", "watermark");
			String marktext = settingService.getSetting("photo", "marktext");
			String markpos = settingService.getSetting("photo", "markpos");
			String markcolor = settingService.getSetting("photo", "markcolor");
			String marksize = settingService.getSetting("photo", "marksize");
		
			marktext= StringUtil.isEmpty(marktext)? "水印文字":marktext;
			marksize= StringUtil.isEmpty(marksize)? "12":marksize;
			
			
			if(watermark!=null && watermark.equals("on")){
				ImageMagickMaskUtil magickMask = new ImageMagickMaskUtil();
				magickMask.mask(filePath, marktext,markcolor,Integer.valueOf(marksize),Integer.valueOf(   markpos ),
					EopSetting.EOP_PATH + "/fonts/st.TTF");	
				//"d:/webhome/eop/fonts/st.TTF");
			}
		} 
		return path;
	}

	public static String getContextFolder(){
		return EopContext.getContext().getContextPath();
	}

	/**
	 * 生成商品缩略图<br>
	 * 传递的图片地址中包含有静态资源服务器地址，替换为本地硬盘目录，然后生成。<br>
	 * 如果是公网上的静态资源则不处理
	 */
	public void createThumb(String filepath,String thumbName,int thumbnail_pic_width,int thumbnail_pic_height) {
		String  imgDomain ="http://static.enationsoft.com/attachment/"; // EopSetting.IMG_SERVER_DOMAIN.startsWith("http")? EopSetting.IMG_SERVER_DOMAIN:"http://"+EopSetting.IMG_SERVER_DOMAIN;
		 if(filepath!=null && !filepath.startsWith(imgDomain  )){
			filepath=filepath.replaceAll( EopSetting.IMG_SERVER_DOMAIN, EopSetting.IMG_SERVER_PATH );
			thumbName=thumbName.replaceAll( EopSetting.IMG_SERVER_DOMAIN, EopSetting.IMG_SERVER_PATH );
			IThumbnailCreator thumbnailCreator = ThumbnailCreatorFactory.getCreator( filepath, thumbName);
			thumbnailCreator.resize(thumbnail_pic_width, thumbnail_pic_height);				 
		 }
	  
	}

	
	@Override
	public int getTotal() {
		return this.goodsManager.list().size();
	}

	@Override
	public void recreate(int start,int end) {
		
		int thumbnail_pic_width =107;
		int thumbnail_pic_height =107;
		int detail_pic_width=320;
		int detail_pic_height=240;
		int  album_pic_width =550;
		int album_pic_height=412;
		
		/**
		 * 由配置中获取各种图片大小
		 */
		try{
			thumbnail_pic_width =Integer.valueOf(getSettingValue("thumbnail_pic_width").toString());
			thumbnail_pic_height =Integer.valueOf(getSettingValue("thumbnail_pic_height").toString());
			
			detail_pic_width =Integer.valueOf(getSettingValue("detail_pic_width").toString());			
			detail_pic_height =Integer.valueOf(getSettingValue("detail_pic_height").toString());
			
			album_pic_width =Integer.valueOf(getSettingValue("album_pic_width").toString());			
			album_pic_height =Integer.valueOf(getSettingValue("album_pic_height").toString());	
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		List<Map> goodsList = this.goodsManager.list();
		for(int i=start-1;i<end;i++){
			Map goods = goodsList.get(i);
			String imageFile = (String)goods.get("image_file"); //读取此商品图片
			if(imageFile!=null){
				String[] imgFileAr = imageFile.split(",");
				System.out.println("Create thumbnail image, the index:" + (i + 1));
				for(String imgFile:imgFileAr){
					System.out.println("Image file:" + imgFile);
					//生成缩略图
					String thumbName = UploadUtil.getThumbPath(imgFile, "_thumbnail");
					this.createThumb1(imgFile, thumbName, thumbnail_pic_width, thumbnail_pic_height);
					
					// 生成小图
					thumbName = UploadUtil.getThumbPath(imgFile, "_small");
					createThumb1(imgFile,thumbName, detail_pic_width, detail_pic_height);
					
					
					// 生成大图
					thumbName =UploadUtil.getThumbPath(imgFile, "_big");
					createThumb1(imgFile,thumbName,album_pic_width , album_pic_height);
					
				}
			}
			
		}
		
	}
	
	
	private String getSettingValue(String code){
		return  settingService.getSetting("photo", code);
	}
	
	
	/**
	 * 创建一个缩略图
	 * @param filepath
	 * @param thumbName
	 * @param thumbnail_pic_width
	 * @param thumbnail_pic_height
	 */
	private void createThumb1(String filepath,String thumbName,int thumbnail_pic_width,int thumbnail_pic_height){
		 if(!StringUtil.isEmpty(filepath)){
			String ctx =  EopContext.getContext().getContextPath();
			filepath=filepath.replaceAll( EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_PATH +ctx);
			thumbName=thumbName.replaceAll( EopSetting.FILE_STORE_PREFIX, EopSetting.IMG_SERVER_PATH +ctx);
			IThumbnailCreator thumbnailCreator = ThumbnailCreatorFactory.getCreator( filepath, thumbName);
			thumbnailCreator.resize(thumbnail_pic_width, thumbnail_pic_height);	 
		 }		
	}
 
	
	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}


	
}
