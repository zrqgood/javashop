package com.enation.javashop.core.action.backend;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.resource.IUserManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.resource.model.EopUser;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.DlyCenter;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.PrintTmpl;
import com.enation.javashop.core.service.IDlyCenterManager;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.IMemberManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IPrintTmplManager;

public class OrderPrintAction extends WWAction {
	
	private IOrderManager orderManager;
	private IFreeOfferManager freeOfferManager;//赠品
	private IMemberManager memberManager;
	private IDlyCenterManager dlyCenterManager;
	private IPrintTmplManager printTmplManager;
	
	private Member member;
	
	private Integer orderId;
	private Order ord;
	private List itemList;
	private List orderGiftList;//赠品列表
	private Map ordermap;
	private List dlyCenterList;
	private Integer dly_center_id;
	private DlyCenter dlyCenter;
	private List printTmplList;
	private Integer prt_tmpl_id;
	private PrintTmpl printTmpl;
	private String saveAddr;
	private EopSite site;
	private EopUser user;
	private IUserManager userManager;
	
	public String order_prnt() {
		site = EopContext.getContext().getCurrentSite();
		user = userManager.get(site.getUserid());
		itemList = this.orderManager.listGoodsItems(orderId); // 订单商品列表
		this.ord = this.orderManager.get(orderId); // 订单信息
		this.orderGiftList = this.freeOfferManager.getOrderGift(orderId);
		if (ord.getMember_id() != null) {
			this.member = this.memberManager.get(ord.getMember_id());
			this.ordermap = this.orderManager.mapOrderByMemberId(ord
					.getMember_id());
		}
		return "order_prnt";
	}

	public String delivery_prnt() {
		site = EopContext.getContext().getCurrentSite();
		user = userManager.get(site.getUserid());
		itemList = this.orderManager.listGoodsItems(orderId); // 订单商品列表
		this.ord = this.orderManager.get(orderId); // 订单信息
		this.orderGiftList = this.freeOfferManager.getOrderGift(orderId);
		if (ord.getMember_id() != null) {
			this.member = this.memberManager.get(ord.getMember_id());
		}
		return "delivery_prnt";
	}
	
	public String global_prnt() {
		site = EopContext.getContext().getCurrentSite();
		user = userManager.get(site.getUserid());
		itemList = this.orderManager.listGoodsItems(orderId); // 订单商品列表
		this.ord = this.orderManager.get(orderId); // 订单信息
		if (ord.getMember_id() != null) {
			this.member = this.memberManager.get(ord.getMember_id());
			this.ordermap = this.orderManager.mapOrderByMemberId(ord
					.getMember_id());
		}
		return "global_prnt";
	}
	
	public String ship_prnt_step1(){
		this.ord = this.orderManager.get(orderId); // 订单信息
		this.dlyCenterList = this.dlyCenterManager.list();
		this.printTmplList = this.printTmplManager.listCanUse();
		return "ship_prnt_step1";
	}
	
	public String ship_prnt_step2(){
		site = EopContext.getContext().getCurrentSite();
		user = userManager.get(site.getUserid());
		Order order = this.orderManager.get(orderId); // 订单信息
		order.setShip_addr(ord.getShip_addr());
		order.setShip_name(ord.getShip_name());
		order.setShipping_area(ord.getShipping_area());
		order.setShip_zip(ord.getShip_zip());
		order.setShip_mobile(ord.getShip_mobile());
		order.setShip_tel(ord.getShip_tel());
		order.setRemark(ord.getRemark());
		if(StringUtil.equals(saveAddr, "1")){
			orderManager.edit(order);
		}
		this.dlyCenter = this.dlyCenterManager.get(dly_center_id);
		this.printTmpl = this.printTmplManager.get(prt_tmpl_id);
		String prt_tmpl_data = processTmplData(printTmpl.getPrt_tmpl_data(), order, dlyCenter);
		this.printTmpl.setPrt_tmpl_data(prt_tmpl_data);
		return "ship_prnt_step2";
	}
	
