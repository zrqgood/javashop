package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.IntegerMapper;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.plugin.IPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Coupons;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.model.support.DiscountPrice;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;
import com.enation.javashop.core.plugin.promotion.PromotionPluginBundle;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.core.service.promotion.IDiscountBehavior;
import com.enation.javashop.core.service.promotion.IGiveGiftBehavior;
import com.enation.javashop.core.service.promotion.IPromotionMethod;
import com.enation.javashop.core.service.promotion.IReduceFreightBehavior;
import com.enation.javashop.core.service.promotion.IReducePriceBehavior;
import com.enation.javashop.core.service.promotion.ITimesPointBehavior;


/**
 * 优惠规则管理
 * @author kingapex
 *2010-4-15下午05:14:57
 */

public class PromotionManager extends BaseSupport implements IPromotionManager {
	private PromotionPluginBundle promotionPluginBundle ;
	@Transactional(propagation = Propagation.REQUIRED)
	
	public Integer add(Promotion promotion, Integer[] memberLvIdArray,
			Integer[] goodsIdArray) {
		
		if(promotion== null ) throw new  IllegalArgumentException("param promotion is NULL");
		if(promotion.getPmt_describe()== null ) throw new  IllegalArgumentException("param promotion 's Pmt_describe is NULL");
		if(promotion.getPmts_id()== null ) throw new  IllegalArgumentException("param promotion 's Pmts_id is NULL");
		if(promotion.getPmt_time_begin()== null ) throw new  IllegalArgumentException("param promotion 's pmt_time_begin is NULL");
		if(promotion.getPmt_time_end()== null ) throw new  IllegalArgumentException("param promotion 's Pmt_time_end is NULL");
	//	if(promotion.getPmt_basic_type()== null ) throw new  IllegalArgumentException("param promotion 's Pmt_basic_type is NULL");
		
		
		promotion.setPmt_update_time(new java.util.Date().getTime());
		promotion.setDisabled("false");
		IPlugin plugin = this.getPlugin(promotion.getPmts_id());
		promotion.setPmt_basic_type(plugin.getType());
		
		this.baseDaoSupport.insert("promotion", promotion);
		Integer pmtid = this.baseDaoSupport.getLastId("promotion");
		promotion.setPmt_id(pmtid); 
		

		//保存会员级别关联
		if(memberLvIdArray!=null){
			this.saveMemberLv(pmtid, memberLvIdArray);
		}
		
		if(goodsIdArray!=null){
			this.saveGoods(pmtid, goodsIdArray);
		}
		
		this.pluginSave(promotion);
		return pmtid;
	}
	
	public Integer add(Promotion promotion,Integer[] memberLvIdArray, int type, Integer[] goodsIdArray, Integer[] goodsCatIdArray, Integer[] tagIdArray) {
		Integer result = null;
		if(type==1){
			result = this.add(promotion, memberLvIdArray, goodsIdArray);
		}
		if(type==2){}
		return result;
	}
	
	
	public void edit(Promotion promotion, Integer[] memberLvIdArray, Integer[] goodsIdArray) {
		
		if(promotion== null ) throw new  IllegalArgumentException("param promotion is NULL");
		if(promotion.getPmt_describe()== null ) throw new  IllegalArgumentException("param promotion 's Pmt_describe is NULL");
		if(promotion.getPmts_id()== null ) throw new  IllegalArgumentException("param promotion 's Pmts_id is NULL");
		if(promotion.getPmt_time_begin()== null ) throw new  IllegalArgumentException("param promotion 's pmt_time_begin is NULL");
		if(promotion.getPmt_time_end()== null ) throw new  IllegalArgumentException("param promotion 's Pmt_time_end is NULL");
		
		promotion.setPmt_update_time(new java.util.Date().getTime());
		promotion.setDisabled("false");
		IPlugin plugin = this.getPlugin(promotion.getPmts_id());
		promotion.setPmt_basic_type(plugin.getType());
		Integer pmtid = promotion.getPmt_id();
		this.baseDaoSupport.update("promotion", promotion,"pmt_id="+pmtid);
		promotion.setPmt_id(pmtid); 
		

		//保存会员级别关联
		if(memberLvIdArray!=null){
			this.baseDaoSupport.execute("delete from pmt_member_lv where pmt_id=?",pmtid);
			this.saveMemberLv(pmtid, memberLvIdArray);
		}
		
		if(goodsIdArray!=null){
			this.baseDaoSupport.execute("delete from pmt_goods where pmt_id=?",pmtid);
			this.saveGoods(pmtid, goodsIdArray);
		}
		
		this.pluginSave(promotion);
		
	}
	
	
	
