package com.enation.javashop.core.service.impl.batchimport;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.resources.Files;
import org.w3c.dom.Element;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.image.IThumbnailCreator;
import com.enation.framework.image.ThumbnailCreatorFactory;
import com.enation.framework.util.FileUtil;
import com.enation.javashop.core.model.ImportDataSource;
import com.enation.javashop.core.service.IGoodsAlbumManager;
import com.enation.javashop.core.service.batchimport.IGoodsDataImporter;
import com.enation.javashop.core.service.impl.batchimport.util.GoodsImageReader;

/**
 * 商品图片导入器
 * @author kingapex
 *
 */
public class GoodsImageImporter implements IGoodsDataImporter {
	protected final Logger logger = Logger.getLogger(getClass());
	private IDaoSupport daoSupport;
	private GoodsImageReader goodsImageReader;
 
	public void imported(Object value, Element node, ImportDataSource importDs,
			Map goods) {
		Integer goodsid  = (Integer)goods.get("goods_id");
		String excludeStr = node.getAttribute("exclude");
		
		if(this.logger.isDebugEnabled()){
			logger.debug("开始导入商品["+goodsid+"]图片...");
		}
		
		String[] images= goodsImageReader.read(importDs.getDatafolder(), goodsid, excludeStr);
		if(images!=null)
		this.daoSupport.execute("update es_goods set image_file=? ,image_default=? where goods_id=?", images[0],images[1],goodsid);
		
		if(this.logger.isDebugEnabled()){
			logger.debug(" 商品["+goodsid+"]图片导入完成");
		}
	}

	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	public GoodsImageReader getGoodsImageReader() {
		return goodsImageReader;
	}

	public void setGoodsImageReader(GoodsImageReader goodsImageReader) {
		this.goodsImageReader = goodsImageReader;
	}
	
	 
}
