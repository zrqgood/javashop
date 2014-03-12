package com.enation.eop.processor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.sdk.context.EopContextIniter;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.context.SaasEopContextIniter;
import com.enation.eop.sdk.utils.JspUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * 独立版的filter
 * @author kingapex
 * @version 1.0
 * @created 12-十月-2009 10:30:23
 */
public class DispatcherFilter implements Filter {

	public void init(FilterConfig config) {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			
			String uri = httpRequest.getServletPath();
			httpRequest= this.wrapRequest(httpRequest, uri);
			
			if(uri.startsWith("/statics")) { 
				chain.doFilter(httpRequest, httpResponse);
				return; 
			}
			
			if(!uri.startsWith("/install") && EopSetting.INSTALL_LOCK.toUpperCase().equals("NO")){
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/install");
				return;
			}
			if(!uri.startsWith("/install.html") &&uri.startsWith("/install")
			  && !uri.startsWith("/install/images")		
			  && EopSetting.INSTALL_LOCK.toUpperCase().equals("YES")){
				httpResponse.getWriter().write("如要重新安装，请先删除/install/install.lock文件，并重起web容器");
				return ;
			}
			
			if("2".equals(EopSetting.RUNMODE)){
				SaasEopContextIniter.init(httpRequest, httpResponse);
			}else{
				EopContextIniter.init(httpRequest, httpResponse);
			}
			
			
			// System.out.println(uri);
			Processor processor = ProcessorFactory.newProcessorInstance(uri,
					httpRequest);

			if (processor == null) {
				chain.doFilter(request, response);
			//	System.out.println(uri+" null...");
			} else {
			 
				Response eopResponse = processor.process(0, httpResponse,
						httpRequest);

				InputStream in = eopResponse.getInputStream();

				if (in != null) {
					byte[] inbytes= IOUtils.toByteArray(in);
					httpResponse.setContentType(eopResponse.getContentType());
					httpResponse.setCharacterEncoding("UTF-8");
					httpResponse.setHeader("Content-Length",""+inbytes.length); 
					OutputStream output = httpResponse.getOutputStream();
					IOUtils.write(inbytes, output) ;
				} else {
					chain.doFilter(request, response);
				}

			}
			FreeMarkerPaser.remove();
		} catch (RuntimeException exception) {
			exception.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			request.setAttribute("message", exception.getMessage());
			String content = JspUtil.getJspOutput("/commons/error.jsp",
					httpRequest, httpResponse);
			response.getWriter().print(content);
			// response.flushBuffer();
		}
	}

	public void destroy() {

	}
	
	private HttpServletRequest wrapRequest(HttpServletRequest request,String url){
		List<String> safeUrlList = EopSetting.safeUrlList;
		for(String safeUrl:safeUrlList){
			if(safeUrl.equals(url)){
				return request;
			}
		}
		return new SafeHttpRequestWrapper(request);//包装安全request
	}
}