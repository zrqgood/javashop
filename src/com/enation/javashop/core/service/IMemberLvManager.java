package com.enation.javashop.core.service;

import java.util.List;

import com.enation.app.base.core.model.MemberLv;
import com.enation.framework.database.Page;



/**
 * 会员级别管理
 * @author kingapex
 *2010-4-30上午10:07:50
 */
public interface IMemberLvManager{
	
	 
	/**
	 * 获取缺省的会员级别 如果没有缺省的会员级别返回null
	 * @return
	 */
	public Integer getDefaultLv() ;
	
	
	
	
	/**
	 * 添加一个会员级别
	 * @param lv
	 */
	public void add(MemberLv lv);
	
	
	
	
	
	/**
	 * 编辑会员级别
	 * @param lv
	 */
	public void edit(MemberLv lv);
	
	
	
	
	
	/**
	 * 分页读取会员等级
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page list(String order,int page,int pageSize);
	
	
	
	
	 /**
	  * 获取一个会员级别
	  * @param lv_id
	  * @return
	  */
	public MemberLv get(Integer lv_id);
	
	/**
	 * 根据积分获取合适的会员等级
	 * @param point
	 * @return
	 */
	public MemberLv getByPoint(int point);
	
	
	
	/**
	 * 删除会员级别
	 * @param id
	 */
	public void delete(String id);
	
	
	
	/**
	 * 读取所有级别列表
	 * @return
	 */
	public  List<MemberLv> list();
	
	
	/**
	 * 根据级别列表(,号分隔)读取会员级别
	 * @param ids(,号分隔的会员级别id字串)
	 * @return 会员级别列表
	 */
	public List<MemberLv> list(String ids);
	
}