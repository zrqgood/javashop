package com.enation.javashop.core.model;

import com.enation.framework.database.NotDbField;
import com.enation.javashop.core.model.support.OrderPrice;

/**
 * 订单实体
 * @author kingapex
 *2010-4-6上午11:11:27
 */
public class Order implements java.io.Serializable {

	private Integer order_id;

	private String sn;

	private Integer member_id;

	private Integer status;

	private Integer pay_status;

	private Integer ship_status;
	
	//状态显示字串
	private String shipStatus;
	private String payStatus;
	private String orderStatus;
	
	
	//收货地区id三级省市的最后一级
	private Integer regionid; 
	private Integer shipping_id;

	private String shipping_type;

	private String shipping_area;

	private String goods;

	private Long create_time;

	private String ship_name;

	private String ship_addr;

	private String ship_zip;

	private String ship_email;

	private String ship_mobile;

	private String ship_tel;

	private String ship_day;

	private String ship_time;

	private Integer is_protect;

	private Double protect_price;

	private Double goods_amount;

	private Double shipping_amount;
	private Double discount; //优惠金额
	private Double order_amount;

	private Double weight;
	
	private Double paymoney;

	private String remark;
	
	private Integer disabled;
	
	private Integer payment_id;
	private String payment_name;
	private String payment_type;
	private Integer goods_num;
	private int gainedpoint;
	private int consumepoint;
	
	//订单的价格，非数据库字段
	private OrderPrice orderprice;
	
	
	public Integer getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(Integer goodsNum) {
		goods_num = goodsNum;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Double getGoods_amount() {
		return goods_amount;
	}

	public void setGoods_amount(Double goods_amount) {
		this.goods_amount = goods_amount;
	}

	public Integer getIs_protect() {
		is_protect =is_protect==null?0:is_protect;
		return is_protect;
	}

	public void setIs_protect(Integer is_protect) {
		this.is_protect = is_protect;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Double getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getShip_addr() {
		return ship_addr;
	}

	public void setShip_addr(String ship_addr) {
		this.ship_addr = ship_addr;
	}

	public String getShip_day() {
		return ship_day;
	}

	public void setShip_day(String ship_day) {
		this.ship_day = ship_day;
	}

	public String getShip_email() {
		return ship_email;
	}

	public void setShip_email(String ship_email) {
		this.ship_email = ship_email;
	}

	public String getShip_mobile() {
		return ship_mobile;
	}

	public void setShip_mobile(String ship_mobile) {
		this.ship_mobile = ship_mobile;
	}

	public String getShip_name() {
		return ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}

	public Integer getShip_status() {
		return ship_status;
	}

	public void setShip_status(Integer ship_status) {
		this.ship_status = ship_status;
	}

	public String getShip_tel() {
		return ship_tel;
	}

	public void setShip_tel(String ship_tel) {
		this.ship_tel = ship_tel;
	}

	public String getShip_time() {
		return ship_time;
	}

	public void setShip_time(String ship_time) {
		this.ship_time = ship_time;
	}

	public String getShip_zip() {
		return ship_zip;
	}

	public void setShip_zip(String ship_zip) {
		this.ship_zip = ship_zip;
	}

	public Double getShipping_amount() {
		return shipping_amount;
	}

	public void setShipping_amount(Double shipping_amount) {
		this.shipping_amount = shipping_amount;
	}

	public String getShipping_area() {
		return shipping_area;
	}

	public void setShipping_area(String shipping_area) {
		this.shipping_area = shipping_area;
	}

	public Integer getShipping_id() {
		return shipping_id;
	}

	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}

	public String getShipping_type() {
		return shipping_type;
	}

	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getProtect_price() {
		return protect_price;
	}

	public void setProtect_price(Double protect_price) {
		this.protect_price = protect_price;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public String getPayment_name() {
		return payment_name;
	}

	public void setPayment_name(String payment_name) {
		this.payment_name = payment_name;
	}

	public Double getPaymoney() {
		return paymoney;
	}

	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}

	public int getGainedpoint() {
		return gainedpoint;
	}

	public void setGainedpoint(int gainedpoint) {
		this.gainedpoint = gainedpoint;
	}

	public int getConsumepoint() {
		return consumepoint;
	}

	public void setConsumepoint(int consumepoint) {
		this.consumepoint = consumepoint;
	}

	@NotDbField
	public Integer getRegionid() {
		return regionid;
	}

	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}

	@NotDbField
	public String getShipStatus() {
 
		if(ship_status==0)shipStatus="未发货";
		if(ship_status==1)shipStatus="已发货";
		if(ship_status==2)shipStatus="已退货";
		if(ship_status==3)shipStatus="部分退货";
		if(ship_status==4)shipStatus="部分发货";
		
		return shipStatus;
	}

	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}
	
	@NotDbField
	public String getPayStatus() {
 
		if(pay_status==0)payStatus="未支付";
		if(pay_status==1)payStatus="已支付";
		if(pay_status==2)payStatus="已退款";
		if(pay_status==3)payStatus="部分退款";
		if(pay_status==4)payStatus="部分支付";
		
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@NotDbField
	public String getOrderStatus() {
		
	 
		
		if(status==-7)orderStatus="已换货";
		if(status==-6)orderStatus="换货被拒绝";
		if(status==-5)orderStatus="退货被拒绝";
		if(status==-4)orderStatus="申请换货";
		if(status==-3)orderStatus="申请退货";
		if(status==-2)orderStatus="退货";
		if(status==-1)orderStatus="退款";
		if(status==0)orderStatus="未付款";
		if(status==1)orderStatus="已付款";
		if(status==2)orderStatus="已发货";
		if(status==3)orderStatus="完成";
		if(status==4)orderStatus="作废";
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String paymentType) {
		payment_type = paymentType;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@NotDbField
	public OrderPrice getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(OrderPrice orderprice) {
		this.orderprice = orderprice;
	}
	
}