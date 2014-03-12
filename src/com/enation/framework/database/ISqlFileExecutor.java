package com.enation.framework.database;


/**
 * sql文件执行器
 * @author kingapex
 * 2010-1-25上午11:49:50
 */
public interface ISqlFileExecutor {
	
	/**
	 * 批量执行sql语句
	 * @param sql 可以以两种形式传递sql:<br>
	 * <li>1.路径方式：file:com/enation/eop/eop_empty.sql</li>
	 * <li>2.sql内容：直接传递文件内容</li>
	 */
	public void execute(String sql); 
}
