package com.enation.javashop.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.GroupBuy;
import com.enation.javashop.core.model.GroupBuyCount;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IGroupBuyManager;


/**
 * 团购管理
 * @author kingapex
 *
 */
public class GroupBuyManager extends BaseSupport implements IGroupBuyManager {

	private  IGoodsManager goodsManager ;
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(GroupBuy groupBuy) {
		if(groupBuy.getDis_type()==0 && groupBuy.getDiscount()!=null){
			Double price  =this.goodsManager.getGoods(groupBuy.getGoodsid()).getPrice();
			price = groupBuy.getDiscount() * price;
			groupBuy.setPrice(price);
		}
		groupBuy.setAdd_time(DateUtil.getDatelineLong() );
		this.baseDaoSupport.insert("group_buy", groupBuy);
		this.updateGoodsGroupBuy(groupBuy.getGoodsid(), 1);
		
		
		int groupid = this.baseDaoSupport.getLastId("group_buy");
		List<GroupBuyCount> list = groupBuy.getCountRuleList();
		for(GroupBuyCount buyCount :list){
			buyCount.setGroupid(groupid);
			this.baseDaoSupport.insert("group_buy_count", buyCount);
		}
		
	}
	
	private void updateGoodsGroupBuy(int goodsid,int isgroup){
		this.baseDaoSupport.execute("update goods set isgroup=? where goods_id=?", isgroup , goodsid);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void delete(int groupid) {
		GroupBuy groupbuy  = this.get(groupid);
		this.updateGoodsGroupBuy(groupbuy.getGoodsid(), 0);
		this.baseDaoSupport.execute("delete from group_buy_count where groupid=? ", groupid);
		this.baseDaoSupport.execute("delete from group_buy where groupid=? ", groupid);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void edit(GroupBuy groupBuy) {
		
		/**
		 * 更新原有的商品为非团购
		 */
		GroupBuy older  = this.get(groupBuy.getGroupid());
		this.updateGoodsGroupBuy(older.getGoodsid(),0);
		
		if(groupBuy.getDis_type()==0 && groupBuy.getDiscount()!=null){
			Double price  =this.goodsManager.getGoods(groupBuy.getGoodsid()).getPrice();
			price = groupBuy.getDiscount() * price;
			groupBuy.setPrice(price);
		}
		this.baseDaoSupport.update("group_buy", groupBuy, "groupid="+groupBuy.getGroupid());
		this.baseDaoSupport.execute("delete from group_buy_count where groupid=? ", groupBuy.getGroupid());
		List<GroupBuyCount> list = groupBuy.getCountRuleList();
		for(GroupBuyCount buyCount :list){
			buyCount.setGroupid(groupBuy.getGroupid());
			this.baseDaoSupport.insert("group_buy_count", buyCount);
		}
		
		/**
		 * 更新商品为团购商品
		 */
		this.updateGoodsGroupBuy(groupBuy.getGoodsid(), 1);
	}

	
	public GroupBuy get(int groupid) {
		GroupBuy groupBuy =(GroupBuy)this.baseDaoSupport.queryForObject("select  * from group_buy where groupid=?", GroupBuy.class, groupid);
		Goods goods  =this.goodsManager.getGoods(groupBuy.getGoodsid());
		groupBuy.setGoods(goods);
		groupBuy.setOldprice(goods.getPrice());
		return groupBuy;
	}

	
	public Page list(int pageNo, int pageSize) {
		return this.baseDaoSupport.queryForPage("select  * from group_buy order by add_time desc", pageNo, pageSize, GroupBuy.class);
	}

	
	public List<GroupBuy> listEnable() {
		long now  = DateUtil.getDatelineLong();
 
		return this.daoSupport.queryForList("select gb.* ,g.cat_id catid ,g.price oldprice  from "+this.getTableName("group_buy")+" gb , "+this.getTableName("goods")+" g where gb.goodsid = g.goods_id and  gb.start_time<? and gb.end_time>? order by add_time desc",  GroupBuy.class,now,now);
	}

	 
	public List<GroupBuyCount> listRule(int groupid) {
		
		return  this.baseDaoSupport.queryForList("select * from group_buy_count where groupid =? ",  GroupBuyCount.class , groupid);
	}

	public void addBuyCount() {

		long now = DateUtil.getDatelineLong(); 
		
		//读取出可用团购的数量增长规则
		List<GroupBuyCount> rulelist   =this.daoSupport.queryForList("select * from es_group_buy_count where groupid in(select groupid from es_group_buy where start_time<? and end_time>?) ",  GroupBuyCount.class,now ,now );
		
		List<GroupBuy> groupBuyList = this.listEnable();
		
		for(GroupBuy groupBuy:groupBuyList){
			
			if(this.logger.isDebugEnabled()){
				this.logger.debug("计算是否要更新团购["+groupBuy.getTitle()+"]...");
			}
			
			long addtime = groupBuy.getAdd_time();
			long hour = (now-addtime)/ 3600;  //计算添加至现在多少小时
			if(this.logger.isDebugEnabled()){
				this.logger.debug("添加时间距现在["+hour+"]小时");
			}
			
			
			for(GroupBuyCount buyCount :rulelist){
				if(this.logger.isDebugEnabled()){
					this.logger.debug("计算是否要更新团购["+groupBuy.getTitle()+"]...");
				}
				
				int start =buyCount.getStart_time();   
				int end = buyCount.getEnd_time();
				if( groupBuy.getGroupid() ==buyCount.getGroupid()  &&  start<=hour && end>hour){
					int num = buyCount.getNum();
					this.baseDaoSupport.execute("update group_buy set buy_count= buy_count+? where groupid=?", num,groupBuy.getGroupid());
					if(this.logger.isDebugEnabled()){
						this.logger.debug("更新团购["+groupBuy.getTitle()+"]团购数加["+num+"]");
					}
					break;
				}
			}
		}
		
	}
	
	
	public final IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public final void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public static void main(String[] args){
		int add_time =1301564988;
		long now = DateUtil.getDatelineLong();
		long end = (long)(DateUtil.toDate("2011-03-31 19:53", "yyyy-MM-dd HH:mm").getTime()/1000);
		System.out.println(now);
		System.out.println(end);
		System.out.println((end-now)/ 3600 );
	}

}
