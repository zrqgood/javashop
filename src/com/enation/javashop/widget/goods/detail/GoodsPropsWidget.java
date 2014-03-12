package com.enation.javashop.widget.goods.detail;

import java.util.List;
import java.util.Map;

import com.enation.eop.processor.core.freemarker.FreeMarkerPaser;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.core.service.ILimitBuyManager;
import com.enation.javashop.widget.goods.AbstractGoodsDetailWidget;

/**
 * shopex式的商品属性信息展示挂件
 * @author kingapex
 * 2010-2-3下午06:25:29
 */
public class GoodsPropsWidget extends AbstractGoodsDetailWidget  {
	
	private IGoodsTypeManager goodsTypeManager;
	private ILimitBuyManager limitBuyManager;
	
	public void execute(Map<String, String> params,Map goods) {
		Integer type_id =(Integer)goods.get("type_id");
		if(type_id==null) {this.showHtml =false;return ;} //没有定义类型，忽略此挂件
		
		List<Attribute> attrList =goodsTypeManager.getAttrListByTypeId(type_id);
		
		int i=1;
		for(Attribute attr: attrList){
			String value =""+goods.get("p"+i);
			attr.setValue(value);
			goods.put("p"+i+"_text", attr.getValStr()); //为商品取出属性的字符值
			i++;
		}
		
		/**
		 * 计算限时抢购价格
		 */
		List<Map> goodsList  = this.limitBuyManager.listEnableGoods();
		
		for(Map limitgoods : goodsList){
			Integer goodsid =(Integer)limitgoods.get("goods_id");
			if( goodsid.compareTo((Integer) goods.get("goods_id") )==0 ){
				this.putData("limitprice", limitgoods.get("limitprice"));
			}
		}
		
		
		
		//设置页名称为 base
		setPageName("props");
		//压入商品基本信息
		putData("goods", goods);
		putData("attrList",attrList);
		 
	}
 
	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}

	
	protected void config(Map<String, String> params) {
		
	}

	public ILimitBuyManager getLimitBuyManager() {
		return limitBuyManager;
	}

	public void setLimitBuyManager(ILimitBuyManager limitBuyManager) {
		this.limitBuyManager = limitBuyManager;
	}

 
	
	

	 
 

}
