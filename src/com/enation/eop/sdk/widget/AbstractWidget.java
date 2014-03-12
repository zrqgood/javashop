package com.enation.eop.sdk.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.widget.nav.Nav;
import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;

/**
 * 基于freemarker的挂件基类
 * 
 * @author kingapex 2010-1-29上午10:08:46
 */
abstract public class AbstractWidget extends BaseSupport implements IWidget {

	
	//是否要解析html并显示
	protected boolean showHtml=true;
	protected FreeMarkerPaser freeMarkerPaser;
	private Map<String,String > urls;
	protected String customPage; //自定义挂件页面,以当前模板为上下文
	protected String folder; //自定义挂件页面所在文件夹，不指定为当前模板
	protected boolean disableCustomPage=false;
	protected String action;
	/**
	 * 完成freemarker的模板处理<br/>
	 * 模板路径是子类挂件所在包<br/>
	 * 在解析模板之前会调用子类的 {@link #display(Map)}方法来设置挂件模板中的变量
	 */
	
	public  String process(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		//校验是否必须登陆
		String mustbelogin = params.get("mustbelogin");
		if("yes".equals(mustbelogin)){
			Member member  = UserServiceFactory.getUserService().getCurrentMember();
			if(member== null){
				String forward = request.getServletPath();
				return "<script>location.href='member_login.html?forward="+forward+"'</script>";
			}
		}
		
		action = request.getParameter("action");
		
		//显示挂件html
		String html = show(params);
		return html;
	
	}

	/**
	 * 挂件一些更新操作
	 * 子类如需要时可以选择覆写此方法
	 * @param params
	 */
	public void update(Map<String, String> params){ }
	 
	
	
	/**
	 * 挂件是否可以缓存
	 */
	public boolean  cacheAble(){
		return true;
	}
	

	/**
	 * 根据参数字串压入request的参数
	 * @param reqparams 要获取reqeust中参数的参数名字，以,号隔开，如：name1,name2
	 */
	private void putRequestParam(String reqparams,Map<String, String> params ){
		if(!StringUtil.isEmpty(reqparams)){
			HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
			String[] reqparamArray = reqparams.split(",");
			for(String paramname :reqparamArray){
				String value = httpRequest.getParameter(paramname);
				  params.put(paramname, value);
			}
		}
	}
	
	
	private String show(Map<String, String> params){
		
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		freeMarkerPaser.setPageFolder(null);
		freeMarkerPaser.setPageName(null);
		this.customPage= params.get("custom_page");
		this.folder= params.get("folder");
			

		String showHtmlStr = params.get("showhtml");

		showHtml=true;


		if(action!=null && "yes".equals(params.get("actionpage")) ){
			this.freeMarkerPaser.setPageName(customPage+"_"+action);
		}else{
			this.freeMarkerPaser.setPageName(customPage);
		}
		

		
		/**
		 * -----------------------
		 *  压入request中的参数值
		 * -----------------------
		 */
		String reqparams =  params.get("reqparams");
		putRequestParam(reqparams,params);
		 
		freeMarkerPaser.putData( params);


		display(params);

		 if(!StringUtil.isEmpty(showHtmlStr) &&  showHtmlStr.equals("false")){
			showHtml = false; 
		 } 


		if(showHtml || "yes".equals(params.get("ischild"))){
			if(!this.disableCustomPage && !StringUtil.isEmpty(customPage) ){

				EopSite site  = EopContext.getContext().getCurrentSite();
				String contextPath  = EopContext.getContext().getContextPath();
				if(StringUtil.isEmpty(this.folder)){
					this.freeMarkerPaser.setPageFolder(contextPath+"/themes/"+site.getThemepath());
				}else{
					this.freeMarkerPaser.setPageFolder(contextPath+"/themes/"+site.getThemepath()+"/"+folder);
				}
			}
			String html =freeMarkerPaser.proessPageContent();
			if( "yes".equals(params.get("ischild")) ){
				this.putData("widget_"+params.get("widgetid"),html);
			}
			return html;
		}
		else return "";		
		
	}
	
	
	/**
	 * 获取当前模板位置
	 * @return
	 */
	protected String getThemePath(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		String contextPath  = EopContext.getContext().getContextPath();
		return contextPath+"/themes/"+site.getThemepath();
	}
	
	
 
	protected void disableCustomPage(){
		disableCustomPage=true;
	}
	protected void enableCustomPage(){
		disableCustomPage=false;
	}

	public String setting(Map<String, String> params) {
		freeMarkerPaser = FreeMarkerPaser.getInstance();
		freeMarkerPaser.setClz(getClass());
		config(params);
 
		if(showHtml)
			return freeMarkerPaser.proessPageContent();
		
		else return "";
	}
	
	
	
