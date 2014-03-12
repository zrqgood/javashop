package com.enation.javashop.core.model.support;

/**
 * 配送方式配置实体
 * @author kingapex
 *2010-3-28下午09:13:44
 */
public class DlyTypeConfig {
	
	
	/**
	 * 首重重量
	 */
	private Integer firstunit;
	
	
	/**
	 * 续重重量
	 */
	private Integer continueunit; 
	
	/**
	 * 是否统一配送地区设置<br/>
	 * 1为统一配置，0为否
	 */
	private Integer is_same;
	
	
	/**
	 * 是否启用默认费用<br/>
	 * 1为启用，0为不启用
	 */
	
	private Integer defAreaFee;

	
	
	/**
	 * 首重费用<br/>
	 */
	private Double firstprice;
	
	
	
	/**
	 * 续重费用 
	 */
	private Double  continueprice;
	
	
	/**
	 * 公式，启用公式时设置此属性
	 */
	private String expression;
	
	/**
	 * 是否启用公式
	 * 1为是，0为否
	 */
	private Integer useexp;
	
	
	/**
	 * 是否支持货到付款<br/>
	 * 1为支持，0为否
	 */
	private Integer have_cod; 
	
	public Integer getFirstunit() {
		return firstunit;
	}


	public void setFirstunit(Integer firstunit) {
		this.firstunit = firstunit;
	}


	public Integer getContinueunit() {
		return continueunit;
	}


	public void setContinueunit(Integer continueunit) {
		this.continueunit = continueunit;
	}


	public Integer getIs_same() {
		return is_same;
	}


	public void setIs_same(Integer isSame) {
		is_same = isSame;
	}


	public Integer getDefAreaFee() {
		
		return defAreaFee==null?0:defAreaFee;
	}


	public void setDefAreaFee(Integer defAreaFee) {
		this.defAreaFee = defAreaFee;
	}


	public Double getFirstprice() {
		return firstprice;
	}


	public void setFirstprice(Double firstprice) {
		this.firstprice = firstprice;
	}


	public Double getContinueprice() {
		return continueprice;
	}


	public void setContinueprice(Double continueprice) {
		this.continueprice = continueprice;
	}


	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}


	public Integer getUseexp() {
		return useexp;
	}


	public void setUseexp(Integer useexp) {
		this.useexp = useexp;
	}


	public Integer getHave_cod() {
		return have_cod;
	}


	public void setHave_cod(Integer haveCod) {
		have_cod = haveCod;
	}

	
}
