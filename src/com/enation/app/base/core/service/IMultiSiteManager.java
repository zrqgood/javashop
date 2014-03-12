package com.enation.app.base.core.service;

import java.util.List;

import com.enation.app.base.core.model.MultiSite;
import com.enation.eop.resource.model.Theme;

/**
 * 多站点管理
 * @author lzf<br/>
 * 2010-12-23 下午02:57:11<br/>
 * version 2.1.5
 */
public interface IMultiSiteManager {
	
	
	/**
	 * 开启多站点功能
	 * @param domain 指定主站的域名
	 */
	public void open(String domain);
	
	
	
	/**
	 * 关闭多站点功能
	 */
	public void close();
	
	
	
	
	/**
	 * 新增子站
	 * <li>1.写入es_site子站表</li>
	 * <li>
	 * 2.向域名对照表中写入域名，站点id为此站主id
	 * </li>
	 * <li>3.为此子站安装模板</li>
	 * @param site
	 * @throws RuntimeException 如果子域已经存在，则提示域名已经存在
	 */
	public void add(MultiSite site);
	
	/**
	 * 编辑子站
	 * @param site
	 */
	public void update(MultiSite site);
	
	/**
	 * 删除子站点
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 列出某站的所有子站
	 * @param parentid
	 * @return
	 */
	public List list();
	

	
	/**
	 * 取出指定的子站信息
	 * @param id
	 * @return
	 */
	public MultiSite get(int id);
	
		/**
	 * 根据域名获取子站
	 * @param domain
	 * @return
	 */
	public MultiSite get(String domain);
 
}
