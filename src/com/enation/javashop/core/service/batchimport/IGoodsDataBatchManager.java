package com.enation.javashop.core.service.batchimport;

/**
 * 商品数据批量导入器接口
 * @author kingapex
 *
 */
public interface IGoodsDataBatchManager {
	
	/**
	 * 批量导入商品数据
	 * @param path
	 */
	public void batchImport(String path);
	
}
