package com.enation.app.base.core.service.solution;

import org.w3c.dom.Document;


public interface IAdminThemeInfoFileLoader {
	
	/**
	 * 根据产品唯一标识（非数据库索引 ）加载产品包下的profile.xml并返回其document对象
	 * @param productName
	 * @return
	 */
	public Document load(String productId, String path, Boolean isCommonTheme);
	
}
