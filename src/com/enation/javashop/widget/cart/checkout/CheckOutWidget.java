package com.enation.javashop.widget.cart.checkout;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberAddress;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.DlyType;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.support.OrderPrice;
import com.enation.javashop.core.service.GoodsPicDirectiveModel;
import com.enation.javashop.core.service.ICartManager;
import com.enation.javashop.core.service.IDlyTypeManager;
import com.enation.javashop.core.service.IMemberAddressManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPaymentManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.widget.Regions.RegionsSelectWidget;
/**
 * 购物车checkout挂件
 * @author kingapex
 *2010-3-25下午06:05:19
 */
public class CheckOutWidget extends AbstractWidget {
	private HttpServletRequest  request;
	private IMemberAddressManager memberAddressManager;
	private IDlyTypeManager dlyTypeManager;
	private ICartManager cartManager;
	private IPaymentManager paymentManager;
	private IOrderManager orderManager;
	private IPromotionManager promotionManager ;
	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		request  = ThreadContextHolder.getHttpRequest();
		Member member  = UserServiceFactory.getUserService().getCurrentMember();
		String loginpage = params.get("loginpage");
//		 
//		if( member == null ){
//			if(!StringUtil.isEmpty(loginpage)){
//				this.setPageName(loginpage);
//			}else{	
//				this.setPageName("loginBuy");
//			}
//			return;
//		}
		
		
		String sessionid =  request.getSession().getId();
		//System.out.println("action " + action);
		if(action==null || action.equals("")){
			
			this.setPageName("checkout");
	 
			
			if(member!=null){
				//读取此会员的收货地址列表
				List addressList =memberAddressManager.listAddress();
				this.putData("addressList", addressList);
			}else{
				this.putData("addressList", new ArrayList());
			}
			
			
			//读取购物车中的商品列表
			List goodsItemList = cartManager.listGoods(sessionid);
			List giftItemList = cartManager.listGift(sessionid);
			List pgkItemList  = cartManager.listPgk(sessionid);
			List groupBuyList = cartManager.listGroupBuy(sessionid);
			
			
			
			this.putData("giftItemList", giftItemList);
			this.putData("goodsItemList", goodsItemList);
			this.putData("pgkItemList", pgkItemList);
			this.putData("groupBuyList", groupBuyList);
			
			this.putData("GoodsPic",new  GoodsPicDirectiveModel());
			 
			if(goodsItemList==null|| goodsItemList.isEmpty()){
				this.putData("hasGoods", false);
			}else{
				this.putData("hasGoods", true);
			}
			
			if(giftItemList==null|| giftItemList.isEmpty()){
				this.putData("hasGift", false);
			}else{
				this.putData("hasGift", true);
			}
			
			if(pgkItemList==null|| pgkItemList.isEmpty()){
				this.putData("hasPgk", false);
			}else{
				this.putData("hasPgk", true);
			}		

			if(groupBuyList==null|| groupBuyList.isEmpty()){
				this.putData("hasGroupBuy", false);
			}else{
				this.putData("hasGroupBuy", true);
			}				
			
			//读取支付方式列表
			List paymentList  = this.paymentManager.list();
			this.putData("paymentList", paymentList);
		
			
			if(member!=null){
				//显示可享受的优惠规则
				Double originalTotal = cartManager.countGoodsTotal(sessionid);
				List pmtList  =this.promotionManager.list(originalTotal, member.getLv_id());
				this.putData("pmtList",pmtList);
				
				//显示可获得的赠品
				List giftList  = this.promotionManager.listGift(pmtList);
				this.putData("giftList", giftList);
			}
			
		}
		
		if("loginBuy".equals(action)){
			if(!StringUtil.isEmpty(loginpage)){
				EopSite site  = EopContext.getContext().getCurrentSite();
				String contextPath  = EopContext.getContext().getContextPath();
				this.setPageFolder(contextPath+"/themes/"+site.getThemepath());
				this.setPageName(loginpage);
			}else{	
				this.setPageName("loginBuy");
			}
		}
		
		if("showAddress".equals(action)){
			Integer addr_id = Integer.valueOf( request.getParameter("addr_id") );
			MemberAddress address = memberAddressManager.getAddress(addr_id);
			this.putData("json", JSONObject.fromObject(address).toString()); 
			this.setPageName("cartJson");
		}
		
		
		//异步显示在配送范围的配送方式
		if("showDlyType".equals(action)){
		
			Double orderPrice = cartManager.countGoodsTotal(sessionid);
			Double weight = cartManager.countGoodsWeight(sessionid);
			
			List<DlyType> dlyTypeList = this.dlyTypeManager.list(weight, orderPrice, request.getParameter("regionid"));
			this.putData("dlyTypeList", dlyTypeList);
			this.setPageName("dlyType");
		}
		
