package com.enation.cms.core.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.cms.core.model.DataModel;
import com.enation.cms.core.service.IDataModelManager;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;

/**
 * 数据模型业务类
 * @author kingapex
 * 2010-7-2下午02:37:26
 */
public class DataModelManager extends  BaseSupport<DataModel> implements IDataModelManager {

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(DataModel dataModel) {
		dataModel.setIf_audit(0);
		dataModel.setAdd_time(System.currentTimeMillis());
		this.baseDaoSupport.insert("data_model", dataModel);
		
		
		StringBuffer createTbSql= new StringBuffer("create table ");
		createTbSql.append(this.createTableName(dataModel.getEnglish_name()));
		createTbSql.append("( id mediumint(8) not null");
		createTbSql.append(" auto_increment,sort smallint(1) default 0, add_time datetime, lastmodified datetime, hit bigint, able_time");
		createTbSql.append(" bigint, state mediumint(8) comment '1:未审核,2:已审核,3:被拒绝',");
		createTbSql.append(" user_id bigint, cat_id mediumint(8), is_commend mediumint(4)");
		createTbSql.append(" comment '0:普通,1:推荐', sys_lock mediumint(4) default 0 comment '0:正常,1:系统锁定',page_title varchar(255), page_keywords varchar(255), page_description text, site_code int default 100000, siteidlist varchar(255), primary key (id) )type = MYISAM;");
		this.daoSupport.execute(createTbSql.toString());
		
	}
	
	
	private String createTableName(String tbname){
		tbname = this.getTableName(tbname);
		return tbname;
	}
	
	

	@Transactional(propagation = Propagation.REQUIRED)
	
	public void delete(Integer modelid) {
		DataModel dataModel  = this.get(modelid);
		
		
		//删除模型表
		this.daoSupport.execute("drop table "+this.createTableName(dataModel.getEnglish_name()));
		
		//删除模型字段数据记录
		this.baseDaoSupport.execute("delete from data_field where model_id=?",modelid);
		
		//删除模型记录
		this.baseDaoSupport.execute("delete from data_model where model_id=?", modelid);
		
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	
	public void edit(DataModel dataModel) {
		DataModel oldmodel = this.get(dataModel.getModel_id());
		this.baseDaoSupport.update("data_model", dataModel, "model_id="+ dataModel.getModel_id());
		String tbname = this.createTableName(dataModel.getEnglish_name());
		if(! oldmodel.getEnglish_name().equals(tbname)){// 表名变了，更新表名
			StringBuffer sql  =new StringBuffer("ALTER TABLE ");
			sql.append(this.getTableName(oldmodel.getEnglish_name()));
			sql.append(" RENAME TO ");
			sql.append(tbname);
			this.daoSupport.execute(sql.toString());
		}
		
	}

	
	
	public DataModel get(Integer modelid) {
		String sql ="select * from data_model where model_id=?";
		return this.baseDaoSupport.queryForObject(sql, DataModel.class, modelid);
	}

	
	public List<DataModel> list() {
		
		return this.baseDaoSupport.queryForList("select * from data_model order by add_time asc", DataModel.class);
	}


	public int checkIfModelInUse(Integer modelid) {
		DataModel dataModel  = this.get(modelid);
		return this.daoSupport.queryForInt("select count(0) from " +this.createTableName(dataModel.getEnglish_name()));
	}

}
