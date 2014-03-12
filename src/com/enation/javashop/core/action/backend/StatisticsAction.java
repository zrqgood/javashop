package com.enation.javashop.core.action.backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.statistics.DayAmount;
import com.enation.javashop.core.model.statistics.MonthAmount;
import com.enation.javashop.core.service.IStatisticsManager;

/**
 * 统计
 * 
 * @author lzf <br/>
 *         2010-3-9 下午03:14:18 <br/>
 *         version 1.0
 */
public class StatisticsAction extends WWAction {
	
	private List<MonthAmount> month_AmountList;
	private List<DayAmount> day_AmountList;
	private IStatisticsManager statisticsManager;
	private String year;
	private String month;
	private int daycount;
	private List<Map> orderStatList;
	
	public String monthamount(){
		if(year == null || year.equals("")){
			Date date = new Date();
			SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");
			year = sdfyear.format(date);
			SimpleDateFormat sdfmonth = new SimpleDateFormat("MM");
			month = sdfmonth.format(date);
		}
		month_AmountList = this.statisticsManager.statisticsMonth_Amount(year + "-" + month);
		day_AmountList = this.statisticsManager.statisticsDay_Amount(year + "-" + month);
		daycount = day_AmountList.size();
		return "monthamount";
	}

	
	public String statByPayment(){
		this.orderStatList  = this.statisticsManager.orderStatByPayment();
		return "payment";
	}
	
	public String statByShip(){
		this.orderStatList  = this.statisticsManager.orderStatByShip();
		return "ship";
	}	
	
	
	public List<MonthAmount> getMonth_AmountList() {
		return month_AmountList;
	}



	public void setMonth_AmountList(List<MonthAmount> monthAmountList) {
		month_AmountList = monthAmountList;
	}

	public List<DayAmount> getDay_AmountList() {
		return day_AmountList;
	}

	public void setDay_AmountList(List<DayAmount> dayAmountList) {
		day_AmountList = dayAmountList;
	}

	public IStatisticsManager getStatisticsManager() {
		return statisticsManager;
	}

	public void setStatisticsManager(IStatisticsManager statisticsManager) {
		this.statisticsManager = statisticsManager;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDaycount() {
		return daycount;
	}

	public void setDaycount(int daycount) {
		this.daycount = daycount;
	}


	public List<Map> getOrderStatList() {
		return orderStatList;
	}


	public void setOrderStatList(List<Map> orderStatList) {
		this.orderStatList = orderStatList;
	}
	
	

}
