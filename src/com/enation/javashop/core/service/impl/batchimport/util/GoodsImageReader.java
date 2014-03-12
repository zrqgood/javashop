package com.enation.javashop.core.service.impl.batchimport.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.image.IThumbnailCreator;
import com.enation.framework.image.ThumbnailCreatorFactory;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;

public class GoodsImageReader {
	protected final Logger logger = Logger.getLogger(getClass());
	private ISettingService settingService ;
	private String getSettingValue(String code){
		return  settingService.getSetting("photo", code);
	}
	public String[] read(String folder,Integer goodsid,String excludeStr ){
		String[] exclude = null;

		if(!StringUtil.isEmpty(excludeStr))
			exclude = excludeStr.split(",");
		
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
		
		
	 
		if(this.logger.isDebugEnabled()){
			logger.debug("开始导入商品["+goodsid+"]图片...");
		}
		
		String imgsString="";
		String defaultImg ="";
		String datafolder  = folder+"/images";
		File file  = new File(datafolder);
		
		if(!file.exists()) {
			if(this.logger.isDebugEnabled()){
				logger.debug("商品["+goodsid+"]图片目录不存在跳过");
			}
			return null;
		}
		
		String[] imgNames = file.list();
		for(String imgname: imgNames){
			
			if(this.logger.isDebugEnabled()){
				logger.debug("处理图片["+imgname+"]...");
			}
			
			
			//过滤掉排除的文件
			if(this.isExclude(exclude, imgname)){
				
				if(this.logger.isDebugEnabled()){
					logger.debug("图片["+imgname+"]在排除范围，跳过.");
				}
				
				continue;
			}
							
			String sourcePath = datafolder+"/"+imgname;
			
			//过滤掉文件夹
			if(new File(sourcePath).isDirectory()) continue;
			//copy原图
			imgname=imgname.toLowerCase();
			FileUtil.copyFile(sourcePath,  EopSetting.IMG_SERVER_PATH+"/attachment/goods/"+goodsid+"/"+imgname);
			String goodsImg=  "fs:/attachment/goods/"+goodsid +"/"+imgname;
			
			/**
			 * -------------------
			 *     生成各种缩略图
			 * -------------------
			 */
			if(this.logger.isDebugEnabled()){
				logger.debug("为图片["+imgname+"]生成各种缩略图...");
			}
			String targetPath = getThumbPath(imgname,"_thumbnail",goodsid);
			createThumb(sourcePath,targetPath,thumbnail_pic_width,thumbnail_pic_height);
			
			targetPath = getThumbPath(imgname,"_small",goodsid);
			createThumb(sourcePath,targetPath,detail_pic_width,detail_pic_height);
			
			targetPath = getThumbPath(imgname,"_big",goodsid);
			createThumb(sourcePath,targetPath,album_pic_width,album_pic_height);
			
			
			/**
			 * -------------------
			 *     处理商品图片字串
			 * -------------------
			 */			
			
			//连接商品图片字串
			if(!imgsString.equals("")) imgsString+=",";
			imgsString +=goodsImg;
			//设置商品默认图片
			if(imgname.startsWith("default")){
				defaultImg = goodsImg;
			}
			
			 
		}
		 
 
		//如果没有指定默认图片,将每一张图片设定为默认图片
		if(defaultImg.equals("") && imgsString!=null)  defaultImg=imgsString.split(",")[0];
		 
		if(this.logger.isDebugEnabled()){
			logger.debug("缩略图生成完毕，图片字串信息为image_file["+imgsString+"],defaultImg["+defaultImg+"]");
		}
	//	this.daoSupport.execute("update es_goods set image_file=? ,image_default=? where goods_id=?", imgsString,defaultImg,goodsid);
		
		if(this.logger.isDebugEnabled()){
			logger.debug(" 商品["+goodsid+"]图片导入完成");
		}
		
		return new String[]{imgsString,defaultImg};
	}

	/**
	 * 判断某个图片名是否在排除范围
	 * @param exclude
	 * @param imageName
	 * @return
	 */
	private boolean isExclude(String[] exclude,String imageName){
		
		if(exclude==null)return false;
		for(String ex:exclude){
			if(imageName.startsWith(ex)) return true;
		}
		return false;
		
	}
 
	private void createThumb(String sourcePath,String targetPath,int width,int height){
		IThumbnailCreator thumbnailCreator = ThumbnailCreatorFactory.getCreator( sourcePath, targetPath);
		thumbnailCreator.resize(width, height);		
	}
	private String getThumbPath(String filename,String shortName,int goodsid){
		String name  =UploadUtil.getThumbPath(filename, shortName);
		String path  =  EopSetting.IMG_SERVER_PATH+"/attachment/goods/"+goodsid;
		File file =new File(path);
		
		if(!file.exists()) file.mkdir();
		
		path = path+"/"+name;
		return path;
	}
	public ISettingService getSettingService() {
		return settingService;
	}
	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}
	
}
