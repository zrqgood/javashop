package com.enation.javashop.core.model.statistics;

/**
 * 销售量（额）排名实体
 * 
 * @author lzf<br/>
 *         2010-3-10 上午09:49:44<br/>
 *         version 1.0<br/>
 */
public class Rank {
	private String name;
	private int count;
	private Double amount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
