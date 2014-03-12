package com.enation.eop.processor.backend.support;

import com.enation.eop.processor.AbstractFacadeProcessor;
import com.enation.eop.processor.FacadePage;
import com.enation.eop.processor.core.Response;
import com.enation.eop.resource.IAdminThemeManager;
import com.enation.eop.resource.model.AdminTheme;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.util.HttpUtil;

/**
 * 后台页面获取器
 * @author kingapex
 * 2010-9-13下午12:13:24
 */
public class BackPageGetter extends AbstractFacadeProcessor {
	
	private IAdminThemeManager adminThemeManager;

	public BackPageGetter(FacadePage page) {
		super(page);
	}

	
	protected Response process() {

		EopSite site = EopContext.getContext().getCurrentSite();//this.page.getSite();
		
		adminThemeManager = SpringContextHolder.getBean("adminThemeManager");
		
		//读取后台使用的模板
		AdminTheme theme = adminThemeManager.get( site.getAdminthemeid());
		String path ="default";
		if(theme!=null){
			path = theme.getPath();
		}
		StringBuffer context = new StringBuffer();

		// context.append(EopSetting.IMG_SERVER_DOMAIN);
		//当前用户的上下文
		//String contextPath  = EopContext.getContext().getContextPath();
//		context.append(contextPath);
		context.append(EopSetting.ADMINTHEMES_STORAGE_PATH);
		context.append("/");
		context.append(path);
		
		StringBuffer  staticdomain = new StringBuffer();
		
		/***********************20100920修改：***************************/
//		//静态资源分离模式
//		if(EopSetting.RESOURCEMODE.equals("1")){
//			staticdomain.append(EopSetting.IMG_SERVER_DOMAIN);
//		}
		//静态资源合并模式
//		if(EopSetting.RESOURCEMODE.equals("2")){
			if("/".equals(EopSetting.CONTEXT_PATH) )
				staticdomain.append("");
			else	
				staticdomain.append(EopSetting.CONTEXT_PATH);
//		}
		
		// 设置页面上变量的值
		httpRequest.setAttribute("context", staticdomain + context.toString()); //静态资源上下文		
		httpRequest.setAttribute("title",site.getSitename()); //站点名称
		httpRequest.setAttribute("ico",site.getIcofile());    //ico文件
		httpRequest.setAttribute("logo", site.getLogofile()) ; //logo文件
		httpRequest.setAttribute("version", EopSetting.VERSION) ; //版本
		httpRequest.setAttribute("bkloginpicfile", site.getBkloginpicfile()); //后台登录logo
		httpRequest.setAttribute("bklogo", site.getBklogofile()==null?site.getLogofile():site.getBklogofile()); //后台主界面logo
		
		String uri = page.getUri();

		if (uri.startsWith("/admin/main")) { //后台首页
			
			uri = context.toString() + "/main.jsp";
//			request  = new GetPointJsWrapper(page, request); //包装积分获取js
			request = new HelpDivWrapper(page, request);
		
		} else if (uri.equals("/admin/") || uri.equals("/admin/index.jsp")) { //登录页面
			//读取记住的用户名
			String username  = HttpUtil.getCookieValue(httpRequest, "loginname");
			httpRequest.setAttribute("username", username);
			uri =  context.toString() + "/login.jsp";
		} else {
			
			if(EopSetting.EXTENSION.equals("action")){
				uri = uri.replace(".do", ".action");
			}
			
			String ajax = httpRequest.getParameter("ajax");
			if(!"yes".equals(ajax)){ //非异步包装后台内容界面
				request = new BackTemplateWrapper(page, request); 
				request = new HelpDivWrapper(page, request);
				
			}
			
		}

		Response response = request.execute(uri, httpResponse, httpRequest);
	 
		return response;

	}
}
