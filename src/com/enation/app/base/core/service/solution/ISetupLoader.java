package com.enation.app.base.core.service.solution;

import org.dom4j.Document;

/**
 * 根据产品唯一标识（非数据库索引 ）加载产品包下的setup.xml并返回其document对象
 * @author lzf
 * 2011-1-10下午03:07:43
 * version 2.2.0
 */
public interface ISetupLoader {
	
	/**
	 * 根据产品唯一标识（非数据库索引 ）加载产品包下的setup.xml并返回其document对象
	 * @param productName
	 * @return
	 */
	public Document load(String productId);
	
}
