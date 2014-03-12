package com.enation.eop.sdk;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EopServlet   {
 
	public void service(HttpServletRequest request, HttpServletResponse response)throws  IOException ;
 

}
