package com.enation.eop.processor.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.Processor;
import com.enation.eop.processor.core.RemoteRequest;
import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.StringResponse;
import com.enation.eop.sdk.user.UserContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;

public class LogoutProcessor implements Processor {

	
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		
		WebSessionContext<UserContext> sessonContext = ThreadContextHolder.getSessionContext();			
		Response response= new StringResponse();
		sessonContext.removeAttribute(UserContext.CONTEXT_KEY);
		response.setContent("<script>location.href='index.jsp'</script>");
		return response;
	}
 
}
