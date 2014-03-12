package com.enation.eop.resource.model;

/**
 * @author lzf
 *         <p>
 *         created_time 2009-11-25 下午04:34:33
 *         </p>
 * @version 1.0
 */
public class AdminTheme extends Resource {
	private String themename;
	private String path;
	private String author;
	private String version;
	private String thumb = "preview.png";

	/**
	 * 0否 1是
	 */
	private int framemode;

	// public String getThumb(String type) {
	// String result = "";
	// if(type.toUpperCase().equals("APP")){
	// result = StringUtil.getEopCachePath() +
	// "/eop/"+this.getAppid()+"/theme/"+this.getPath()+"/preview.png";
	// }else{
	// result = StringUtil.getEopCachePath()
	// +"/user/"+this.getUserid()+"/"+this.getSiteid()+"/theme/"+this.getPath()+"/preview.png";
	// }
	//		
	// return result;
	// }

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getThemename() {
		return themename;
	}

	public void setThemename(String themename) {
		this.themename = themename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getFramemode() {
		return framemode;
	}

	public void setFramemode(int framemode) {
		this.framemode = framemode;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
