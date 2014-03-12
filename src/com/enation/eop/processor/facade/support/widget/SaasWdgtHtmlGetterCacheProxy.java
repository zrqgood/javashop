package com.enation.eop.processor.facade.support.widget;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

import com.enation.app.base.core.service.IWidgetCacheManager;
import com.enation.eop.processor.core.EopException;
import com.enation.eop.processor.widget.IWidgetHtmlGetter;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.SaasEopContextIniter;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.cache.CacheFactory;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;

/**
 * Saas挂件html缓存代理<br>
 * 缓存规则：
 * 以 userid_siteid为key划分出缓存区域，以map作为值。
 * map中存储此站点的挂件html片断 map
 * 
 * @author kingapex
 *
 */
public class SaasWdgtHtmlGetterCacheProxy extends AbstractCacheProxy<Map<String,String>>
		implements IWidgetHtmlGetter {
	private IWidgetHtmlGetter widgetHtmlGetter;

	public SaasWdgtHtmlGetterCacheProxy(IWidgetHtmlGetter _widgetHtmlGetter) {
		super(CacheFactory.WIDGET_CACHE_NAME_KEY);
		this.widgetHtmlGetter = _widgetHtmlGetter;
	} 
	
	private boolean getCacheOpen(){
		 IWidgetCacheManager widgetCacheManager =  SpringContextHolder.getBean("widgetCacheManager");
		 return  widgetCacheManager.isOpen();
	}
	/**
	 * 实现挂件解析的缓存包装
	 */
	public String process(Map<String, String> params, String pageUri) {
		String widgetType = params.get("type");
		String html = null;
		
		//得到挂件类
		IWidget widget = SpringContextHolder.getBean(widgetType);
		if (widget == null)
			return "widget[" + widgetType + "] is null";
		
//		if(this.logger.isDebugEnabled() ){
//			this.logger.debug("缓存类解析挂件["+widgetType+"]");
//		}
		// 挂件可以被缓存，则尝试由缓存读取
		if (getCacheOpen() && widget.cacheAble()) { 
//			if(this.logger.isDebugEnabled() ){
//				this.logger.debug("此挂件缓存");
//			}
			//以当前站点作为整站挂件缓存的key
			//缓存的是一个map，map的key为当前页面uri和挂件id的组合
			EopSite site = EopContext.getContext().getCurrentSite();
			String site_key = "widget_"+ site.getUserid() +"_"+site.getId();
			
			//由缓存中找此站点的挂件级存集合
			Map<String,String> htmlCache=  cache.get(site_key);
			
			//未找到缓存，生成一个新map
			if(  htmlCache==null){
				htmlCache = new HashMap<String, String>();
				cache.put(site_key, htmlCache);
			}
			
			//挂件map集合的key
			String key = pageUri + "_" + params.get("widgetid");
			
			//获取挂件html
			html = htmlCache.get(key);
			
			// 缓存未命中，解析挂件的内容并压入缓存
			if (html == null) { 
				html = widgetHtmlGetter.process(params, pageUri);
				htmlCache.put(key, html);
				
			// 如果缓存命中，则只执行挂件更新操作	
			} else { 
				widget.update(params);
			}
		} else { // 挂件不缓存，由htmlGetter解析
//			if(this.logger.isDebugEnabled() ){
//				this.logger.debug("此挂件不缓存");
//			}
			html = widgetHtmlGetter.process(params, pageUri);
		}

		return html;
	}
	
	


}
