package com.enation.eop.resource;

import java.util.List;

import com.enation.eop.resource.model.Theme;

/**
 * 前台主题管理接口
 * @author lzf
 *         <p>
 *         2009-12-16 上午11:37:49
 *         </p>
 * @version 1.0
 */
public interface IThemeManager {
	
	
	/**
	 * 安装一个空白模板
	 * @param theme
	 * @since 2.1.3 
	 * @author kingapex
	 */
	public void addBlank(Theme theme);
	
	/**
	 * 添加模板
	 * @param theme
	 * @param isCommon
	 * @return
	 */
	public Integer add(Theme theme,boolean isCommon);
	
	/**
	 * 取某站点的所有模板列表
	 * @return
	 */
	public List<Theme> list();
	
	
	/**
	 * 获取某个子站的模板列表
	 * @param siteid
	 * @return
	 */
	public List<Theme> list(int siteid);
	
	
	/**
	 * 获取个模板的详细
	 * @param themeid
	 * @return
	 */
	public Theme getTheme(Integer themeid);
	

	/**
	 * 清除
	 * 一般用于站点安装时
	 */
	public void clean();
}
