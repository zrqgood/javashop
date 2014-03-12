package com.enation.app.base.core.service.solution.impl;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.enation.app.base.core.service.solution.ISetupLoader;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.util.FileUtil;

public class SetupLoaderImpl implements ISetupLoader {

	
	public Document load(String productId) {
		String xmlFile = EopSetting.PRODUCTS_STORAGE_PATH+"/"+productId +"/setup.xml"; 
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {
			if (FileUtil.exist(xmlFile)) {
				document = saxReader.read(new File(xmlFile));
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} 	
		return document;
		 
	}

}
