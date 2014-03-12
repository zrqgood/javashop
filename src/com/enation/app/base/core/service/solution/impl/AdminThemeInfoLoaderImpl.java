package com.enation.app.base.core.service.solution.impl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.enation.app.base.core.service.solution.IAdminThemeInfoFileLoader;
import com.enation.eop.sdk.context.EopSetting;

public class AdminThemeInfoLoaderImpl implements IAdminThemeInfoFileLoader {

	
	public Document load(String productId, String path,
			Boolean isCommonTheme) {
		String xmlFile = null;
		if (isCommonTheme) {
			xmlFile = EopSetting.EOP_PATH + "/adminthemes/" + path + "/themeini.xml";
		} else {
			xmlFile = EopSetting.PRODUCTS_STORAGE_PATH+"/"+productId + "/adminthemes/" + path
					+ "/themeini.xml";//注意！现在资源ＳＤＫ未解决文件复制时的source目录问题
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			return document;
		} catch (Exception e) {
			throw new RuntimeException("load [" + productId + " , " + path + "] themeini error!FileName:"+xmlFile);
		}

	}

}
