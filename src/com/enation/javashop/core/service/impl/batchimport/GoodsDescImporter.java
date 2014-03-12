package com.enation.javashop.core.service.impl.batchimport;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.FileUtil;
import com.enation.javashop.core.model.ImportDataSource;
import com.enation.javashop.core.service.batchimport.IGoodsDataImporter;
import com.enation.javashop.core.service.impl.batchimport.util.GoodsDescReader;

/**
 * 商品描述导入器
 * @author kingapex
 *
 */
public class GoodsDescImporter implements IGoodsDataImporter {
	protected final Logger logger = Logger.getLogger(getClass());
	private IDaoSupport daoSupport;
	private GoodsDescReader goodsDescReader;
	
	@Override
	public void imported(Object value, Element node, ImportDataSource importDs,
			Map goods) {
		
		Integer goodsid  = (Integer )goods.get("goods_id");
		
		
		if(this.logger.isDebugEnabled()){
			logger.debug("开始导入商品["+goodsid+"]描述...");
		}		
		
		String bodyhtml = goodsDescReader.read(importDs.getDatafolder(), goodsid);
	 
		if(bodyhtml!=null){
			this.daoSupport.execute("update es_goods set intro=? where goods_id=?", bodyhtml,goodsid);
		}
		 
		 
		if(this.logger.isDebugEnabled()){
			logger.debug("导入商品["+goodsid+"]描述 完成");
		}		
		
	}
	
	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}
	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

 	
	public GoodsDescReader getGoodsDescReader() {
		return goodsDescReader;
	}

	public void setGoodsDescReader(GoodsDescReader goodsDescReader) {
		this.goodsDescReader = goodsDescReader;
	}

	public static void main(String[] args){
		System.out.println(FileUtil.read("D:/goodsimport/goods/彩片/3/desc.htm", "GBK"));
	}

}
