package com.enation.eop.resource;

import com.enation.app.base.core.model.DataLog;
import com.enation.framework.database.Page;

/**
 * 日志管理接口
 * @author kingapex
 * 2010-10-19下午03:33:28
 */
public interface IDataLogManager {
	
	
	/**
	 * 添加日志 
	 * @param datalog
	 */
	public void add(DataLog datalog);
	
	
	
	/**
	 * 分页读取日志列表
	 * @param start 开始时间 ，格式：yyyy-MM-dd ，如果为null则不限制
	 * @param end 结束 始时间 ，格式：yyyy-MM-dd ，如果为null则不限制
	 * @param pageNo 页数
	 * @param pageSize 分页大小
	 * @return 分页结果集
	 */
	public Page list(String start,String end,int pageNo,int pageSize);
	
	
	/**
	 * 批量删除日志
	 * @param ids
	 */
	public void delete(Integer[] ids);
	
	
}
