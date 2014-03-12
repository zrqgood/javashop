package com.enation.framework.database.impl;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.context.EopSetting;


/**
 * 覆写jdbctemlate ，使用LowerCaseColumnMapRowMapper
 * @author kingapex
 * 2010-6-13上午11:05:32
 */
public class LowerCaseJdbcTemplate extends JdbcTemplate {
	@Override
	protected RowMapper getColumnMapRowMapper() {
		if("2".equals(EopSetting.DBTYPE)){
			return new LowerCaseColumnMapRowMapper();
		}else{
			return new ColumnMapRowMapper();
		}
	}

}
