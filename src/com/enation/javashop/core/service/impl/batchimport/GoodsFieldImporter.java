package com.enation.javashop.core.service.impl.batchimport;

import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.enation.javashop.core.model.ImportDataSource;
import com.enation.javashop.core.service.batchimport.IExcelDataEnable;
import com.enation.javashop.core.service.batchimport.IGoodsDataImporter;

/**
 * 商品字段导入器
 * @author kingapex
 *
 */
public class GoodsFieldImporter implements IGoodsDataImporter{

	
	public void imported(Object value,Element node, ImportDataSource importConfig,Map goods) {
		String fieldname = node.getAttribute("fieldname");
		if(importConfig.isNewGoods())
			goods.put(fieldname, value);
	}
 

	
	
}
