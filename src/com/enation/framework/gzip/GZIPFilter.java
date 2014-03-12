package com.enation.framework.gzip;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GZIPFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
    	
       if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
        	//System.out.println("gizp:"+request.getServletPath());
            String ae = request.getHeader("accept-encoding");
            if (ae != null && ae.indexOf("gzip") != -1) {
                GZIPResponseWrapper gZIPResponseWrapper = new GZIPResponseWrapper(response);
                chain.doFilter(req, gZIPResponseWrapper);
                gZIPResponseWrapper.finishResponse();
                return;
          }
            chain.doFilter(req, res);            
       }else{
    	   System.out.println("not servlet request...");
       }
    }

    public void init(FilterConfig filterConfig) {
        System.out.println("The GZIP Is Enable");
    }

    public void destroy() {
        //
    }
}
