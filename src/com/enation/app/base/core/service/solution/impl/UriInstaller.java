package com.enation.app.base.core.service.solution.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.service.ISitemapManager;
import com.enation.app.base.core.service.solution.IInstaller;
import com.enation.app.base.core.service.solution.InstallUtil;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;

/**
 * Uri安装器
 * @author kingapex
 * 2010-1-20下午05:37:23
 */
public class UriInstaller implements IInstaller {
	
	protected final Logger logger = Logger.getLogger(getClass());
	private IThemeUriManager themeUriManager;
	private ISitemapManager sitemapManager;
	
	
	public void install(String productId ,Node fragment)  {
 		//this.themeUriManager.clean();
		NodeList uriList = fragment.getChildNodes();
		InstallUtil.putMessaage("正在安装uri映射...");
		this.installUri(uriList);
		InstallUtil.putMessaage("uri映射安装完成!");
	}

	 
	/**
	 * 将一个NodeList集合中的menu结点数据保存到库中
	 * @param nodeList
	 * @param parentId
	 */
	private void installUri(NodeList nodeList){
		 
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() ==Node.ELEMENT_NODE){
				this.installUri((Element)node);
			}
		}
	}
	
	
	
	
	/**
	 * 将一个uri 数据保存在库中
	 * @param ele
	 * @param parentId
	 */
	private void installUri(Element ele){
	 
		try {
			
			ThemeUri themeUri = new ThemeUri();
		
			themeUri.setUri(ele.getAttribute("from"));
			themeUri.setPath(ele.getAttribute("to"));
			String name = ele.getAttribute("name");
			String point  = ele.getAttribute("point");
			String sitemaptype = ele.getAttribute("sitemaptype");
			
			//2.1.3版本新增页面名称和消耗积分，流量统计所用--kingapex
			if(name!=null)
				themeUri.setPagename(name);
			
			if(!StringUtil.isEmpty(point)){
				themeUri.setPoint( Integer.valueOf(point ));
			}
			
			if(!StringUtil.isEmpty(sitemaptype)){
				themeUri.setSitemaptype( Integer.valueOf(sitemaptype ));
			}
			
			themeUriManager.add(themeUri);
			//如果sitemaptype=="1"，则加入到sitemap中
			if("1".equals(sitemaptype)){
				SiteMapUrl url = new SiteMapUrl();
				url.setLoc(ele.getAttribute("from"));
				url.setLastmod(System.currentTimeMillis());
				this.sitemapManager.addUrl(url);
			}
			NodeList children = ele.getChildNodes();
			
			if(children!=null){
				this.installUri(children);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new RuntimeException("install uri error");
		}
	}


	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}


	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}


	public ISitemapManager getSitemapManager() {
		return sitemapManager;
	}


	public void setSitemapManager(ISitemapManager sitemapManager) {
		this.sitemapManager = sitemapManager;
	}

	
}
