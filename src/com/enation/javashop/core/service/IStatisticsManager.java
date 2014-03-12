package com.enation.javashop.core.service;

import java.util.List;
import java.util.Map;

import com.enation.javashop.core.model.statistics.DayAmount;
import com.enation.javashop.core.model.statistics.MonthAmount;

/**
 * 订单管理接口
 * 
 * @author lzf 2010-3-9 上午11:14:20 version 1.0
 */
public interface IStatisticsManager {

	/**
	 * 统计当年的 月-销售额
	 * 
	 * @return
	 */
	public List<MonthAmount> statisticsMonth_Amount();

	/**
	 * 统计指定月份所在的年的 月-销售额
	 * 
	 * @param monthinput
	 * @return
	 */
	public List<MonthAmount> statisticsMonth_Amount(String monthinput);

	/**
	 * 统计当前月的天-销售额
	 * 
	 * @return
	 */
	public List<DayAmount> statisticsDay_Amount();

	/**
	 * 统计指定月份的天-销售额
	 * 
	 * @param monthinput
	 * @return
	 */
	public List<DayAmount> statisticsDay_Amount(String monthinput);

	
	/**
	 * 按支付方式统计订单 
	 * @return key:
	 * num - 订单数量
	 * amount -订单总额
	 * payment_name -支付方式名称
	 */
	public List<Map> orderStatByPayment();
	
	
	
	/**
	 * 按配送方式统计订单 
	 * @return
	 * key:
	 * num - 订单数量
	 * amount -订单总额
	 * shipping_type -配送方式名称
	 */
	public List<Map> orderStatByShip();
	

}
