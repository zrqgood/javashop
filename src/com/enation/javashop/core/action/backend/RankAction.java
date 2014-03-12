package com.enation.javashop.core.action.backend;

import java.util.List;
import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.service.IRankManager;

/**
 * 统计-销售量（额）排名
 * 
 * @author lzf<br/>
 *         2010-3-10 上午11:30:40<br/>
 *         version 1.0<br/>
 */
public class RankAction extends WWAction {
	
	private final static int PAGESIZE = 20;  //排名显示的数量
	private IRankManager rankManager;
	private String order;
	private String beginTime;
	private String endTime;
	private List list;
	private Map rankall;
	
	
	public String execute(){
		String condition = "";
		if(beginTime != null && !(beginTime.equals(""))){
			condition += " and orders.create_time > " + DateUtil.toDate(beginTime, "yyyy-MM-dd").getTime();
		}
		if(endTime != null && !(endTime.equals(""))){
			condition += " and orders.create_time <" + DateUtil.toDate(endTime, "yyyy-MM-dd").getTime();
		}
		list = this.rankManager.rank_goods(1, PAGESIZE, condition, order);
		return this.SUCCESS;
	}
	
	public String rankmember(){
		String condition = "";
		if(beginTime != null && !(beginTime.equals(""))){
			condition += " and orders.create_time > " + DateUtil.toDate(beginTime, "yyyy-MM-dd").getTime();
		}
		if(endTime != null && !(endTime.equals(""))){
			condition += " and orders.create_time <" + DateUtil.toDate(endTime, "yyyy-MM-dd").getTime();
		}
		list = this.rankManager.rank_member(1, PAGESIZE, condition, order);
		return "rankmember";
	}
	
	public String rankbuy(){
		list = this.rankManager.rank_buy(1, PAGESIZE, order);
		return "rankbuy";
	}
	
	public String rankall(){
		rankall = this.rankManager.rank_all();
		return "rankall";
	}

	public IRankManager getRankManager() {
		return rankManager;
	}

	public void setRankManager(IRankManager rankManager) {
		this.rankManager = rankManager;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map getRankall() {
		return rankall;
	}

	public void setRankall(Map rankall) {
		this.rankall = rankall;
	}
	

}