	private String processTmplData(String tmplData, Order order, DlyCenter dlyCenter){
		Date date = new Date();
		Calendar cal =Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		String result = tmplData.replaceAll("收货人-姓名", order.getShip_name());
		result = result.replaceAll("收货人-地区", order.getShipping_area());
		result = result.replaceAll("收货人-地址", order.getShip_addr());
		result = result.replaceAll("收货人-电话", order.getShip_tel());
		result = result.replaceAll("收货人-手机", order.getShip_mobile());
		result = result.replaceAll("收货人-邮编", order.getShip_zip());
		result = result.replaceAll("发货人-姓名", dlyCenter.getName());
		result = result.replaceAll("发货人-地区", dlyCenter.getProvince()+"-"+dlyCenter.getCity()+"-"+dlyCenter.getRegion());
		result = result.replaceAll("发货人-地址", dlyCenter.getAddress());
		result = result.replaceAll("发货人-电话", dlyCenter.getPhone());
		result = result.replaceAll("发货人-手机", dlyCenter.getCellphone());
		result = result.replaceAll("发货人-邮编", dlyCenter.getZip());
		result = result.replaceAll("当日日期-年", String.valueOf(year));
		result = result.replaceAll("当日日期-月", String.valueOf(month));
		result = result.replaceAll("当日日期-日", String.valueOf(day));
		result = result.replaceAll("订单-订单号", order.getSn());
		result = result.replaceAll("订单总金额",  StringUtil.toCurrency(order.getOrder_amount()));
		result = result.replaceAll("订单费用金额",  StringUtil.toCurrency(order.getShipping_amount()));
		result = result.replaceAll("订单物品总重量", StringUtil.toString(order.getWeight()));
		result = result.replaceAll("订单-物品数量", StringUtil.toString(order.getGoods_num()));
		result = result.replaceAll("订单-备注", order.getRemark());
		result = result.replaceAll("订单-送货时间", order.getShip_day()+order.getShip_time());
		result = result.replaceAll("网店名称", EopContext.getContext().getCurrentSite().getSitename());
		return result;
	}

	public IOrderManager getOrderManager() {
		return orderManager;
	}

	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}

	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}

	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Order getOrd() {
		return ord;
	}

	public void setOrd(Order ord) {
		this.ord = ord;
	}

	public List getItemList() {
		return itemList;
	}

	public void setItemList(List itemList) {
		this.itemList = itemList;
	}

	public List getOrderGiftList() {
		return orderGiftList;
	}

	public void setOrderGiftList(List orderGiftList) {
		this.orderGiftList = orderGiftList;
	}

	public Map getOrdermap() {
		return ordermap;
	}

	public void setOrdermap(Map ordermap) {
		this.ordermap = ordermap;
	}

	public List getDlyCenterList() {
		return dlyCenterList;
	}

	public void setDlyCenterList(List dlyCenterList) {
		this.dlyCenterList = dlyCenterList;
	}

	public IDlyCenterManager getDlyCenterManager() {
		return dlyCenterManager;
	}

	public void setDlyCenterManager(IDlyCenterManager dlyCenterManager) {
		this.dlyCenterManager = dlyCenterManager;
	}

	public IPrintTmplManager getPrintTmplManager() {
		return printTmplManager;
	}

	public void setPrintTmplManager(IPrintTmplManager printTmplManager) {
		this.printTmplManager = printTmplManager;
	}

	public List getPrintTmplList() {
		return printTmplList;
	}

	public void setPrintTmplList(List printTmplList) {
		this.printTmplList = printTmplList;
	}

	public PrintTmpl getPrintTmpl() {
		return printTmpl;
	}

	public void setPrintTmpl(PrintTmpl printTmpl) {
		this.printTmpl = printTmpl;
	}

	public Integer getDly_center_id() {
		return dly_center_id;
	}

	public void setDly_center_id(Integer dlyCenterId) {
		dly_center_id = dlyCenterId;
	}

	public DlyCenter getDlyCenter() {
		return dlyCenter;
	}

	public void setDlyCenter(DlyCenter dlyCenter) {
		this.dlyCenter = dlyCenter;
	}

	public Integer getPrt_tmpl_id() {
		return prt_tmpl_id;
	}

	public void setPrt_tmpl_id(Integer prtTmplId) {
		prt_tmpl_id = prtTmplId;
	}

	public String getSaveAddr() {
		return saveAddr;
	}

	public void setSaveAddr(String saveAddr) {
		this.saveAddr = saveAddr;
	}

	public EopSite getSite() {
		return site;
	}

	public void setSite(EopSite site) {
		this.site = site;
	}

	public EopUser getUser() {
		return user;
	}

	public void setUser(EopUser user) {
		this.user = user;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

}