	public List<Promotion> list(Integer goodsid, Integer memberLvId) {
		if(goodsid== null ) throw new  IllegalArgumentException("goodsid is NULL");
		if(memberLvId== null ) throw new  IllegalArgumentException("memberLvId is NULL");
		long now  = System.currentTimeMillis();
		String sql  ="select * from  "+ this.getTableName("promotion") +" where pmt_basic_type='goods'" +
				" and  pmt_time_begin<"+now+" and  pmt_time_end>" +now + 
				" and pmt_id in  (select pmt_id from "+this.getTableName("pmt_goods")+" where goods_id=?)" +
				" and pmt_id in (select pmt_id from "+this.getTableName("pmt_member_lv")+" where lv_id =? )"
				+" and pmt_type='0' ";
		
		//如果会员使作了优惠卷，将优惠卷对应的促销规则加入
		Coupons coupon = (Coupons)ThreadContextHolder.getSessionContext().getAttribute("coupon");
		if(coupon!=null){
			int  coup_pmtid = coupon.getPmt_id();
			sql+=" or (pmt_id="+coup_pmtid+" and pmt_basic_type='goods')";
		}
 
		return this.daoSupport.queryForList(sql, Promotion.class, goodsid,memberLvId);
	}

	
	
	
	
	public List<Promotion> list(Double orderPrice, Integer memberLvId) {
		long now  = System.currentTimeMillis();
		
		
		String sql  ="select * from  "+ this.getTableName("promotion") +" where pmt_basic_type='order' " +
		" and pmt_time_begin<"+now+" and  pmt_time_end>" +now + 
		" and order_money_from<=? and order_money_to>=?"+
		" and pmt_id in (select pmt_id from "+this.getTableName("pmt_member_lv")+" where lv_id =? ) "
		+" and pmt_type='0' ";
		
		//如果会员使作了优惠卷，将优惠卷对应的促销规则加入
		Coupons coupon = (Coupons)ThreadContextHolder.getSessionContext().getAttribute("coupon");
		if(coupon!=null){
			int  coup_pmtid = coupon.getPmt_id();
			sql+=" or ( pmt_id="+coup_pmtid +" and pmt_basic_type='order')";
		}
 
		return  this.daoSupport.queryForList(sql, Promotion.class, orderPrice,orderPrice,memberLvId);
	}
	
