package com.enation.javashop.core.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.util.DateUtil;
import com.enation.javashop.core.plugin.field.GoodsField;
import com.enation.javashop.core.plugin.field.GoodsFieldPluginBundle;
import com.enation.javashop.core.service.IGoodsFieldManager;

/**
 * 商品字段管理
 * @author kingapex
 *
 */
public class GoodsFieldManager extends BaseSupport<GoodsField> implements IGoodsFieldManager {

	private GoodsFieldPluginBundle goodsFieldPluginBundle;
	public List<GoodsField> list(Integer type_id) {
		String sql  ="select * from goods_field where type_id=?";
		return this.baseDaoSupport.queryForList(sql, GoodsField.class, type_id);
	}

	
	public Integer add(GoodsField goodsField) {
		goodsField.setAdd_time(DateUtil.getDatelineLong());
		this.baseDaoSupport.insert("goods_field",goodsField);
		Integer fieldid = this.baseDaoSupport.getLastId("data_field");
		StringBuffer sql  = new StringBuffer();
		sql.append("alter table ");
		sql.append( this.getTableName("goods") );
		sql.append(" add ");
		sql.append( this.getFieldTypeSql( goodsField  ) );
		
		this.daoSupport.execute(sql.toString());
		this.goodsFieldPluginBundle.onCreate(goodsField);
		return fieldid;
	}
	
	
	
	/**
	 * 拼装增加字段数据类型及大小sql
	 * @param field_name
	 * @param data_type 1:字符串 2:文本
	 * @param data_size
	 * @return
	 */
	private   String getFieldTypeSql(GoodsField field){
		return  field.getEnglish_name()+" "+this.goodsFieldPluginBundle.getFieldPlugin(field).getDataType();
	}

	public void edit(GoodsField goodsField) {
		GoodsField oldDataField  = this.get(goodsField.getField_id());
		this.baseDaoSupport.update("goods_field", goodsField, "field_id="+goodsField.getField_id());
		if(!oldDataField.getEnglish_name().equals(goodsField.getEnglish_name())){//变更了字段名
			StringBuffer sql  = new StringBuffer();
			sql.append("alter table ");
			sql.append( this.getTableName("goods"));
			sql.append( " change column ");
			sql.append(oldDataField.getEnglish_name());
			sql.append(" ");
			sql.append(this.getFieldTypeSql( goodsField ));
			this.daoSupport.execute(sql.toString());
		}
	}
	

	public GoodsField get(Integer fieldid) {
		String sql  ="select * from goods_field where field_id=?";
		return this.baseDaoSupport.queryForObject(sql, GoodsField.class, fieldid);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer fieldid) {
		
		GoodsField goodsField  = this.get(fieldid);
		
		//删除模型中的相应字段
		String sql ="alter table " + this.getTableName("goods")+" drop "+ goodsField.getEnglish_name();
		this.daoSupport.execute(sql);
		
		//删除字段表里的数据
		sql ="delete from  goods_field where field_id=?";
		this.baseDaoSupport.execute(sql, fieldid);
		
	}		

	public GoodsFieldPluginBundle getGoodsFieldPluginBundle() {
		return goodsFieldPluginBundle;
	}

	public void setGoodsFieldPluginBundle(
			GoodsFieldPluginBundle goodsFieldPluginBundle) {
		this.goodsFieldPluginBundle = goodsFieldPluginBundle;
	}


	/**
	 * 批量删除某模型的所有字段
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED)	
	public void delete(String typeids) {
		String sql ="select * from goods_field where type_id in("+typeids+")";
		List<GoodsField> fieldList  = this.baseDaoSupport.queryForList(sql, GoodsField.class);
		for(GoodsField field:fieldList){
			sql="alter table "+this.getTableName("goods")+" drop column "+ field.getEnglish_name();
		}
		sql="delete from goods_field where type_id in("+typeids+")";
		this.baseDaoSupport.execute(sql);
	}

	

}
