package com.enation.eop.resource;

import java.util.List;

import com.enation.eop.resource.model.EopApp;

/**
 * 应用管理 * 
 * @author lzf
 *         <p>
 *         created_time 2009-12-14 上午10:10:41
 *         </p>
 * @version 1.0
 */
public interface IAppManager {

	
	/**
	 * 添加一个应用
	 * @param app
	 */
	public void add(EopApp app);
	
	/**
	 * 获取所有应用列表
	 * @return
	 */
	public List<EopApp> list();
	
	
	/**
	 * 获取某个应用
	 * @param appid
	 * @return
	 */
	public EopApp get(String appid);

}
