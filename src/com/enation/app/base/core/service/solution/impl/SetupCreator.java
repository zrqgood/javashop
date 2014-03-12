package com.enation.app.base.core.service.solution.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.enation.app.base.core.service.solution.ISetupCreator;
import com.enation.framework.util.FileUtil;

public class SetupCreator implements ISetupCreator {

	public void addTable(Document document, String target, String table) {
		document.getRootElement().element(target).addElement("table").setText(
				table);

	}

	public Document createSetup(String path) {
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {
			if (FileUtil.exist(path)) {
				document = saxReader.read(new File(path));
			}

		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		if (null == document) {
			String docStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
			docStr += "<setup><clean/><recreate/></setup>";
			try {
				document = DocumentHelper.parseText(docStr);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			this.save(document, path);
		}
		return document;
	}

	public void save(Document document, String path) {
		try {
			XMLWriter output = new XMLWriter(new FileWriter(new File(path)));
			output.write(document);
			output.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
