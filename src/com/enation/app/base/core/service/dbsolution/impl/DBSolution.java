package com.enation.app.base.core.service.dbsolution.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.enation.app.base.core.service.dbsolution.IDBSolution;
import com.enation.framework.util.StringUtil;

/**
 * 数据库导入导出基类，在此基础上可以进一步实现不同厂商的数据库导入导出方法
 * 
 * @author liuzy
 * 
 */
public abstract class DBSolution implements IDBSolution {
	public static final String EXECUTECHAR = "!-->";

	protected String prefix = "";

	protected List<String> functions = new ArrayList<String>(); // 自定义函数名称列表

	protected Connection conn;

	protected JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 返回带有前缀的表名
	 * 
	 * @param table
	 * @return
	 */
	protected String getTableName(String table) {
		/**
		 * 下列代码已经移至StringUtils中
		 * String result = ""; if (table.length() > prefix.length()) { if
		 * (table.substring(0, prefix.length()).toLowerCase()
		 * .equals(prefix.toLowerCase())) result = table; else result = prefix +
		 * table; } else result = prefix + table;
		 * 
		 * return result;
		 */
		return StringUtil.addPrefix(table, prefix);
	}

	/**
	 * 将特殊字符编码
	 * 
	 * @param text
	 * @return
	 */
	public String encode(String text) {
		text = text.replaceAll("&", "&amp;");
		text = text.replaceAll("<", "&lt;");
		text = text.replaceAll(">", "&gt;");
		return text;
	}

	/**
	 * 将特殊字符解码
	 * 
	 * @param text
	 * @return
	 */
	public String decode(String text) {
		text = text.replaceAll("&amp;", "&");
		text = text.replaceAll("&lt;", "<");
		text = text.replaceAll("&gt;", ">");
		return text;
	}

	/**
	 * 将字段值编码
	 * 
	 * @param value
	 * @return
	 */
	public String encodeValue(String value) {
		return value.replaceAll(",", "!comma;");
	}

	/**
	 * 将字段值解码
	 * 
	 * @param value
	 * @return
	 */
	public String decodeValue(String value) {
		return value.replaceAll("!comma;", ",");
	}

	/**
	 * 用于派生类重载，获取派生类自定义function名称列表
	 * 
	 * @return
	 */
	protected String[] getFuncName() {
		return null;
	}

	/**
	 * 用于派生类重载，由派生类传回function调用后的返回值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	protected Object getFuncValue(String name, String value) {
		return value;
	}

	/**
	 * 用于派生类重载，sql语句被执行前进行处理
	 * 
	 * @param value
	 * @return
	 */
	protected String getConvertedSQL(String sql) {
		return sql;
	}

	protected void initFunctions() {
		functions.add("time");
	}

	protected Object doFunction(String name, String value) {
		if ("time".equals(name)) {
			return new Timestamp(Long.parseLong(value));
		}
		return null;
	}

	/*
	 * 执行插入之前调用，用于扩展处理
	 */
	protected boolean beforeInsert(String table, String fields, String values) {
		return true;
	}

	/*
	 * 执行插入之后调用，用于扩展处理
	 */
	protected void afterInsert(String table, String fields, String values) {

	}

	/**
	 * 开始导入之前执行，用于扩展
	 */
	protected void beforeImport() {

	}

	/**
	 * 导入之后执行，用于扩展
	 */
	protected void afterImport() {

	}

	protected Object getFuncValue(String value) {
		for (int i = 0; i < functions.size(); i++) {
			if (functions.get(i).length() < value.length()) {
				String prefix = value.substring(0, functions.get(i).length())
						.toLowerCase();
				if (functions.get(i).equals(prefix)) {
					String param = value.substring(prefix.length() + 1);
					param = param.substring(0, param.length() - 1);
					return doFunction(prefix, param);
				}
			}
		}
		return value;
	}

	/**
	 * 根据字段类型所回字段值，如字符符需加单引号；继承类中可实现对特殊类型字段值进行转化，如日期型
	 * 
	 * @param type
	 * @return
	 */
	public String getFieldValue(int fieldType, Object fieldValue) {
		String value = encodeValue("" + fieldValue);
		if (Types.VARCHAR == fieldType || Types.NVARCHAR == fieldType
				|| Types.CHAR == fieldType)
			return "'" + value + "'";
		else if (Types.NVARCHAR == fieldType || Types.LONGVARCHAR == fieldType
				|| Types.CLOB == fieldType)
			return "'" + value + "'";
		return "" + value;
	}

	@Override
	public boolean dbExport(String[] tables, String xml) {
		DBExporter dbExporter = new DBExporter();
		if (dbExporter.createPorter(this, conn)) {
			boolean result = dbExporter.doExport(prefix, tables, xml);
			return result & dbExporter.closePorter();
		}
		return false;
	}

	@Override
	public String dbExport(String[] tables, boolean dataOnly) {
		DBExporter dbExporter = new DBExporter();
		if (dbExporter.createPorter(this, conn)) {
			String result = dbExporter.doExport(prefix, tables, dataOnly);
			if (!dbExporter.closePorter())
				result = null;
			return result;
		}
		return null;
	}

	@Override
	public boolean dbImport(String xml) {
		initFunctions();
		DBImporter dbImporter = new DBImporter();
		if (dbImporter.createPorter(this, conn)) {
			boolean result = dbImporter.doImport(xml);
			return result & dbImporter.closePorter();
		}
		return false;
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean setConnection(Connection conn) {
		// this.conn = conn;
		return true;
	}

	/**
	 * 循环执行多条使用分隔符隔开的sql语句
	 * 
	 * @param sql
	 * @return
	 */
	/**
	 * @param sql
	 * @return
	 */
	public boolean executeSqls(String sql) {
		String sqls[] = sql.split(DBSolution.EXECUTECHAR);
		for (int i = 0; i < sqls.length; i++) {
			try {
				if (!"".equals(sqls[i].trim())) {
					jdbcTemplate.execute(sqls[i]);
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
				System.out.println("出错语句：" + sqls[i]);
				return false;
			}
		}
		return true;
	}

	public String getInertSQL(Element action) {
		String sql = "insert into " + getTableName(action.elementText("table"))
				+ " (";
		sql = sql + action.elementText("fields") + ") values (";
		sql = sql + decode(action.elementText("values")) + ")" + EXECUTECHAR;

		return sql;
	}

	@Override
	public int deleteTable(String table) {
		if (executeSqls(getDeleteSQL(table)))
			return 1;
		return 0;
	}

	public abstract String getCreateSQL(Element action);

	public abstract String getDeleteSQL(String table);
}