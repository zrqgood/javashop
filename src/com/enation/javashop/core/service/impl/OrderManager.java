package com.enation.javashop.core.service.impl;

import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Member;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.utils.DateUtil;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.DoubleMapper;
import com.enation.framework.database.Page;
import com.enation.framework.util.CurrencyUtil;
import com.enation.framework.util.ExcelUtil;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.DlyType;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.OrderItem;
import com.enation.javashop.core.model.OrderLog;
import com.enation.javashop.core.model.PayCfg;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.model.support.DiscountPrice;
import com.enation.javashop.core.model.support.OrderPrice;
import com.enation.javashop.core.plugin.order.OrderPluginBundle;
import com.enation.javashop.core.service.ICartManager;
import com.enation.javashop.core.service.IDlyTypeManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.core.service.OrderStatus;

/**
 * 订单管理
 * @author kingapex
 *2010-4-6上午11:16:01
 */
public class OrderManager extends BaseSupport implements IOrderManager {

	private ICartManager cartManager; 
	private IDlyTypeManager dlyTypeManager;
	private IPaymentManager paymentManager;
	private IPromotionManager promotionManager; 
	private OrderPluginBundle orderPluginBundle;
	
	 
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePrice(double price,int orderid){
		Order order  = this.get(orderid);
		double  amount  =order.getOrder_amount();
//		double discount=  amount-price;
		double discount=  CurrencyUtil.sub(amount,price);
		this.baseDaoSupport.execute("update order set order_amount=? where order_id=?", price,orderid);
		this.baseDaoSupport.execute("update order set discount=discount+? where order_id=?", discount,orderid);
		
		this.log(orderid, "修改订单价格为"+ price, null, "管理员");
	}
	
	/**
	 * 记录订单操作日志
	 * @param order_id
	 * @param message
	 * @param op_id
	 * @param op_name
	 */
	private void log(Integer order_id,String message,Integer op_id,String op_name){
		OrderLog orderLog = new OrderLog();
		orderLog.setMessage(message);
		orderLog.setOp_id(op_id);
		orderLog.setOp_name(op_name);
		orderLog.setOp_time(System.currentTimeMillis());
		orderLog.setOrder_id(order_id);
		this.baseDaoSupport.insert("order_log", orderLog);
	}	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Order add(Order order,String sessionid) {
		
		if(order==null) throw new RuntimeException("error: order is null");
		
		/**************************用户信息****************************/
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		//非匿名购买
		if(member!=null){
			order.setMember_id(member.getMember_id());
		}
		
		/**************************计算价格、重量、积分****************************/
		boolean  isProtected = order.getIs_protect().compareTo(1)==0;
		OrderPrice orderPrice  =cartManager.countPrice(sessionid, order.getShipping_id(),  ""+order.getRegionid(), isProtected);
		
		order.setGoods_amount( orderPrice.getGoodsPrice());
		order.setWeight(orderPrice.getWeight());		
		order.setDiscount(orderPrice.getDiscountPrice());
		order.setOrder_amount(orderPrice.getOrderPrice());
		order.setProtect_price(orderPrice.getProtectPrice());
		order.setShipping_amount(orderPrice.getShippingPrice());
		order.setGainedpoint(orderPrice.getPoint());
		
		//配送方式名称
		DlyType dlyType = dlyTypeManager.getDlyTypeById(order.getShipping_id());
		if(dlyType==null)  throw new RuntimeException("shipping not found count error");
		order.setShipping_type(dlyType.getName() );
		
		
		/************支付方式价格及名称************************/
		PayCfg payCfg = this.paymentManager.get(order.getPayment_id());
		order.setPaymoney(this.paymentManager.countPayPrice(order.getOrder_id()));
		order.setPayment_name( payCfg.getName());
		order.setPayment_type(payCfg.getType());
		
		/************创建订单************************/
		order.setCreate_time(System.currentTimeMillis());
		order.setSn(this.createSn());
		order.setStatus(0);
		order.setDisabled(0);
		order.setPay_status(OrderStatus.PAY_NO);
		order.setShip_status(OrderStatus.SHIP_NO);	 		
		this.baseDaoSupport.insert("order", order);
		
		
		
		/************写入订单货物列表************************/
		List itemList  = this.cartManager.listGoods(sessionid);
		List pgkList  = this.cartManager.listPgk(sessionid);
		
		/***************赠品货物列表**************************/
		List<CartItem> giftList  = cartManager.listGift(sessionid);
		
		if(itemList.isEmpty() && pgkList.isEmpty() && giftList.isEmpty()) throw new RuntimeException("创建订单失败，购物车为空");
		
 
		
		Integer orderId = this.baseDaoSupport.getLastId("order");
		
		itemList.addAll(pgkList );
		this.saveGoodsItem(itemList, orderId);
		
		
		if(giftList!=null && !giftList.isEmpty())
		this.saveGiftItem(giftList, orderId);
		
		/**
		 * 应用订单优惠，送出优惠卷及赠品，并记录订单优惠方案
		 */
		if(member!=null){
			this.promotionManager.applyOrderPmt(orderId, orderPrice.getOrderPrice(), member.getLv_id());
			List<Promotion> pmtList =  promotionManager.list(orderPrice.getOrderPrice(), member.getLv_id());
			for(Promotion pmt :pmtList){
				String sql ="insert into order_pmt(pmt_id,order_id,pmt_describe)values(?,?,?)";
				this.baseDaoSupport.execute(sql, pmt.getPmt_id(),orderId,pmt.getPmt_describe());
			}
			
		}
		
		
		/************写入订单日志************************/
		OrderLog log = new OrderLog();
		log.setMessage("订单创建");
		log.setOp_name("顾客");
		log.setOrder_id(orderId);
		this.addLog(log);
		order.setOrder_id(orderId);
		
		cartManager.clean(sessionid);
		ThreadContextHolder.getSessionContext().removeAttribute("coupon");
		order.setOrderprice(orderPrice);
		this.orderPluginBundle.onCreate(order, sessionid);
		return order;
	}
	
