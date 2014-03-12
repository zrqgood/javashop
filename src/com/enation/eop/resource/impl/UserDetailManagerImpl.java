package com.enation.eop.resource.impl;

import com.enation.eop.resource.IUserDetailManager;
import com.enation.eop.resource.model.EopUserDetail;
import com.enation.framework.database.IDaoSupport;
/**
 * 用户详细信息管理
 * @author kingapex
 *2010-5-10下午12:36:16
 */
public class UserDetailManagerImpl implements IUserDetailManager {
	private IDaoSupport<EopUserDetail> daoSupport;
	
	public void add(EopUserDetail eopUserDetail) {
		this.daoSupport.insert("eop_userdetail", eopUserDetail);
	}

	
	public void edit(EopUserDetail eopUserDetail) {
		this.daoSupport.update("eop_userdetail", eopUserDetail, " userid = " + eopUserDetail.getUserid());
	}

	
	public EopUserDetail get(Integer userid) {
		return this.daoSupport.queryForObject(	"select * from eop_userdetail where userid = ?",
				EopUserDetail.class, userid);
	}

	public IDaoSupport<EopUserDetail> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopUserDetail> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
}
