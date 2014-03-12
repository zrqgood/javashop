package com.enation.javashop.widget.member;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberLv;
import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberLvManager;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 抽像的会员中心挂件基类
 * @author kingapex
 *2010-3-11下午10:24:06
 */
public  class MemberMainWidget extends AbstractWidget { 
	
	private IMemberLvManager memberLvManager;

	/**
	 * 不缓存
	 */
	public boolean  cacheAble(){
		return false;
	}
	
	
	protected void config(Map<String, String> params) {

	}
	
	public String process(Map<String, String> params) {
		String servletPath  = ThreadContextHolder.getHttpRequest().getServletPath();
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		
		//拦截未登录进入会员中心
		if(
			!servletPath.equals("/member_login.html")
		   &&!servletPath.equals("/member_register.html")
		   &&!servletPath.equals("/member_logout.html")
		    &&!servletPath.equals("/member_findpassword.html")
		    &&!servletPath.startsWith("/member_orderdetail")
		    &&!servletPath.startsWith("/member_orderpay")
		   &&member==null){
			
			String loginpage =params.get("page_login");
			if(!StringUtil.isEmpty(loginpage)){
				freeMarkerPaser.setPageFolder(this.getThemePath()+"/"+params.get("folder"));
				freeMarkerPaser.setPageName(params.get("page_login"));
			}else{
				freeMarkerPaser.setPageName("login");
			}
			return freeMarkerPaser.proessPageContent();
		}
		
 
		freeMarkerPaser.setPageFolder(null);
		freeMarkerPaser.setPageName(null);

		
	
		servletPath= servletPath.substring(1,servletPath.indexOf('.'));
		
		if(servletPath.startsWith("member_orderdetail_") ){
			servletPath ="member_orderdetail";
		}
		
		String url =RequestUtil.getRequestUrl(ThreadContextHolder.getHttpRequest());
		url = url.substring(1,url.length());
		this.putData("menuUrl", url);
		AbstractMemberWidget  widget =SpringContextHolder.getBean(servletPath);

		
		//由子来决定是否显示菜单
		this.putData("showMenu", widget.getShowMenu());
		
		/**
		 * -------------------------
		 * 解析会员中心挂件自定义页面
		 * since version2.2.4
		 * -------------------------
		 */
		String pagename =servletPath.replaceAll("member_", "");
		if(pagename!=null){
			pagename = params.get("page_"+pagename);
			
			if(pagename!=null){
				params.put("custom_page", pagename);
			}
		}
		
		String subHtml = widget.process(params);
		
		
		if( "yes".equals(ThreadContextHolder.getHttpRequest().getParameter("ajax") )){
			return subHtml;
		}else{
			
			if(!widget.getShowMenu()) return subHtml;
			
			execute(subHtml, params);
			if(showHtml){
				this.customPage= params.get("custom_page");
				this.folder= params.get("folder");
				if(!this.disableCustomPage && !StringUtil.isEmpty(customPage) ){
					EopSite site  = EopContext.getContext().getCurrentSite();
					String contextPath  = EopContext.getContext().getContextPath();
					if(StringUtil.isEmpty(this.folder)){
						this.freeMarkerPaser.setPageFolder(contextPath+"/themes/"+site.getThemepath());
					}else{
						this.freeMarkerPaser.setPageFolder(contextPath+"/themes/"+site.getThemepath()+"/"+folder);
					}
				}
				
				return freeMarkerPaser.proessPageContent();
			}
			else return "";
		}

	}

 

 
	protected void execute(String mainHtml, Map<String, String> params) {
		
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		if(member!=null){
			this.putData("member", member);
			if(member.getLv_id()!=null){
				MemberLv memberLv = memberLvManager.get(member.getLv_id());
				
				this.putData("memberLv", memberLv.getName());
			}else{
				this.putData("memberLv", "无等级");
			}
		}
		this.freeMarkerPaser.setClz(MemberMainWidget.class);
		this.freeMarkerPaser.setWrapPath(false);
		//因为上面子已经走过一遍，重至上freemarker配置
		this.setPageFolder(null);
		this.setPageFolder(null);
		this.setPageName(null);
		this.setPathPrefix(null);
		
		this.setPageExt(".html");
		String index = params.get("index");
		if(StringUtil.isEmpty(index)){
			this.makeSureSetPageName("main");	
		}else{
			this.makeSureSetPageName("mainindex");
		}
		
		
		/**
		 * 解析会员中心菜单
		 */
		String menuPageName= params.get("page_menu");
		String menuHtml = null;
		if(menuPageName==null) { //如果自定义菜单页面为空则解析默认菜单
			menuHtml =this.parseDefaultMenu();
		}else{ //解析自定义菜单
			menuHtml =this.parseMenuHtml(params.get("folder"), menuPageName);
		}
		
		this.putData("menuHtml", menuHtml);
		this.putData("main", mainHtml);
	}
	
	/**
	 * 解析自定义菜单
	 * @param folder
	 * @param menuPageName
	 * @return
	 */
	private String parseMenuHtml(String folder,String menuPageName) {

		try {
			EopSite site  = EopContext.getContext().getCurrentSite();
			Map data = new HashMap();
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
			String pageFolder = EopContext.getContext().getContextPath()
					+ "/themes/"+site.getThemepath()+"/"+folder;
			cfg.setServletContextForTemplateLoading(ThreadContextHolder
					.getHttpRequest().getSession().getServletContext(),
					pageFolder);
			Template temp = cfg.getTemplate(menuPageName+".html");
			ByteOutputStream stream = new ByteOutputStream();

			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);

			out.flush();
			String html = stream.toString();
			return html;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "";

	}
	
	
	/**
	 * 解析默认菜单页面
	 * @return
	 */
	private String parseDefaultMenu(){
		
		try{
			Map data  = new HashMap();
			Configuration cfg = new Configuration();
			cfg.setObjectWrapper(new DefaultObjectWrapper());	
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
			
		 
			cfg.setClassForTemplateLoading(this.getClass(), "");
			Template temp = cfg.getTemplate("member_menu.html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			
			out.flush();
			String html = stream.toString();
			return html;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}
	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}
	
	protected void display(Map<String, String> params) {
		
	}
	
 
}
