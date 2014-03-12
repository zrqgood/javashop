package com.enation.app.base.core.service.dbsolution.impl;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Element;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Oracle数据库导入导出
 * 
 * @author liuzy
 * 
 */
public class OracleSolution extends DBSolution {
	/**
	 * 返回与当前类匹配的类型名称
	 * 
	 * @param type
	 * @return
	 */
	private String toLocalType(String type, String size) {
		int nsize = Integer.parseInt(size);
		if ("int".equals(type)) {
			if (nsize < 2)
				return "NUMBER(2)";
			return "NUMBER(" + size + ")";
		}
		if ("memo".equals(type))
			return "CLOB";

		if ("datetime".equals(type))
			return "TIMESTAMP";

		if ("long".equals(type))
			return "NUMBER(20)";

		if ("decimal".equals(type))
			return "NUMBER(20,2)";

		return type.toUpperCase() + "(" + size + ")";
	}

	private String getBlockSQL(String sql) {
		return "BEGIN\n" + "\tEXECUTE IMMEDIATE '" + sql + "';\n"
				+ "\tEXCEPTION WHEN OTHERS THEN NULL;\n" + "END;" + EXECUTECHAR
				+ "\n";
	}

	private String getTriggerSQL(String table, String field) {
		String trigger = getBlockSQL("DROP TRIGGER TIB_" + table)
				+ "CREATE TRIGGER \"TIB_" + table + "\" BEFORE INSERT\n"
				+ "\tON " + table + " FOR EACH ROW\n" + "\tDECLARE\n"
				+ "\tINTEGRITY_ERROR  EXCEPTION;\n"
				+ "\tERRNO            INTEGER;\n"
				+ "\tERRMSG           CHAR(200);\n" + "\tMAXID			INTEGER;\n"
				+ "BEGIN\n" + "\tIF :NEW." + field + " IS NULL THEN\n"
				+ "\t\tSELECT MAX(" + field + ") INTO MAXID FROM " + table
				+ ";\n" + "\t\tSELECT S_" + table + ".NEXTVAL INTO :NEW."
				+ field + " FROM DUAL;\n" + "\t\tIF MAXID>:NEW." + field
				+ " THEN\n" + "\t\t\tLOOP\n" + "\t\t\t\tSELECT S_" + table
				+ ".NEXTVAL INTO :NEW." + field + " FROM DUAL;\n"
				+ "\t\t\t\tEXIT WHEN MAXID<:NEW." + field + ";\n"
				+ "\t\t\tEND LOOP;\n" + "\t\tEND IF;\n" + "\tEND IF;\n"
				+ "EXCEPTION\n" + "\tWHEN INTEGRITY_ERROR THEN\n"
				+ "\t\tRAISE_APPLICATION_ERROR(ERRNO, ERRMSG);\n" + "END;";
		return trigger;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCreateSQL(Element action) {
		String table = getTableName(action.elementText("table").toUpperCase());
		List<Element> fields = action.elements("field");

		String sql = getBlockSQL("DROP TABLE " + table + " cascade constraints");

		sql = sql + "CREATE TABLE " + table + " (";

		String sequence = "";
		for (int i = 0; i < fields.size(); i++) {
			String nl = "";
			Element field = fields.get(i);
			String name = field.elementText("name").toUpperCase();
			String size = field.elementText("size");
			String type = toLocalType(field.elementText("type").toLowerCase(),
					size);
			String option = field.elementText("option"); // 获取字段参数，第一位为自增，第二位为允许空值
			String def = field.elementText("default");

			if ("1".equals(option.substring(0, 1))) { // 如果第一位为1，则为主键
				sequence = getBlockSQL("DROP SEQUENCE S_" + table);
				sequence = sequence + "CREATE SEQUENCE S_" + table
						+ EXECUTECHAR + "\n";
				sequence = sequence + getTriggerSQL(table, name);
			}

			if ("1".equals(option.substring(1, 2)))
				nl = " NOT NULL";

			if (def != null)
				nl = " default " + def + nl;

			sql = sql + name + " " + type + nl + ",";
		}
		sql = sql.substring(0, sql.length() - 1) + ")" + EXECUTECHAR + "\n";
		sql = sql + sequence;
		return sql;
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix.toUpperCase();
	}

	/**
	 * 重载此函数，因为Oracle中&字符代表变量
	 */
	@Override
	protected String getConvertedSQL(String sql) {
		sql = sql.replaceAll("&", "&'||'");
		System.out.println(sql);
		return sql;
	}

	/**
	 * 由基类调用的多态函数，传入参数为function名称和值，返回自定义值给基类
	 */
	@Override
	protected Object getFuncValue(String name, String value) {
		if ("time".equals(name)) {
			Date date = new Date(Long.parseLong(value));
			return "TIMESTAMP'"
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
	public String getDeleteSQL(String table) {
		table = table.toUpperCase();
		String sql = getBlockSQL("DROP TRIGGER TIB_" + table) 
				+ getBlockSQL("DROP TABLE " + table)
				+ getBlockSQL("DROP SEQUENCE S_" + table);
		return sql;
	}
}
