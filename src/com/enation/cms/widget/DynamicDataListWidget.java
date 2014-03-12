package com.enation.cms.widget;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.service.IDataCatManager;
import com.enation.cms.core.service.IDataManager;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.pager.StaticPagerHtmlBuilder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;

/**
 * 动态数据列表挂件<br/>
 * 数据分类和页数由地址栏获取
 * @author kingapex
 * 2010-7-6上午11:53:44
 */
public class DynamicDataListWidget extends AbstractWidget {
	
	private IDataManager dataManager;
	private IDataCatManager dataCatManager;
	
	@Override
	protected void config(Map<String, String> params) {
	}

	@Override
	protected void display(Map<String, String> params) {
		String pageSize = params.get("pagesize");
		String term  = params.get("term");
		pageSize = StringUtil.isEmpty(pageSize) ? "20" :pageSize;
		Integer[] ids = this.paseId();
		Integer catid  = ids[1];
		Integer pageNo  = ids[0];
		
		String showchilds = params.get("showchild");//是否显示子站数据 yes/no
		boolean showchild = showchilds==null?false:(showchilds.trim().toUpperCase().equals("YES"));
		
		String  orders = params.get("orders");
		
		Page webpage  = dataManager.listAll(catid,term, orders, showchild,pageNo, Integer.valueOf( pageSize ));
		
		
		StaticPagerHtmlBuilder pagerHtmlBuilder = new StaticPagerHtmlBuilder( pageNo, webpage.getTotalCount(), Integer.valueOf(pageSize));
		String page_html = pagerHtmlBuilder.buildPageHtml();
		
		long totalPageCount =webpage.getTotalPageCount();
		long totalCount = webpage.getTotalCount();
		this.putData("dataList", webpage.getResult());
		this.putData("pager", page_html);
		this.putData("pagesize", pageSize);
		this.putData("pageno", pageNo);
		this.putData("totalcount", totalCount);
		this.putData("totalpagecount",totalPageCount);
		
		//读取父树
		List<DataCat> parents  = this.dataCatManager.getParents(catid);
		DataCat cat  =parents.get(parents.size()-1); //最后一个为此类别本身
		//给页面设置当前类别名称
		this.putData("catname", cat.getName());
		
		//<span><a href='http://www.bjmingtao.com/'>首页</a> > <a href='news.html'>铭滔要闻</a> > ${catname}</a></span>
		StringBuffer navBar = new StringBuffer();
		navBar.append("<a href='index.html'>首页</a>");
		for(DataCat c: parents){
			navBar.append("> <a href='"+c.getUrl()+"'>"+c.getName()+"</a>");
		}
		
		this.putData("navbar", navBar.toString());
				
	}
	
	
	private Integer[] paseId(){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		String pattern = "/(.*)-(\\d+)-(\\d+).html(.*)";
		String page= null;
		String catid = null;
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			page = m.replaceAll("$3");
			catid = m.replaceAll("$2");
		}		
		
		return new Integer[]{Integer.valueOf(""+page),Integer.valueOf(""+catid)};
	}

	public IDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public IDataCatManager getDataCatManager() {
		return dataCatManager;
	}

	public void setDataCatManager(IDataCatManager dataCatManager) {
		this.dataCatManager = dataCatManager;
	}



}
