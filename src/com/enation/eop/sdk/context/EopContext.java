package com.enation.eop.sdk.context;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.MultiSite;
import com.enation.eop.resource.model.EopSite;

public class EopContext {
	private static ThreadLocal<HttpServletRequest> HttpRequestHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<EopContext> EopContextHolder = new ThreadLocal<EopContext>();
	
	public static void setContext(EopContext context){
		EopContextHolder.set(context);
	}
	
	public static EopContext getContext(){
		EopContext context =  EopContextHolder.get();
		return context;
	}
	
	public static void setHttpRequest(HttpServletRequest request){
		HttpRequestHolder.set(request);
	}
	
	
	public static HttpServletRequest getHttpRequest(){
		return HttpRequestHolder.get();
	}
	
	//当前站点（主站）
	private EopSite currentSite;
	
	//当前子站
	private MultiSite currentChildSite;
	
	public  EopSite getCurrentSite(){
		return currentSite;
	}
	
	public  void setCurrentSite(EopSite site){
		currentSite = site;
	}
	
	
	public MultiSite getCurrentChildSite() {
		return currentChildSite;
	}

	public void setCurrentChildSite(MultiSite currentChildSite) {
		this.currentChildSite = currentChildSite;
	}

	
	//得到当前站点上下文
	public String getContextPath(){
		if("2".equals(EopSetting.RUNMODE) ){
			EopSite site  = this.getCurrentSite();
			StringBuffer context = new StringBuffer("/user");
			context.append("/");
			context.append(site.getUserid());
			context.append("/");
			context.append(site.getId());
			return context.toString();
		}else{
			return "";
		}
	}
}
