package com.enation.javashop.widget.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.eop.sdk.utils.ValidCodeServlet;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.service.IMemberManager;

/**
 * 会员登陆挂件
 * @author kingapex
 *2010-4-29上午08:08:39
 */
public class MemberLoginWidget extends AbstractMemberWidget {
	private HttpServletRequest request;
	private IMemberManager memberManager;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		request = ThreadContextHolder.getHttpRequest();

		String username  = request.getParameter("username");
		String password  = request.getParameter("password");
		String validcode =request.getParameter("validcode");
		
		if(this.logger.isDebugEnabled()){
			this.logger.debug("login action is " + action);
		}
		
		this.showMenu(false);
		if("login".equals(action)){

			this.login(username, password, validcode);
		}else if("ajaxlogin".equals(action)){
			this.ajaxlogin(username, password, validcode);
		}else{
			String forward =request.getParameter("forward");
			this.putData("forward", forward);
			this.setPageName("login");
		}
		
	}
	
	private void ajaxlogin(String username,String password,String validcode){
		String json = "";
		if(this.validcode(validcode) ==1){
			
			int result  = this.memberManager.login(username, password);
			String forward = request.getParameter("forward");
			json ="{result:"+ result +",forward:'"+ forward +"'}";
 
		}else{
			json = "{result:-1}";
		}
		this.showJson(json);
	}
	
	private void login(String username,String password,String validcode){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("do login");
		}
		
		if(this.validcode(validcode) ==1){
			
			int result  = this.memberManager.login(username, password);
			
			if(this.logger.isDebugEnabled()){
				this.logger.debug("login result is " + result);
			}
			
			
			if(result==1){
				String forward = request.getParameter("forward");
				if(forward!=null && !forward.equals("")){
					String message = request.getParameter("message");
					this.putUrl(message, forward);
					this.showSuccess("登录成功", message, forward);
				}else{
					this.showSuccess("登录成功","会员中心首页", "member_index.html");
				}
			}else{
				this.showError("用户名或密码输入错误");
			}
		}else{
			this.showError("验证码输入错误");
		}
		
	}
	
	
	/**
	 * 校验验证码
	 * @param validcode
	 * @return 1成功  0失败
	 */
	private int validcode(String validcode){
		if(validcode==null   ){
			return 0;
		}
		
		String code  = (String)ThreadContextHolder.getSessionContext().getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"memberlogin");
		if(code==null){
			return 0 ;
		}else{
			if(!code.equals(validcode)){
				return 0;
			}
		}
		
		return 1;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	
	

}
