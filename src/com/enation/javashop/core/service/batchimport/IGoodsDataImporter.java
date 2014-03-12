package com.enation.javashop.core.service.batchimport;

import java.util.Map;

import org.w3c.dom.Element;

import com.enation.javashop.core.model.ImportDataSource;


/**
 * 商品数据导入器接口
 * @author kingapex
 *
 */
public interface IGoodsDataImporter {
	
	/**
	 * 导入
	 * @param node
	 */
	public void imported(Object value,Element node,ImportDataSource importDs,Map goods);
	
	
}