		//显示订单价格信息
		if("showOrderTotal".equals(action)){
			
			Integer typeId = Integer.valueOf(request.getParameter("typeId") );
			String  regionId = request.getParameter("regionId");
			boolean isProtected =  "1".equals(request.getParameter("isProtected"));
			
			OrderPrice  orderPrice  = this.cartManager.countPrice(sessionid, typeId, regionId, isProtected);
			
		/*	NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			this.putData("reducePrice", nf.format( orderPrice.getDiscountPrice() )) ;//优惠金额:减价的金额
			this.putData("originalTotal", orderPrice.getOriginalPrice() ); ////商品总价格:即原始价格
			this.putData("dlyPrice", orderPrice.getShippingPrice());  
		
			if(isProtected){
				this.putData("protectPrice",orderPrice.getProtectPrice());
				 
			}
			this.putData("orderPrice", orderPrice.getOrderPrice());
			this.putData("point", orderPrice.getPoint());*/
			
			this.putData("orderPrice",orderPrice);
			
			this.setPageName("checkoutTotal");
			
		}
		
		//创建订单
		if("createOrder".equals(action)){
			try{
				Order order  = this.createOrder();
//				this.putData("order", order);
//				
//				PayCfg payCfg = this.paymentManager.get(order.getPayment_id());
//				this.putData("paypluginid", payCfg.getType());
//				
//				this.setPageName("orderPay");
				this.showJson("{result:1,ordersn:"+order.getSn()+"}");
				
			}catch(RuntimeException e){
//				e.printStackTrace();
//				this.putData("json", e.getMessage());
//				this.setPageName("cartJson");
				this.showJson("{result:0,message:'"+e.getMessage()+"'}");
			}
		}
		
		
	}
	
	private Order createOrder(){
		
		Integer addressId = this.getIntParam("addressId");
	 
		if(addressId==null  &&addressId.intValue()!=0 ) throw new RuntimeException("收货地址不能为空");
	
		Integer shippingId = this.getIntParam("typeId");
		if(shippingId==null ) throw new RuntimeException("配送方式不能为空");
		
		Integer paymentId = this.getIntParam("paymentId");
		if(paymentId==null ) throw new RuntimeException("支付方式不能为空");
		
		Order order = new Order() ;
		order.setShipping_id(shippingId); //配送方式
		order.setPayment_id(paymentId);//支付方式
		
		MemberAddress address = null;
		if(addressId.intValue()==0 ) {//是新增地址
			address = this.createAddress();
		}else{ //选择已有的地址 
			 address = this.memberAddressManager.getAddress(addressId);

		}
 
		order.setShip_addr(address.getAddr());
		order.setShip_mobile(address.getMobile());
		order.setShip_tel(address.getTel());
		order.setShip_zip(address.getZip());
		order.setShipping_area(address.getProvince()+"-"+ address.getCity()+"-"+ address.getRegion());
		order.setShip_name(address.getName());
		order.setRegionid(address.getRegion_id());
		if (addressId.intValue()==0) {
		//	if ("yes".equals(request.getParameter("saveAddress"))) {
			if (UserServiceFactory.getUserService().getCurrentMember() != null) {
					this.memberAddressManager.addAddress(address);
			}
		//	}
		}
 
		order.setShip_day(this.getStringParam("shipDay"));
		order.setShip_time(this.getStringParam("shipTime"));
		order.setRemark(this.getStringParam("remark"));
		return	this.orderManager.add(order, this.request.getSession().getId());
		
	}
	
	private MemberAddress createAddress(){
		
		MemberAddress address = new MemberAddress();
 

		String name = request.getParameter("shipName");
		address.setName(name);

		String tel = request.getParameter("shipTel");
		address.setTel(tel);

		String mobile = request.getParameter("shipMobile");
		address.setMobile(mobile);

		String province_id = request.getParameter("province_id");
		address.setProvince_id(Integer.valueOf(province_id));

		String city_id = request.getParameter("city_id");
		address.setCity_id(Integer.valueOf(city_id));

		String region_id = request.getParameter("region_id");
		address.setRegion_id(Integer.valueOf(region_id));

		String province = request.getParameter("province");
		address.setProvince(province);

		String city = request.getParameter("city");
		address.setCity(city);

		String region = request.getParameter("region");
		address.setRegion(region);

		String addr = request.getParameter("shipAddr");
		address.setAddr(addr);

		String zip = request.getParameter("shipZip");
		address.setZip(zip);
	
		return address;
	}
	
	private String getStringParam(String name){

		
		return request.getParameter(name);
	}
	
	private Integer getIntParam(String name){
		try{
		 return Integer.valueOf( request.getParameter(name) );
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}

	public IMemberAddressManager getMemberAddressManager() {
		return memberAddressManager;
	}

	public void setMemberAddressManager(IMemberAddressManager memberAddressManager) {
		this.memberAddressManager = memberAddressManager;
	}

	public IDlyTypeManager getDlyTypeManager() {
		return dlyTypeManager;
	}

	public void setDlyTypeManager(IDlyTypeManager dlyTypeManager) {
		this.dlyTypeManager = dlyTypeManager;
	}

	public ICartManager getCartManager() {
		return cartManager;
	}

	public void setCartManager(ICartManager cartManager) {
		this.cartManager = cartManager;
	}

	public IPaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}

	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}

	
}