	/**
	 * 添加订单日志
	 * @param log
	 */
	private void addLog(OrderLog log){
		log.setOp_time(System.currentTimeMillis());
		this.baseDaoSupport.insert("order_log", log);
	}
 
	
	/**
	 * 保存商品订单项
	 * @param itemList
	 * @param order_id
	 */
	private void saveGoodsItem(List itemList,Integer order_id){
		for(int i=0;i<itemList.size();i++){
			
			OrderItem  orderItem = new OrderItem();
			
			CartItem cartItem =(CartItem) itemList.get(i);
			orderItem.setPrice(cartItem.getCoupPrice() ) ;
			orderItem.setName( cartItem.getName());
			orderItem.setNum(cartItem.getNum());

			orderItem.setGoods_id(cartItem.getGoods_id());
			orderItem.setShip_num(0);
			orderItem.setSpec_id(cartItem.getProduct_id());
			orderItem.setOrder_id(order_id);
			orderItem.setPrice(cartItem.getPrice());
			orderItem.setGainedpoint(cartItem.getPoint());
			orderItem.setAddon( cartItem.getAddon() );
			this.baseDaoSupport.insert("order_items", orderItem);
		}
	}
	
	
	/**
	 * 保存赠品项
	 * @param itemList
	 * @param orderid
	 * @throws IllegalStateException 会员尚未登录,不能兑换赠品!
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void saveGiftItem(List<CartItem> itemList ,Integer orderid){
		Member member = UserServiceFactory.getUserService().getCurrentMember();
		if(member==null){
			throw new IllegalStateException("会员尚未登录,不能兑换赠品!"); 
		}
		
		
		int point =0;
		for(CartItem item : itemList){
			point  += item.getSubtotal().intValue();
			this.baseDaoSupport.execute("insert into order_gift(order_id,gift_id,gift_name,point,num,shipnum,getmethod)values(?,?,?,?,?,?,?)", 
					orderid,item.getProduct_id(),item.getName(),item.getPoint(),item.getNum(),0,"exchange");			
		}
		if(member.getPoint().intValue() < point){
			throw new IllegalStateException("会员积分不足,不能兑换赠品!"); 
		}
		member.setPoint( member.getPoint()-point ); //更新session中的会员积分
		this.baseDaoSupport.execute("update member set point=? where member_id=? ", member.getPoint(),member.getMember_id());
		
	}
	
	
	public Page list(int pageNO, int pageSize,int disabled, String searchkey,
			String searchValue, String order) {
		StringBuffer sql   = new StringBuffer("select * from order where disabled=? ");
		if(!StringUtil.isEmpty(searchkey) && !StringUtil.isEmpty(searchValue) ){
			sql.append(" and ");
			sql.append(searchkey);
			sql.append("=?"); 
		}
		order = StringUtil.isEmpty(order)?"order_id desc":order;
		sql.append(" order by "+ order);
		Page page =null;
		
		if(!StringUtil.isEmpty(searchkey) && !StringUtil.isEmpty(searchValue) ){
			//page  = this.saasDaoSupport.queryForPage(sql.toString(), pageNO, pageSize, searchValue);
			page = this.baseDaoSupport.queryForPage(sql.toString(), pageNO, pageSize, Order.class,disabled, searchValue);
		}else{
			page  = this.baseDaoSupport.queryForPage(sql.toString(), pageNO, pageSize, Order.class,disabled);
		}
		return page;
	}
	
	
	
	public Order get(Integer orderId) {
		String sql  ="select * from order where order_id=?";
		Order order  = (Order)this.baseDaoSupport.queryForObject(sql, Order.class, orderId);
		return order;
	}

	
	public Order get(String ordersn) {
		String sql  ="select * from order where sn=?";
		Order order  = (Order)this.baseDaoSupport.queryForObject(sql, Order.class, ordersn);
		return order;
		 
	}
	
	
	public List<Map> listGoodsItems(Integer orderId) {

		String sql = "select items.*, g.image_default as image, p.sn as sn from " + this.getTableName("order_items") + " items ";
		sql += " left join " + this.getTableName("goods") + " g on g.goods_id = items.goods_id ";
		sql += " left join " + this.getTableName("product") + " p on p.product_id = items.spec_id ";
		sql += " where items.order_id = ?";
		List<Map > itemList = this.daoSupport.queryForList(sql, new RowMapper(){

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Map map = new HashMap();
				map.put("item_id", rs.getInt("item_id"));
				map.put("order_id", rs.getInt("order_id"));
				map.put("goods_id", rs.getInt("goods_id"));
				map.put("spec_id", rs.getInt("spec_id"));
				map.put("num", rs.getInt("num"));   
				map.put("ship_num", rs.getInt("ship_num"));
				map.put("name", rs.getString("name"));
				map.put("price", rs.getObject("price"));
				map.put("gainedpoint", rs.getInt("gainedpoint"));
				map.put("addon", rs.getString("addon"));	
				
				String image_default = rs.getString("image");
				if(image_default!=null){
					image_default  =UploadUtil.replacePath(image_default);
				}
				map.put("image", image_default);
				map.put("sn", rs.getString("sn"));
				return map;
			}
			
		}, orderId);
		this.orderPluginBundle.onFilter(orderId, itemList);
		return itemList;
	}
	
	

	
	public List listGiftItems(Integer orderId) {
		String sql ="select * from order_gift where order_id=?";
		return this.baseDaoSupport.queryForList(sql, orderId);
	}
	
	
	/**
	 * 读取订单日志 
	 */
	
