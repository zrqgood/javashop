package com.enation.javashop.widget.goods.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberLv;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.StringMapper;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.GoodsLvPrice;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.mapper.GoodsListMapper;
import com.enation.javashop.core.model.support.GoodsView;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberPriceManager;
import com.enation.javashop.core.service.IPromotionManager;
 

public class GoodsDataProvider extends BaseSupport  {
	private IMemberPriceManager memberPriceManager;
	private IMemberLvManager memberLvManager;
	
	private IPromotionManager promotionManager;

	private IGoodsCatManager goodsCatManager;
	
	private static Map<Integer,String> orderMap;
	static{
		orderMap = new HashMap<Integer, String>();
		orderMap.put(0, " goods_id desc");
		orderMap.put(1, " last_modify desc");
		orderMap.put(2, " last_modify asc");
		orderMap.put(3, " price desc");
		orderMap.put(4, " view_count desc");
		orderMap.put(5, " buy_count desc");
	}
	

	
	public List list(Term term ,int num){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from goods where disabled=0 and market_enable=1");
		String tagids  = term.getTagid();
		String brandids = term.getBrandid();
		String catids = term.getCatid();
		Integer typeid = term.getTypeid();
		String  keyword = term.getKeyword();
		
		if(!StringUtil.isEmpty(keyword)){
			sql.append(" and (name like '%"+keyword+"%' or brief like '%"+keyword+"%' or intro like '%"+keyword+"%'   )");
		}
		
		
		if(typeid!=null ){
			sql.append(" and type_id ="+typeid);		
		}
		
		if(!StringUtil.isEmpty(brandids)){
			sql.append(" and brand_id in("+brandids+")");		
		}
		 
		if(!StringUtil.isEmpty(catids)){//lzf modified 20101210
			String str_catids = "0";
			String[] ids = catids.split(",");
			for(String cat_id:ids){
				Cat cat = this.goodsCatManager.getById(Integer.valueOf(cat_id));
				List<String> catIdList = this.baseDaoSupport.queryForList("select cat_id from goods_cat where cat_path like '" + cat.getCat_path() + "%'", new StringMapper());
				str_catids += "," + StringUtil.listToString(catIdList,
						",");
			}
			sql.append(" and cat_id in("+str_catids+")");		
		}		
		
 
		if(!StringUtil.isEmpty(tagids)){
			String filter= this.goodsIdInTags(tagids);
			filter =filter.equals("")?"-1":filter;
			sql.append( " and goods_id in(" +filter+")" );
		}
		
		if( term.getMaxprice() !=null){
			sql.append(" and price<="+ term.getMaxprice());
		}
		
		if( term.getMinprice() !=null){
			sql.append(" and price>="+ term.getMinprice());
		}
		
		Integer order = term.getOrder();
		if(order==null){
			order = 1;
		}
		
		sql.append( " order by "+ orderMap.get(order));
		
		List list = this.baseDaoSupport.queryForList(sql.toString(), 1, num, new GoodsListMapper());
		if(list!=null  && !list.isEmpty()){
		  ((GoodsView)list.get(list.size()-1)).setIsLast(true) ;
		  ((GoodsView)list.get(0)).setIsFirst(true) ;
		}
		list = list == null ? new ArrayList() : list;
		
		
		/****************计算会员价格******************/
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		List<GoodsLvPrice> memPriceList = new ArrayList<GoodsLvPrice>();
		double discount =1; //默认是原价,防止无会员级别时出错
		if(member!=null && member.getLv_id()!=null){
			
			memPriceList  = this.memberPriceManager.listPriceByLvid(member.getLv_id());
			MemberLv lv =this.memberLvManager.get(member.getLv_id());
			discount = lv.getDiscount()/100.00;
			this.applyMemPrice(list, memPriceList, discount);
		}
		
		return list; 
	}
	
	
	/**
	 * 应用会员价
	 * @param itemList
	 * @param memPriceList
	 * @param discount
	 */
	private void applyMemPrice(List<GoodsView>  itemList,List<GoodsLvPrice> memPriceList,double discount ){
		for(GoodsView item : itemList ){
			double price  = item.getPrice() *  discount;
			for(GoodsLvPrice lvPrice:memPriceList){
				if( item.getHasSpec()==0 && item.getGoods_id().intValue() == lvPrice.getGoodsid() ){
					price = lvPrice.getPrice();
				} 
			}
			
			item.setPrice(price);
		}
	}
	
//	private void applyMemPromotionPrice(double price, Integer goodsid){
//		IUserService userService = UserServiceFactory.getUserService();
//		Member member = userService.getCurrentMember();
//		Integer memberLvid = member.getLv_id();
//		List<Promotion> list = promotionManager.list(goodsid, memberLvid);
//		if((list!=null)&&list.size()>0){
//			Promotion pmn = list.get(0);
//			pmn.get
//		}
//		
//	}
	
	
	/**
	 * 读取在tags范围的goodsid字串，以,号分隔
	 * @param tags
	 * @return 如果没有找到返回 ""
	 */
	private String goodsIdInTags(String tags){
		String sql ="select rel_id from tag_rel where tag_id in (" +tags+")";
		List<String> goodsIdList = this.baseDaoSupport.queryForList(sql, new StringMapper());
		return StringUtil.listToString(goodsIdList, ",");
	}
 

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}
 

	public IMemberPriceManager getMemberPriceManager() {
		return memberPriceManager;
	}

	public void setMemberPriceManager(IMemberPriceManager memberPriceManager) {
		this.memberPriceManager = memberPriceManager;
	}

	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}

	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}


	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}


	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}
	
}
