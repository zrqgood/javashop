package com.enation.javashop.widget.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.Message;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IMessageManager;

/**
 * 短信息挂件
 * @author kingapex
 *
 */
public class MemberMessageWidget extends AbstractMemberWidget {
	
	private IMessageManager messageManager;
	private IMemberManager memberManager;

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		action = action == null ? "" : action;
		if(action.equals("inbox")){
			this.setPageName("message_inbox");
			String page  = request.getParameter("page");
			page = (page == null || page.equals("")) ? "1" : page;
			int pageSize = 10;
			Page inboxPage = this.messageManager.pageMessage(Integer.valueOf(page), pageSize, "inbox");
			Long totalCount = inboxPage.getTotalCount();
			Long pageCount = inboxPage.getTotalPageCount();
		
			List inboxList = (List)inboxPage.getResult();
			inboxList = inboxList == null ? new ArrayList() : inboxList;
			this.putData("totalCount", totalCount);
			this.putData("pageSize", pageSize);
			this.putData("page", page);
			this.putData("inboxList", inboxList);
			this.putData("pageCount", pageCount);
		}else if(action.equals("outbox")){
			this.setPageName("message_outbox");
			String page  = request.getParameter("page");
			page = (page == null || page.equals("")) ? "1" : page;
			int pageSize = 10;
			Page outboxPage = this.messageManager.pageMessage(Integer.valueOf(page), pageSize, "outbox");
			Long totalCount = outboxPage.getTotalCount();
			Long pageCount = outboxPage.getTotalPageCount();
		
			List outboxList = (List)outboxPage.getResult();
			outboxList = outboxList == null ? new ArrayList() : outboxList;
			this.putData("totalCount", totalCount);
			this.putData("pageSize", pageSize);
			this.putData("page", page);
			this.putData("outboxList", outboxList);
			this.putData("pageCount", pageCount);
		}else if(action.equals("send")){
			this.setPageName("message_send");
		}else if(action.equals("sendSave")){
			IUserService userService = UserServiceFactory.getUserService();
			Member member = userService.getCurrentMember();
			Message message = new Message();
			message.setFrom_id(member.getMember_id());
			message.setMsg_from(member.getUname());
			
			String subject = request.getParameter("subject");
			message.setSubject(subject);
			
			String msg = request.getParameter("message");
			message.setMessage(msg);
			
			message.setDate_line((new Date()).getTime());
			
			String msg_to = request.getParameter("msg_to");
			msg_to = msg_to.replace(" ", ",");
			String[] msg_to_array = msg_to.split(",");
			String returnmessage = "";
			
			try{
				for(String uname:msg_to_array){
					Member m = memberManager.getMemberByUname(uname);
					if(m!=null){
						message.setTo_id(m.getMember_id());
						message.setMsg_to(m.getUname());
						messageManager.addMessage(message);
						returnmessage += "[" + uname + "] ";
					}
				}
				this.showSuccess("消息已发送给"+returnmessage,"发送消息", "member_message.html?action=send");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("发送失败", "发送消息", "member_message.html?action=send");
			}
		}else if(action.equals("indel")){
			String[] ids = request.getParameterValues("delete");
			try{
				messageManager.delinbox(StringUtil.arrayToString(ids, ","));
				this.showSuccess("删除成功","收件箱", "member_message.html?action=inbox");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("删除失败", "收件箱", "member_message.html?action=inbox");
			}
		}else if(action.equals("outdel")){ 
			String[] ids = request.getParameterValues("delete");
			try{
				messageManager.deloutbox(StringUtil.arrayToString(ids, ","));
				this.showSuccess("删除成功","发件箱", "member_message.html?action=outbox");
			}catch(Exception e){
				if(this.logger.isDebugEnabled()){
					logger.error(e.getStackTrace());
				}
				this.showError("删除失败", "发件箱", "member_message.html?action=outbox");
			}
		}else if(action.equals("reply")){
			this.setPageName("message_reply");
			String msg_to = request.getParameter("msg_to");
			String subject = request.getParameter("subject");
			this.putData("msg_to", StringUtil.to(msg_to, "utf-8"));
			this.putData("subject", StringUtil.to(subject, "utf-8"));
		}else if(action.equals("read")){
			this.putData("json", "{message:'ok'}");
			this.setPageName("json");
			Message m = new Message();
			String msg_id = request.getParameter("msg_id");
			messageManager.setMessage_read(Integer.valueOf(msg_id));
		}
	}

	public IMessageManager getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

}
