package com.enation.app.base.core.service.solution.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.SQLNestedException;
import org.dom4j.Document;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.enation.app.base.core.service.solution.ISetupCreator;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.util.StringUtil;

public class SqlExportService {

	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	private ISetupCreator setupCreator;
	/**
	 * 备份文件，并且返回SQL语句
	 * @return
	 * @throws SQLNestedException 
	 */
	public String dumpSql() {
		List tables = getAllTableNames();
		return this.dumpSql();
		 
	}
	
	public String dumpSql(List<String> tables){
		return dumpSql(tables,"es");
	}
	
	public String dumpSql(List<String> tables,String prefix){
		String sql = "";
		
		if(tables == null)
			throw new RuntimeException("不存在数据库或者是没有表");//不存在数据库或者是没有表
		for(int i = 0; i < tables.size(); i++){
			String tabname= tables.get(i).toString();
		//	if(!tabname.equals("es_print_tmpl"))continue;
			if(tabname.startsWith("eop_"))continue;
			if(tabname.endsWith("widgetbundle"))continue;
			
			if(tabname.endsWith("border"))continue;
			if(tabname.endsWith("menu") &&!tabname.endsWith("site_menu")  )continue;
			if(tabname.endsWith("themeuri"))continue;
			if(tabname.endsWith("theme"))continue;
			if(tabname.endsWith("admintheme"))continue;
			String querySql  ;
			
			if("2".equals(EopSetting.RUNMODE)){
				EopSite site  = EopContext.getContext().getCurrentSite();
				querySql="show tables like '" + prefix + tabname + "_"+site.getUserid()+"_"+site.getId()+"'";
			}else{
				querySql="show tables like '" + prefix + tabname + "'";
			}
			
			List tblist = this.simpleJdbcTemplate.queryForList(querySql);
			if(tblist==null || tblist.isEmpty()){
				continue;
			}			
			
			sql += dumpTableSql(prefix + tabname);
		}
		
		return sql ;
	}
	
	public String dumpSql(List<String> tables, String target, Document setup){
		String sql = "";
		
		if(tables == null)
			throw new RuntimeException("不存在数据库或者是没有表");//不存在数据库或者是没有表
		for(int i = 0; i < tables.size(); i++){
			String tabname= tables.get(i).toString();
		//	if(!tabname.equals("es_print_tmpl"))continue;
			if(tabname.startsWith("eop_"))continue;
			if(tabname.endsWith("widgetbundle"))continue;
			
			if(tabname.endsWith("border"))continue;
			if(tabname.endsWith("menu") &&!tabname.endsWith("site_menu")  )continue;
			if(tabname.endsWith("themeuri"))continue;
			if(tabname.endsWith("theme"))continue;
			if(tabname.endsWith("admintheme"))continue;
			String querySql;
			
			if("2".equals(EopSetting.RUNMODE)){
				EopSite site = EopContext.getContext().getCurrentSite();
				querySql="show tables like 'es_"+ tabname +"_"+site.getUserid()+"_"+site.getId()+"'";
			}else{
				querySql="show tables like 'es_"+ tabname+"'";
			}
			
			List tblist = this.simpleJdbcTemplate.queryForList(querySql);
			if(tblist==null || tblist.isEmpty()){
				continue;
			}	
			if(tabname.toLowerCase().equals("data_cat")||tabname.toLowerCase().equals("data_model")||tabname.toLowerCase().equals("data_field")){
				setupCreator.addTable(setup, "clean", "es_" + tabname);
			}else{
				setupCreator.addTable(setup, target, "es_" + tabname);
			}
			
			sql += dumpTableSql("es_"+tabname);
		}
		
		return sql ;
	}
	
	
	/**
	 * 获取所有表
	 * @return
	 */
	public List getAllTableNames() {
		List tableList = new ArrayList();
		List list = simpleJdbcTemplate.queryForList("show tables");
		for(int i = 0; i < list.size(); i++){
			Map map = (Map)list.get(i);
			Object[] keyArray = map.keySet().toArray();
			for(int j = 0; j < keyArray.length; j++){
				if(map.get(keyArray[j].toString()) != null)
					tableList.add(map.get(keyArray[j].toString()).toString());
			}
		}
		return tableList;
	}

	/**
	 * 生成一个表的sql文件
	 * @param table
	 * @return
	 */
	public String dumpTableSql(String table) throws BadSqlGrammarException,EmptyResultDataAccessException{
	//	System.out.println("备份：" + table);

		
//		//没有主键的表，不能使用 identity_insert on
//		String[] no_key_tables= new String[]{"es_goods_spec","es_goods_complex","es_tag_rel","es_type_brand" ,"es_pmt_member_lv","es_pmt_goods"
//											,"es_order_pmt","es_dly","es_dly_type_area","es_order_gift","es_member_coupon","es_package_product" };
//		
		String sql ="";//"truncate table "+ table+"_<userid>_<siteid>;\n";//"DROP TABLE IF EXISTS " + table + ";\n";
//		sql+="truncate table "+ table+"_<userid>_<siteid>;\n";
		//sql += getCreateTableSql(table);
		//sql += getTableStatus(table);
//		if(!StringUtil.isInArray(table, no_key_tables) ){
//			sql+="set IDENTITY_INSERT  "+table+"_<userid>_<siteid> on;\n";
//		}
		sql += getInsertSql(table);
//		if(!StringUtil.isInArray(table, no_key_tables) ){
//		sql+="set IDENTITY_INSERT  "+table+"_<userid>_<siteid> off;\n";
//		}
		
		//                      
		return sql;
	}
	
	
	/**
	 * 获取一个表的建表语句
	 * @param table
	 * @return
	 */
	public String getCreateTableSql(String table)throws BadSqlGrammarException{
		Map map = simpleJdbcTemplate.queryForMap("SHOW CREATE TABLE "+table+";");
		Object temp  = map.get("Create Table");
		String sql;
		if(temp instanceof String ){
			sql = (String)temp;
		}else{
			sql =new String((byte[])temp);
		}
		 
		return sql+";\n";
	}
	
