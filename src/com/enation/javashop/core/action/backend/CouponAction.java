package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Coupons;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.service.ICouponManager;

/**
 * 优惠券
 * 
 * @author lzf<br/>
 *         2010-3-26 下午01:58:19<br/>
 *         version 1.0<br/>
 */
public class CouponAction extends PromotionAction {
	
	private ICouponManager couponManager;
	private String order;
	private Coupons coupons;
	private Integer[] id;
	private Integer[] pmtIdArray;
	private Integer cpnsid;
	private Integer point;
	
	private List listCanExchange;
	
	public String add(){
		return "add";
	}
	
	public String saveAdd(){
		if(coupons.getCpns_type()==0){
			coupons.setCpns_prefix("A" + coupons.getCpns_prefix());
		}else{
			coupons.setCpns_prefix("B" + coupons.getCpns_prefix());
		}
		
		ThreadContextHolder.getSessionContext().setAttribute("coupons", coupons);
		
		return this.select_plugin();
//		coupons.setPmt_id(1);//注意！这处要改！
		
/*		try {
			
			couponManager.add(coupons);
			this.msgs.add("优惠卷添加成功");
		} catch (Exception e) {
			this.msgs.add("优惠卷添加失败");
			e.printStackTrace();
		}
		this.urls.put("优惠卷列表", "#");*/
//		return this.MESSAGE;
	}
	
	public String edit(){
		coupons = this.couponManager.get(cpnsid);
		return "edit";
	}
	
	public String saveEdit(){
//		coupons.setPmt_id(1);//注意！这处要改！
//		try {
//			couponManager.edit(coupons);
//			this.msgs.add("优惠卷修改成功");
//		} catch (Exception e) {
//			this.msgs.add("优惠卷修改失败");
//			e.printStackTrace();
//		}
//		this.urls.put("优惠卷列表", "#");
//		return this.MESSAGE;
		ThreadContextHolder.getSessionContext().setAttribute("coupons", coupons);
		return this.select_plugin();
	}
	
	public String delete(){
		try{
			this.couponManager.delete(id, pmtIdArray);
			this.json = "{'result':0,'message':'删除成功'}";
		}catch(Exception e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String list(){
		this.webpage = couponManager.list(this.getPage(), this.getPageSize(), order);
		return "list";
	}
	
	public String addExchange(){
		listCanExchange = couponManager.listCanExchange();
		return "addExchange";
	}
	
	public String saveAddExchange(){
		
		try {
			couponManager.saveExchange(cpnsid, point);
			this.msgs.add("操作成功");
		} catch (Exception e) {
			this.msgs.add("操作失败");
			e.printStackTrace();
		}
		this.urls.put("积分兑换优惠卷列表", "coupon!exlist.do");
		return this.MESSAGE;
	}
	
	public String editExchange(){
		coupons = this.couponManager.get(cpnsid);
		return "editExchange";
	}
	
	public String deleteExchange(){
		try{
			this.couponManager.deleteExchange(id);
			this.json = "{'result':0,'message':'删除成功'}";
		}catch(Exception e){
			e.printStackTrace();
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
		
	}
	
	public String exlist(){
		this.webpage = couponManager.listExchange(this.getPage(), this.getPageSize());
		return "exlist";
	}

	public ICouponManager getCouponManager() {
		return couponManager;
	}

	public void setCouponManager(ICouponManager couponManager) {
		this.couponManager = couponManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public Integer[] getPmtIdArray() {
		return pmtIdArray;
	}

	public void setPmtIdArray(Integer[] pmtIdArray) {
		this.pmtIdArray = pmtIdArray;
	}

	public Integer getCpnsid() {
		return cpnsid;
	}

	public void setCpnsid(Integer cpnsid) {
		this.cpnsid = cpnsid;
	}

	public List getListCanExchange() {
		return listCanExchange;
	}

	public void setListCanExchange(List listCanExchange) {
		this.listCanExchange = listCanExchange;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
