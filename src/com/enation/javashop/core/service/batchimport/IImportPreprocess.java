package com.enation.javashop.core.service.batchimport;

import org.apache.poi.ss.usermodel.Sheet;
import org.w3c.dom.Node;

/**
 * 导入预处理接口
 * @author kingapex
 *
 */
public interface IImportPreprocess {
	
	/**
	 * 在执行导入前进行预处理
	 * @param sheet 当前数据的excel的sheet
	 * @param catNode 配置文件中的cat节点
	 */
	public void preprocess(Sheet sheet,Node catNode );
	
	
}
