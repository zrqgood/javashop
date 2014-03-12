package com.enation.app.base.core.service;


/**
 * 批量sql执行器
 * @author kingapex
 * 2010-10-14下午11:58:21
 */
public interface IBatchSqlExecutor {
	
	
	/**
	 * 为所有站点批量执行sql<br>
	 * 将sql语句中<_userid>替换为真实userid<br>
	 * 将sql语句中<_siteid>替换 为真实siteid <br>
	 * @param sql
	 */
	public void exeucte(String sql);
	
	
	
	/**
	 * 为所有的cms站点执行sql语句<br>
	 * 其逻辑是查找每个站点的datamodel，并为相应的表执行sql语句。其中变名以<tbname>字串表示<br>
	 * 如：alter table <tbname> add sort int; 
	 * @param sql
	 */
	public void executeForCms(String sql);
}
