package com.enation.javashop.core.action.backend;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.enation.app.base.core.model.MemberLv;
import com.enation.framework.action.WWAction;
import com.enation.framework.context.spring.SpringContextHolder;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.javashop.core.model.Coupons;
import com.enation.javashop.core.model.Promotion;
import com.enation.javashop.core.plugin.promotion.IPromotionPlugin;
import com.enation.javashop.core.service.ICouponManager;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IPromotionManager;
import com.enation.javashop.core.service.promotion.IPromotionMethod;
import com.enation.javashop.core.service.promotion.PromotionConditions;

/**
 * 促销规则管理
 * @author kingapex
 *2010-4-21上午11:35:55
 */
public class PromotionAction extends WWAction {
	
	//添动id如果为空，则说明是再为优惠卷设置规则
	private Integer activityid;
	
	//促销规则id，如果不为空则在编辑状态
	private Integer pmtid;
	
	private IPromotionManager promotionManager ;
	private IMemberLvManager memberLvManager;
	private ICouponManager couponManager;
	
	
	private List pmtList;
	private List pluginList;
	private List lvList;
	private List goodsList;
	private String pluginid;
	
	private  PromotionConditions conditions;
	private String solutionHtml;
	private Double money_from;
	private Double money_to;
	private Date time_begin;
	private Date time_end;
	private Integer[] lvidArray;
	private Integer[] goodsidArray;
	private Integer[] pmtidArray;
	private int ifcoupon; //是否使用优惠卷
	private String describe;
	private Promotion promotion ;
	/**
	 * 读取某活动的促销规则列表
	 * @return
	 */
	public String list(){
		pmtList = this.promotionManager.listByActivityId(activityid);
		return "list";
	}


	/**
	 * 选择促销插件
	 * @return
	 */
	public String select_plugin(){
		if(this.pmtid!=null){//编辑时找到相应插件id
			promotion= 	this.promotionManager.get(pmtid);
			this.pluginid= promotion.getPmts_id();
		}
		this.pluginList = this.promotionManager.listPmtPlugins();
		return "select_plugin";
	}
	
	
	/**
	 * 选择规则条件
	 * @return
	 */
	public String select_condition(){
		
		try{
			this.lvList  = this.memberLvManager.list();
			
			String solution =null;
			if(pmtid!=null){ //编辑时使用库中读取的开始、结束时间
				this.promotion = this.promotionManager.get(pmtid);
				this.time_begin = new Date( promotion.getPmt_time_begin());
				this.time_end = new Date(promotion.getPmt_time_end());
				solution = this.promotion.getPmt_solution();
				this.describe = this.promotion.getPmt_describe();
				this.ifcoupon = this.promotion.getPmt_ifcoupon();
				this.money_from = this.promotion.getOrder_money_from();
				this.money_to = this.promotion.getOrder_money_to();
				
				//使关联的会员级别id选中
				List<Integer> dbLvList  = this.promotionManager.listMemberLvId(pmtid);
				for(int i=0;i<lvList.size();i++){
					MemberLv lv =(MemberLv)lvList.get(i);
					for(Integer dbLvid :dbLvList ){
						if(lv.getLv_id().intValue() == dbLvid.intValue()){
							lv.setSelected(true);
						}
					}
				}
				
				this.goodsList=this.promotionManager.listGoodsId(pmtid);
				
			}else{ //新增时开始时间为今天，结束时间为之后的第10天
			
				this.time_begin = new Date();
				Calendar cal = Calendar.getInstance(); 
				cal.add(Calendar.DAY_OF_MONTH, +10);  
				this.time_end = cal.getTime();
			}
			IPromotionPlugin plugin = this.promotionManager.getPlugin(pluginid);
			conditions = new PromotionConditions( plugin.getConditions());
			String methodBeanName = plugin.getMethods();
			IPromotionMethod pmtMethod=  SpringContextHolder.getBean(methodBeanName);
			solutionHtml = pmtMethod.getInputHtml(pmtid, solution);
			
			return "select_condition";
		}catch(RuntimeException e){
			logger.error(e);
			this.msgs.add(e.getMessage());
			return this.MESSAGE;
		}
	}
	
	

	
	/**
	 * 保存
	 *  
	 * @return
	 */
	public String save(){
		

		try{
			IPromotionPlugin plugin = this.promotionManager.getPlugin(pluginid);
			conditions = new PromotionConditions( plugin.getConditions());
			
			if(pmtid!=null){
				this.promotion = this.promotionManager.get(pmtid);
			}else{
				this.promotion = new Promotion();
			}
			
			
			promotion.setOrder_money_from(money_from);
			promotion.setOrder_money_to(money_to);
			promotion.setPmt_time_begin( this.time_begin.getTime() );
			promotion.setPmt_time_end(this.time_end.getTime());
 			promotion.setPmt_ifcoupon(ifcoupon);
			promotion.setPmt_describe(this.describe);
			
 		
 			
 			if(this.activityid!=null){
 				promotion.setPmta_id(this.activityid); //促销活动id
 				promotion.setPmt_type(0); //促销活动
 			}else{
 				promotion.setPmt_type(1); //优惠券
 			}
 			
 			promotion.setPmts_id(this.pluginid);
 
 			
			if(pmtid!=null){
				this.promotionManager.edit(promotion, lvidArray, goodsidArray);

				if(this.activityid == null){ //优惠卷
					Coupons coupons = (Coupons)ThreadContextHolder.getSessionContext().getAttribute("coupons");
					coupons.setPmt_id(pmtid);
					this.couponManager.edit(coupons);
					this.msgs.add("优惠券修改成功");
				}else{
					this.msgs.add("促销规则修改成功");
				}
				
				
			}else{
				pmtid = this.promotionManager.add(promotion, lvidArray, goodsidArray);
				
				if(this.activityid == null){ //添加优惠卷
					Coupons coupons = (Coupons)ThreadContextHolder.getSessionContext().getAttribute("coupons");
					coupons.setPmt_id(pmtid);
					this.couponManager.add(coupons);
					this.msgs.add("优惠券添加成功");
				}else{
					this.msgs.add("促销规则添加成功");
				}
			}
			
			if(this.activityid == null){ 
				this.urls.put("优惠券列表", "coupon!list.do");
			}else{
				this.urls.put("此活动促销规则列表", "promotion!list.do?activityid="+promotion.getPmta_id());
			}
			
			
			return this.MESSAGE;
		 
			
		}catch(RuntimeException e){
			e.printStackTrace();
			 logger.error(e.getStackTrace());
			this.msgs.add(e.getMessage());
			return this.MESSAGE;
		}
		
	
	}
	
 
	
