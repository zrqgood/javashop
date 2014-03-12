package com.enation.javashop.core.service.promotion;

/**
 * 促销方式接口
 * @author kingapex
 *2010-4-15下午03:30:09
 */
public interface IPromotionMethod {

	/**
	 * 定义促销方式名
	 * @return
	 */
	public String getName();
	
	
	
	
	public String getInputHtml(Integer pmtid,String solution);
	
	
	
	/**
	 * 
	 * @param pmtid
	 * @return
	 */
	public String onPromotionSave(Integer pmtid);
	
 
	
}
