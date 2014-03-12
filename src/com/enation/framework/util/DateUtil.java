package com.enation.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关的操作
 * @author Dawei
 *  
 */

public class DateUtil {

	/**
	 * 将一个字符串转换成日期格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if((""+date).equals("")){
			return null;
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 把日期转换成字符串型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern){
		if(date == null){
			return "";
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}

	
	
	/**
	 * 获取上个月的开始结束时间
	 * @return
	 */
	public static String[] getLastMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH) + 1;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";

		   if (month > 1) {
		    month--;
		   } else {
		    year--;
		    month = 12;
		   }
		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   String[] lastMonth = new String[2];
		   lastMonth[0] = firstDay;
		   lastMonth[1] = lastDay;

		 //  System.out.println(lastMonth[0] + "||" + lastMonth[1]);
		   return lastMonth;
		}
	
	
	/**
	 * 获取当月的开始结束时间
	 * @return
	 */
	public static String[] getCurrentMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH)+1 ;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";


		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   String[] currentMonth = new String[2];
		   currentMonth[0] = firstDay;
		   currentMonth[1] = lastDay;

		 //  System.out.println(lastMonth[0] + "||" + lastMonth[1]);
		   return currentMonth;
		}
		
	
	
	public static int getDateline(){
		
		return (int)(System.currentTimeMillis()/1000);
	}
	
	public static int getDateline(String date){
		return (int)(toDate(date, "yyyy-MM-dd").getTime()/1000);
	}
	//lzf 经测，int最大值反应到日期上为2038-01-19，故补充以下两个方法
	public static long getDatelineLong(){
		
		return (long)(System.currentTimeMillis()/1000);
	}
	
	public static long getDatelineLong(String date){
		return (long)(toDate(date, "yyyy-MM-dd").getTime()/1000);
	}
	
	public static void main(String[] args){
//		System.out.println(DateUtil.getDateline("2038-01-20"));
		System.out.println(DateUtil.getDatelineLong("2011-12-31"));
	}
}
