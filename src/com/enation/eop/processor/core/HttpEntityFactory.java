package com.enation.eop.processor.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;


public abstract class HttpEntityFactory {

	
	
	
	public static HttpEntity buildEntity(HttpServletRequest request,Map<String,String> otherParams) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		HttpEntity entity =  null;
		if(isMultipart){
			entity= buildMultipartFormEntity(request,otherParams);
		}else{
			entity= buildFormEntity(request,otherParams);
		}
		return entity;
	}

	
	
	private static HttpEntity buildFormEntity(HttpServletRequest request,Map<String,String> otherParams) {
		try {
//			Map paramMap = request.getParameterMap();
			Enumeration<String> paramNames =   request.getParameterNames();
			if(paramNames==null) return null;
			
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			
			
			while(paramNames.hasMoreElements()){
				String name  = paramNames.nextElement();
				String value  = request.getParameter(name);
				formparams.add(new BasicNameValuePair(name, value));
			}
			
			if(otherParams!=null){
				Iterator<String> iterator = otherParams.keySet().iterator();
			    while(iterator.hasNext()){
			    	String key = iterator.next();
			    	String value = otherParams.get(key);
			    	formparams.add(new BasicNameValuePair(key, value));	    	
			    }
			    
			}			
			
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					"UTF-8");

			return entity;

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	
	private static HttpEntity buildMultipartFormEntity(HttpServletRequest request,Map<String,String> otherParams){
		 

		try {
			MultipartEntity entity = new MultipartEntity();
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);			
			List<FileItem >  items = upload.parseRequest(request);
			for (FileItem item:items) {
			    String fieldName = item.getFieldName();
			  
			    if (item.isFormField()) { 
			    	
			    	StringBody strBody = new StringBody( item.getString());
			    	//entity.addPart(fieldName, strBody);
			    } else {
			    	 String name = item.getName();
			    	InputStream dataIn = new ByteArrayInputStream(item.get());
			    	InputStreamBody isbody= new InputStreamBody( dataIn,name);
			    	//entity.addPart(fieldName, isbody);
			    }
			    
			}

			
			if(otherParams!=null){
				Iterator<String> iterator = otherParams.keySet().iterator();
			    while(iterator.hasNext()){
			    	String key = iterator.next();
			    	String value = otherParams.get(key);
			    	StringBody strBody = new StringBody(value);
			    	//entity.addPart(key, strBody);    	
			    }
			    
			}	
			
			
			
			return entity;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
