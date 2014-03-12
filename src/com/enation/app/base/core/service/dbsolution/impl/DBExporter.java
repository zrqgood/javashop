package com.enation.app.base.core.service.dbsolution.impl;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库导出类
 * 
 * @author liuzy
 * 
 */
public class DBExporter extends DBPorter {
	private String prefix = "";
	private String beginLine = "\t";
	private String endLine = "\n";

	/**
	 * 返回count个beginLine内容，用于格式化输出行
	 * 
	 * @param count
	 * @return
	 */
	private String beginLine(int count) {
		String result = "";
		for (int i = 0; i < count; i++) {
			result = result + beginLine;
		}
		return result;
	}

	/**
	 * 返回字符选项，使用一位的0或1字符标记状态
	 * 
	 * @param rsmd
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	protected String getFieldOption(ResultSetMetaData rsmd, int index)
			throws SQLException {
		String auto = "0";
		String nullable = "0";

		if (rsmd.isAutoIncrement(index))
			auto = "1";
		if (rsmd.isNullable(index) == ResultSetMetaData.columnNoNulls)
			nullable = "1";

		return auto + nullable;
	}

	/**
	 * 创建一个action节点，同时加入command和table节点
	 * 
	 * @param table
	 * @param xmlFile
	 * @param command
	 */
	private void createAction(String table, StringBuilder xmlFile,
			String command) {
		xmlFile.append(beginLine + "<action>" + endLine);
		xmlFile.append(beginLine(2) + "<command>" + command + "</command>"
				+ endLine);
		xmlFile.append(beginLine(2) + "<table>" + table + "</table>" + endLine);
	}

	/**
	 * 输出一个xml格式的action节点，创建一个名为table的表
	 * 
	 * @param table
	 * @param xmlFile
	 * @return
	 */
	private boolean createTableXml(String table, StringBuilder xmlFile) {
		createAction(table, xmlFile, "create");

		try {
			createFieldXml(table, xmlFile);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		xmlFile.append(beginLine + "</action>" + endLine);
		return true;
	}

	/**
	 * 返回实际字段类型的中间类型名称
	 * 
	 * @param type
	 * @return
	 */
	protected String getFieldTypeName(int type) {
		String result = "";
		switch (type) {
		case Types.INTEGER:
		case Types.SMALLINT:
			result = "int";
			break;
		case Types.VARCHAR:
			result = "varchar";
			break;
		case Types.LONGNVARCHAR:
		case Types.LONGVARCHAR:
			result = "memo";
			break;
		case Types.BIGINT:
			result = "long";
			break;
		case Types.DECIMAL:
			result = "decimal";
			break;
		case Types.DATE:
			result = "date";
			break;
		case Types.TIMESTAMP:
			result = "datetime";
			break;
		default:
			result = "varchar";
		}
		return result;
	}

	/**
	 * 输出xml格式的table中字段列表
	 * 
	 * @param table
	 * @param xmlFile
	 * @throws SQLException
	 */
	private void createFieldXml(String table, StringBuilder xmlFile)
			throws SQLException {
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from " + prefix + table);

		DatabaseMetaData metaData = conn.getMetaData();
		Map<String, String> columns = new HashMap<String, String>();
		ResultSet mdrs = metaData.getColumns(null, null, table.toUpperCase(),
				"%");

		// 获取字段默认值
		while (mdrs.next())
			columns.put(mdrs.getString("COLUMN_NAME"),
					mdrs.getString("COLUMN_DEF"));

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			String columnName = rsmd.getColumnName(i);

			xmlFile.append(beginLine(2) + "<field>");

			xmlFile.append("<name>" + rsmd.getColumnName(i) + "</name>");
			xmlFile.append("<type>" + getFieldTypeName(rsmd.getColumnType(i))
					+ "</type>");
			xmlFile.append("<size>" + rsmd.getPrecision(i) + "</size>");
			xmlFile.append("<option>" + getFieldOption(rsmd, i) + "</option>");

			if (columns.get(columnName) != null)
				xmlFile.append("<default>"
						+ solution.getFieldValue(rsmd.getColumnType(i),
								columns.get(columnName)) + "</default>");

			xmlFile.append("</field>" + endLine);
		}
	}

	/**
	 * 保存xml格式的text字符串到文件中
	 * 
	 * @param xml
	 * @param text
	 * @return
	 */
	private boolean saveDocument(String xml, String text) {
		try {
			FileWriter file = new FileWriter(xml);
			file.write(text);
			file.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 输出一个xml格式的action节点，插入所有数据到table表中
	 * 
	 * @param table
	 * @param xmlFile
	 * @return
	 */
	private boolean insertDataXml(String table, StringBuilder xmlFile) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from " + prefix + table);
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				String fields = "";
				String values = "";
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					Object value = rs.getObject(i);
					if (value != null) {
						fields = fields + rsmd.getColumnName(i) + ",";
						values = values
								+ solution.getFieldValue(rsmd.getColumnType(i),
										value) + ",";
					}
				}
				createAction(table, xmlFile, "insert");
				xmlFile.append(beginLine(2) + "<fields>"
						+ fields.substring(0, fields.length() - 1)
						+ "</fields>" + endLine);
				xmlFile.append(beginLine(2)
						+ "<values>"
						+ solution.encode(values.substring(0,
								values.length() - 1)) + "</values>" + endLine);
				xmlFile.append(beginLine + "</action>" + endLine);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 生成xml格式文本并返回，当dataOnly为true时，仅导出insert语句
	 * 
	 * @param prefix
	 * @param tables
	 * @param dataOnly
	 * @return
	 */
	public String doExport(String prefix, String[] tables, boolean dataOnly) {
		StringBuilder xml = new StringBuilder();

		if (!dataOnly) {
			for (int i = 0; i < tables.length; i++)
				createTableXml(prefix + tables[i], xml);
		}

		for (int i = 0; i < tables.length; i++)
			insertDataXml(prefix + tables[i], xml);

		return xml.toString();
	}

	/**
	 * 导出表到xml文件中
	 * 
	 * @param prefix
	 * @param tables
	 * @param xml
	 * @return
	 */
	public boolean doExport(String prefix, String[] tables, String xml) {
		this.prefix = prefix;

		StringBuilder xmlFile = new StringBuilder();
		xmlFile.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + endLine);
		xmlFile.append("<dbsolution>" + endLine);
		xmlFile.append(doExport(prefix, tables, false));
		xmlFile.append("</dbsolution>" + endLine);

		return saveDocument(xml, xmlFile.toString());
	}
}
