package com.enation.javashop.core.plugin.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;
import com.opensymphony.xwork2.ActionContext;

/**
 * 商品插件桩
 * @author kingapex
 *
 */
public class GoodsPluginBundle extends AutoRegisterPluginsBundle {
 
	
	
	public String getName() {
		 
		return "商品插件桩";
	}


	/**
	 * 激发商品添加前事件
	 * @param goods  
	 */
	public void onBeforeAdd(Map goods) {
		try{
			
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx
					.get(ServletActionContext.HTTP_REQUEST);
	
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IGoodsBeforeAddEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onBeforeGoodsAdd 开始...");
						}
						IGoodsBeforeAddEvent event = (IGoodsBeforeAddEvent) plugin;
						event.onBeforeGoodsAdd(goods, request);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onBeforeGoodsAdd 结束.");
						}
					}else{
						if (loger.isDebugEnabled()) {
							loger.debug(" no,skip...");
						}
					}
				}
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			 
		}

	}
	
	
	
	
	/**
	 * 激发商品添加完成后事件
	 * @param goods
	 */
	public void onAfterAdd(Map goods) {
		try{
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		if (plugins != null) {
			for (IPlugin plugin : plugins) {

				if (loger.isDebugEnabled()) {
					loger.debug("check plugin : " + plugin.getName()
							+ " is IGoodsAfterAddEvent ?");
				}

				if (plugin instanceof IGoodsAfterAddEvent) {
					if (loger.isDebugEnabled()) {
						loger.debug(" yes ,do event...");
					}
					IGoodsAfterAddEvent event = (IGoodsAfterAddEvent) plugin;
					event.onAfterGoodsAdd(goods, request);
				}else{
					if (loger.isDebugEnabled()) {
						loger.debug(" no,skip...");
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	/**
	 * 到添加商品页面，为此页填充input数据
	 * 
	 */
	public List<String>   onFillAddInputData() {
		List<String> htmlList = new ArrayList<String>();
		 
		HttpServletRequest request =  ThreadContextHolder.getHttpRequest();
		
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.putData("goods",new HashMap(0));
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IGoodsFillAddInputDataEvent) {
					IGoodsFillAddInputDataEvent event = (IGoodsFillAddInputDataEvent) plugin;
					freeMarkerPaser.setClz(event.getClass());
					String html =event.onFillGoodsAddInput(request);
					request.setAttribute(plugin.getId(), html);
					//htmlList.add(html);
				}
			}
		}
		
		
		return htmlList;
	}
	
	
	/**
	 * 到商品修改页面前为此页填充input数据
	 *
	 */
	public List<String> onFillEditInputData(Map goods){
		List<String> htmlList = new ArrayList<String>();
		HttpServletRequest request =ThreadContextHolder.getHttpRequest();
		FreeMarkerPaser freeMarkerPaser =FreeMarkerPaser.getInstance();
		freeMarkerPaser.putData("goods", goods);
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IGoodsFillEditInputDataEvent) {
					IGoodsFillEditInputDataEvent event = (IGoodsFillEditInputDataEvent) plugin;
					freeMarkerPaser.setClz(event.getClass());
					String html = event.onFillGoodsEditInput(goods, request);
					request.setAttribute(plugin.getId(), html);
					htmlList.add(html);
				}
			}
		}	
		return htmlList;
	}
	
	
	/**
	 * 激发商品修改更新前事件
	 * @param goods 页面传递的商品数据
	 */
	public void onBeforeEdit(Map goods){
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IGoodsBeforeEditEvent) {
					if(loger.isDebugEnabled()){
						loger.debug("调用插件["+ plugin.getName() +"] onBeforeGoodsEdit 开始...");
					}
					IGoodsBeforeEditEvent event = (IGoodsBeforeEditEvent) plugin;
					event.onBeforeGoodsEdit(goods, request);
					if(loger.isDebugEnabled()){
						loger.debug("调用插件["+ plugin.getName() +"] onBeforeGoodsEdit 结束.");
					}					
				}
			}
		}			
	}
	
	/**
	 * 激发商品修改后事件
	 * @param goods 修改后的商品基本数据
	 */
	public void onAfterEdit(Map goods){
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IGoodsAfterEditEvent) {
					IGoodsAfterEditEvent event = (IGoodsAfterEditEvent) plugin;
					event.onAfterGoodsEdit(goods, request);
				}
			}
		}	
		
	}
	
	
 
	
	
	/**
	 * 激发商品删除事件
	 * @param goodsid
	 */
	public void onGoodsDelete(Integer[] goodsid){
		if (plugins != null) {
			for (IPlugin plugin : plugins) {
				if (plugin instanceof IGoodsDeleteEvent) {
					IGoodsDeleteEvent event = (IGoodsDeleteEvent) plugin;
					 event.onGoodsDelete(goodsid);
				}
			}
		}
		
	}
	
}
