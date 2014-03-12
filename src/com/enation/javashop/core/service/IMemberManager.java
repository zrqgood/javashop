package com.enation.javashop.core.service;

import com.enation.app.base.core.model.Member;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.MemberDTO;

/**
 * 会员管理接口
 * @author kingapex
 *2010-4-30上午10:07:35
 */
public interface IMemberManager {

	/**
	 * 添加会员
	 * 
	 * @param member
	 * @return 0：用户名已存在，1：添加成功
	 */
	public int add(Member member);

	
	
	/**
	 * 验证某上用户
	 * @param checkStr
	 */
	public Member checkEmail(String checkStr);
	
	
	
	
	/**
	 * 检测用户名是否存在
	 * 
	 * @param name
	 * @return 存在返回1，否则返回0
	 */
	public int checkname(String name);

	/**
	 * 修改会员信息
	 * 
	 * @param member
	 * @return
	 */
	public Member edit(Member member);

	/**
	 * 根据会员id获取会员信息
	 * 
	 * @param member_id
	 * @return
	 */
	public Member get(Integer member_id);

	/**
	 * 分页读取会员
	 * 
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page list(String order, int page, int pageSize);
	
	public Page list(String order, String name, String uname, int page, int pageSize);

	/**
	 * 删除会员
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 根据用户名称取用户信息
	 * 
	 * @param uname
	 * @return 如果没有找到返回null
	 */
	public Member getMemberByUname(String uname);

	
	
	
	/**
	 * 修改当前登录会员的密码
	 * 
	 * @param password
	 */
	public void updatePassword(String password);
	
	
	
	/**
	 * 更新某用户的密码
	 * @param memberid
	 * @param password
	 */
	public void updatePassword(Integer memberid,String password);
	
	
	/**
	 * 增加预存款
	 */
	public void addMoney(Integer memberid,Double num);
	
	
	
	/**
	 * 减少预存款
	 * @param memberid
	 * @param num
	 */
	public void cutMoney(Integer memberid,Double num);
	
	
	
	
	/**
	 * 会员登录 
	 * @param username 用户名
	 * @param password 密码
	 * @return 1:成功, 0：失败
	 */
	public int login(String username,String password);
	
	
	/**
	 * 会员注销
	 */
	public void logout(  );
	
	
	
	/**
	 * 管理员以会员身份登录
	 * @param username 要登录的会员名称
	 * @return 0登录失败，无此会员
	 * @throws  RuntimeException 无权操作
	 */
	public int loginbysys(String username);
	
	
	/**
	 * 更新某个会员的等级
	 * @param memberid
	 * @param lvid
	 */
	public void updateLv(int memberid,int lvid);
}