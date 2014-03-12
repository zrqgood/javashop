package com.enation.app.base.core.action;

import java.util.Date;

import com.enation.app.base.core.model.Ask;
import com.enation.app.base.core.model.Reply;
import com.enation.app.base.core.service.IAskManager;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;

/**
 * 问题action
 * @author kingapex
 * 2010-8-10上午11:47:56
 */
public class AskAction extends WWAction {
	
	private IAskManager askManager;
	private IUserManager userManager;
	
	private Integer askid;
	private Ask ask;
	private Integer[] id;
	
	
	//搜索条件
	private String keyword; //关键字
	private String startTime;//开始时间
	private String endTime;//结束时间
	
	private String title;
	private String content;
	
	
	/**
	 * 读取所有问题，管理员在后台查看用
	 * @return
	 */
	public String listAll(){
		keyword = StringUtil.isEmpty(keyword)?null: StringUtil.toUTF8(keyword);
		Date start = StringUtil.isEmpty(startTime)?null: DateUtil.toDate(startTime, "yyyy-MM-dd");
		Date end  =  StringUtil.isEmpty(endTime)?null: DateUtil.toDate(endTime, "yyyy-MM-dd");
		this.webpage =this.askManager.listAllAsk(keyword, start, end, this.getPage(), this.getPageSize());
		
		return "alllist";
	}


	/**
	 * 读取我的问题列表
	 * @return
	 */
	public String listMy(){
		keyword = StringUtil.isEmpty(keyword)?null: StringUtil.toUTF8(keyword);
		Date start = StringUtil.isEmpty(startTime)?null: DateUtil.toDate(startTime, "yyyy-MM-dd");
		Date end  =  StringUtil.isEmpty(endTime)?null: DateUtil.toDate(endTime, "yyyy-MM-dd");
	this.webpage=	this.askManager.listMyAsk(keyword, start, end, this.getPage(), this.getPageSize());
		
		return "mylist";
	}

	
	/**
	 * 用户查看问题详细
	 * @return
	 */
	public String userview(){
		this.ask = this.askManager.get(askid);
		return "user_view";
	}
	
	
	/**
	 * 管理员查看问题详细
	 * @return
	 */
	public String adminview(){
		this.ask = this.askManager.get(askid);
		return "admin_view";
	}	
	
	
 
	/**
	 * 管理员回复
	 * @return
	 */
	public String adminReply(){
		Reply reply = new Reply();
		reply.setAskid(askid);
		reply.setContent(content);
		reply.setUsername("客服");
		this.askManager.reply(reply);
		this.msgs.add("回答已经提交");
		this.urls.put("问题列表", "ask!listAll.do");
		return this.MESSAGE;
	}
	
	
	
	/**
	 *  用户回复
	 * @return
	 */
	public String userReply(){
		IUserService userService = UserServiceFactory.getUserService();
		Integer userid  = userService.getCurrentUserId();
		EopUser user  = this.userManager.get(userid);
		
		Reply reply = new Reply();
		reply.setAskid(askid);
		reply.setContent(content);
		reply.setUsername(user.getUsername());
		this.askManager.reply(reply);
		this.msgs.add("回答已经提交");
		this.urls.put("问题列表", "ask!listMy.do");		
		return this.MESSAGE;
	}
	
	public String toAsk(){
		
		return "ask";
	}

	public String ask(){
		Ask ask  = new Ask();
		ask.setTitle(title);
		ask.setContent(content);
		this.askManager.add(ask);
		this.msgs.add("问题已经提交");
		this.urls.put("问题列表", "ask!listMy.do");		
		return this.MESSAGE;		
	}
	
	public String delete(){
		try{
			this.askManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		}catch(RuntimeException e){
			this.json="{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public Integer[] getId() {
		return id;
	}


	public void setId(Integer[] id) {
		this.id = id;
	}


	public IAskManager getAskManager() {
		return askManager;
	}

	public void setAskManager(IAskManager askManager) {
		this.askManager = askManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Ask getAsk() {
		return ask;
	}


	public void setAsk(Ask ask) {
		this.ask = ask;
	}


	public Integer getAskid() {
		return askid;
	}


	public void setAskid(Integer askid) {
		this.askid = askid;
	}


	public IUserManager getUserManager() {
		return userManager;
	}


	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
