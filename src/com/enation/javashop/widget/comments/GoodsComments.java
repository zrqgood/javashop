package com.enation.javashop.widget.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.service.ISettingService;
import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.service.ICommentsManager;

public class GoodsComments extends AbstractWidget {
	private ICommentsManager commentsManager;
	private ISettingService settingService;
	
	
	protected void config(Map<String, String> params) {
		this.setPageName("Comments_config");
		String commentType = params.get("commentType");
		String object_type = params.get("object_type");
		this.putData("commentType", commentType);
		this.putData("object_type", object_type);
	}
	
	private String commentType;
	private String object_type;

	
	protected void display(Map<String, String> params) {
//		String commentType = params.get("commentType");
//		String object_type = params.get("object_type");
		Integer goods_id  = object_type.equals("leavewords")? 0 : this.getGoodsId();
		
		String pageSize = settingService.getSetting("comments", "pageSize");
		pageSize = pageSize == null ? "5" : pageSize;
		
		String directShow = settingService.getSetting("comments", "directShow");
		directShow = directShow == null ? "0" : directShow;
		
		String anonymous = settingService.getSetting("comments", "anonymous");
		anonymous = anonymous == null ? "0" : anonymous;
		
		String validcode = settingService.getSetting("comments", "validcode");
		validcode = validcode == null ? "0" : validcode;
		
		Page commentsPage  = commentsManager.pageComments_Display(1, Integer.valueOf(pageSize), goods_id, object_type, commentType);
		Long totalCount = commentsPage.getTotalCount();
		List commentsList  =  (List)commentsPage.getResult();
		commentsList = commentsList==null?new ArrayList():commentsList;
		
		this.putData("wid", object_type);
		this.putData("totalCount", totalCount);
		this.putData("commentType", commentType);
		this.putData("object_type", object_type);
		this.putData("commentsList", commentsList);
		this.putData("goods_id", goods_id);
		this.putData("pageSize", pageSize);
		this.putData("directShow", directShow);
		this.putData("anonymous", anonymous);
		this.putData("validcode", validcode);
		this.setPageName("Comments");
		//this.setPageFolder("/debug");
		
		if("shop".equals(commentType)){
			Nav nav = new Nav();
			nav.setTitle("客户留言");
			this.putNav(nav);
		}
		
	}
	
	
	private Integer getGoodsId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		
		if(url.startsWith("/widget")) return 0;
		
		String goods_id = this.paseGoodsId(url);
		
		return Integer.valueOf(goods_id);
	}

	private  static  String  paseGoodsId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}

	public void setCommentsManager(ICommentsManager commentsManager) {
		this.commentsManager = commentsManager;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}


	public String getCommentType() {
		return commentType;
	}


	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}


	public String getObject_type() {
		return object_type;
	}


	public void setObject_type(String objectType) {
		object_type = objectType;
	}
	
	
}
