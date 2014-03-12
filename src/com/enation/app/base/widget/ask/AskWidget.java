package com.enation.app.base.widget.ask;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.app.base.core.model.Ask;
import com.enation.app.base.core.service.IAskManager;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.database.Page;
import com.enation.framework.pager.StaticPagerHtmlBuilder;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;

public class AskWidget extends AbstractWidget {

	private IAskManager askManager;
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request  = EopContext.getHttpRequest();
		String action  = request.getParameter("action");
		if(action.equals("list")){
			list();
		}
		if(action.equals("toAsk")){
			toAsk();
		}

		if(action.equals("ask")){
			ask();
		}
		
		if(action.equals("view")){
			view();
		}
	}
	
	private void list(){
		HttpServletRequest request  = EopContext.getHttpRequest();
		String keyword = request.getParameter("keyword");
		String startTime = request.getParameter("startTime");
		String endTime  = request.getParameter("endTime");
		String page= request.getParameter("page");
		Date start = StringUtil.isEmpty(startTime)?null: DateUtil.toDate(startTime, "yyyy-MM-dd");
		Date end  =  StringUtil.isEmpty(endTime)?null: DateUtil.toDate(endTime, "yyyy-MM-dd");
		Page askPage   = this.askManager.listMyAsk(keyword, start, end, Integer.valueOf(page), 20);
		this.putData("askList", askPage.getResult());
		
		StaticPagerHtmlBuilder pagerHtmlBuilder = new StaticPagerHtmlBuilder( Integer.valueOf(page), askPage.getTotalCount(), 20);
		String page_html = pagerHtmlBuilder.buildPageHtml();
		this.putData("pager", page_html);
		
		this.setPageName("list");
	}
	
	
	private void toAsk(){
		
		this.setPageName("ask");
	}
	

	private void ask(){
		HttpServletRequest request  = EopContext.getHttpRequest();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Ask ask = new Ask();
		ask.setTitle(title);
		ask.setContent(content);
				
		this.askManager.add(ask);		
		this.showSuccess("问题提交成功","我的问题", "member_ask.html?action=list");
	}
	
	private void view(){
		HttpServletRequest request  = EopContext.getHttpRequest();
		String askid  = request.getParameter("askid");
		Ask ask = this.askManager.get(Integer.valueOf(askid));
		this.putData("ask", ask);
		this.setPageName("view");
	}

	public IAskManager getAskManager() {
		return askManager;
	}


	public void setAskManager(IAskManager askManager) {
		this.askManager = askManager;
	}

}
