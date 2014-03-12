package com.enation.app.base.core.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.enation.eop.resource.model.Access;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.ExcelUtil;
import com.enation.framework.util.FileUtil;

/**
 * 流量导出器
 * @author kingapex
 *
 */
public class AccessExporter implements   Runnable  {
 
	private IDaoSupport daoSupport;
	private Integer userid;
	private Integer siteid;
	
	public void setContext(Integer userid,Integer siteid){
		this.userid = userid;
		this.siteid = siteid;
	}
	
	public void run() { 
		
		String tablename ="es_access";
		if("2".equals(EopSetting.RUNMODE)){
			tablename =tablename+"_"+this.userid+"_"+siteid;
		}
		
		//使用excel导出流量报表
		ExcelUtil excelUtil = new ExcelUtil(); 
		
		//流量报表excel模板在类包中，转为流给excelutil
		InputStream in =FileUtil.getResourceAsStream("com/enation/eop/resource/access.xls");
		excelUtil.openModal( in );
		
		//查询某站点上个月的流量记录
		String[] lastMonth = DateUtil.getLastMonth(); //得到上个月第一天和最后一天的字串数组 
		int start = (int)( DateUtil.toDate(lastMonth[0], "yyyy-MM-dd").getTime() /1000);  //上个月第一天的秒数
		int end = (int)( DateUtil.toDate(lastMonth[1], "yyyy-MM-dd").getTime() /1000);    //上个月最后一天的秒数
		
		String sql ="select * from "+tablename +" where access_time>=? and access_time<=? order by access_time";
		List<Access> list  = this.daoSupport.queryForList(sql,Access.class,start,end);
		
		if(list!=null && !list.isEmpty()){
			//写入报表，行从第2行开始
			int i=1;
			for(Access access :list){
				excelUtil.writeStringToCell(i, 0, access.getIp()); //ip
				excelUtil.writeStringToCell(i, 1, access.getArea()); //地区
				excelUtil.writeStringToCell(i, 2, access.getPage()); //页面名称
				excelUtil.writeStringToCell(i, 3,""+ access.getStay_time()); //停留时间
				excelUtil.writeStringToCell(i, 4, DateUtil.toString(new Date((long)access.getAccess_time()*1000), "yyyy-MM-dd hh:mm:ss")); //访问日期
				excelUtil.writeStringToCell(i, 5, ""+access.getPoint()); //消耗积分
				i++;
			}
			String target= EopSetting.IMG_SERVER_PATH;
			//saas 版导出目录用户上下文目录access文件夹
			if("2".equals( EopSetting.RUNMODE )){
				target =target+"/user/"+userid+"/"+siteid+"/access";
			}else{
				target =target+"/access";
			}
			File file  = new File(target);
			if(!file.exists())file.mkdirs();
			
			excelUtil.writeToFile(target+"/access"+lastMonth[0].replaceAll("-01", "")+".xls");
			
			
			
			// 更新站点累计消费积分
			sql="select sum(point) point  from "+tablename +" where access_time>=? and access_time<=?";
			Long sumpoint = (Long) this.daoSupport.queryForLong(sql,  start,end);
			this.daoSupport.execute("update eop_site set sumpoint=sumpoint+?  where id=?",sumpoint, this.siteid);
			
			//更新站点累计访问量
			sql="select count(0) c  from "+tablename+" where access_time>=? and access_time<=?" ;
			Long sumaccess = (Long) this.daoSupport.queryForLong(sql,  start,end);
			this.daoSupport.execute("update eop_site set sumaccess=sumaccess+?  where id=?", sumaccess,this.siteid);
			
			
			//删除导出的数据
			this.daoSupport.execute("delete  from "+tablename+" where access_time>=? and access_time<=?", start,end);
		}
	}

	public static void main(String[] args){
		String date ="2010-06-03";
		System.out.println( DateUtil.toDate(date, "yyyy-MM-dd") .getTime() /1000);
	}
	
	

	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	
}