	public List listLogs(Integer orderId) {
		String sql = "select * from order_log where order_id=?";
		return this.baseDaoSupport.queryForList(sql, orderId);
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void clean(Integer[] orderId) {
		String ids = StringUtil.arrayToString(orderId, ",");
		String sql = "delete from order where order_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql);
		
		sql = "delete from order_items where order_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql);
		
		sql = "delete from order_log where order_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql);
		
		sql = "delete from payment_logs where order_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql);
		
 
		sql = "delete from " + this.getTableName("delivery_item") + " where delivery_id in (select delivery_id from " + this.getTableName("delivery") + " where order_id in (" + ids + "))";
		this.daoSupport.execute(sql);
		
		sql = "delete from delivery where order_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql);
		
		/**
		 * -------------------
		 * 激发订单的删除事件
		 * -------------------
		 */
		this.orderPluginBundle.onDelete(orderId);
		
	}
	
	private void exec(Integer[] orderId, int disabled){
		String ids = StringUtil.arrayToString(orderId, ",");
		String sql = "update order set disabled = ? where order_id in (" + ids + ")";
		this.baseDaoSupport.execute(sql, disabled);
	}

	
	public void delete(Integer[] orderId) {
		exec(orderId, 1);
		
	}

	
	public void revert(Integer[] orderId) {
		exec(orderId, 0);
		
	}	
	
	private String createSn(){
		Date now = new Date();
		String sn = com.enation.framework.util.DateUtil.toString(now, "yyyyMMddhhmmss");
		return sn;
	}

	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}

	public IDlyTypeManager getDlyTypeManager() {
		return dlyTypeManager;
	}

