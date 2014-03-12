package com.enation.javashop.core.model.statistics;

/**
 * 虚拟实体，统计中的月-销售额
 * 
 * @author lzf<br/>
 *         2010-3-9 下午03:37:15<br/>
 *         version 1.0<br/>
 */
public class MonthAmount {

	private String month;
	private Double amount;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
