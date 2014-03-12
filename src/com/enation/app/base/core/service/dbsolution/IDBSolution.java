package com.enation.app.base.core.service.dbsolution;

import java.sql.Connection;
/**
 * 
 * @author liuzy
 * 
 * 数据库导入导出解决方案接口
 * 
 */
public interface IDBSolution {
	public boolean setConnection(Connection conn);
	/**
	 * 通过设置并创建JDBC连接
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 */
	public boolean dbImport(String xml);
	public boolean dbExport(String[] tables,String xml);
	public String dbExport(String[] tables,boolean dataOnly);
	public int deleteTable(String table);
	public void setPrefix(String prefix);
}