	private Integer getCoupPmtId(String coupcode){
		String sql  ="select  c.pmt_id from  " + this.getTableName("member_coupon") +" mc ,"+this.getTableName("coupons") +" c where mc.cpns_id = c.cpns_id and mc.memc_code=? ";
		return  this.daoSupport.queryForInt(sql, coupcode);
	}
	
	
 
	
	public void applyGoodsPmt(List<CartItem> list, Integer memberLvId) {
		
		if(list == null || memberLvId== null) return;
		
		for(CartItem item :list){
			
			Integer goodsid = item.getGoods_id();
			
			//查询此商品所享有的优惠规则
			List<Promotion> pmtList  = this.list(goodsid, memberLvId);
			item.setPmtList(pmtList);
			
			for(Promotion pmt:pmtList){
				
				//查找相应插件
				String pluginBeanId = pmt.getPmts_id();
				IPromotionPlugin plugin = this.getPlugin(pluginBeanId);
				
				
				if(plugin==null){ 
					logger.error("plugin["+pluginBeanId+"] not found ");
					throw new ObjectNotFoundException("plugin["+pluginBeanId+"] not found ");
				}
				
				//查找相应优惠方式
				String methodBeanName = plugin.getMethods();
				if(this.logger.isDebugEnabled()){
					this.logger.debug("find promotion method["+methodBeanName+"]");
				}		
			 	IPromotionMethod promotionMethod =  SpringContextHolder.getBean(methodBeanName);
			 	if(promotionMethod== null ){ 
			 		logger.error("plugin["+methodBeanName+"] not found ");
			 		throw new ObjectNotFoundException("promotion method["+methodBeanName+"] not found ");
			 	}
			 	 //打折方式
			 	if( promotionMethod instanceof IDiscountBehavior ){ 
			 		IDiscountBehavior discountBehavior = (IDiscountBehavior)promotionMethod;
			 		Double newPrice  = discountBehavior.discount(pmt, item.getCoupPrice());
			 		item.setCoupPrice(newPrice);
			 		
			 	}
			 	
			 	//翻倍积分方式
			 	if(promotionMethod instanceof ITimesPointBehavior){
			 		Integer point  = item.getPoint();
			 		ITimesPointBehavior timesPointBehavior = (ITimesPointBehavior)promotionMethod;
			 		point  = timesPointBehavior.countPoint(pmt, point);
			 		item.setPoint(point);
			 	}
			 	
			}
			
			
		}			
	}
	


	
	public DiscountPrice applyOrderPmt(Double orderPrice,Double shipFee,Integer point, Integer memberLvId) {
		List<Promotion >  pmtList = this.list(orderPrice, memberLvId);
		for(Promotion pmt:pmtList){
			
			//查找相应插件
			String pluginBeanId = pmt.getPmts_id();
			IPromotionPlugin plugin = this.getPlugin(pluginBeanId);
			if(plugin==null){ 
				logger.error("plugin["+pluginBeanId+"] not found ");
				throw new ObjectNotFoundException("plugin["+pluginBeanId+"] not found ");
			}
			
			//查找相应优惠方式
			String methodBeanName = plugin.getMethods();
			if(this.logger.isDebugEnabled()){
				this.logger.debug("find promotion method["+methodBeanName+"]");
			}		
		 	IPromotionMethod promotionMethod =  SpringContextHolder.getBean(methodBeanName);
		 	if(promotionMethod== null ){ 
		 		logger.error("plugin["+methodBeanName+"] not found ");
		 		throw new ObjectNotFoundException("promotion method["+methodBeanName+"] not found ");
		 	}
		 	
		 	//减价方式
			if( promotionMethod instanceof IReducePriceBehavior ){
				IReducePriceBehavior  reducePriceBehavior = (IReducePriceBehavior)promotionMethod;
				orderPrice  =reducePriceBehavior.reducedPrice(pmt,orderPrice);
			}
			
		 	//打折方式
			if( promotionMethod instanceof IDiscountBehavior ){
				IDiscountBehavior discountBehavior = (IDiscountBehavior)promotionMethod;
				orderPrice = discountBehavior.discount(pmt, orderPrice);
			}			
			
			//免运费方式
			if( promotionMethod instanceof IReduceFreightBehavior ){
				IReduceFreightBehavior  reduceFreightBehavior = (IReduceFreightBehavior)promotionMethod;
				shipFee = reduceFreightBehavior.reducedPrice(shipFee);
			}
			
			//翻倍积分方式
			if( promotionMethod instanceof ITimesPointBehavior ){
				ITimesPointBehavior  timesPointBehavior = (ITimesPointBehavior)promotionMethod;
				point  =timesPointBehavior.countPoint(pmt, point);
			}			
			
			
		}		
		DiscountPrice discountPrice = new DiscountPrice();
		discountPrice.setOrderPrice(orderPrice);
		discountPrice.setShipFee(shipFee);
		discountPrice.setPoint(point);
		return discountPrice;
	}
	

	
	public void applyOrderPmt(Integer orderId,Double orderPrice, Integer memberLvId) {
		List<Promotion >  pmtList = this.list(orderPrice, memberLvId);
		for(Promotion pmt:pmtList){
			
			//查找相应插件
			String pluginBeanId = pmt.getPmts_id();
			IPromotionPlugin plugin = this.getPlugin(pluginBeanId);
			if(plugin==null){ 
				logger.error("plugin["+pluginBeanId+"] not found ");
				throw new ObjectNotFoundException("plugin["+pluginBeanId+"] not found ");
			}
			
			//查找相应优惠方式
			String methodBeanName = plugin.getMethods();
			if(this.logger.isDebugEnabled()){
				this.logger.debug("find promotion method["+methodBeanName+"]");
			}		
		 	IPromotionMethod promotionMethod =  SpringContextHolder.getBean(methodBeanName);
		 	if(promotionMethod== null ){ 
		 		logger.error("plugin["+methodBeanName+"] not found ");
		 		throw new ObjectNotFoundException("promotion method["+methodBeanName+"] not found ");
		 	}
			
			//送赠品
			if( promotionMethod instanceof IGiveGiftBehavior ){
				IGiveGiftBehavior  giveGiftBehavior = (IGiveGiftBehavior)promotionMethod;
				giveGiftBehavior.giveGift(pmt, orderId);
			}			
			
		}			
	}
	
	
	
