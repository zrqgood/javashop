package com.enation.app.base.core.service.dbsolution.impl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * SQLServer数据库导入导出
 * 
 * @author liuzy
 * 
 */
public class SQLServerSolution extends DBSolution {
	/**
	 * 解决id插入值问题开始
	 */
	private String currTable;

	private boolean setIdentity(String table, boolean on) {
		table = table + " " + (on ? "ON" : "OFF");
		try {
			Statement stat = conn.createStatement();
			stat.execute("SET IDENTITY_INSERT " + table);
			stat.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	protected void beforeImport() {
		currTable = null;
	}

	@Override
	protected boolean beforeInsert(String table, String fields, String values) {
		if (!table.equals(currTable)) {
			if (currTable != null)
				setIdentity(currTable, false);
			currTable = table;
			setIdentity(table, true);
		}
		return true;
	}

	@Override
	protected void afterImport() {
		if (currTable != null)
			setIdentity(currTable, false);
	}

	/**
	 * 解决id插入值问题结束
	 */


	/**
	 * 返回与当前类匹配的类型名称
	 * 
	 * @param type
	 * @return
	 */
	private String toLocalType(String type, String size) {
		if ("int".equals(type)) {
			if ("1".equals(size))
				return "smallint";
			else
				return "int";
		}

		if ("memo".equals(type))
			return "text";

		if ("datetime".equals(type))
			return "datetime";

		if ("long".equals(type))
			return "bigint";

		return type + "(" + size + ")";
	}

	@Override
	public String getCreateSQL(Element action) {
		String table = getTableName(action.elementText("table"));
		List<Element> fields = action.elements("field");

		String sql = "if exists (select 1 from sysobjects where id = object_id('"
				+ table
				+ "')"
				+ "and type = 'U') drop table "
				+ table
				+ EXECUTECHAR;
		sql = sql + "create table " + table + " (";

		String pk = "";
		for (int i = 0; i < fields.size(); i++) {
			String nl = "";
			Element field = fields.get(i);
			String name = field.elementText("name");
			String size = field.elementText("size");
			String type = toLocalType(field.elementText("type").toLowerCase(),
					size);
			String option = field.elementText("option");
			String def = field.elementText("default");

			if ("1".equals(option.substring(1, 2))) // 如果第二位为1，不允许空值
				nl = " not null";

			if (def != null)
				nl = nl + " default " + def;

			if ("1".equals(option.substring(0, 1))) { // 如果第一位为1，则为主键
				pk = "constraint PK_" + table.toUpperCase()
						+ " primary key nonclustered (" + name + "),";
				nl = nl + " identity";
			}

			sql = sql + name + " " + type + nl + ",";
		}
		sql = sql + pk;
		sql = sql.substring(0, sql.length() - 1) + ")";

		return sql;
	}

	/**
	 * 由基类调用的多态函数，返回当前类所要捕获的自定义function列表，与getFuncValue配合使用
	 */
	@Override
	public String[] getFuncName() {
		String[] name = { "time" };
		return name;
	}

	@Override
	public String getFieldValue(int fieldType, Object fieldValue) {
		if (Types.TIMESTAMP == fieldType) {
			Timestamp value = (Timestamp) fieldValue;
			return "time(" + value.getTime() + ")";
		} else
			return super.getFieldValue(fieldType, fieldValue);

	}

	@Override
	public String getDeleteSQL(String table) {
		String sql = "";
		return sql;
	}
}
