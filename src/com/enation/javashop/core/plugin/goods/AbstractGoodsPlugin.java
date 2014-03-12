package com.enation.javashop.core.plugin.goods;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.plugin.page.JspPageTabs;

public abstract class AbstractGoodsPlugin extends AutoRegisterPlugin implements  IGoodsFillAddInputDataEvent, IGoodsBeforeAddEvent ,IGoodsFillEditInputDataEvent, IGoodsAfterAddEvent,IGoodsAfterEditEvent,IGoodsBeforeEditEvent {


	
	public void register(){
		addTabs();
 
 
	}
 
	
	
	public abstract void addTabs();	
	
	
	
	protected void addTags(Integer id,String name){
		JspPageTabs.addTab("goods",id, name);	
	}
	
	
	protected void registerPage(String type,String path){
		//PluginPageContext.addPage(type,path);
	}
	
	
}