	public List listGift(List<Promotion> pmtList) {
		List giftList = new ArrayList();
		for(Promotion pmt:pmtList){
				
				//查找相应插件
				String pluginBeanId = pmt.getPmts_id();
				IPromotionPlugin plugin = this.getPlugin(pluginBeanId);
				if(plugin==null){ 
					logger.error("plugin["+pluginBeanId+"] not found ");
					throw new ObjectNotFoundException("plugin["+pluginBeanId+"] not found ");
				}
				
				//查找相应优惠方式
				String methodBeanName = plugin.getMethods();
				if(this.logger.isDebugEnabled()){
					this.logger.debug("find promotion method["+methodBeanName+"]");
				}		
			 	IPromotionMethod promotionMethod =  SpringContextHolder.getBean(methodBeanName);
			 	if(promotionMethod== null ){ 
			 		logger.error("plugin["+methodBeanName+"] not found ");
			 		throw new ObjectNotFoundException("promotion method["+methodBeanName+"] not found ");
			 	}
			 	
			 	if(promotionMethod instanceof  IGiveGiftBehavior){
			 		IGiveGiftBehavior giveGiftBehavior = (IGiveGiftBehavior)promotionMethod;
			 		List list = giveGiftBehavior.getGiftList(pmt);
			 		giftList.addAll(list);
			 		 
			 	}
		}
		
		return giftList;
	}

	

	/**
	 * 读取某活动的规则列表
	 */
	
	public List listByActivityId(Integer activityid) {
		String sql  ="select * from promotion where disabled='false' and pmta_id=?";
		return this.baseDaoSupport.queryForList(sql, Promotion.class, activityid);
	}
	
	
	
	/**
	 * 调用相应插件的保存（包括添加和修改）
	 * @param promotion
	 */
	private void pluginSave(Promotion promotion ){
		//寻找优惠规则插件，并做相应处理
		if(this.logger.isDebugEnabled()){
			this.logger.debug("find promotion plugin["+promotion.getPmts_id()+"]");
		}
		IPromotionPlugin plugin = this.getPlugin(promotion.getPmts_id());
		
		if(plugin==null){ 
			logger.error("plugin["+promotion.getPmts_id()+"] not found ");
			throw new ObjectNotFoundException("plugin["+promotion.getPmts_id()+"] not found ");
		}
		
		//寻找优惠方法，并执行其保存操作
		String methodBeanName = plugin.getMethods();
		if(this.logger.isDebugEnabled()){
			this.logger.debug("find promotion method["+methodBeanName+"]");
		}		
	 	IPromotionMethod promotionMethod =  SpringContextHolder.getBean(methodBeanName);
	 	if(promotionMethod== null ){ 
	 		logger.error("plugin["+methodBeanName+"] not found ");
	 		throw new ObjectNotFoundException("promotion method["+methodBeanName+"] not found ");
	 	}
	 	
	 	String  solution = promotionMethod.onPromotionSave(promotion.getPmt_id());
	 	this.baseDaoSupport.execute("update promotion set pmt_solution =? where pmt_id=?", solution,promotion.getPmt_id());
				
	}
	


	
	
