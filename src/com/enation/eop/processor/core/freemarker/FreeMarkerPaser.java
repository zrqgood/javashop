package com.enation.eop.processor.core.freemarker;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.EopUtil;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarker解析器
 * 
 * @author kingapex 2010-2-16下午03:42:40
 */
public   final class FreeMarkerPaser {

	private static final Log log = LogFactory.getLog(FreeMarkerPaser.class);
	private static ThreadLocal<FreeMarkerPaser> managerLocal = new ThreadLocal<FreeMarkerPaser>();
	
	public FreeMarkerPaser(){ 
		data = new HashMap<String, Object>();
		this.clazz =null;
		this.pageFolder =null;
	}
	
	public void setClz(Class clz){
		this.clazz = clz;
	}
	/**
	 * 获取当前线程的 fremarkManager
	 * @return
	 */
	public final static FreeMarkerPaser getInstance(){
		
		if( managerLocal.get()==null ){
			throw new RuntimeException("freemarker paser is null");
		}
		FreeMarkerPaser fmp =managerLocal.get();
		
		fmp.setPageFolder(null);
		fmp.setWrapPath(true);
//		System.out.println(fmp);
		return fmp;
	}
	
	public final static FreeMarkerPaser getCurrInstance(){
		if( managerLocal.get()==null ){
			throw new RuntimeException("freemarker paser is null");
		}
		FreeMarkerPaser fmp =managerLocal.get();

		return fmp;
	}
	
	public final static void set(FreeMarkerPaser fp){
		 managerLocal.set(fp);
	} 
	public final static void remove(){
		 managerLocal.remove();
	} 
	
	private Class clazz;
	public  FreeMarkerPaser(Class clz){
		this.clazz = clz;
		data = new HashMap<String, Object>();
	}

	public FreeMarkerPaser(Class clz,String folder){
		this.clazz = clz;
		this.pageFolder = folder;
		data = new HashMap<String, Object>();
		
	}
	/**
	 * 设置挂件模板的变量
	 * 
	 * @param key
	 * @param value
	 */
	public void putData(String key, Object value) {
		if (key != null && value != null)
			data.put(key, value);
	}
	
	public void putData(Map  map){
		if(map!=null)
			data.putAll(map);
	}
	
	public Object getData(String key){
		if(key==null) return null;
		
		return data.get(key);
	}
	
	private boolean wrapPath=true;
	
	public void setWrapPath(boolean wp){
		wrapPath =wp;
	}

	public String proessPageContent(){
		
		try {
			String name = this.clazz.getSimpleName();
			pageExt = pageExt == null ? ".html" : pageExt;		
			name = this.pageName == null ? name : pageName;
		 
			cfg = this.getCfg();
			cfg.setNumberFormat("0.##");
			Template temp = cfg.getTemplate(name + pageExt);
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			out.flush();
			String content = stream.toString();
			
			if(wrapPath){
//				System.out.println("before:---------------------------");
//				System.out.println(content);
				
				content = EopUtil.wrapjavascript(content, this.getResPath());
				content =  EopUtil.wrapcss(content, getResPath());
//				System.out.println("after:---------------------------");
//				System.out.println(content);
			}
			//content= StringUtil.compressHtml(content);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return "widget  processor error";
	}
		
	
	private static   Configuration  cfg;
	

	/*
	 * freemarker data model 通过putData方法设置模板中的值
	 */
	private Map<String, Object> data;

	/*
	 * 模板路径前缀 默认为"" 可以通过 {@link #setPathPrefix(String)} 设置
	 */
	private String pathPrefix;

	/*
	 * 模板文件的名字，默认为与插件类同名
	 */
	private String pageName;

	/*
	 * 模板页面的扩展名，默认为.html
	 */
	private String pageExt;
 
	
	
	/*
	 * 页面所在文件夹
	 * 默认为插件类所在文件夹
	 */
	private String pageFolder; 
	
 
	
	private Configuration getCfg(){
			
			if(cfg==null){
				 cfg = FreeMarkerUtil.getCfg();		
			}

			 
			pathPrefix = pathPrefix== null?"":pathPrefix;
			
			if(pageFolder==null) {//默认使用挂件所在文件夹
				//System.out.println(" folder null use "+ this.clazz.getName() );
				cfg.setClassForTemplateLoading(this.clazz, pathPrefix);
				}
			else{
				//System.out.println(" folder not null use "+ pageFolder);
			  cfg.setServletContextForTemplateLoading(ThreadContextHolder.getHttpRequest().getSession().getServletContext(), pageFolder);
			}
			cfg.setObjectWrapper(new DefaultObjectWrapper());			
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(java.util.Locale.CHINA);
			cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
		return cfg;
	}
	
	

	



	/**
	 * 设置模板路径前缀
	 * 
	 * @param path
	 */
	public void setPathPrefix(String path) {
		this.pathPrefix = path;
	}

	
	/**
	 * 设置模板文件的名称
	 * 
	 * @param pageName
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * 设置模板页面扩展名
	 * 
	 * @param pageExt
	 */
	public void setPageExt(String pageExt) {
		this.pageExt = pageExt;
	}

	public void setPageFolder(String pageFolder){
		this.pageFolder = pageFolder;
	}

	
	/**
	 * 获取资源根路径
	 * @return
	 */
	private String getResPath(){
		String ctx =EopSetting.CONTEXT_PATH;
		ctx = ctx.equals("/")?"":ctx;
		if( this.pageFolder ==null ){
			return ctx+"/resource/"+ this.clazz.getPackage().getName().replaceAll("\\.", "/")+"/";
		}else{
			return ctx+pageFolder+"/";
		}
		
	}
	
 

 
 
	
	
	

		
}
