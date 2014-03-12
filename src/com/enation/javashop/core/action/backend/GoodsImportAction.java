package com.enation.javashop.core.action.backend;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.service.batchimport.IGoodsDataBatchManager;

/**
 * 商品导入
 * @author kingapex
 *
 */
public class GoodsImportAction extends WWAction {
	private IGoodsDataBatchManager goodsDataBatchManager ;
	private String path;
	public String execute(){
		
		return "input";
	}
	
	public String imported(){
		try{
			goodsDataBatchManager.batchImport(path);
			this.json="{result:1}";
		}catch(RuntimeException e){
			
			this.logger.error("商品导入",e);
			this.json="{result:0,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}

	public IGoodsDataBatchManager getGoodsDataBatchManager() {
		return goodsDataBatchManager;
	}

	public void setGoodsDataBatchManager(
			IGoodsDataBatchManager goodsDataBatchManager) {
		this.goodsDataBatchManager = goodsDataBatchManager;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
