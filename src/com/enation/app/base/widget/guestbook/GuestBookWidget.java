package com.enation.app.base.widget.guestbook;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.GuestBook;
import com.enation.app.base.core.service.IGuestBookManager;
import com.enation.eop.sdk.utils.ValidCodeServlet;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.pager.FacadePagerHtmlBuilder;
import com.enation.framework.util.StringUtil;
import com.enation.framework.util.ip.IPSeeker;

/**
 * 留言板挂件
 * @author kingapex
 * 2010-8-14下午09:09:55
 */
public class GuestBookWidget extends AbstractWidget {
	
	protected IGuestBookManager guestBookManager;
	
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String action  = request.getParameter("action");
		if(action==null || action.equals("list")){
			
			//分页大小参数
			String pageSize  = params.get("pagesize");
			if(StringUtil.isEmpty(pageSize)){
				pageSize  = request.getParameter("pagesize");
				if(StringUtil.isEmpty(pageSize)){pageSize ="10";}
			}
			this.putData("pagesize", pageSize);
			
			//管理员名称参数
			String adminname = params.get("adminname");
			if(StringUtil.isEmpty(adminname)) adminname="管理员";
			this.putData("adminname", adminname);
			
			//成功提示信息参数
			String message  = params.get("message");
			if(StringUtil.isEmpty(message)) message="留言成功，稍候我们会对您的留言回复。";
			this.putData("message", message);
			
			this.list(Integer.valueOf(pageSize));
		}
		
		if("add".equals(action)){
			add();
		}
	}
	
	/**
	 * 显示留言列表
	 */
	protected void list(int pageSize){
		
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String pageNo = request.getParameter("page");
		if(StringUtil.isEmpty(pageNo)) pageNo="1";
		
		Page page = this.guestBookManager.list(null, Integer.valueOf(pageNo), pageSize);
		this.putData("subjectList", page.getResult());
		
		FacadePagerHtmlBuilder pagerHtmlBuilder = new FacadePagerHtmlBuilder( Integer.valueOf(pageNo), page.getTotalCount(),pageSize);
		String page_html = pagerHtmlBuilder.buildPageHtml();
		this.putData("pager", page_html);
				
	}
	
	
	/**
	 * 发表留言
	 */
	protected void add(){
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		String username  = this.htmlEncode(request.getParameter("username"));
		String title  = this.htmlEncode(request.getParameter("title"));
		String content  = this.htmlEncode(request.getParameter("content"));
		String email  = request.getParameter("email");
		String qq  = this.htmlEncode(request.getParameter("qq"));
		String tel  = this.htmlEncode(request.getParameter("tel"));
		String sex  = request.getParameter("sex");
		String validate  = request.getParameter("vcode");
		String ip = request.getRemoteAddr();
		String area  = new IPSeeker() .getCountry( ip);
		this.setPageFolder("/commons");
		this.setPageName("json");
		if(validcode(validate)==0){
			this.putData("json", "{result:0,message:'验证码输入错误'}");
		}else{
		
			GuestBook guestbook  = new GuestBook();
			guestbook.setIp(ip);
			guestbook.setArea(area);
			guestbook.setTitle(title);
			guestbook.setContent(content);
			guestbook.setQq(qq);
			guestbook.setEmail(email);
			guestbook.setSex(Integer.valueOf(sex));
			guestbook.setTel(tel);
			guestbook.setUsername(username);
			this.guestBookManager.add(guestbook);
			this.putData("json", "{result:1}");
			
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
		
		String code  = (String)ThreadContextHolder.getSessionContext().getAttribute(ValidCodeServlet.SESSION_VALID_CODE+"guestbook");
		if(code==null){
			return 0 ;
		}else{
			if(!code.equals(validcode)){
				return 0;
			}
		}
		
		return 1;
	}
	
	protected String htmlEncode(String input){
		if(input==null)
			return "";
		return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	public IGuestBookManager getGuestBookManager() {
		return guestBookManager;
	}

	public void setGuestBookManager(IGuestBookManager guestBookManager) {
		this.guestBookManager = guestBookManager;
	}
	
	

}
