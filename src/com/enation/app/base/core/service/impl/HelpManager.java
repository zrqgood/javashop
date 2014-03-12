package com.enation.app.base.core.service.impl;

import com.enation.app.base.core.model.Help;
import com.enation.app.base.core.service.IHelpManager;
import com.enation.eop.sdk.database.BaseSupport;

/**
 * 帮助管理
 * @author kingapex
 * 2010-10-18上午01:37:12
 */
public class HelpManager extends BaseSupport<Help> implements IHelpManager {

	public Help get(String helpid) {
		String sql ="select * from es_help_1_1 where helpid=?";
		return this.daoSupport.queryForObject(sql, Help.class, helpid);
	}
	
}
