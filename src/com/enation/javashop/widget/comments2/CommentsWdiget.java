package com.enation.javashop.widget.comments2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.service.ICommentsManager;

/**
 * 评论挂件
 * @author kingapex
 *
 */
public class CommentsWdiget extends AbstractWidget {
	private ICommentsManager commentsManager;
	private ISettingService settingService;
	protected void config(Map<String, String> params) {
		
	}

	protected void display(Map<String, String> params) {
		if("list".equals(action)){
			String commenttype = params.get("commenttype") ;
			String objecttype = params.get("objecttype") ;
			this.list(commenttype, objecttype);
		}
	}
	
	/**
	 * 读取评论列表
	 * @param commentType 评论对象类型，如goods、article ，标识是文章评论或者是商品评论
	 * @param object_type 评论类型，如 ask 提问，discuss评论
	 * 此处参数规则，十分奇怪，似乎应该将参数意义交换更为合理
	 */
	private void list(String commentType,String object_type){
		
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String objectid = request.getParameter("objectid");
		String pageno = request.getParameter("page");
		pageno = pageno== null ?"1":pageno;
		
		String pageSize = settingService.getSetting("comments", "pageSize");
		pageSize = pageSize == null ? "5" : pageSize;
		
		String directShow = settingService.getSetting("comments", "directShow");
		directShow = directShow == null ? "0" : directShow;
		
		String anonymous = settingService.getSetting("comments", "anonymous");
		anonymous = anonymous == null ? "0" : anonymous;
		
		String validcode = settingService.getSetting("comments", "validcode");
		validcode = validcode == null ? "0" : validcode;
		
		Page commentsPage  = null;
		
		if(object_type!=null){
			commentsPage = commentsManager.pageComments_Display(Integer.valueOf(pageno), Integer.valueOf(pageSize), Integer.valueOf(objectid), object_type, commentType);
		}else{
			commentsPage = commentsManager.listAll(Integer.valueOf(pageno), Integer.valueOf(pageSize), Integer.valueOf(objectid), commentType);
		}
		
		Long totalCount = commentsPage.getTotalCount();
		List commentsList  =  (List)commentsPage.getResult();
		commentsList = commentsList==null?new ArrayList():commentsList;
		
		this.putData("pageno", pageno);
		this.putData("pagesize", pageSize);
		this.putData("totalcount", totalCount);
		this.putData("commentType", commentType);
		this.putData("object_type", object_type);
		this.putData("commentsList", commentsList);
		this.putData("validcode", validcode);
		
	}

	
	
	private void add(){
		
	}
	
	public ICommentsManager getCommentsManager() {
		return commentsManager;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

}
