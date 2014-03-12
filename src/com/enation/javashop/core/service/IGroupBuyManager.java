package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.Page;
import com.enation.javashop.core.model.GroupBuy;
import com.enation.javashop.core.model.GroupBuyCount;




/**
 * 团购管理
 * @author kingapex
 *
 */
public interface IGroupBuyManager {
	
	
	/**
	 * 添加一个团购信息
	 * @param broupBuy
	 */
	public void add(GroupBuy groupBuy);
	
	
	
	/**
	 * 获取团购的详细信息
	 * @param groupid
	 * @return
	 */
	public GroupBuy get(int groupid);
	
	
	/**
	 * 修改一个团购信息
	 * @param broupBuy
	 */
	public void edit(GroupBuy groupBuy);
	
	
	/**
	 * 读取所有团购列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page list(int pageNo,int pageSize);
	
	
	
	/**
	 *  获取某个团购物数量增长规则
	 * @param groupid
	 * @return
	 */
	public List<GroupBuyCount> listRule(int groupid);
	
	
	
	/**
	 * 读取当前可用团购
	 * @return
	 */
	public List<GroupBuy> listEnable();
	
	/**
	 * 删除某个团购
	 * @param groupid
	 */
	public void delete( int groupid);
	
	
	/**
	 * 增加团购购买数
	 */
	public void addBuyCount();
	
	
}
