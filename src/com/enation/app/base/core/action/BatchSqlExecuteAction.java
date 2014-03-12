package com.enation.app.base.core.action;

import com.enation.app.base.core.service.IBatchSqlExecutor;
import com.enation.framework.action.WWAction;

public class BatchSqlExecuteAction extends WWAction {
	private IBatchSqlExecutor batchSqlExecutor;
	private String sql ;
	private String toCms; //是否为cms执行
	
	public String toExe(){
		
		return "input";
	}
	
	public String execute(){
		try{
			if(toCms==null)
				batchSqlExecutor.exeucte(sql);
			else
				batchSqlExecutor.executeForCms(sql);
			this.msgs.add("执行成功");
		}catch(RuntimeException e){
			this.msgs.add(e.getMessage());
		}
		return this.MESSAGE;
	}
	
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public IBatchSqlExecutor getBatchSqlExecutor() {
		return batchSqlExecutor;
	}
	public void setBatchSqlExecutor(IBatchSqlExecutor batchSqlExecutor) {
		this.batchSqlExecutor = batchSqlExecutor;
	}

	public String getToCms() {
		return toCms;
	}

	public void setToCms(String toCms) {
		this.toCms = toCms;
	}
	
	
}
