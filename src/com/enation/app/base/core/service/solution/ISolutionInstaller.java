package com.enation.app.base.core.service.solution;

/**
 * 解决方案安装器
 * @author kingapex
 *
 */
public interface ISolutionInstaller {
	
	
	/**
	 * 为用户安装解决方案
	 * @param userid 用户id
	 * @param siteid 站点id
	 * @param productId 唯一id(非数据库索引)
	 */
	public void install(Integer userid, Integer siteid, String productId);
	
	
	
	
}
