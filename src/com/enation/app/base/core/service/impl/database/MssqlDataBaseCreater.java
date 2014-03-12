package com.enation.app.base.core.service.impl.database;

import com.enation.app.base.core.service.IDataBaseCreater;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.ISqlFileExecutor;


/**
 * mssql数据库创建者
 * @author kingapex
 *
 */
public class MssqlDataBaseCreater implements IDataBaseCreater {
	private  ISqlFileExecutor sqlFileExecutor;
	public void create() {
		if(EopSetting.RUNMODE.equals("2")){
			this.sqlFileExecutor.execute("USE [master];");
//			this.sqlFileExecutor.execute("GO;");
			this.sqlFileExecutor.execute("IF  EXISTS (SELECT name FROM sys.databases WHERE name = N'eop')  DROP DATABASE [eop]");
			this.sqlFileExecutor.execute("CREATE DATABASE [eop]");
			this.sqlFileExecutor.execute("USE [eop]");
		}
			
		sqlFileExecutor.execute("file:com/enation/eop/eop_mssql.sql");
		sqlFileExecutor.execute("file:com/enation/javashop/javashop_mssql.sql");
		sqlFileExecutor.execute("file:com/enation/cms/cms_mssql.sql");		
	}
	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}
	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}
}
