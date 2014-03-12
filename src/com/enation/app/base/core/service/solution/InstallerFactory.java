package com.enation.app.base.core.service.solution;

/**
 * 根据类型返回合适的Installer,此文件必须工作于spring 容器下
 * @author kingapex
 * 2010-1-20下午07:20:34
 */
public class InstallerFactory {
	public static final String TYPE_APP ="apps";
	public static final String TYPE_MENU ="menus";
	public static final String TYPE_ADMINTHEME ="adminThemes";
	public static final String TYPE_THEME ="themes";
	public static final String TYPE_URL ="urls";
	public static final String TYPE_WIDGET ="widgets";
	public static final String TYPE_INDEX_ITEM ="indexitems";
	
	
	private IInstaller menuInstaller;
	private IInstaller adminThemeInstaller;
	private IInstaller themeInstaller;
	private IInstaller uriInstaller;
	private IInstaller widgetInstaller;
	private IInstaller appInstaller;
	private IInstaller indexItemInstaller;
	
	public IInstaller getInstaller(String type){
		
		
		if(TYPE_APP.equals(type)){
			return this.appInstaller;
		}
		
		if(TYPE_MENU.equals(type)){
			return this.menuInstaller;
		}
		

		if(TYPE_ADMINTHEME.equals(type)){
			return this.adminThemeInstaller;
		}
		
		if(TYPE_THEME.equals(type)){
			return this.themeInstaller;
			
		}

		if(TYPE_URL.equals(type)){
			return this.uriInstaller;
		}

		if(TYPE_WIDGET.equals(type)){
			return this.widgetInstaller;
		}
		
		if(TYPE_INDEX_ITEM.equals(type))
		{
			return this.indexItemInstaller;
		}
		
 
		throw new  RuntimeException(" get Installer instance error[incorrect type param]");
	}

	public void setMenuInstaller(IInstaller menuInstaller) {
		this.menuInstaller = menuInstaller;
	}

	public void setAdminThemeInstaller(IInstaller adminThemeInstaller) {
		this.adminThemeInstaller = adminThemeInstaller;
	}

	public void setThemeInstaller(IInstaller themeInstaller) {
		this.themeInstaller = themeInstaller;
	}

	public void setUriInstaller(IInstaller uriInstaller) {
		this.uriInstaller = uriInstaller;
	}

	public void setWidgetInstaller(IInstaller widgetInstaller) {
		this.widgetInstaller = widgetInstaller;
	}

	public IInstaller getAppInstaller() {
		return appInstaller;
	}

	public void setAppInstaller(IInstaller appInstaller) {
		this.appInstaller = appInstaller;
	}

	public void setIndexItemInstaller(IInstaller indexItemInstaller) {
		this.indexItemInstaller = indexItemInstaller;
	}
	
}
