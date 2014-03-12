package com.enation.eop.sdk;

import net.sf.json.JSONObject;

/**
 * 
 * 一个web元素的抽像,对应html元素<br/>
 * 此元素有text,style非别对应html元素的innerHTML和style属性,className为此元素的样式名<br/>
 * url为此元素的超级链接地址，为空则没有超级链接,target指定此超级链接的打开方式 。
 * 
 * @author kingapex
 * @version 1.0
 * @created 06-十一月-2009 13:14:40
 */
public class WebItem {
	
	public WebItem(){}
	
	public WebItem(String text){
		this.text = text;
	}

	private String style;
	private String target;
	private String text;
	private String url;
	private String className;
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String toJson(){
		JSONObject jsonObject  = JSONObject.fromObject(this);
		return jsonObject.toString();
	}
	

}