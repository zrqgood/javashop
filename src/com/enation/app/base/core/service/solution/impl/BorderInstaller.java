package com.enation.app.base.core.service.solution.impl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.service.solution.IInstaller;
import com.enation.eop.resource.IBorderManager;
import com.enation.eop.resource.model.Border;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.util.FileUtil;

/**
 * 挂件边框安装器
 * @author kingapex
 * 2010-1-25上午10:54:39
 */
public class BorderInstaller implements IInstaller {
	private IBorderManager borderManager;
 
	public void install(String productId,  Node fragment) {

		try {
		///	this.borderManager.clean();
			FileUtil.copyFolder(
					EopSetting.PRODUCTS_STORAGE_PATH + "/" + productId + "/borders/",
					EopSetting.EOP_PATH +EopContext.getContext().getContextPath()+ "/borders/");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("安装borders出错...");
		}
		
		
		if (fragment.getNodeType() == Node.ELEMENT_NODE) {
			Element themeNode = (Element) fragment;
			 NodeList nodeList = themeNode.getChildNodes();
			 for( int i=0;i<nodeList.getLength();i++){
				 Node node  = nodeList.item(i);
				 if(node.getNodeType() ==  Node.ELEMENT_NODE){
					 Element el = (Element) node;
					 String id  = el.getAttribute("id");
					 String name  = el.getAttribute("name");
					 Border border = new Border();
					 border.setBorderid(id);
					 border.setBordername(name);
					 border.setThemepath(themeNode.getAttribute("id"));
					 borderManager.add(border);
				 }
			 }

		}
	}
	public void setBorderManager(IBorderManager borderManager) {
		this.borderManager = borderManager;
	}
	

}
