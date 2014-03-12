package com.enation.eop.resource.impl;

import java.util.List;

import com.enation.eop.resource.IThemeManager;
import com.enation.eop.resource.model.Theme;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.util.FileUtil;

public class ThemeManagerImpl extends BaseSupport<Theme> implements IThemeManager {
 

	public void clean() {
		this.baseDaoSupport.execute("truncate table theme");
	}
	public Theme getTheme(Integer themeid) {
		//System.out.println(themeid);
		return this.baseDaoSupport.queryForObject("select * from theme where id=?", Theme.class, themeid);
	}

	
	public List<Theme> list() {
		if(EopContext.getContext().getCurrentSite().getMulti_site()==0){
			return this.baseDaoSupport.queryForList("select * from theme where siteid = 0",Theme.class);
		}else{
			return this.baseDaoSupport.queryForList("select * from theme where siteid = ?",Theme.class, EopContext.getContext().getCurrentChildSite().getSiteid());
		}
		
	}
	
	/* 
	 * 取得主站的theme列表
	 * (non-Javadoc)
	 * @see com.enation.eop.core.resource.IThemeManager#getMainThemeList()
	 */
	public List<Theme> list(int siteid) {
		return this.baseDaoSupport.queryForList("select * from theme where siteid = 0",Theme.class);
	}

	public void addBlank(Theme theme){
		try {
	 
			//公用模板由common目录复制，非公用由产品目录复制
			String basePath =  EopSetting.APP_DATA_STORAGE_PATH;
			basePath =basePath + "/themes";
			
			//复制资源到静态资源服务器
			theme.setSiteid(0);
			String contextPath = EopContext.getContext().getContextPath();
			String targetPath  = EopSetting.IMG_SERVER_PATH+ contextPath+ "/themes/" + theme.getPath();
			FileUtil.copyFolder(basePath + "/blank/images",targetPath + "/images");
			FileUtil.copyFile(basePath + "/blank/preview.png",targetPath + "/preview.png");
			FileUtil.copyFolder(basePath + "/blank/css",targetPath + "/css");
			FileUtil.copyFolder(basePath + "/blank/js",targetPath+ "/js");
			FileUtil.copyFolder(basePath + "/blank",EopSetting.EOP_PATH
					+contextPath
					+ "/themes/" + theme.getPath());
			this.baseDaoSupport.insert("theme", theme);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建主题出错");
		}
	}
	
	public Integer add(Theme theme,boolean isCommon) {
		try {
			this.copy(theme,isCommon);
		//	System.out.println("安装"+ theme.getThemename());
			this.baseDaoSupport.insert("theme", theme);
			return this.baseDaoSupport.getLastId("theme");
		} catch (Exception e) {
			 
			e.printStackTrace();
			throw new RuntimeException("安装主题出错");
		}
		
	}

	
	private  void copy( Theme theme ,boolean isCommon) throws Exception {
		//公用模板由common目录复制，非公用由产品目录复制
		String basePath = isCommon?EopSetting.APP_DATA_STORAGE_PATH:EopSetting.PRODUCTS_STORAGE_PATH+"/" + theme.getProductId() ;
		basePath =basePath + "/themes";
		
		//复制资源到静态资源服务器
		String contextPath = EopContext.getContext().getContextPath();
		String targetPath  = EopSetting.IMG_SERVER_PATH+ contextPath+ "/themes/" + theme.getPath();
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/images",targetPath + "/images");
		FileUtil.copyFile(basePath + "/" + theme.getPath() + "/preview.png",targetPath + "/preview.png");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/css",targetPath + "/css");
		FileUtil.copyFolder(basePath + "/" + theme.getPath() + "/js",targetPath+ "/js");

		
		FileUtil.copyFolder(basePath + "/" + theme.getPath(),EopSetting.EOP_PATH
				+contextPath
				+ "/themes/" + theme.getPath());
		/*
		 * 只考jsp到eop应用服务器中
		 
		IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".jsp");
		IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);

		FileUtils.copyDirectory(
				new File(basePath + "/" + theme.getPath() )
				, 
				
				new File(EopSetting.EOP_PATH
				+ "/user/"
				+userid
				+ "/"
				+siteid
				+ "/themes/" + theme.getPath())
				, 
				txtFiles
				);
		
		
		FileUtil.copyFolder(basePath + "/" + theme.getPath(), StringUtil
				.getRootPath()
				+ "/user/"
				+userid
				+ "/"
				+siteid
				+ "/themes/" + theme.getPath());
				*/
	}
}
