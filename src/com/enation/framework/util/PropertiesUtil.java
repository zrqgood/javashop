package com.enation.framework.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;



/**
 * 属性文件工具类
 * @author kingapex
 * 2010-1-6下午02:12:11
 */
public class PropertiesUtil {

	private PropertiesUtil(){}
	
	private HashMap propertiesMap;

	public PropertiesUtil(String filePath) {
		InputStream in  = FileUtil.getResourceAsStream(filePath);
		load(in);
	}

	public void load(InputStream in) {
		try {
			Properties props = new Properties();
			propertiesMap = new HashMap();
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				propertiesMap.put(key, Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Key获取属性值
	 * 
	 * @param key
	 * @return
	 */
	public String getProperties(String key) {
		Object value = propertiesMap.get(key);
		if (value != null) {
			return value.toString();
		}
		return null;
	}

	/**
	 * 获取存放键和值的map
	 * 
	 * @return
	 */
	public HashMap getPropertiesMap() {
		return propertiesMap;
	}

	/**
	 * 测试部分
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropertiesUtil pu = new PropertiesUtil(
				"E:\\ProductSpace\\EOA\\resources\\config\\info.properties");

	}

}