	/**
	 * 获取表的状态SQL,如自增值等
	 * @param table
	 * @return
	 */
	public String getTableStatus(String table)throws EmptyResultDataAccessException{
		String sql = "";
		Map map = simpleJdbcTemplate.queryForMap("SHOW TABLE STATUS LIKE '"+table+"'");
		if(map != null){
			if(map.get("Auto_increment") != null && !map.get("Auto_increment").equals("")){
				sql = "  AUTO_INCREMENT=" + map.get("Auto_increment").toString();
			}
		}
		sql += ";\n\n";
		return sql;
	}
	

	
	/**
	 * 获取插入数据的SQL
	 */
	public String getInsertSql(final String table)throws BadSqlGrammarException{

		String rname = table;
		 EopSite site  = EopContext.getContext().getCurrentSite();

			final Integer userid  = site.getUserid();
			final Integer siteid = site.getId();
		if("2".equals(EopSetting.RUNMODE)){
			rname  =table+"_"+userid+"_"+siteid;
		}
		StringBuffer sql = new StringBuffer();
		
		int total = simpleJdbcTemplate.queryForInt("SELECT COUNT(0) FROM " + rname);
		int pageSize = 200;
		int pageTotal = (int) Math.ceil((double) total / pageSize);
		
		ParameterizedRowMapper mapper = new ParameterizedRowMapper() {
			private String getValueString(String value,int type){
				
				String separator="";
				
				switch (type) {
				case Types.VARCHAR :
					separator="'";
					break;
				
				case Types.LONGVARCHAR :
					separator="'";
					break;
				case Types.CHAR:
					separator="'";
					break;
				case Types.DATE:
					separator="'";
					break;
				case Types.TIME:
					separator="'";
					break;
				case Types.TIMESTAMP:
					separator="'";
					break;				
				}
				
				return separator+value+separator;
				
			}
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				StringBuffer fieldstr = new StringBuffer();
				StringBuffer valuestr = new StringBuffer();
				StringBuffer sb = new StringBuffer();
				
				ResultSetMetaData rsmd = rs.getMetaData();
				sb.append("INSERT INTO "+table+"_<userid>_<siteid> (");
				int count = rsmd.getColumnCount();
				String comma = "";
				for(int i = 1; i <= count; i++){
//					System.out.println(rsmd.getColumnName(i));
					String fieldname  = rsmd.getColumnName(i);
					int type=rsmd.getColumnType(i);
					
					if(i!=1){
						fieldstr.append(",");
					}
					fieldstr.append(""+fieldname+"");
					
					String value = rs.getString(fieldname);
					if(value != null){
						valuestr.append(comma + getValueString( mysql_escape_string(value),type ));
					}else{
						valuestr.append(comma + "null");
					}
					comma = ",";
//					System.out.println(rsmd.getColumnName(i) + ":" + rs.getString(rsmd.getColumnName(i)));
				}
				
				sb.append(fieldstr); //组各字段名
				sb.append(") VALUES (");
				sb.append(valuestr);
				sb.append(");\n");
				return sb.toString();
			}
		};
		
		String  querysql;
		List list = null;
		
		for(int i = 1; i <= pageTotal; i++){
			querysql  =  "SELECT * FROM "+rname+" LIMIT "+((i-1)*pageSize)+", " + pageSize;
			list = simpleJdbcTemplate.query(querysql,mapper);
			for(int j = 0; j < list.size(); j++){
				sql.append(list.get(j).toString());
			}
		}
		
		return sql.toString();
	}
	
	/**
	 * 获取mysql的版本信息
	 * @return
	 */
	public String getMySqlVersion(){
		String version = "";
		Map map = simpleJdbcTemplate.queryForMap("SELECT version() as version");
		version = map.get("version").toString();		
		return version;
	}
	
	/**
	 * 转义SQL语句中的字符
	 * @param str
	 * @return
	 */
	private String mysql_escape_string(String str){
	       if( str == null || str.length() == 0 )
	           return str;
	       if("1".equals(EopSetting.DBTYPE)){
	    	   str = str.replaceAll("'", "\\\\\'");
	       }
	       
	       if("3".equals(EopSetting.DBTYPE)){
	    	   str = str.replaceAll("'", "''");
	       }
	       
	       str = str.replaceAll("\"", "\\\"");     
	       str = str.replaceAll("\r", "");
	       str = str.replaceAll("\n", "");
	       return str;
	}

	
	public static void main(String args[]){
		System.out.println("<p'dd'a>".replaceAll("'", "\\\\'"));
	}
	
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}


	public ISetupCreator getSetupCreator() {
		return setupCreator;
	}


	public void setSetupCreator(ISetupCreator setupCreator) {
		this.setupCreator = setupCreator;
	}
	
}