	public void setDlyTypeManager(IDlyTypeManager dlyTypeManager) {
		this.dlyTypeManager = dlyTypeManager;
	}

	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	
	public List listOrderByMemberId(int member_id) {
		String sql = "select * from order where member_id = ? order by create_time desc";
		List list = this.baseDaoSupport.queryForList(sql, Order.class, member_id);
		return list;
	}
	
	
	public Map mapOrderByMemberId(int memberId) {
		Integer buyTimes = this.baseDaoSupport.queryForInt("select count(0) from order where member_id = ?", memberId);
		Double buyAmount = (Double)this.baseDaoSupport.queryForObject("select sum(paymoney) from order where member_id = ?",new DoubleMapper(), memberId);
		Map map = new HashMap();
		map.put("buyTimes", buyTimes);
		map.put("buyAmount", buyAmount);
		return map;
	}

	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}

	
	public void edit(Order order) {
		this.baseDaoSupport.update("order", order, "order_id = " + order.getOrder_id());
		
	}

	
	public List<Map> listAdjItem(Integer orderid) {
		String sql  ="select * from order_items where order_id=? and addon!=''";
		return this.baseDaoSupport.queryForList(sql, orderid);
	}

	//已废弃，使用CartManager.countPrice
/*	public OrderPrice countPrice(String sessionid,Integer shippingid,String regionid,boolean isProtected ) {
		
		OrderPrice orderPrice = new OrderPrice();
		
		//计算商品重量
		Double weight  = cartManager.countGoodsWeight(sessionid);
		//计算商品原始价格
		Double originalPrice = cartManager.countGoodsTotal(sessionid); 
		//计算捆绑商品的总价，并加入订单总价中
		Double pgkTotal = this.cartManager.countPgkTotal(sessionid);
		//计算团购总价
		Double groupBuyTotal = this.cartManager.countGroupBuyTotal(sessionid);
		
		originalPrice = CurrencyUtil.add(originalPrice,pgkTotal);
		originalPrice = CurrencyUtil.add(originalPrice,groupBuyTotal);
		
		Integer point  = this.cartManager.countPoint(sessionid);	
		
		//计算原始配置送费用
		Double[] priceArray = this.dlyTypeManager.countPrice(shippingid, weight, originalPrice, regionid, isProtected);
		Double dlyPrice = priceArray[0];//费送费用
		
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		Double orderTotal =null;
		if(member!=null){
			Double coupPrice =cartManager.countGoodsDiscountTotal(sessionid); //应用了商品优惠规则后的商品价格
			
			//对订单价格和积分执行优惠
			DiscountPrice discountPrice  = this.promotionManager.applyOrderPmt(coupPrice, dlyPrice,point, member.getLv_id()); 
			
			coupPrice=discountPrice.getOrderPrice() ; //优惠会后订单金额
			dlyPrice = discountPrice.getShipFee();
			point = discountPrice.getPoint();
//			orderTotal = coupPrice+ dlyPrice; //订单总金额	
			orderTotal = CurrencyUtil.add(coupPrice, dlyPrice); //订单总金额
//			Double reducePrice = originalPrice - coupPrice;
			Double reducePrice = CurrencyUtil.sub(originalPrice , coupPrice);
			
			*//**设置订单价格对象**//*
			orderPrice.setGoodsPrice(coupPrice); //商品金额，优惠后的
			orderPrice.setDiscountPrice(reducePrice); //优惠的金额
			orderPrice.setShippingPrice(dlyPrice);
			orderPrice.setPoint(point);
						
		}else{
//			orderTotal = originalPrice+ dlyPrice; //如果是非会员购买，订单总金额=商品原始金额+配送用
			orderTotal = CurrencyUtil.add(originalPrice, dlyPrice);
			orderPrice.setPoint(point);
			orderPrice.setShippingPrice(dlyPrice);
		}
		
	
		
		//计算保价费用
		if(isProtected){
			Double protectPrice = priceArray[1];
//			orderTotal+=protectPrice;
			orderTotal=CurrencyUtil.add(orderTotal, protectPrice);
			
			*//**设置保价费用**//*
			orderPrice.setProtectPrice(protectPrice);
		}
		
		
		
		*//**设置订单价格对象**//*
		orderPrice.setOriginalPrice(originalPrice);
		orderPrice.setOrderPrice(orderTotal);
		orderPrice.setWeight(weight);
		
		return orderPrice;
	}*/

	
	/**
	 * 统计订单状态 
	 */
	@SuppressWarnings("unchecked")
	public Map censusState() {
		
		//构造一个返回值Map，并将其初始化：各种订单状态的值皆为0
		Map<String,Integer> stateMap = new HashMap<String,Integer>(7);
		String[] states= {"cancel_ship","cancel_pay","not_pay","pay","ship","complete","cancellation"};
		for(String s:states){
			stateMap.put(s, 0);
		}
		
		//分组查询、统计订单状态 
		String sql ="select count(0) num,status  from "+this.getTableName("order")+" where disabled = 0 group by status";
		List<Map<String,Integer> > list  = this.daoSupport.queryForList(sql,new RowMapper(){

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Map<String,Integer> map = new HashMap<String, Integer>();
				map.put("status", rs.getInt("status"));
				map.put("num", rs.getInt("num"));
				return map;
			}
			
		});
		
		//将list转为map
		for(Map<String,Integer> state:list){
			stateMap.put( this.getStateString( state.get("status")), state.get("num"));
		}
		
		return stateMap;
	}
	
	/**
	 * 根据订单状态值获取状态字串，如果状态值不在范围内反回null。
	 * @param state
	 * @return
	 */
	private String getStateString(Integer state){
		String str=null;
		switch (state.intValue()) {
		case -2:
			str= "cancel_ship";
			break;
		case -1:
			str= "cancel_pay";
			break;
		case 0:
			str= "not_pay";
			break;
		case 1:
			str= "pay";
			break;	
		case 2:
			str= "ship";
			break;
		case 3:
			str= "complete";
			break;
		case 4:
			str= "cancellation";
			break;
		default:
			str=null;
			break;
		}
		return str;
	}

	public OrderPluginBundle getOrderPluginBundle() {
		return orderPluginBundle;
	}

	public void setOrderPluginBundle(OrderPluginBundle orderPluginBundle) {
		this.orderPluginBundle = orderPluginBundle;
	}

	@Override
	public String export(Date start, Date end) {
		String sql  ="select * from order where disabled=0 ";
		if(start!=null){
			sql+=" and create_time>"+ start.getTime();
		}
		
		if(end!=null){
			sql+="  and create_timecreate_time<"+ end.getTime();
		}
		
		List<Order> orderList  = this.baseDaoSupport.queryForList(sql,Order.class);
		
		
		//使用excel导出流量报表
		ExcelUtil excelUtil = new ExcelUtil(); 
		
		//流量报表excel模板在类包中，转为流给excelutil
		InputStream in =FileUtil.getResourceAsStream("com/enation/javashop/core/service/impl/order.xls");
		
		excelUtil.openModal( in );
		int i=1;
		for(Order order :orderList){
			
			excelUtil.writeStringToCell(i, 0,order.getSn()); //订单号
			excelUtil.writeStringToCell(i, 1,DateUtil.toString(new Date(order.getCreate_time()), "yyyy-MM-dd HH:mm:ss")  ); //下单时间
			excelUtil.writeStringToCell(i, 2,order.getOrderStatus() ); //订单状态
			excelUtil.writeStringToCell(i, 3,""+order.getOrder_amount() ); //订单总额
			excelUtil.writeStringToCell(i, 4,order.getShip_name() ); //收货人
			excelUtil.writeStringToCell(i, 5,order.getPayStatus()); //付款状态
			excelUtil.writeStringToCell(i, 6,order.getShipStatus()); //发货状态
			excelUtil.writeStringToCell(i, 7,order.getShipping_type()); //配送方式
			excelUtil.writeStringToCell(i, 8,order.getPayment_name()); //支付方式
			i++;
		}
		//String target= EopSetting.IMG_SERVER_PATH;
		//saas 版导出目录用户上下文目录access文件夹
		String filename= "";
		if("2".equals( EopSetting.RUNMODE )){
			EopSite site = EopContext.getContext().getCurrentSite();
			filename ="/user/"+site.getUserid()+"/"+site.getId()+"/order";
		}else{
			filename ="/order";
		}
		File file  = new File(EopSetting.IMG_SERVER_PATH + filename);
		if(!file.exists())file.mkdirs();
		
		filename =filename+ "/order"+com.enation.framework.util.DateUtil.getDatelineLong()+".xls";
		excelUtil.writeToFile(EopSetting.IMG_SERVER_PATH+filename);
		
		return EopSetting.IMG_SERVER_DOMAIN +filename ;
	}
	
}
