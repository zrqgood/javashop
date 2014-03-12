package com.enation.app.base.core.service.solution.impl;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.service.solution.IAdminThemeInfoFileLoader;
import com.enation.app.base.core.service.solution.IInstaller;
import com.enation.app.base.core.service.solution.InstallUtil;
import com.enation.eop.resource.IAdminThemeManager;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.resource.model.AdminTheme;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;

/**
 * 后台主题安装器
 * @author kingapex
 * 2010-1-20下午10:56:25
 */
public class AdminThemeInstaller implements IInstaller {
	private final Logger logger = Logger.getLogger(getClass());
	private ISiteManager siteManager;
	
	private IAdminThemeManager adminThemeManager;
	private IAdminThemeInfoFileLoader adminThemeInfoFileLoader;
 
	public void install(String productId,Node fragment) {
		
		if(logger.isDebugEnabled()){
			logger.debug("user install admintheme[" + fragment + "] from " + productId);
		}
		
		if(fragment==null) throw new RuntimeException("install admintheme error[node is null]");
		
		NodeList themeList = fragment.getChildNodes();
		
	//	this.adminThemeManager.clean();
		this.install(themeList, productId);
		//InstallUtil.putMessaage("后台主题安装完成!");
	}

	private void install(Element themeNode, String productId){
		String isdefault = themeNode.getAttribute("default");
		AdminTheme adminTheme= new AdminTheme();
		String path = themeNode.getAttribute("id");
		InstallUtil.putMessaage("正在安装后台主题" + path + "...");	
		String commonAttr = themeNode.getAttribute("isCommonTheme");
		commonAttr = commonAttr==null?"":commonAttr;
		Boolean isCommonTheme = (commonAttr.toUpperCase().equals("TRUE"));
		Document iniFileDoc = adminThemeInfoFileLoader.load(productId, path, isCommonTheme);
		Node themeN = null;
		try{
			 themeN = iniFileDoc.getFirstChild();
			 if(themeN==null){throw new RuntimeException("adminthem node is null");}
		}catch(Exception e){
			e.printStackTrace();
		}
		Node authornode = null;
		try{
			NodeList list=((Element)themeN).getElementsByTagName("author");
			if(list== null || list.item(0) == null){throw new RuntimeException("author node is null");}
			authornode = list.item(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		String author = authornode.getTextContent();
		
 		Node versionnode = null;
		try{
			 NodeList list=((Element)themeN).getElementsByTagName("version");
			 if(list== null || list.item(0) == null){throw new RuntimeException("author node is null");}
			 versionnode = list.item(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		String version = versionnode.getTextContent();
		adminTheme.setPath(path);
		adminTheme.setThemename(themeNode.getAttribute("name"));
		adminTheme.setThumb("preview.png");
		adminTheme.setAuthor(author);
		adminTheme.setVersion(version);
		
		try {
			Integer themeid = adminThemeManager.add(adminTheme,isCommonTheme);	
			if("yes".equals( isdefault)){
				if(logger.isDebugEnabled())
					logger.debug("change theme["+themeid+"] ");
				siteManager.changeAdminTheme(themeid);
			}
			InstallUtil.putMessaage("完成!");		
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("install admin theme error");
		}
	}
	
	//E:/workspace/java/neweop/eop/src/main/storage/adminthemes/blue/themeini.xml
	private void install(NodeList nodeList, String productId){
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() ==Node.ELEMENT_NODE){
				this.install((Element)node, productId);
			}
		}			
	}

	public void setAdminThemeInfoFileLoader(
			IAdminThemeInfoFileLoader adminThemeInfoFileLoader) {
		this.adminThemeInfoFileLoader = adminThemeInfoFileLoader;
	}

	public IAdminThemeManager getAdminThemeManager() {
		return adminThemeManager;
	}

	public void setAdminThemeManager(IAdminThemeManager adminThemeManager) {
		this.adminThemeManager = adminThemeManager;
	}

	public IAdminThemeInfoFileLoader getAdminThemeInfoFileLoader() {
		return adminThemeInfoFileLoader;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
}
