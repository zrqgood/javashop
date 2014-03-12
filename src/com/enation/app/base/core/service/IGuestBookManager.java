package com.enation.app.base.core.service;

import com.enation.app.base.core.model.GuestBook;
import com.enation.framework.database.Page;

public interface IGuestBookManager {
	
	
	/**
	 * 发表留言
	 * @param guestbook
	 */
	public void add(GuestBook guestbook);
	
	
	/**
	 * 回复留言
	 * @param guestbook
	 */
	public void reply(GuestBook guestbook);
	
	
	
	
	/**
	 * 分页读取留言列表
	 * @param keyword 关键字，可搜索标题，内容和用户名。
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(String keyword,int pageNo,int pageSize);
	
	
	
	/**
	 * 读取一个详细，包括主题和回复
	 * @param id
	 * @return
	 */
	public GuestBook get(int id);
	
	
	/**
	 * 修改一条信息，只会修改其content
	 * @param guestbook
	 */
	public void edit(int id,String content);
	
	
	/**
	 * 指删除留言
	 * @param idArray 要删除的留言id数组
	 */
	public void delete(Integer[] idArray);
	
	
	
}
