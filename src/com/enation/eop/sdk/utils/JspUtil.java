package com.enation.eop.sdk.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class JspUtil {
	public static String getJspOutput(String jsppath, HttpServletRequest request,
			HttpServletResponse response)  {
		WrapperResponse wrapperResponse = new WrapperResponse(response);
		try {
			//System.out.println("1-1:"+request);
			request.getRequestDispatcher(jsppath).include(request, wrapperResponse);
			
		} catch (ServletException e) {
		 
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		return wrapperResponse.getContent();
	}
	
	public static String getJspOutput1(String jsppath, HttpServletRequest request,
			HttpServletResponse response)  {
		WrapperResponse wrapperResponse = new WrapperResponse(response);
		try {
		 
			request.getRequestDispatcher(jsppath).forward(request, wrapperResponse);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wrapperResponse.getContent();
	}
	
	
}
