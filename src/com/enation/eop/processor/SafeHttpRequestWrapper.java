package com.enation.eop.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import com.enation.framework.util.StringUtil;

/**
 * 安全的httprequest包装器
 * @author kingapex
 *
 */
public class SafeHttpRequestWrapper extends HttpServletRequestWrapper {

	public SafeHttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 对字符进行安全过滤
	 * @param value
	 * @return
	 */
	private String safeFilter(String value){
		if(value==null) return null;
		//防止sql注入的过滤
	 	value=value.replaceAll("\\'", "‘");
	 	value=value.replaceAll("--", "－－");
	 	value=value.replaceAll("\\*", "×");
	 	value=value.replaceAll("\\=", "＝");
	 	
	 	//防止跨站点脚本攻击的过滤
	 	value=value.replaceAll("<", "&lt;");
		return value;
	}
	
	/**
	 * 对字串数组进行安全过滤
	 * @param values
	 */
	private void safeFilter(String[] values){
		 if(values!=null){
			 for(int i=0;i<values.length;i++){
				 values[i]= this.safeFilter(  values[i] );
			 }
		 }
	}
	
	public String getParameter(String name) {
		String value = 	super.getParameter(name);
		value = this.safeFilter(value);
		return value;
	}

	
	public Map getParameterMap() {
		 Map map =super.getParameterMap();
		 Iterator keiter = map.keySet().iterator();
		 while(keiter.hasNext()){
			 String name=keiter.next().toString();
			 Object value  =map.get(name);
			 if(value instanceof String){
				 value =  this.safeFilter( ((String)value) );
			 }
			 if(value instanceof String[]){
				 String[] values = (String[])value;
				 this.safeFilter(values);
			 }
			 
		 }
		return map;
	}
	public String[] getParameterValues(String arg0) {
		 String[] values = super.getParameterValues(arg0);
		 this.safeFilter( values );
		return values;
	}


}
