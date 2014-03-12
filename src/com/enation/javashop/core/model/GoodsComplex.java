package com.enation.javashop.core.model;

/**
 * 相关商品
 * 
 * @author lzf<br/>
 *         2010-3-30 下午12:03:45<br/>
 *         version 1.0<br/>
 */
public class GoodsComplex implements java.io.Serializable {
	private int goods_1;
	private int goods_2;
	private String manual;
	private int rate;// enum('left','both')

	public int getGoods_1() {
		return goods_1;
	}

	public void setGoods_1(int goods_1) {
		this.goods_1 = goods_1;
	}

	public int getGoods_2() {
		return goods_2;
	}

	public void setGoods_2(int goods_2) {
		this.goods_2 = goods_2;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
