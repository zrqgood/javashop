package com.enation.app.base.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.enation.app.base.core.service.IBatchSqlExecutor;
import com.enation.eop.resource.ISiteManager;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.ISqlFileExecutor;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;

/**
 * 为每个站点执行sql语句
 * @author kingapex
 * 2010-10-15上午12:34:49
 */
public class BatchSqlExecutor  implements IBatchSqlExecutor {

	protected final Logger logger = Logger.getLogger(getClass());
	private ISiteManager siteManager;
	private ISqlFileExecutor sqlFileExecutor;
	private IDaoSupport daoSupport;
	
	
	/**
	 * 为saas站点执行数据库脚本
	 * 1.替换掉_<userid>_<siteid>为数据库执行基础表结构变更
	 * 2.为每个站表执行带有_<userid>_<siteid>的表变更
	 */
	public void exeucte(String sql) {
		
		//替换掉_<userid>_<siteid>为数据库执行基础表结构变更
		String baseSql = sql.replaceAll("_<userid>", "");
		baseSql = baseSql.replaceAll("_<siteid>", "");
		this.sqlFileExecutor.execute(baseSql);
		
		 sql= this.getSiteUpdateSql(sql);
		
		List siteList  = siteManager.list();
		
		for(int i=0;i<siteList.size();i++){
			
			Map site = (Map)siteList.get(i);
			String exesql = sql.replaceAll("<userid>",site.get("userid").toString());
			exesql= exesql.replaceAll("<siteid>",site.get("id").toString());
			try{
				this.sqlFileExecutor.execute(exesql);
			}catch(RuntimeException e){
				this.logger.error("为站点userid["+site.get("userid")+"]siteid["+site.get("id")+"]执行sql出错，跳过...");
			}
		}
		
	}
	
	/**
	 * 提取包含站点的sql
	 * @param sql
	 * @return
	 */
	private   String  getSiteUpdateSql(String content){
		StringBuffer sql= new StringBuffer();
		//去掉sql的注释
		content = StringUtil.delSqlComment(content);
		
		//如果有\r去掉，以便用;\n拆分为数组
		content = content.replaceAll("\r", "");
		String[] sql_ar =(content.split(";\n"));
		
		//找出含有userid或siteid的sql
		for(String s:sql_ar){
			if(s!=null && ( s.indexOf("<userid>")>0 || s.indexOf("<siteid>")>0 )){
				sql.append(s+";\n");
			}
		}
		return sql.toString();
	}
	
	public static void main(String[] args){
		String content= FileUtil.read("D:/work/eopnew/docs/version/2.2/update.sql", "UTF-8");
		//System.out.println( getSiteUpdateSql(content) );
	}
	
	/**
	 * 为所有的cms站点执行sql语句<br>
	 * 其逻辑是查找每个站点的datamodel，并为相应的表执行sql语句。其中变名以<tbname>字串表示<br>
	 * 如：alter table <tbname> add sort int; 
	 * @param sql
	 */	
	public void executeForCms(String sql) {
		List siteList  = siteManager.list();
		for(int i=0;i<siteList.size();i++){
			Map site = (Map)siteList.get(i);
			String userid  =site.get("userid").toString();
			String siteid  =site.get("id").toString();
			if(this.logger.isDebugEnabled()){
				this.logger.debug("为站点userid["+ userid +"]siteid["+siteid+"]执行cms sql ");
			}
			this.executeForCms(sql,userid,siteid );
		}
	}
	
	
	/**
	 * 读取某个站点的datamodel信息，并根据此信息的表名替换执行sql
	 * @param sql
	 * @param userid
	 * @param siteid
	 */
	private void  executeForCms(String sql,String userid,String siteid) {

		try{
			List<Map> modelList  = this.listCmsDataModel(userid,siteid);
			for(Map model:modelList){
				String  tablename =   model.get("english_name").toString();
				
				if("2".equals(EopSetting.RUNMODE) ){//saas要组合表名
					String exesql = sql.replaceAll("<tbname>","es_"+tablename+"_"+userid+"_"+siteid);
					this.sqlFileExecutor.execute(exesql);
				}else{ //独立版只替换表名
					String exesql = sql.replaceAll("<tbname>","es_"+tablename);
					if(this.logger.isDebugEnabled()){
						this.logger.debug("执行sql["+exesql+"] ");
					}
					this.sqlFileExecutor.execute(exesql);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 查找某个站点的datamodel
	 * @param userid
	 * @param siteid
	 * @return
	 */
	private List<Map> listCmsDataModel(String userid,String siteid){
		try{
			if("2".equals(EopSetting.RUNMODE)){
				String sql  ="select english_name from es_data_model_" + userid+"_"+siteid;
				return this.daoSupport.queryForList(sql);
			}else{
				String sql  ="select english_name from es_data_model";
				return this.daoSupport.queryForList(sql);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList();
		}
	}
	
	public ISiteManager getSiteManager() {
		return siteManager;
	}
	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	public ISqlFileExecutor getSqlFileExecutor() {
		return sqlFileExecutor;
	}
	public void setSqlFileExecutor(ISqlFileExecutor sqlFileExecutor) {
		this.sqlFileExecutor = sqlFileExecutor;
	}

	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
