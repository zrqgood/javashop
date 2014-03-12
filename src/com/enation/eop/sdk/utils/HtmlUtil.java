package com.enation.eop.sdk.utils;

/**
 * html工具类
 * @author kingapex
 * 2010-2-9上午01:08:02
 */
public class HtmlUtil {
	private HtmlUtil(){};
	
	/**
	 * 将某内容添加至某元素末尾
	 * @param html
	 * @param nodeName
	 * @param content
	 * @return
	 */
	public static String appendTo(String html,String nodeName,String content){
		
		return html.replaceAll("</"+nodeName+">", content+"</"+nodeName+">");
	}
	
	/**
	 * 将内容插入的某元素的开始
	 * @param html
	 * @param nodeName
	 * @param content
	 * @return
	 */
	public static String insertTo(String html,String nodeName,String content){
		return html.replaceAll("<"+nodeName+">", "<"+nodeName+">"+content);
	}
	
	public static void main(String[] args){
		String html ="<html><head>adfbb</head><body>adfadsf</body></html>";
		html = insertTo(html,"head","abc");
		System.out.println(html);
	}
}
