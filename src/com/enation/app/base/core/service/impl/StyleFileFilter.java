package com.enation.app.base.core.service.impl;

import java.io.File;
import java.io.FileFilter;

import com.enation.framework.util.FileUtil;

public class StyleFileFilter implements FileFilter {
	private static final String[] EXTS={"css","js","jpg","png","gif","bmp"};
	public boolean accept(File pathname) {
		String fileName = pathname.getName().toLowerCase();
		
		if(pathname.isDirectory()){
			if(fileName.equals("borders")) return false;
			if(fileName.equals("common")) return false;
			if(fileName.equals("custompage")) return false;
			if(fileName.equals(".svn")) return false;
			return true;
		}
		String ext  = FileUtil.getFileExt(fileName).toLowerCase();
		for(String e:EXTS){
			if(ext.equals(e)){
				return true;
			}
		} 
		return false; 
	}

}
