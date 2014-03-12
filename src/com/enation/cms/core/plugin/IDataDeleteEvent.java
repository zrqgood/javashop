package com.enation.cms.core.plugin;

/**
 * Cms数据删除事件
 * @author lzf<br/>
 * 2010-12-1 下午01:59:27<br/>
 * version 2.1.5
 */
public interface IDataDeleteEvent {
	
	/**
	 * Cms数据删除事件接口定义
	 * @param catid
	 * @param articleid
	 */
	public void onDelete(Integer catid,Integer articleid);

}
