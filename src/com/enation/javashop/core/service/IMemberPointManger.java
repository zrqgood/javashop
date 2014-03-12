package com.enation.javashop.core.service;

/**
 * 会员积分管理接口
 * @author kingapex
 *
 */
public interface IMemberPointManger {

	/**
	 * 获取积分项常量
	 */
	String TYPE_REGISTER= "register";
	String TYPE_EMIAL_CHECK="email_check";
	String TYPE_BUYGOODS ="buygoods";
	String TYPE_ONLINEPAY="onlinepay";
	String TYPE_LOGIN="login";
	String TYPE_COMMENT="comment";
	String TYPE_COMMENT_IMG="comment_img";
	String TYPE_REGISTER_LINK="register_link";
	
	
	/**
	 * 使用消费积分 
	 * @param memberid
	 * @param point
	 * @param reson
	 * @param relatedId
	 */
	public void useMarketPoint(int memberid,int point,String reson,Integer relatedId);
	
	
	
	/**
	 * 检测某项是否获取积分
	 * @param itemname
	 * @return
	 */
	public boolean checkIsOpen(String itemname);
	
	/**
	 * 获取某项的获取积分值
	 * @param itemname
	 * @return
	 */
	public int getItemPoint(String itemname);
	
	/**
	 * 为会员增加积分
	 * @param point
	 * @param itemname
	 */
	public void add(int memberid,int point,String itemname,Integer relatedId);
	
	
}
