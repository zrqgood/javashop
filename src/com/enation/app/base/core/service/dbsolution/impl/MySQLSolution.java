package com.enation.app.base.core.service.dbsolution.impl;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Element;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * MySQL数据库导入导出
 * 
 * @author liuzy
 * 
 */
public class MySQLSolution extends DBSolution {
	/**
	 * 返回与当前类匹配的类型名称
	 * 
	 * @param type
	 * @return
	 */
	private String toLocalType(String type, String size) {
		if ("int".equals(type)) {
			if ("1".equals(size))
				return "smallint(1)";
			else
				return "int";
		}

		if ("memo".equals(type))
			return "longtext";

		if ("datetime".equals(type))
			return "datetime";

		if ("long".equals(type))
			return "bigint";

		return type + "(" + size + ")";
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getCreateSQL(Element action) {
		String table = getTableName(action.elementText("table"));
		List<Element> fields = action.elements("field");

		String sql = "drop table if exists " + table + ";" + EXECUTECHAR;
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
				pk = "primary key (" + name + "),";
				nl = nl + " auto_increment";
			}

			sql = sql + name + " " + type + nl + ",";
		}
		sql = sql + pk;
		sql = sql.substring(0, sql.length() - 1) + ");";
		return sql;
	}

	/**
	 * 由基类调用的多态函数，传入参数为function名称和值，返回自定义值给基类
	 */
	@Override
	protected Object getFuncValue(String name, String value) {
		if ("time".equals(name)) {
			Date date = new Date(Long.parseLong(value));
			return "'"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
					+ "'";
		}
		return super.getFuncValue(name, value);
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
		String sql = "drop table if exists " + table;
		return sql;
	}
}
