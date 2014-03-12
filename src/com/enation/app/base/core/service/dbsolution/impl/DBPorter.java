package com.enation.app.base.core.service.dbsolution.impl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 导入、导出类的公共部分
 * 
 * @author liuzy
 * 
 */
public class DBPorter {
	protected boolean closeConn = false;
	protected DBSolution solution;
	protected Connection conn;

	public boolean createPorter(DBSolution solution, Connection conn) {
		this.solution = solution;
		if (conn == null) { // 如果传入值为空，则使用jdbcTemplate
			try {
				closeConn = true;
				this.conn = solution.jdbcTemplate.getDataSource()
						.getConnection();
			} catch (SQLException e) {
				return false;
			}
		} else
			this.conn = conn;
		return true;
	}

	public boolean closePorter() {
		if (closeConn) {
			try {
				this.conn.close();
				return true;
			} catch (SQLException e) {
				return false;
			}
		} else
			return true;
	}
}
