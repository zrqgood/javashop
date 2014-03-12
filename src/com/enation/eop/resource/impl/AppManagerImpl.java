package com.enation.eop.resource.impl;

import java.util.List;

import com.enation.eop.resource.IAppManager;
import com.enation.eop.resource.model.EopApp;
import com.enation.eop.sdk.IApp;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.IDaoSupport;

/**
 * 应用管理
 * @author kingapex
 *2010-5-10上午11:13:21
 */
public class AppManagerImpl implements IAppManager {

	private IDaoSupport< EopApp > daoSupport;
	
	public EopApp get(String appid) {
		String sql ="select * from eop_app where id=?";
		return this.daoSupport.queryForObject(sql, EopApp.class, appid);
	}

	
	public List<EopApp> list() {
		String sql ="select * from eop_app";
		return this.daoSupport.queryForList(sql,  EopApp.class);
	}

	public IDaoSupport<EopApp> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopApp> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public void add(EopApp app) {
		this.daoSupport.insert("eop_app", app);
	}

}
