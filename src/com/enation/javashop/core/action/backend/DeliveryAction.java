package com.enation.javashop.core.action.backend;

import java.util.ArrayList;
import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;
import com.enation.javashop.core.model.Order;
import com.enation.javashop.core.model.Regions;
import com.enation.javashop.core.service.IDlyTypeManager;
import com.enation.javashop.core.service.ILogiManager;
import com.enation.javashop.core.service.IOrderFlowManager;
import com.enation.javashop.core.service.IOrderManager;
import com.enation.javashop.core.service.IRegionsManager;

/**
 * 收退货action
 * 
 * @author apexking
 * 
 */
public class DeliveryAction extends WWAction {
	
	
	private ILogiManager logiManager;
	private IDlyTypeManager dlyTypeManager;
	private IOrderManager orderManager;
	private IOrderFlowManager orderFlowManager;
	private IRegionsManager regionsManager;
	private Integer orderId;
	private Order ord;
	private List logiList;
	private List dlyTypeList;
	private List itemList;
	private List giftList;
	
	private Delivery delivery;
	private String[] goods_nameArray;
	private String[] goods_snArray;
	private Integer[] goods_idArray;
	private Integer[] product_idArray;
	private Integer[] numArray;
	
	/********以下为赠品发货信息***********/
	private Integer[] giftidArray;
	private String[] giftnameArray;
	private Integer[] giftnumArray;
	private boolean hasGift; //是否有赠品货物
	
	private Regions province;
	private Regions city;
	private Regions region;

	public Regions getProvince() {
		return province;
	}
	public void setProvince(Regions province) {
		this.province = province;
	}
	public Regions getCity() {
		return city;
	}
	public void setCity(Regions city) {
		this.city = city;
	}
	public Regions getRegion() {
		return region;
	}
	public void setRegion(Regions region) {
		this.region = region;
	}

	public IRegionsManager getRegionsManager() {
		return regionsManager;
	}
	public void setRegionsManager(IRegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}
	/**
	 * 显示发货对话框
	 * @return
	 */
	public String showShipDialog(){
		this.fillShipData();
		//读取未发货明细
		this.itemList = this.orderFlowManager.listNotShipGoodsItem(orderId);
		giftList = this.orderFlowManager.listNotShipGiftItem(orderId);
		hasGift= giftList==null||giftList.isEmpty()?false:true;
		return "ship_dialog";
	}
	
	
	
	/**
	 * 显示退货对话框
	 * @return
	 */
	public String showReturnDialog(){
		this.fillShipData();
		//读取已发货明细
		this.itemList = this.orderFlowManager.listShipGoodsItem(orderId);		//商品已发货明细
		giftList = this.orderFlowManager.listShipGiftItem(orderId);
		hasGift= giftList==null||giftList.isEmpty()?false:true;
		return "return_dialog";
	}
	
	
	/**
	 * 显示换货对话框
	 * @return
	 */
	public String showChangeDialog(){
		this.fillShipData();
		//读取已发货明细
		this.itemList = this.orderFlowManager.listShipGoodsItem(orderId);		//商品已发货明细
//		giftList = this.orderFlowManager.listNotShipGiftItem(orderId);
//		hasGift= giftList==null||giftList.isEmpty()?false:true;
		return "change_dialog";
	}
	
	

