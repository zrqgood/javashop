package com.enation.app.base.core.service.auth;

import java.util.List;
import java.util.Map;

import com.enation.eop.resource.model.AdminUser;

/**
 * 管理员管理接口
 * @author kingapex
 * 2010-11-7下午05:49:12
 */
public interface IAdminUserManager {
	


	/**
	 * 为当前站点添加一个管理员
	 * @param adminUser
	 * @return 添加的管理员id
	 */
	public Integer add(AdminUser adminUser);
	
	
	
	/**
	 * 为某个站点添加管理员
	 * @param userid
	 * @param siteid
	 * @param adminUser
	 * @return 添加的管理员id
	 */
	public Integer add(int userid,int siteid,AdminUser adminUser);
	
	
	
	
	
	/**
	 * 管理员
	 * @param username
	 * @param password 此处为未经过md5加密的密码
	 * @return 返回登录成功的用户id
	 * @throws RuntimeException 登录失败抛出此异常，登录失败原因可通过getMessage()方法获取
	 */
	public int  login(String username,String password);
	
	
	/**
	 * 获取当前登录管理员
	 * @return
	 */
	public AdminUser getCurrentUser();
	
	
	/**
	 * 系统登录
	 * @param username
	 * @param password 此处为未经过md5加密的密码
	 * @return 返回登录成功的用户id
	 * @throws RuntimeException 登录失败抛出此异常，登录失败原因可通过getMessage()方法获取
	 */
	public int loginBySys(String username, String password) ;
	
	
	
	/**
	 * 读取管理员信息
	 * @param id
	 * @return
	 */
	public AdminUser get(Integer id);
	
	
	/**
	 * 修改管理员信息 
	 * @param eopUserAdmin
	 */
	public void edit(AdminUser eopUserAdmin);
	
	
	/**
	 * 删除管理员
	 * @param id
	 * @throws RuntimeException  当删除最后一个管理员时
	 */
	public void delete(Integer id);
	
	
	/**
	 * 检查是否为最后一个管理员
	 * @return 
	 */
	public int checkLast();
 
  
	/**
	 * 读取此站点所有管理员
	 * @return
	 */
	public List list( ) ;
	
	
	/**
	 * 读取某站点的所有的管理员
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List<Map> list(Integer userid,Integer siteid);
	
	/**
	 * 清除本站点的所有管理员
	 * 一般安装所用
	 */
	public void clean();
}
