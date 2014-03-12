package com.enation.app.base.core.action;

import java.io.File;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;

/**
 * 附件上传
 * 
 * @author kingapex 2010-3-10下午04:24:47
 */
public class UploadAction extends WWAction {

	private String fileFileName;
	private File file;

	// 是否创建 缩略图,默认不创建
	private int createThumb = 0;

	// 子目录地址
	private String subFolder;

	private int ajax;

	private String type;
	private String picname;
	private int width;
	private int height;

	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public String execute() {

		return this.INPUT;
	}

	public String upload() {
		if (file != null && fileFileName != null) {
			
			
			String path = null;
			
			if(this.createThumb==1){
				path = UploadUtil.upload(file, fileFileName, subFolder, width, height)[0];
				
			}else{
				path = UploadUtil.upload(file, fileFileName, subFolder);
			}
			// 将本地图片路径换为静态资源服务器的地址
			if (path != null)
				path = UploadUtil.replacePath(path);

			if (ajax == 1) {
				this.json = "{'result':1,'path':'" + path + "',filename:'"
						+ fileFileName + "'}";
				return this.JSON_MESSAGE;
			}
		}
		return this.SUCCESS;
	}

	public String delete() {
		UploadUtil.deleteFile(picname);
		this.json = "{'result':0}";
		return this.JSON_MESSAGE;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getCreateThumb() {
		return createThumb;
	}

	public void setCreateThumb(int createThumb) {
		this.createThumb = createThumb;
	}

	public String getSubFolder() {
		return subFolder;
	}

	public void setSubFolder(String subFolder) {
		this.subFolder = subFolder;
	}

	public int getAjax() {
		return ajax;
	}

	public void setAjax(int ajax) {
		this.ajax = ajax;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
