package com.enation.javashop.core.service;

import com.enation.app.base.core.model.Message;
import com.enation.framework.database.Page;

/**
 * 留言、短消息管理
 * 
 * @author lzf<br/>
 *         2010-3-18 上午11:30:28<br/>
 *         version 1.0<br/>
 */
public interface IMessageManager {
	
	/**
	 * 分页读取留言或短消息
	 * @param pageNo
	 * @param pageSize
	 * @param folder, 'inbox'=>收件箱; 'outbox'=>发件箱
	 * @return
	 */
	public Page pageMessage(int pageNo, int pageSize, String folder);
	
	/**
	 * 添加短消息
	 * @param message
	 */
	public void addMessage(Message message);
	
	/**
	 * 删除收件箱中的消息
	 * @param ids
	 */
	public void delinbox(String ids);
	
	/**
	 * 删除发件箱中的消息
	 * @param ids
	 */
	public void deloutbox(String ids);
	
	/**
	 * 修改短消息
	 * @param m
	 */
	public void setMessage_read(int msg_id);

}
