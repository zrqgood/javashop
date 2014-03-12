package com.enation.javashop.core.service;

import java.util.List;

import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Coupons;

/**
 * 优惠券
 * 
 * @author lzf<br/>
 *         2010-3-26 下午01:25:37<br/>
 *         version 1.0<br/>
 */
public interface ICouponManager {
	
	/**
	 * 分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param order
	 * @return
	 */
	public Page list(int pageNo, int pageSize, String order);
	
	
	
	/**
	 * 读取某个优惠卷详细
	 * @param cpnsid 优惠卷id
	 * @return 优惠卷实体
	 * @throws IllegalArgumentException  如果cpnsid为null
	 * @throws ObjectNotFoundException 如果优惠卷不存在
	 */
	public Coupons get(Integer cpnsid);
	
	
	
	
	
	/**
	 * 新增优惠卷<br>
	 * @param coupons 优惠卷实体<br>
	 * <li>cpns_status为空则默认为0(禁用状态)</li>
	 * <li>cpns_type为空则默认为0(A类优惠卷)</li>
	 * <li>disabled赋值无效，总为"false"</li>
	 * 
	 * @throws IllegalArgumentException下列情况抛出此异常：
	 * <li>如果coupons.cpns_name为空</li>
	 * <li>如果coupons.cpns_prefix为空</li>
	 * <li>如果coupons.pmt_id为空</li>
	 */
	public void add(Coupons coupons);
	
	
	/**
	 * 修改优惠卷<br>
	 * 优惠卷代码Cpns_prefix不可修改,此值传递也无效
	 * @param coupons 优惠卷实体<br>
	 * <li>cpns_status为空则默认为0(禁用状态)</li>
	 * <li>cpns_type为空则默认为0(A类优惠卷)</li>
	 * <li>disabled赋值无效，总为0</li>
	 * 
	 * @throws IllegalArgumentException下列情况抛出此异常：
	 * <li>如果coupons.cpns_id为空</li>
	 * <li>如果coupons.cpns_name为空</li>
	 * <li>如果coupons.cpns_prefix为空</li>
	 * <li>如果promotion.pmt_type不为1</li>
	 */
	public void edit(Coupons coupons);
	
	
	
	/**
	 * 批量删除优惠卷,并删除相应的优惠规则<br>
	 * 即删除promotion表中pmt_id= coupons.pmt_id
	 * @param cpnsIdArray 要删除的优惠卷id数组<br>
	 * 如果此参数为空或大小为0则不删除任何优惠卷数据
	 * 
	 * @param pmtIdArray 对应的促销规则id<br>
	 * 如果此参数为或大小为0则不删除任何促销规则数据
	 * 
	 * @throws IllegalArgumentException 如果cpnsIdArray的大小不等于的大小
	 */
	public void delete(Integer[] cpnsIdArray,Integer[] pmtIdArray);
	
	
	
	/**
	 * 读取所有可添加兑换规则的优惠卷<br>
	 * 即：cpns_type为1(B类优惠卷)
	 * 且cpns_point为空 
	 * 且状态为可用状态
	 * @return 优惠卷列表
	 */
	public List listCanExchange();
	
	
	
	/**
	 * 分页读取优惠卷兑换规则列表<br>
	 * 即：cpns_point不为空的优惠卷列表
	 * @return
	 */
	public Page listExchange(int pageNo,int pageSize);
	
	
	
	/**
	 * 保存一个优惠卷兑换规则
	 * @param cpnsId 兑换卷id
	 * @param point 需要的积分值
	 * @throws IllegalArgumentException ：
	 * <li>如果cpnsId为空</li>
	 * <li>如果point为空</li>
	 */
	public void saveExchange(Integer cpnsId,Integer point);
	
 
	
	/**
	 * 删除优惠卷兑换规则<br>
	 * 将相应的优惠卷point置为空
	 * @param cpnsIdArray 如果为这则不做任何处理
	 */
	public void deleteExchange(Integer[] cpnsIdArray);
	
}
