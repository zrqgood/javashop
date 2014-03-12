package com.enation.app.base.core.action;

import java.io.File;

import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.action.WWAction;

/**
 * ckeditor 文件上传 
 * @author kingapex
 * 2010-7-11上午09:46:00
 */
public class CkEditorUploadAction extends WWAction {
	private File upload;
	private String uploadFileName;
	private String path;
	private String funcNum;
	public String execute(){
		funcNum = this.getRequest().getParameter("CKEditorFuncNum");
		if(upload!=null  && uploadFileName!=null){
			path = UploadUtil.upload(upload,uploadFileName,"ckeditor");
			path = UploadUtil.replacePath(path);
		}
		return this.SUCCESS;
		
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFuncNum() {
		return funcNum;
	}

	public void setFuncNum(String funcNum) {
		this.funcNum = funcNum;
	}
	
	
}
