package com.enation.javashop.core.service;

import com.enation.framework.database.Page;

/**
 * 会员-优惠券
 * 
 * @author lzf<br/>
 *         2010-3-23 下午12:41:18<br/>
 *         version 1.0<br/>
 */
public interface IMemberCouponsManager {

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page pageMemberCoupons(int pageNo, int pageSize);

	/**
	 * 待兑换优惠券列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page pageExchangeCoupons(int pageNo, int pageSize);

	/**
	 * 执行兑换
	 * 
	 * @param cpns_id
	 */
	public void exchange(int cpns_id);
	
 
}
