package com.enation.framework.plugin.page;

import java.io.File;

import com.enation.framework.util.StringUtil;

/**
 * 插件页面加载器。<br/>
 * 加载指定文件夹内的页面，并记录其所属的插类型。<br/>
 * 插件页面信息被记录在 PluginPageContext 中。
 * @see PluginPageContext
 * @author apexking
 */
public  class PluginPageLoader {
	
	private String path;
	private String type;
	
	
	/**
	 * 构造器，在实例化时则必须提供插件类型及其对应的插件页面文件夹。	 * 
	 * @param type
	 * 			插件类型
	 * @param path
	 * 			插件页面文件夹
	 */
	public  PluginPageLoader(String type,String path){
		path= path.endsWith("/") ? path= path.substring(0, path.length()-1):path;
		this.type=type;
		this.path = path;
		this.initPages();
	}
	
 	/**
 	 * 扫描插件文件夹，并加载文件夹内的文件。
 	 *
 	 */
	private void initPages(){
		String root_path = StringUtil.getRootPath();
		System.out.println("加载插件文件夹：" + root_path+path);
		File file = new File(root_path+path);
		File[] files = file.listFiles();
		for(File f :files){
			//System.out.println("加载" + f.getName());
			//if(f.getName().endsWith("header.jsp") || f.getName().endsWith("body.jsp") ){
				PluginPageContext.addPage(type, path+"/" +f.getName());
		//	}
		}
		
	}
	
	
	public static void main(String args[]){
		PluginPageLoader pluginPageLoader = new PluginPageLoader("userlist","/admin/user/plugin");
	}
}
