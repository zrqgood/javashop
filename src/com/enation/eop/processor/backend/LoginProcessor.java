package com.enation.eop.processor.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.enation.app.base.core.service.auth.IAdminUserManager;
import com.enation.eop.processor.Processor;
import com.enation.eop.processor.core.EopException;
import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.StringResponse;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.dto.SessionDTO;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.user.UserContext;
import com.enation.eop.sdk.utils.ValidCodeServlet;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.context.webcontext.WebSessionContext;
import com.enation.framework.util.EncryptionUtil;
import com.enation.framework.util.HttpUtil;
import com.enation.framework.util.StringUtil;

/**
 * 用户登录处理器
 * @author kingapex
 * <p>2009-12-16 上午10:20:33</p>
 * @version 1.0
 */
public class LoginProcessor implements Processor {

	protected final Logger logger = Logger.getLogger(getClass());
	
	/**
	 * 进行用户登录操作<br/>
	 * 如果登录成功，则生成应用的domain json供SSO的javascript使用
	 */
	@SuppressWarnings("unchecked")
	
	public Response process(int mode,  HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		String type = httpRequest.getParameter("type");
		if(type==null || "".equals(type)){
			return this.userLogin(httpResponse, httpRequest);
		}else{
			return this.sysLogin(httpResponse, httpRequest);
		}
		
	
	}
	
	
	/**
	 * 系统跳转登录后台
	 * @param httpResponse
	 * @param httpRequest
	 * @return
	 */
	public Response sysLogin(HttpServletResponse httpResponse,
			HttpServletRequest httpRequest){
		Response response = new StringResponse();
		
		String endata =  httpRequest.getParameter("s");
		if(endata==null){ response.setContent("非法数据"); return response;}
		
		endata =  EncryptionUtil.authCode(endata, "DECODE");
		String[] ar = endata.split(",");
		//if(ar==null||ar.length!=3){ response.setContent("非法数据"); return response;}
		
		String username = ar [0];
		String password = ar [1];
		Long dateline = Long.valueOf(ar[2]);
		
//		if(new Date().getTime() - dateline>5000){
//			 response.setContent("已经过期"); return response;
//		}
		 
		try {
			
			/*
			 * 登录校验
			 */
			IAdminUserManager userManager =SpringContextHolder.getBean("adminUserManager");
			int result = userManager.loginBySys(username, password);
			if(result==1){
				response.setContent( "<script>location.href='main.jsp'</script>正在转向后台...");
			}else{
				response.setContent("{'result':1,'message':'登录失败：用户名或密码错误'}");
			}
			return response;
			
		} catch (EopException exception) {
			
			response.setContent("{'result':1,'message':'"+exception.getMessage()+"'}");
			return response;
		}	 
		
	}
	
	/**
	 * 用户手工登录后台
	 * @param httpResponse
	 * @param httpRequest
	 * @return
	 */
	public Response userLogin(HttpServletResponse httpResponse,
			HttpServletRequest httpRequest){
		String username = httpRequest.getParameter("username");
		String password = httpRequest.getParameter("password");
		String valid_code = httpRequest.getParameter("valid_code");
		
		try {
			
			/*
			 * 校验验证码
			 */
			if(valid_code==null) throw new EopException("验证码输入错误");			
			WebSessionContext sessonContext = ThreadContextHolder.getSessionContext();			
			Object realCode = sessonContext.getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"admin");
			
			if(!valid_code.equals(realCode)){
				throw new EopException("验证码输入错误");
			}
			
			/*
			 * 登录校验
			 */
			IAdminUserManager userManager =SpringContextHolder.getBean("adminUserManager");
			userManager.login(username, password);
 
			StringBuffer json = new StringBuffer();
			json.append("{'result':0}");
		 
			
			Response response = new StringResponse();
			response.setContent( json.toString() );
			
			String remember_login_name = httpRequest.getParameter("remember_login_name");
			if(!StringUtil.isEmpty(remember_login_name)){ //记住用户名
				HttpUtil.addCookie(httpResponse, "loginname", username, 365*24*60*60);
			}else{  //删除用户名
				HttpUtil.addCookie(httpResponse, "loginname", "", 0);
			}
			
			
			return response;
			 
		} catch (RuntimeException exception) {
			exception.printStackTrace();
			this.logger.error(exception.getMessage(),exception.fillInStackTrace());
			Response response = new StringResponse();
			response.setContent("{'result':1,'message':'"+exception.getMessage()+"'}");
			return response;
		}	 
	}
 


}
