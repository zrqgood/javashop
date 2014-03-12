package com.enation.app.base.core.service;

import java.util.Date;

import com.enation.app.base.core.model.Ask;
import com.enation.app.base.core.model.Reply;
import com.enation.framework.database.Page;


/**
 * 问答管理接口
 * @author kingapex
 * 2010-8-7下午04:00:03
 */
public interface IAskManager {
	
	/**
	 * 提问<br>
	 * 实现者要记录提问时间、提问人、相应站点名称及站点域名
	 * @param ask 实体中不包含上述信息， 只包含问题本身信息
	 */
	public void add(Ask ask);

	
	/**
	 * 回复<br>
	 * <li>调用者要设置reply的askid、username及其它基本信息</li>
	 * <li>实现者要记录回复时间。</li>
	 * @param ask
	 */
	public void reply(Reply reply);
	
	
	/**
	 * 读取一个问题的详细，包括其回复信息
	 * @param askid
	 * @return 回复列表数据存储在replyList属性中
	 */
	public Ask get(Integer askid);
	
	
	
	/**
	 * 读取我的问题列表，此查询在作用域为当前用户的问题
	 * @param keyword 关键字，可搜索title和content，为空则不以此条件筛选
	 * @param startTime 开始时间条件,为空则不以此条件筛选
	 * @param endTime 结束时间,为空则不以此条件筛选
	 * @param pageNo  页码
	 * @param pageSize 页大小
	 * @return 
	 */
	public Page listMyAsk(String keyword,Date startTime,Date endTime,int pageNo ,int pageSize);
	
	
	/**
	 * 读取所有的问题列表，此查询在作用域为当前用户的问题
	 * @param keyword 关键字，可搜索title、content、站点名称，提问人名称、域名，为空则不以此条件筛选
	 * @param startTime 开始时间条件,为空则不以此条件筛选
	 * @param endTime 结束时间,为空则不以此条件筛选
	 * @param pageNo  页码
	 * @param pageSize 页大小
	 * @return 
	 */
	public Page listAllAsk(String keyword,Date startTime,Date endTime,int pageNo ,int pageSize);
	
	
	/**
	 * 删除问题 
	 * @param id
	 */
	public void delete(Integer[] id);
}
