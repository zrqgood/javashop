package com.enation.javashop.widget.groupbuy;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.javashop.core.service.IGroupBuyManager;
import com.enation.javashop.core.model.GroupBuy;

public class GroupBuyDetailWidget extends AbstractWidget {
	private IGroupBuyManager groupBuyManager;
	protected void config(Map<String, String> params) {
		//读取挂件的参数并设置给挂件设置页面		
	}
	protected void display(Map<String, String> params) {
		Integer groupid = this.getGroupId();
		GroupBuy goods = this.groupBuyManager.get(groupid);
		this.putData("goods", goods);
	}
	public final IGroupBuyManager getGroupBuyManager() {
		return groupBuyManager;
	}
	public final void setGroupBuyManager(IGroupBuyManager groupBuyManager) {
		this.groupBuyManager = groupBuyManager;
	}
	private Integer getGroupId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		String id = this.paseGroupId(url);
		return Integer.valueOf(id);
	}
	private  static  String  paseGroupId(String url){
		String pattern = "/(.*)-(\\d+).html(.*)";
		String value = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			value = m.replaceAll("$2");
		}
		return value;
	}
}
