package com.enation.eop.resource.impl.cache;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.resource.IThemeUriManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.ThemeUri;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.cache.CacheFactory;

/**
 * Theme Uri 缓存代理
 * @author kingapex
 * <p>2009-12-16 下午05:48:25</p>
 * @version 1.0
 */
public class ThemeUriCacheProxy extends AbstractCacheProxy<List<ThemeUri>> implements IThemeUriManager {

	private IThemeUriManager themeUriManager;
	public static final String LIST_KEY_PREFIX ="theme_uri_list_";
	

	public void clean() {
		this.themeUriManager.clean();
	}
	public void add(ThemeUri uri) {
		 this.themeUriManager.add(uri);
		 this.cleanCache();
	}
	
	public void edit(List<ThemeUri> uriList) {
		themeUriManager.edit(uriList);
		 this.cleanCache();
	}	
	
	public void edit(ThemeUri themeUri) {
		themeUriManager.edit(themeUri);
		this.cleanCache();
	}
	
	
	public ThemeUriCacheProxy(IThemeUriManager themeUriManager) {
		super(CacheFactory.THEMEURI_CACHE_NAME_KEY);
		this.themeUriManager = themeUriManager;
	}

	private void cleanCache(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		Integer userid  = site.getUserid();
		Integer siteid  = site.getId();
		this.cache.remove(LIST_KEY_PREFIX+userid+"_"+siteid);
	}
	
	public List<ThemeUri> list() {
			EopSite site  = EopContext.getContext().getCurrentSite();
			Integer userid  = site.getUserid();
			Integer siteid  = site.getId();
			List<ThemeUri> uriList  = this.cache.get(LIST_KEY_PREFIX+userid+"_"+siteid);
			
			if(uriList==null){
//				if(logger.isDebugEnabled()){
//					logger.debug("get user:"+userid+" site: "+ siteid +" theme uri list from database");
//				}
				uriList = this.themeUriManager.list();
				this.cache.put(LIST_KEY_PREFIX+userid+"_"+siteid, uriList);
			}else{
//				if(logger.isDebugEnabled()){
//					logger.debug("get user:"+userid+" site: "+ siteid +" theme uri list from cache");
//				}				
			}
			
		return uriList;
	}
	
	
	/**
	 * 从缓存列表中匹配uri
	 */
	
	public ThemeUri getPath( String uri) {
		if(uri.equals("/")){
			uri="/index.html";
		}
//		if(logger.isDebugEnabled()){
//			logger.debug("parse uri["+ uri+"]...");
//		}	
 
		List<ThemeUri> uriList  =  this.list();
		for( ThemeUri themeUri: uriList){
			String key = themeUri.getUri();
			String path = themeUri.getPath();
			Pattern p = Pattern.compile(key, 2 | Pattern.DOTALL);
			Matcher m = p.matcher(uri);
			
//			if(logger.isDebugEnabled()){
//				logger.debug("compile["+key+"],matcher["+uri+"],replace["+path+"]");
//			}	
			
			if (m.find()) {
				//String s = m.replaceAll(path);
//				if(logger.isDebugEnabled()){
//					logger.debug("found...");
//					//logger.debug("dispatch  uri["+key+"=="+ uri+"] to "+ s+"["+path+"]");
//					
//				}				
				return themeUri;
			}else{
//				if(logger.isDebugEnabled())
//					logger.debug("not found...");
			}
		}
		
		throw new EopException(uri + "not found");
	}

	
	public static void main(String[] args){
/*		Pattern p = Pattern.compile("/goods-(\\d+).html", 2 | Pattern.DOTALL);
		Matcher m = p.matcher("/goods-1.html");  
		String s = m.replaceAll("/goods.do?id=$1");		
		System.out.println(s);*/
//[/goods-(\\d+).html],matcher[/goods-1.html],replace[/goods.jsp]
		Pattern p = Pattern.compile("/goods-(\\d+).html", 2 | Pattern.DOTALL);
		Matcher m = p.matcher("/goods-1.html");  
		if(m.find()){
			System.out.println("found...");
			String s = m.replaceAll("/goods.jsp");		
			System.out.println(s);			
		}else{
			System.out.println("not found...");
		}

	}
	
	public void delete(int id){
		this.themeUriManager.delete(id);
		this.cleanCache();
	}

	public ThemeUri get(Integer id) {
		return this.themeUriManager.get(id);
	}




}
