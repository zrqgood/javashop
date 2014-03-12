package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberLv;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.database.DoubleMapper;
import com.enation.framework.database.ObjectNotFoundException;
import com.enation.framework.util.CurrencyUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cart;
import com.enation.javashop.core.model.GoodsLvPrice;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.mapper.CartItemMapper;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.model.support.DiscountPrice;
import com.enation.javashop.core.model.support.OrderPrice;
import com.enation.javashop.core.plugin.cart.CartPluginBundle;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;
import com.enation.javashop.core.service.ICartManager;
import com.enation.javashop.core.service.IDlyTypeManager;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberPriceManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.core.service.promotion.IPromotionMethod;
import com.enation.javashop.core.service.promotion.ITimesPointBehavior;

/**
 * 购物车业务实现
 * 
 * @author kingapex 2010-3-23下午03:30:50
 * edited by lzf 2011-10-08
 */

public class CartManager extends BaseSupport implements ICartManager {
	private IDlyTypeManager dlyTypeManager;
	private IPromotionManager promotionManager;
	private IMemberPriceManager memberPriceManager;
	private IMemberLvManager memberLvManager;
	private CartPluginBundle cartPluginBundle;

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Cart cart) {
		String sql = "select count(0) from cart where  product_id=? and session_id=? and itemtype=? ";
		int count = this.baseDaoSupport.queryForInt(sql, cart.getProduct_id(),
				cart.getSession_id(), cart.getItemtype());
		if (count > 0) {
			this.baseDaoSupport
					.execute(
							"update cart set num=num+? where  product_id=? and session_id=? and itemtype=? ",
							cart.getNum(), cart.getProduct_id(), cart
									.getSession_id(), cart.getItemtype());
		} else {

			this.baseDaoSupport.insert("cart", cart);
			Integer cartid = this.baseDaoSupport.getLastId("cart");
			cart.setCart_id(cartid);
			this.cartPluginBundle.onAdd(cart);
		}
	}

	public Integer countItemNum(String sessionid) {
		String sql = "select count(0) from cart where session_id =?";
		return this.baseDaoSupport.queryForInt(sql, sessionid);
	}

	public List listGoods(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select g.cat_id as catid,g.goods_id,g.image_default, c.name ,  p.specs  ,g.mktprice,g.point,p.product_id,c.price,c.cart_id as cart_id,c.num as num,c.itemtype,c.addon  from "
						+ this.getTableName("cart")
						+ " c,"
						+ this.getTableName("product")
						+ " p,"
						+ this.getTableName("goods") + " g ");
		sql
				.append("where c.itemtype=0 and c.product_id=p.product_id and p.goods_id= g.goods_id and c.session_id=?");
		List<CartItem> list = this.daoSupport.queryForList(sql.toString(),
				new CartItemMapper(), sessionid);
		cartPluginBundle.filterList(list, sessionid);

		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		List<GoodsLvPrice> memPriceList = new ArrayList<GoodsLvPrice>();
		double discount = 1; // 默认是原价,防止无会员级别时出错
		if (member != null && member.getLv_id() != null) {
			// this.promotionManager.applyGoodsPmt(list, member.getLv_id());
			memPriceList = this.memberPriceManager.listPriceByLvid(member
					.getLv_id());
			MemberLv lv = this.memberLvManager.get(member.getLv_id());
			discount = lv.getDiscount() / 100.00;
			this.applyMemPrice(list, memPriceList, discount);
			// lzf edit 2011-10-05
			this.promotionManager.applyGoodsPmt(list, member.getLv_id());
		}
		return list;
	}

	/**
	 * 应用会员价
	 * 
	 * @param itemList
	 * @param memPriceList
	 * @param discount
	 */
	private void applyMemPrice(List<CartItem> itemList,
			List<GoodsLvPrice> memPriceList, double discount) {
		for (CartItem item : itemList) {
			double price = item.getCoupPrice()* discount;
			for (GoodsLvPrice lvPrice : memPriceList) {
				if (item.getProduct_id().intValue() == lvPrice.getProductid()) {
					price = lvPrice.getPrice();
				}
			}

		 
			item.setCoupPrice(price);
		}
	}

	public List listPgk(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select g.sn,g.goods_id,g.image_default,c.name,g.mktprice,g.point,p.product_id,c.price,c.cart_id as cart_id,c.num as num,c.itemtype  from "
						+ this.getTableName("cart")
						+ " c,"
						+ this.getTableName("product")
						+ " p,"
						+ this.getTableName("goods") + " g ");
		sql
				.append("where c.itemtype=1 and c.product_id=p.product_id and p.goods_id= g.goods_id and c.session_id=? ");
		List<CartItem> list = this.daoSupport.queryForList(sql.toString(),
				new CartItemMapper(), sessionid);
		return list;
	}

	public List listGift(String sessionid) {

		StringBuffer sql = new StringBuffer();
		sql
				.append("select f.fo_id as goods_id,f.list_thumb image_default,f.fo_name as name, f.price as mktprice, f.score as point,f.fo_id as product_id,f.score as price,f.limit_purchases as limitnum,c.cart_id as cart_id,c.num as num,c.itemtype  from "
						+ this.getTableName("cart")
						+ " c,"
						+ this.getTableName("freeoffer") + " f");
		sql
				.append(" where c.itemtype=2 and c.product_id=f.fo_id  and c.session_id=?");
		List<CartItem> list = this.daoSupport.queryForList(sql.toString(),
				new CartItemMapper(), sessionid);

		return list;
	}

	/**
	 * 读取团购列表
	 * 
	 * @param sessionid
	 * @return
	 */
	public List listGroupBuy(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select g.goods_id,g.image_default, c.name , g.mktprice,g.point,gb.groupid as product_id,c.price,c.cart_id as cart_id,c.num as num,c.itemtype,c.addon  from "
						+ this.getTableName("cart")
						+ " c,"
						+ this.getTableName("goods") + " g ");
		sql.append("," + this.getTableName("group_buy") + " gb ");
		sql
				.append("where c.itemtype=3 and c.product_id = gb.groupid and gb.goodsid= g.goods_id   and c.session_id=?");
		List<CartItem> list = this.daoSupport.queryForList(sql.toString(),
				new CartItemMapper(), sessionid);
		System.out.println(sql);
		return list;
	}

	public void clean(String sessionid) {
		String sql = "delete from cart where session_id=?";
		this.baseDaoSupport.execute(sql, sessionid);
	}

	public void clean(String sessionid, Integer userid, Integer siteid) {

		if ("2".equals(EopSetting.RUNMODE)) {
			String sql = "delete from es_cart_" + userid + "_" + siteid
					+ " where session_id=?";
			this.daoSupport.execute(sql, sessionid);

		} else {
			String sql = "delete from cart where session_id=?";
			this.baseDaoSupport.execute(sql, sessionid);
		}

		if (this.logger.isDebugEnabled()) {
			this.logger.debug("clean cart sessionid[" + sessionid + "]");
		}
	}

	public void delete(String sessionid, Integer cartid) {
		String sql = "delete from cart where session_id=? and cart_id=?";
		this.baseDaoSupport.execute(sql, sessionid, cartid);
		this.cartPluginBundle.onDelete(sessionid, cartid);
	}

	public void updateNum(String sessionid, Integer cartid, Integer num) {
		String sql = "update cart set num=? where session_id =? and cart_id=?";
		this.baseDaoSupport.execute(sql, num, sessionid, cartid);
	}

	public Double countGoodsTotal(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum( c.price * c.num ) as num from cart c ");
		sql.append("where  c.session_id=? and c.itemtype=0 ");
		Double price = (Double) this.baseDaoSupport.queryForObject(sql
				.toString(), new DoubleMapper(), sessionid);
		return price;
	}

	public Double countPgkTotal(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum( c.price * c.num ) as num from cart c  ");
		sql.append("where c.itemtype=1  and c.session_id=?");
		Double price = (Double) this.baseDaoSupport.queryForObject(sql
				.toString(), new DoubleMapper(), sessionid);

		return price;
	}

	public Double countGroupBuyTotal(String sessionid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum( c.price * c.num ) as num from cart c  ");
		sql.append("where c.itemtype=3  and c.session_id=?");
		Double price = (Double) this.baseDaoSupport.queryForObject(sql
				.toString(), new DoubleMapper(), sessionid);

		return price;

	}

	public Double countGoodsDiscountTotal(String sessionid) {

		List<CartItem> itemList = this.listGoods(sessionid);

		double price = 0; // 计算商品促销规则优惠后的总价
		for (CartItem item : itemList) {
			// price+=item.getSubtotal();
			price = CurrencyUtil.add(price, item.getSubtotal());
		}

		return price;
	}

	public Integer countPoint(String sessionid) {

		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if (member != null) {
			Integer memberLvId = member.getLv_id();
			StringBuffer sql = new StringBuffer();
			sql
					.append("select c.*, g.goods_id, g.point from "
							+ this.getTableName("cart")
							+ " c,"
							+ this.getTableName("goods")
							+ " g, "
							+ this.getTableName("product")
							+ " p where p.product_id = c.product_id and g.goods_id = p.goods_id and c.session_id = ?");
			List<Map> list = this.daoSupport.queryForList(sql.toString(),
					sessionid);
			Integer result = 0;
			for (Map map : list) {
				Integer goodsid = StringUtil.toInt(map.get("goods_id")
						.toString());
				List<Promotion> pmtList = promotionManager.list(goodsid,
						memberLvId);
				for (Promotion pmt : pmtList) {

					// 查找相应插件
					String pluginBeanId = pmt.getPmts_id();
					IPromotionPlugin plugin = promotionManager
							.getPlugin(pluginBeanId);

					if (plugin == null) {
						logger.error("plugin[" + pluginBeanId + "] not found ");
						throw new ObjectNotFoundException("plugin["
								+ pluginBeanId + "] not found ");
					}

					// 查找相应优惠方式
					String methodBeanName = plugin.getMethods();
					if (this.logger.isDebugEnabled()) {
						this.logger.debug("find promotion method["
								+ methodBeanName + "]");
					}
					IPromotionMethod promotionMethod = SpringContextHolder
							.getBean(methodBeanName);
					if (promotionMethod == null) {
						logger.error("plugin[" + methodBeanName
								+ "] not found ");
						throw new ObjectNotFoundException("promotion method["
								+ methodBeanName + "] not found ");
					}

					// 翻倍积分方式
					if (promotionMethod instanceof ITimesPointBehavior) {
						Integer point = StringUtil.toInt(map.get("point")
								.toString());
						ITimesPointBehavior timesPointBehavior = (ITimesPointBehavior) promotionMethod;
						point = timesPointBehavior.countPoint(pmt, point);
						result += point;
					}

				}
			}
			return result;
		} else {
			StringBuffer sql = new StringBuffer();
			sql.append("select  sum(g.point * c.num) from "
					+ this.getTableName("cart") + " c,"
					+ this.getTableName("product") + " p,"
					+ this.getTableName("goods") + " g ");
			sql
					.append("where (c.itemtype=0  or c.itemtype=1)  and c.product_id=p.product_id and p.goods_id= g.goods_id and c.session_id=?");

			return this.daoSupport.queryForInt(sql.toString(), sessionid);
		}
	}

	public Double countGoodsWeight(String sessionid) {
		StringBuffer sql = new StringBuffer(
				"select sum( c.weight * c.num )  from cart c where c.session_id=?");
		Double weight = (Double) this.baseDaoSupport.queryForObject(sql
				.toString(), new DoubleMapper(), sessionid);
		return weight;
	}

	
	
	@Override
	public OrderPrice countPrice(String sessionid, Integer shippingid,
			String regionid, Boolean isProtected) {
		
		OrderPrice orderPrice = new OrderPrice();
		
		
		/**
		 * ---------------------------------
		 * 一些计算价格需要的基本信息
		 * ---------------------------------
		 */
		//计算商品重量
		Double weight  = countGoodsWeight(sessionid);

		//计算可获得积分
		Integer point  = countPoint(sessionid);	
		

		
		//计算商品原始价格
		Double originalPrice = countGoodsTotal(sessionid);	
		
		//优惠后的订单价格,默认为商品原始价格
		Double coupPrice =originalPrice; 
		
		//订单总价格
		Double  orderTotal = 0d;//如果没有计算配送信息，为0
		
		//配送费用
		Double dlyPrice = 0d; //如果没有计算配送信息，为0
		
		//保价费用		
		Double protectPrice =0d;//如果没有计算配送信息，为0
		
		
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		
		
		//计算会员优惠
		if(member!=null){
			 coupPrice =countGoodsDiscountTotal(sessionid); //应用了商品优惠规则后的商品价格
		}
		
		
		/**
		 * -------------------------------
		 * 如果传递了配送信息，计算配送费用
		 * -------------------------------
		 * 
		 */
		if(regionid!=null &&shippingid!=null &&isProtected!=null  ){
			//计算原始配置送费用
			Double[] priceArray = this.dlyTypeManager.countPrice(shippingid, weight, originalPrice, regionid, isProtected);
			dlyPrice = priceArray[0];//费送费用
			
			if(member!=null){ //计算会员优惠
				//对订单价格和积分执行优惠
				DiscountPrice discountPrice  = this.promotionManager.applyOrderPmt(coupPrice, dlyPrice,point, member.getLv_id()); 
				coupPrice=discountPrice.getOrderPrice() ; //优惠会后订单金额
				dlyPrice = discountPrice.getShipFee(); //优惠后的配送费用
				point = discountPrice.getPoint(); //优惠后的积分
				

			}
			
			//计算保价费用
			if(isProtected){
				protectPrice = priceArray[1];
				/**设置保价费用**/
				orderPrice.setProtectPrice(protectPrice);
			}
			
		}
		
		
		
		/**
		 * ---------------------------------
		 * 设置订单的各种费用项
		 * ---------------------------------
		 */
		//打折金额：原始的商品价格-优惠后的商品金额
		Double reducePrice = CurrencyUtil.sub(originalPrice , coupPrice);
		
		//订单总金额 为将优惠后的商品金额加上优惠后的配送费用
		orderTotal = CurrencyUtil.add(coupPrice, dlyPrice); 
		orderTotal=CurrencyUtil.add(orderTotal, protectPrice); //再加上保价费用
		
		orderPrice.setDiscountPrice(reducePrice); //优惠的金额
		orderPrice.setGoodsPrice(coupPrice); //商品金额，优惠后的
		orderPrice.setShippingPrice(dlyPrice);
		orderPrice.setPoint(point); 
		orderPrice.setOriginalPrice(originalPrice);
		orderPrice.setOrderPrice(orderTotal);
		orderPrice.setWeight(weight);
		orderPrice  = this.cartPluginBundle.coutPrice(orderPrice);
		return orderPrice;
		
		
		/**
		 * --------------------------------------
		 * 废弃掉的代码在这里，有可能要改成插件的
		 * --------------------------------------
		 * 
		 * 		//计算捆绑商品的总价，并加入订单总价中
		Double pgkTotal = countPgkTotal(sessionid);
		//计算团购总价
		Double groupBuyTotal = countGroupBuyTotal(sessionid);
		
		originalPrice = CurrencyUtil.add(originalPrice,pgkTotal);
		originalPrice = CurrencyUtil.add(originalPrice,groupBuyTotal);
		 */
	}
	

	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
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

	public CartPluginBundle getCartPluginBundle() {
		return cartPluginBundle;
	}

	public void setCartPluginBundle(CartPluginBundle cartPluginBundle) {
		this.cartPluginBundle = cartPluginBundle;
	}

	public IDlyTypeManager getDlyTypeManager() {
		return dlyTypeManager;
	}

	public void setDlyTypeManager(IDlyTypeManager dlyTypeManager) {
		this.dlyTypeManager = dlyTypeManager;
	}



}
