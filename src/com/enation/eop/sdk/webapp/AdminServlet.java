package com.enation.eop.sdk.webapp;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.sdk.EopServlet;
import com.enation.eop.sdk.utils.JspUtil;

public class AdminServlet implements EopServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		
		
		String servletPath  = request.getServletPath();
		request.setAttribute("content",  JspUtil.getJspOutput(servletPath, request, response) );
		String content  = JspUtil.getJspOutput("/eop/main_frame.jsp", request, response) ;
		//response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(content);
		
		
		
	}

}
