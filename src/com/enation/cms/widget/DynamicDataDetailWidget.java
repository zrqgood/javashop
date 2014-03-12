package com.enation.cms.widget;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.widget.header.HeaderConstants;
import com.enation.app.base.widget.nav.Nav;
import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;

/**
 * 动态数据详细挂件
 * 文章id由地址栏解决得来
 * 同时需要提供catid
 * @author kingapex
 * 2010-7-6上午11:01:31
 */
public class DynamicDataDetailWidget extends AbstractWidget {

	private IDataCatManager dataCatManager;
	private IDataManager dataManager;
	private Integer catid;
	private Integer articleid;
	
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		
		
		/*
        				==========================
        						读取文章信息
        				==========================
		*/
		
		Integer[] ids= this.paseId();
		articleid = ids[0];
		catid = ids[1];
		
		String login = params.get("login");
		
		if("1".equals(login)){
			Member member = UserServiceFactory.getUserService().getCurrentMember();
			if(member==null){
				this.putData("isLogin", false);
			}else{
				this.putData("isLogin", true);
				this.putData("member", member);
			}
		}
		
		Map data =  this.dataManager.get(articleid, catid,true);
		this.putData("catid", catid);
		this.putData("data", data);
		
		
		
		
		
		
		
		/*
        			  ==========================
        	        	     上一篇下一篇
        	        	      如果没有相应的文章id为0   
        		      ==========================
		 */		
		
		String shownext= params.get("shownext");
		if("yes".equals(shownext) ){
			int nextid  = this.dataManager.getNextId(articleid, catid);
			int pevid   = this.dataManager.getPrevId(articleid, catid);
			this.putData("nextid", nextid);
			this.putData("pevid", pevid);
		}else{
			this.putData("nextid", 0);
			this.putData("pevid", 0);
		}
		
		
		
		
		
		
		/*
		               ==========================
		                                             读取、设置导航信息
		               ==========================
		*/
		//读取父树
		List<DataCat> parents  = this.dataCatManager.getParents(catid);
		DataCat cat  =parents.get(parents.size()-1); //最后一个为此类别本身
		
		 if(data.get("page_title")!=null &&!data.get("page_title").equals("") )
				this.putData(HeaderConstants.title, data.get("page_title"));
	 
			 
		 if(data.get("page_keywords")!=null &&!data.get("page_keywords").equals("")  )
				this.putData(HeaderConstants.keywords,data.get("page_keywords"));
			 
		if(data.get("page_description")!=null &&!data.get("page_description").equals("") )
				this.putData(HeaderConstants.description,data.get("page_description"));
			 
		StringBuffer navBar = new StringBuffer();
		navBar.append("<a href='index.html'>首页</a>");
		for(DataCat c: parents){
			navBar.append("> <a href='"+c.getUrl()+"'>"+c.getName()+"</a>");
		}
		
		this.putData("navbar", navBar.toString());
	}
	
	
	@Override
	public void update(Map<String, String> params) {
		Integer[] ids= this.paseId();
		Integer articleid = ids[0];
		Integer catid = ids[1];
		this.dataManager.updateHit(articleid, catid);
	}

	private Integer[] paseId(){
		HttpServletRequest  httpRequest =ThreadContextHolder.getHttpRequest(); 
		String url = RequestUtil.getRequestUrl(httpRequest);
		String pattern = "/(.*)-(\\d+)-(\\d+).html(.*)";
		String id = null;
		String catid = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			id = m.replaceAll("$3");
			catid = m.replaceAll("$2");
		}		
		
		return new Integer[]{Integer.valueOf(""+id),Integer.valueOf(""+catid)};
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}

	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}
	
}
