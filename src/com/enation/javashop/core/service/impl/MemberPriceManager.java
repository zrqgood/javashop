package com.enation.javashop.core.service.impl;

import java.util.List;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.javashop.core.model.GoodsLvPrice;
import com.enation.javashop.core.service.IMemberPriceManager;


/**
 * 会员价格管理
 * @author kingapex
 *
 */
public class MemberPriceManager extends BaseSupport<GoodsLvPrice> implements IMemberPriceManager {

	
	/**
	 * 读取某个商品的所有规格的会员价格。
	 * @param goodsid
	 * @return
	 */
	public List<GoodsLvPrice> listPriceByGid(int goodsid) {
		String sql  ="select * from goods_lv_price where goodsid =?";
		return this.baseDaoSupport.queryForList(sql, GoodsLvPrice.class, goodsid);
	}

	/**
	 * 读取某会员级别的商品价格
	 * @param lvid
	 * @return
	 */
	public List<GoodsLvPrice> listPriceByLvid(int lvid) {
		String sql  ="select * from goods_lv_price where lvid =?";
		return this.baseDaoSupport.queryForList(sql, GoodsLvPrice.class, lvid);
	}

	
	/**
	 * 添加会价格
	 */
	public void save(List<GoodsLvPrice> goodsPriceList) {
		
		if(goodsPriceList!=null && goodsPriceList.size()>0){
			this.baseDaoSupport.execute("delete from  goods_lv_price  where goodsid=?", goodsPriceList.get(0).getGoodsid());
		 
		 for(GoodsLvPrice goodsPrice:goodsPriceList){
			 this.baseDaoSupport.insert("goods_lv_price", goodsPrice);
		 }
		}
		
	}
	
	
	

}
