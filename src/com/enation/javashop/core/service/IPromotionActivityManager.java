package com.enation.javashop.core.service;

import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.PromotionActivity;


/**
 * 促销活动管理接口 
 * @author kingapex
 *2010-4-15下午12:04:07
 */
public interface IPromotionActivityManager {
	
	

	
	
	/**
	 * 添加促销活动
	 * @param activity 促销活动
	 * @throws IllegalStateException 如有下列情况之一：
	 * <li>activity 为null</li>
	 * <li>name 为null</li>
	 * <li>begin_time 为null</li> 
	 * <li>end_time 为null</li> 
	 */
	public void add(PromotionActivity  activity);
	
	
	/**
	 * 读取促销活动
	 * @param id 促销活动id
	 * @return 促销活动实体
	 * @throws IllegalStateException 如果id为null
	 * @throws ObjectNotFoundException 如果未找到相应的促销活动
	 */ 
	public PromotionActivity get(Integer id);

	
	
	
	/**
	 * 分页读取促销活动列表
	 * @param pageNo 当前页码
	 * @param pageSize 每页大小
	 * @return  促销活动列表
	 */
	public Page list(int pageNo,int pageSize);
	
	
	
	
	/**
	 * 修改促销活动
	 * @param activity 促销活动
	 * @throws IllegalStateException 如有下列情况之一：
	 * <li>id 为null</li>
	 * <li>name 为null</li>
	 * <li>begin_time 为null</li> 
	 * <li>end_time 为null</li> 
	 */	
	public void edit(PromotionActivity  activity);
	
	
	
	
	/**
	 * 指删除某些促销活动<br>
	 * 删除促销活动，同时删除此活动对应的促销规则（通过pmta_id关联）,及规则关联的商品促销对应数据 、会员级别促销对应表数据<br/>
	 * @param idArray 促销活动id数组
	 * 如果此参数为null或大小为0则不做任何处理 
	 */
	public void delete(Integer[] idArray);
	
}