	/**
	 * 保存会员级别关联
	 * @param pmtid
	 * @param memberLvIdArray
	 */
	private void  saveMemberLv(Integer pmtid, Integer[] memberLvIdArray){
 
		
		for(Integer memberLvId:memberLvIdArray){
			
			this.baseDaoSupport.execute("insert into pmt_member_lv(pmt_id,lv_id)values(?,?)", pmtid,memberLvId);
		}
	}
	
	
	/**
	 * 保存商品关联 
	 * @param pmtid
	 * @param goodsIdArray
	 */
	public void saveGoods(Integer pmtid,Integer[] goodsIdArray){
	 	
		for(Integer goodsid:goodsIdArray){
			this.baseDaoSupport.execute("insert into pmt_goods(pmt_id,goods_id)values(?,?)", pmtid,goodsid);
		}
	}
	
	
	public List listPmtPlugins() {
		
		return   promotionPluginBundle.getPluginList();
	}
	
	
	/**
	 * 在桩中查找某个优惠规则插件，如果没找到则返回 null
	 * @param pluginid
	 * @return
	 */
	
	public IPromotionPlugin getPlugin(String pluginid){
		List<IPlugin> pluginList =  promotionPluginBundle.getPluginList();
								 
		for(IPlugin plugin:pluginList){
			if(plugin.getId().equals(pluginid)){
				if( plugin instanceof IPromotionPlugin ){
					return (IPromotionPlugin)plugin;
				}
			}
		}
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	
	public void delete(Integer[] pmtidArray) {
		if(pmtidArray== null  ||pmtidArray.length==0  ) return ;
		String idStr =  StringUtil.arrayToString(pmtidArray, ",");
		String sql="delete from pmt_goods where pmt_id in("+idStr+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from pmt_member_lv where pmt_id in("+idStr+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from promotion where pmt_id in("+idStr+")";
		this.baseDaoSupport.execute(sql);
		 
	}
	

	
	public Promotion get(Integer pmtid) {
		return (Promotion) this.baseDaoSupport.queryForObject("select * from promotion where pmt_id =?", Promotion.class, pmtid);
	}
	

	
	public List listGoodsId(Integer pmtid) {
		String sql  ="select * from  "+this.getTableName("goods")+ " where goods_id in(select goods_id from "+this.getTableName("pmt_goods")+" where pmt_id =? )";
		return this.daoSupport.queryForList(sql,  pmtid);
	}

 
	
	public List listMemberLvId(Integer pmtid) {
		String sql  ="select lv_id from pmt_member_lv where pmt_id =? ";
		return this.baseDaoSupport.queryForList(sql, new IntegerMapper(), pmtid);
	}

	
	
	public void useCoupon(String code, Integer memberId) {
		String sql  ="select  c.* from  " + this.getTableName("member_coupon") +" mc ,"+this.getTableName("coupons") +" c where mc.cpns_id = c.cpns_id and mc.memc_code=? and mc.member_id=?";
		Coupons coupons =  (Coupons) this.daoSupport.queryForObject(sql, Coupons.class, code,memberId);
		ThreadContextHolder.getSessionContext().setAttribute("coupon", coupons); 
	}
	
	public PromotionPluginBundle getPromotionPluginBundle() {
		return promotionPluginBundle;
	}
	public void setPromotionPluginBundle(PromotionPluginBundle promotionPluginBundle) {
		this.promotionPluginBundle = promotionPluginBundle;
	}

	
	public List<Map> listOrderPmt(Integer orderid) {
		String sql  ="select * from order_pmt where order_id = ?";
		return this.baseDaoSupport.queryForList(sql, orderid);
	}

}