	/**
	 * 子类需要实现在挂件处理方法<br/>
	 * 一般子类在此方法中处理挂件的业务逻辑，设置页面变量。
	 * 
	 * @param params
	 *            挂件参数
	 * @return
	 */
	abstract protected void display(Map<String, String> params);

	
	
	
	/**
	 * 挂件配置处理方法
	 * 
	 * @param params
	 *            挂件参数
	 */
	abstract protected  void config(Map<String, String> params); 
	
	
	
	

	/**
	 * 设置挂件模板的变量
	 * 
	 * @param key
	 * @param value
	 */
	protected void putData(String key, Object value) {
		this.freeMarkerPaser.putData(key, value);
	}
	

	/**
	 * 设置挂件模板的变量
	 * 
	 * @param key 
	 * @param value
	 */
	protected void putData(Map<String,Object> map) {
		this.freeMarkerPaser.putData(map);
	}

	
	

	protected Object getData(String key){
		return this.freeMarkerPaser.getData(key);
	}
	/**
	 * 设置模板路径前缀
	 * 
	 * @param path
	 */
	protected void setPathPrefix(String path) {
		this.freeMarkerPaser.setPathPrefix(path);
	}

	/**
	 * 设置模板文件的名称
	 * 如果用户强制指定了挂件页面文件名，则使自定义页面
	 * 
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		if(this.customPage==null || this.disableCustomPage)
			this.freeMarkerPaser.setPageName(pageName);
		 
	}
	
	/**
	 * 强制设定页面名称
	 * @param pageName
	 */
	public void makeSureSetPageName(String pageName){
		this.freeMarkerPaser.setPageName(pageName);
	}

	/**
	 * 设置模板页面扩展名
	 * 
	 * @param pageExt
	 */
	public void setPageExt(String pageExt) {
		this.freeMarkerPaser.setPageExt(pageExt);
	}

	public void setPageFolder(String pageFolder){
		this.freeMarkerPaser.setPageFolder(pageFolder);
	}
	
	/**
	 * 添加导航项
	 * @param nav
	 */
	protected void putNav(Nav nav){
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		List<Nav> navList  =(List<Nav>)request.getAttribute("site_nav_list");
		navList=navList == null?new ArrayList<Nav>():navList;
		navList.add(nav);
		request.setAttribute("site_nav_list", navList);
	}

	
	/**
	 * 设置操作后的提示信息
	 * @param msg 要设置的信息
	 */
	protected   void setMsg(String msg){
		this.putData("msg", msg);
	}
	
	
	/**
	 * 设置操作后显示页中的下一步可操作url
	 * @param text url的文字
	 * @param url 对应的连接
	 */
	protected void putUrl(String text,String url){
		if(urls==null) 
			urls= new HashMap<String, String>();
		
		urls.put(text, url);
		this.putData("urls",urls);
		this.putData("jumpurl", url);
	}
	
	/**
	 * 显示失败信息-返回上一步操作
	 * @param msg
	 */
	protected void showError(String msg){
		this.customPage=null;
		this.setPageFolder( this.getThemePath());
		this.setPageName("error");
		this.setMsg(msg);
		 
	}
	
	protected void showJson(String json){
		this.customPage=null;
		this.setPageFolder("/shop/common/");
		this.setPageName("json");
		this.putData("json", json);
		 
	}
	
	protected void showErrorJson(String message){
		this.showJson("{result:0,message:'"+message+"'}");
	}
	
	protected void showSuccessJson(String message){
		this.showJson("{result:1,message:'"+message+"'}");
	}	
	
	/**
	 * 显示错误信息--提供跳转连接
	 * @param msg
	 * @param urlText
	 * @param url
	 */
	protected void showError(String msg,String urlText,String url){
		this.customPage=null;
		this.setPageFolder( this.getThemePath());
		this.setPageName("error");
		this.setMsg(msg);
		if(urlText!=null&&  url!=null)
			this.putUrl(urlText, url);
	}

	/**
	 * 显示成功信息并返回上一步,不提供跳转连接
	 * @param msg
	 */
	protected void showSuccess(String msg){
		this.customPage=null;
		this.setPageFolder( this.getThemePath());
		this.setPageName("success");
		this.setMsg(msg);
	 
	}
	
	
	/**
	 * 显示成功提示信息
	 * @param msg
	 * @param urlText
	 * @param url
	 */
	protected void showSuccess(String msg,String urlText,String url){
		this.customPage=null;
		this.setPageFolder( this.getThemePath());
		this.setPageName("success");
		this.setMsg(msg);
		if(urlText!=null&&  url!=null)
		this.putUrl(urlText, url);
	}
	
	
}
