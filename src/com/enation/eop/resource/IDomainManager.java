package com.enation.eop.resource;

import java.util.List;

import com.enation.eop.resource.model.EopSiteDomain;

/**
 * 域名管理
 * @author kingapex
 *2010-5-9下午08:13:24
 */
public interface IDomainManager {
	
	/**
	 * 根据id获取域名
	 * @param id
	 * @return
	 */
	public EopSiteDomain get(Integer id);
	
	
	
	/**
	 * 修改域名
	 * @param domain
	 */
	public void edit(EopSiteDomain domain);
	
	
	
	
	/**
	 * 获取某用户的所有域名
	 * @param userid
	 * @return
	 */
	public List<EopSiteDomain> listUserDomain();
	
	
	
	/**
	 * 读取当前站点的所有域名
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List<EopSiteDomain> listSiteDomain();
	
	
	/**
	 * 读取某站点的所有域名
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List<EopSiteDomain> listSiteDomain(Integer siteid);
	
	
	
	/**
	 * 读取某个站点的域名个数
	 * @param siteid
	 * @return
	 */
	public int getDomainCount(Integer siteid);
	
}
