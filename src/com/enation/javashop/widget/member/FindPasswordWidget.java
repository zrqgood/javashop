package com.enation.javashop.widget.member;

import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.enation.app.base.core.model.Member;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberManager;

public class FindPasswordWidget extends AbstractMemberWidget {
	private IMemberManager memberManager; 
	private JavaMailSender mailSender;
	
	protected void config(Map<String, String> params) {
		
	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String action = request.getParameter("action");
		this.showMenu(false);
		if("find".equals(action)){
			this.find();
		}else if("restorepwd".equals(action)){
			this.restorePwd();
		}
	}
	
	private void find(){
		
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String username = request.getParameter("username");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		Member member =	this.memberManager.getMemberByUname(username);
		if(member==null){
			this.showError("用户"+username +"不存在!");
			return;
		}
		
		String email = member.getEmail();
		MimeMessage message = mailSender.createMimeMessage();
//		String password = StringUtil.
		try {
			String newPwd = StringUtil.getRandom();
			
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
			helper.setFrom("kingapex@163.com");
			helper.setSubject(username+"您好，您的密码已经重置，详情请查看邮件内容。");
			helper.setTo(email);
			helper.setText("<html><body>"+username+"您的密码已经被重置为:<span style='color:red'><b>"+newPwd+"</b></span></body></html>", true);
			mailSender.send(message);
			
			this.memberManager.updatePassword(member.getMember_id(),newPwd);
			this.showSuccess("您的密码已经重置更新，请登录"+ email+"查收您的新密码。", "登录页面", "member_login.html");
			
		} catch (Exception ex) {
			this.showError("密码找回失败"+ ex.getMessage());
		}  	
		
//		
//		if( question==null || answer==null
//			||!question.equals(member.getPw_question() )
//		    ||!answer.equals( member.getPw_answer() )){
//			this.showError("问题或答案输入错误!");
//			return;
//		}
//	
//		ThreadContextHolder.getSessionContext().setAttribute("can_find_pwd", "yes");
//		this.putData("memberid", member.getMember_id());
//		this.putData("username", username);
//		this.setPageName("TypeNewPassword");
	
	}
	
	public void  restorePwd(){
		Object flag = ThreadContextHolder.getSessionContext().getAttribute("can_find_pwd");
		if("yes".equals(flag)){
			HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
			String memberid = request.getParameter("memberid");
			String password = request.getParameter("password");
			this.memberManager.updatePassword(Integer.valueOf(memberid),password);
			 ThreadContextHolder.getSessionContext().removeAttribute("can_find_pwd");
			this.showSuccess("密码成功更新", "登录页面", "member_login.html");
			
		}else{
			this.showError("非法操作!");
		}
	}
	

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}


	public JavaMailSender getMailSender() {
		return mailSender;
	}


	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	



}
