package com.enation.javashop.core.service.impl.batchimport;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.enation.javashop.core.model.Brand;
import com.enation.javashop.core.model.ImportDataSource;
import com.enation.javashop.core.service.batchimport.IGoodsDataImporter;

/**
 * 商品品牌导入器
 * @author kingapex
 *
 */
public class GoodsBrandImporter implements IGoodsDataImporter {
 
	public void imported(Object name,Element node,ImportDataSource importDs,Map goods){
		
		if(!importDs.isNewGoods())return;
		
		String brandname = (String)name;
		if(brandname==null) brandname="";
		brandname=brandname.trim();
		
		List<Brand> brandList  = importDs.getBrandList();
		for(Brand brand:brandList){
			if(brand.getName().equals(brandname)){
				goods.put("brand_id", brand.getBrand_id());
			}
		}
	}

}
