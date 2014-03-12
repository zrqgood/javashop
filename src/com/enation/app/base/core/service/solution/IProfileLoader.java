package com.enation.app.base.core.service.solution;

import org.w3c.dom.Document;

/**
 * 配置文件加载
 * @author kingapex
 * 2010-1-20下午03:16:11
 */
public interface IProfileLoader {
	
	/**
	 * 根据产品唯一标识（非数据库索引 ）加载产品包下的profile.xml并返回其document对象
	 * @param productName
	 * @return
	 */
	public Document load(String productId);
	
}
