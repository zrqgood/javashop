package com.enation.app.base.core.service.solution;


import org.dom4j.Document;
import org.dom4j.Element;


/**
 * 生成setup.xml
 * @author lzf
 *
 */
public interface ISetupCreator {
	
	/**
	 * 加入table列表
	 * @param document
	 * @param target
	 * @param table
	 */
	public void addTable(Document document, String target, String table);
	
	/**
	 * 创建文档
	 * @param path
	 * @return
	 */
	public Document createSetup(String path);
	
	/**
	 * 存为文件
	 * @param document
	 * @param path
	 */
	public void save(Document  document,String path);

}
