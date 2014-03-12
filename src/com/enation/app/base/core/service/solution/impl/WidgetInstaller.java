package com.enation.app.base.core.service.solution.impl;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.enation.app.base.core.service.solution.IInstaller;
import com.enation.eop.resource.IWidgetBundleManager;
import com.enation.eop.resource.model.WidgetBundle;

/**
 * 挂件安装器
 * @author kingapex
 * 2010-1-22下午03:37:57
 */
public class WidgetInstaller implements IInstaller {
 
	private IWidgetBundleManager widgetBundleManager;
	
	public void install(String productId , Node fragment) {
 
		NodeList widgetList = fragment.getChildNodes();
		this.install(widgetList);
	}

	
	private void install(Element ele){
 
		WidgetBundle widgetBundle = new WidgetBundle();
		widgetBundle.setWidgettype(ele.getAttribute("type"));
		widgetBundle.setWidgetname(ele.getAttribute("name"));
		this.widgetBundleManager.add(widgetBundle);
		
	}
	
	
	private void install(NodeList nodeList){
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() ==Node.ELEMENT_NODE){
				this.install((Element)node);
			}
		}			
	}


	public IWidgetBundleManager getWidgetBundleManager() {
		return widgetBundleManager;
	}


	public void setWidgetBundleManager(IWidgetBundleManager widgetBundleManager) {
		this.widgetBundleManager = widgetBundleManager;
	}
	
	


}
