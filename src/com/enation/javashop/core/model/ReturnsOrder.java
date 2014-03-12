package com.enation.javashop.core.model;

import java.util.List;

import com.enation.framework.database.NotDbField;

/**
 * 退货
 * @author kingapex
 *
 */
public class ReturnsOrder {
	   private Integer id;
	   private int  orderid    ;
	   private String ordersn;
	   private int state;
       private int type;
	   private String linkman;
	   private String linktel;
	   private String address;
	   private String attachment;           
	   private int facade   ;
	   private int  wrap ;
	   private int  invoice;
	   private String shiptype ;
	   private String  remark;
	   private long  add_time;
	   private int memberid;
	   private String membername;
	   private String refuse_reson;
	   private List itemList; //退货的商品列表
	   
	@NotDbField   
	public List getItemList() {
		return itemList;
	}
	public void setItemList(List itemList) {
		this.itemList = itemList;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	
	@NotDbField
	public String getMembername() {
		return membername;
	}
	
	
	/**
	 * 根据状态返回状态字串 
	 * 非数据库字段
	 * @return
	 */
	@NotDbField
	public String getStateStr(){
 
		
		String statestr= "";
		String name = "";
		if(type==0){
			name ="退货";
		}
		if(type==1){
			name="换货";
		}
		if(type==2){
			name="返修";
		}
		
		
		switch (state) {
		case 0:
			statestr =name+"申请已提交";
			break;
		case 1:
			statestr =name+"申请审核通过";
			break;
		case 2:
			statestr =name+"申请被拒绝";
			break;			
		default:
			break;
		}
	
		
		return statestr;
	}
	
	
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinktel() {
		return linktel;
	}
	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public int getFacade() {
		return facade;
	}
	public void setFacade(int facade) {
		this.facade = facade;
	}
	public int getWrap() {
		return wrap;
	}
	public void setWrap(int wrap) {
		this.wrap = wrap;
	}
	public int getInvoice() {
		return invoice;
	}
	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}
	public String getShiptype() {
		return shiptype;
	}
	public void setShiptype(String shiptype) {
		this.shiptype = shiptype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(long addTime) {
		add_time = addTime;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getRefuse_reson() {
		return refuse_reson;
	}
	public void setRefuse_reson(String refuseReson) {
		refuse_reson = refuseReson;
	}
	   
}