	private void fillShipData(){
		logiList  = logiManager.list();
		dlyTypeList =  dlyTypeManager.list();
		this.ord = this.orderManager.get(orderId);
//		if(!StringUtil.isEmpty(this.ord.getShipping_area())){
//			Regions[] r = this.regionsManager.get(this.ord.getShipping_area());
//			this.province = r.length >0?r[0]:null;
//			this.city = r.length>1?r[1]:null;
//			this.region = r.length>2?r[2]:null;
//		}
	}

	
	public String ship(){
		try{
			List<DeliveryItem> itemList  = new ArrayList<DeliveryItem>();
			int i=0;
			for(Integer goods_id :goods_idArray){
				
				DeliveryItem item = new DeliveryItem();
				item.setGoods_id(goods_id);
				item.setName(goods_nameArray[i]);
				item.setNum(numArray[i]);  
				item.setProduct_id(product_idArray[i]);
				item.setSn(goods_snArray[i]);
				item.setItemtype(0);
				itemList.add(item);
				i++;
			}
		
			i=0;
			List<DeliveryItem> giftitemList  = new ArrayList<DeliveryItem>();
			if(giftidArray!=null){
				for(Integer giftid :giftidArray){
					
					DeliveryItem item = new DeliveryItem();
					item.setGoods_id(giftid);
					item.setName(giftnameArray[i]);
					item.setNum(giftnumArray[i]);  
					item.setProduct_id(giftid);
					item.setItemtype(2);
					giftitemList.add(item);
					i++;
				}
			}
			
			delivery.setOrder_id(orderId);
			this.orderFlowManager.shipping(delivery, itemList,giftitemList);
			Order order = this.orderManager.get(orderId);
			this.json="{result:1,message:'订单["+order.getSn()+"]发货成功',shipStatus:"+order.getShip_status()+"}";
		}catch(RuntimeException e){
			if(logger.isDebugEnabled()){
				logger.debug(e.getStackTrace());
			this.json="{result:0,message:\"发货失败："+e.getLocalizedMessage()+"\"}";
		}
		}
		
		return this.JSON_MESSAGE;
	}
	
/**
 * 退货
 * @return
 */
	public String returned(){
		try{
			List<DeliveryItem> itemList  = new ArrayList<DeliveryItem>();
			int i=0;
			for(Integer goods_id :goods_idArray){
				
				DeliveryItem item = new DeliveryItem();
				item.setGoods_id(goods_id);
				item.setName(goods_nameArray[i]);
				item.setNum(numArray[i]);  
				item.setProduct_id(product_idArray[i]);
				item.setSn(goods_snArray[i]);
				itemList.add(item);
				i++;
			}
			
			i=0;
			List<DeliveryItem> giftitemList  = new ArrayList<DeliveryItem>();
			if(giftidArray!=null){
				for(Integer giftid :giftidArray){
					
					DeliveryItem item = new DeliveryItem();
					item.setGoods_id(giftid);
					item.setName(giftnameArray[i]);
					item.setNum(giftnumArray[i]);  
					item.setProduct_id(giftid);
					item.setItemtype(2);
					item.setSn(goods_snArray[i]);
					giftitemList.add(item);
					i++;
				}
			}			
			
		
			delivery.setOrder_id(orderId);
			this.orderFlowManager.returned(delivery, itemList,giftitemList);
			Order order = this.orderManager.get(orderId);
			this.json="{result:1,message:'订单["+order.getSn()+"]退货成功',shipStatus:"+order.getShip_status()+"}";
		}catch(RuntimeException e){
			if(logger.isDebugEnabled()){
				logger.debug(e.getStackTrace());
			this.json="{result:0,message:\"退货失败："+e.getLocalizedMessage()+"\"}";
		}
		}
		
		return this.JSON_MESSAGE;
	}
	

	
	/**
	 * 换货
	 * @return
	 */
	public String change() {
		try {
			List<DeliveryItem> itemList = new ArrayList<DeliveryItem>();
			int i = 0;
			for (Integer goods_id : goods_idArray) {

				DeliveryItem item = new DeliveryItem();
				item.setGoods_id(goods_id);
				item.setName(goods_nameArray[i]);
				item.setNum(numArray[i]);
				item.setProduct_id(product_idArray[i]);
				item.setSn(goods_snArray[i]);
				itemList.add(item);
				i++;
			}

			delivery.setOrder_id(orderId);
			this.orderFlowManager.change(delivery, itemList, null); // 赠品退货本版本未实现
			Order order = this.orderManager.get(orderId);
			this.json = "{result:1,message:'订单[" + order.getSn()
					+ "]换货成功',shipStatus:" + order.getShip_status() + "}";
		} catch (RuntimeException e) {
	
				logger.error(e.getMessage(), e);
				
				this.json = "{result:0,message:\"换货失败："
						+ e.getLocalizedMessage() + "\"}";
			
		}

		return this.JSON_MESSAGE;
	}

	public ILogiManager getLogiManager() {
		return logiManager;
	}



	public void setLogiManager(ILogiManager logiManager) {
		this.logiManager = logiManager;
	}



	public IDlyTypeManager getDlyTypeManager() {
		return dlyTypeManager;
	}



	public void setDlyTypeManager(IDlyTypeManager dlyTypeManager) {
		this.dlyTypeManager = dlyTypeManager;
	}



	public List getLogiList() {
		return logiList;
	}



	public void setLogiList(List logiList) {
		this.logiList = logiList;
	}



	public List getDlyTypeList() {
		return dlyTypeList;
	}



	public void setDlyTypeList(List dlyTypeList) {
		this.dlyTypeList = dlyTypeList;
	}



	public IOrderManager getOrderManager() {
		return orderManager;
	}



	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
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



	public IOrderFlowManager getOrderFlowManager() {
		return orderFlowManager;
	}



	public void setOrderFlowManager(IOrderFlowManager orderFlowManager) {
		this.orderFlowManager = orderFlowManager;
	}



	public List getItemList() {
		return itemList;
	}



	public void setItemList(List itemList) {
		this.itemList = itemList;
	}



	public Delivery getDelivery() {
		return delivery;
	}



	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}



	public String[] getGoods_nameArray() {
		return goods_nameArray;
	}



	public void setGoods_nameArray(String[] goodsNameArray) {
		goods_nameArray = goodsNameArray;
	}



	public Integer[] getGoods_idArray() {
		return goods_idArray;
	}



	public void setGoods_idArray(Integer[] goodsIdArray) {
		goods_idArray = goodsIdArray;
	}



	public Integer[] getProduct_idArray() {
		return product_idArray;
	}



	public void setProduct_idArray(Integer[] productIdArray) {
		product_idArray = productIdArray;
	}



	public Integer[] getNumArray() {
		return numArray;
	}



	public void setNumArray(Integer[] numArray) {
		this.numArray = numArray;
	}



	public List getGiftList() {
		return giftList;
	}



	public void setGiftList(List giftList) {
		this.giftList = giftList;
	}



	public Integer[] getGiftnumArray() {
		return giftnumArray;
	}



	public void setGiftnumArray(Integer[] giftnumArray) {
		this.giftnumArray = giftnumArray;
	}



	public Integer[] getGiftidArray() {
		return giftidArray;
	}



	public void setGiftidArray(Integer[] giftidArray) {
		this.giftidArray = giftidArray;
	}



	public String[] getGiftnameArray() {
		return giftnameArray;
	}



	public void setGiftnameArray(String[] giftnameArray) {
		this.giftnameArray = giftnameArray;
	}



	public boolean isHasGift() {
		return hasGift;
	}



	public void setHasGift(boolean hasGift) {
		this.hasGift = hasGift;
	}



	public String[] getGoods_snArray() {
		return goods_snArray;
	}



	public void setGoods_snArray(String[] goodsSnArray) {
		goods_snArray = goodsSnArray;
	}



}
