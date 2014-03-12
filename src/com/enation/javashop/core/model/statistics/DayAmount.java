package com.enation.javashop.core.model.statistics;

/**
 * 虚拟实体，销售额总览之天-销售额
 * 
 * @author lzf<br/>
 *         2010-3-9 下午04:37:32<br/>
 *         version 1.0<br/>
 */
public class DayAmount {
	private String day;
	private Double amount;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