	/**
	 * 删除规则
	 * @return
	 */
	public String delete(){
		try{
			this.promotionManager.delete(this.pmtidArray);
			this.json="{result:0,message:'删除成功'}";
		}catch(RuntimeException e){
			this.json="{result:1,message:'"+e.getMessage()+"'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public Integer getActivityid() {
		return activityid;
	}

	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}



	public IPromotionManager getPromotionManager() {
		return promotionManager;
	}



	public void setPromotionManager(IPromotionManager promotionManager) {
		this.promotionManager = promotionManager;
	}



	public List getPmtList() {
		return pmtList;
	}



	public void setPmtList(List pmtList) {
		this.pmtList = pmtList;
	}


	public List getPluginList() {
		return pluginList;
	}


	public void setPluginList(List pluginList) {
		this.pluginList = pluginList;
	}


	public String getPluginid() {
		return pluginid;
	}


	public void setPluginid(String pluginid) {
		this.pluginid = pluginid;
	}


	public PromotionConditions getConditions() {
		return conditions;
	}


	public void setConditions(PromotionConditions conditions) {
		this.conditions = conditions;
	}


	public String getSolutionHtml() {
		return solutionHtml;
	}


	public void setSolutionHtml(String solutionHtml) {
		this.solutionHtml = solutionHtml;
	}


	public Double getMoney_from() {
		return money_from;
	}


	public void setMoney_from(Double moneyFrom) {
		money_from = moneyFrom;
	}


	public Double getMoney_to() {
		return money_to;
	}


	public void setMoney_to(Double moneyTo) {
		money_to = moneyTo;
	}


	public Date getTime_begin() {
		return time_begin;
	}


	public void setTime_begin(Date timeBegin) {
		time_begin = timeBegin;
	}


	public Date getTime_end() {
		return time_end;
	}


	public void setTime_end(Date timeEnd) {
		time_end = timeEnd;
	}


	public Integer[] getLvidArray() {
		return lvidArray;
	}


	public void setLvidArray(Integer[] lvidArray) {
		this.lvidArray = lvidArray;
	}


	public Integer[] getGoodsidArray() {
		return goodsidArray;
	}


	public void setGoodsidArray(Integer[] goodsidArray) {
		this.goodsidArray = goodsidArray;
	}



	public Promotion getPromotion() {
		return promotion;
	}


	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}


	public int getIfcoupon() {
		return ifcoupon;
	}


	public void setIfcoupon(int ifcoupon) {
		this.ifcoupon = ifcoupon;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}


	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}


	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}


	public List getLvList() {
		return lvList;
	}


	public void setLvList(List lvList) {
		this.lvList = lvList;
	}


	public Integer[] getPmtidArray() {
		return pmtidArray;
	}


	public void setPmtidArray(Integer[] pmtidArray) {
		this.pmtidArray = pmtidArray;
	}


	public Integer getPmtid() {
		return pmtid;
	}


	public void setPmtid(Integer pmtid) {
		this.pmtid = pmtid;
	}


	public List getGoodsList() {
		return goodsList;
	}


	public void setGoodsList(List goodsList) {
		this.goodsList = goodsList;
	}


	public ICouponManager getCouponManager() {
		return couponManager;
	}


	public void setCouponManager(ICouponManager couponManager) {
		this.couponManager = couponManager;
	}

}
