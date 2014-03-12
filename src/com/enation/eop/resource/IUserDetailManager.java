package com.enation.eop.resource;

import com.enation.eop.resource.model.EopUserDetail;

/**
 * 用户详细管理接口
 * @author kingapex
 *2010-5-10下午12:33:51
 */
public interface IUserDetailManager {
	
	/**
	 * 读取详细
	 * @param userid
	 * @return
	 */
	public EopUserDetail get(Integer userid);

	
	/**
	 * 添加
	 * @param eopUserDetail
	 */
	public void add(EopUserDetail eopUserDetail);

	
	
	/**
	 * 修改
	 * @param eopUserDetail
	 */
	public void edit(EopUserDetail eopUserDetail);
}
