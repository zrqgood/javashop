package com.enation.app.base.core.service;

import java.util.List;

import com.enation.app.base.core.model.Adv;
import com.enation.framework.database.Page;

/**
 * 广告接口
 * 
 * @author 李志富 lzf<br/>
 *         2010-2-4 下午03:25:36<br/>
 *         version 1.0<br/>
 * <br/>
 */
public interface IAdvManager {

	/**
	 * 广告信息修改
	 * 
	 * @param adv
	 */
	public void updateAdv(Adv adv);

	/**
	 * 获取广告详细
	 * 
	 * @param advid
	 * @return
	 */
	public Adv getAdvDetail(Long advid);

	/**
	 * 广告新增
	 * 
	 * @param adv
	 */
	public void addAdv(Adv adv);

	/**
	 * 广告删除
	 * 
	 * @param advid
	 */
	public void delAdvs(String ids);

	/**
	 * 分页读取广告
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageAdv(String order, int page, int pageSize);
	
	/**
	 * 获取对应acid的所有广告列表
	 * @param acid
	 * @return
	 */
	public List listAdv(Long acid);
	
	
	/**
	 * 搜索关键字
	 * @param acid
	 * @param cname
	 * @return
	 */
	public Page search(Long acid,String advname,int pageNo,int pageSize,String order);
	
	
}
