package com.enation.app.base.core.model;

/**
 * 资源管理器的一个文件实体
 * @author kingapex
 * 2010-8-18下午12:06:40
 */
public class FileNode {
	
	private String name;
	private long size;
	private long lastmodify;
	private int isfolder; //是否是文件夹,1是，0否
	private String content; //文件内容
	private String ext;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getLastmodify() {
		return lastmodify;
	}
	public void setLastmodify(long lastmodify) {
		this.lastmodify = lastmodify;
	}
	public int getIsfolder() {
		return isfolder;
	}
	public void setIsfolder(int isfolder) {
		this.isfolder = isfolder;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
 
}
