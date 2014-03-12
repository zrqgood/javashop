package com.enation.app.base.core.service.dbsolution.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.enation.framework.util.FileUtil;

/**
 * 数据库导入类
 * 
 * @author liuzy
 * 
 */
public class DBImporter extends DBPorter {
	private Document xmlDoc;

	/**
	 * 加载xml文件
	 * 
	 * @param xmlFile
	 * @return
	 */
	private Document loadDocument(String xmlFile) throws DocumentException {
		Document document = null;
		SAXReader saxReader = new SAXReader();
		File file = new File(xmlFile);
		if (file.exists())
			document = saxReader.read(new File(xmlFile));
		return document;
	}

	private List<Object> prepareValue(String values) {
		List<Object> objects = new ArrayList<Object>();
		String[] value = values.split(",");
		for (int i = 0; i < value.length; i++) {
			objects.add(solution.getFuncValue(solution.decodeValue(value[i]
					.replaceAll("'", ""))));
		}

		return objects;
	}

	private boolean doInsert(Element action) {
		String table = solution.getTableName(action.elementText("table"));
		String fields = action.elementText("fields");
		String values = action.elementText("values");

		List<Object> objects = prepareValue(values);

		String sql = "insert into " + table + " (" + fields + ") values (";
		String[] value = values.split(",");
		for (int i = 0; i < value.length; i++)
			sql = sql + "?,";

		sql = sql.substring(0, sql.length() - 1) + ")";

		try {
			if (solution.beforeInsert(table, fields, values)) {
				solution.jdbcTemplate.update(sql,objects.toArray());
				solution.afterInsert(table, fields, values);
			} else {
				System.out.println("beforeInsert返回false，insert被阻止！");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错语句：" + sql);
			System.out.println("出错值：" + values);
			return false;
		}
		return true;
	}

	private boolean doCreate(Element action) {
		String sql = solution.getCreateSQL(action);

		return solution.executeSqls(sql);
	}

	/**
	 * 执行action内容
	 * 
	 * @param action
	 * @return
	 */
	private boolean doAction(Element action) {
		String command = action.elementText("command").toLowerCase();
		if ("create".equals(command)) {
			return doCreate(action);
		} else if ("insert".equals(command)) {
			return doInsert(action);
		}
		return true;
	}

	/**
	 * 导入一个xml文件到数据库中
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean doImport(String xml) {
		solution.beforeImport();
		try {
			if (xml.startsWith("file:")) {
				xml = FileUtil.readFile(xml.replaceAll("file:", ""));
				xmlDoc = DocumentHelper.parseText(xml);
			} else {
				xmlDoc = loadDocument(xml);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
		List<Element> actions = xmlDoc.getRootElement().elements("action");
		for (Element action : actions) {
			if (!doAction(action))
				return false;
		}
		solution.afterImport();
		return true;
	}

	public static void main(String[] argv) {
		String values = "63,0,time(1284926761000),12,'便民消费卡的续费指南','','&lt;p&gt;	便民消费卡的续费指南内容&lt;/p&gt;',0,time(1320668326000),'','','',100000,''";
		List<Object> objects = new ArrayList<Object>();
		String[] value = values.split(",");
		for (int i = 0; i < value.length; i++) {
			objects.add(value[i]);
			System.out.println(objects.get(i));
		}
	}
}
