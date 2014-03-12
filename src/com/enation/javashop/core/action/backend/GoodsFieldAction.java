package com.enation.javashop.core.action.backend;

import java.util.List;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.plugin.field.GoodsField;
import com.enation.javashop.core.plugin.field.GoodsFieldPluginBundle;
import com.enation.javashop.core.service.IGoodsFieldManager;

public class GoodsFieldAction extends WWAction {
	private IGoodsFieldManager goodsFieldManager;
	private GoodsFieldPluginBundle goodsFieldPluginBundle;
	
	
	private Integer typeid;
	private GoodsField goodsField;
	private List fieldPluginList;
	private Integer fieldid;
	private boolean isEdit;
	private Integer[] ids;
	private Integer[] sorts;

	private List<GoodsField> fieldList;
	
	public String list(){
		this.fieldList =this.goodsFieldManager.list(typeid);
		return "list";
	}
	
	public String add() {
		this.isEdit = false;
		fieldPluginList = this.goodsFieldPluginBundle.getPlugins();
		return this.INPUT;
	}

	public String edit() {
		this.isEdit = true;
		goodsField = this.goodsFieldManager.get(fieldid);
		fieldPluginList = this.goodsFieldPluginBundle.getPlugins();		
		return this.INPUT;
	}
	
	

	public String saveAdd() {
		try{
 
			
			fieldid  = this.goodsFieldManager.add(goodsField);
			this.json ="{result:1,fieldid:"+fieldid+",message:'字段添加成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'字段添加出错'}";
			
		}
		return this.JSON_MESSAGE;
	}

	public String saveEdit() {
		try{
			 this.goodsFieldManager.edit(goodsField);
			this.json ="{result:1,message:'字段修改成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'字段修改出错'}";
			
		}
		return this.JSON_MESSAGE;
	}
	
	public String delete(){
		try{
			goodsFieldManager.delete(fieldid);
			this.json ="{result:1,message:'字段删除成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'字段删除出错'}";
			
		}
		return this.JSON_MESSAGE;
	}	

	
	public String disInputHtml(){
		fieldList= this.goodsFieldManager.list(typeid);
		for(GoodsField field:fieldList){
			String html =this.goodsFieldPluginBundle.onDisplay(field, null);
			field.setInputHtml(html);
		}
		return "input_html";
	}
	
 
	
	
	public IGoodsFieldManager getGoodsFieldManager() {
		return goodsFieldManager;
	}

	public void setGoodsFieldManager(IGoodsFieldManager goodsFieldManager) {
		this.goodsFieldManager = goodsFieldManager;
	}

 

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public GoodsField getGoodsField() {
		return goodsField;
	}

	public void setGoodsField(GoodsField goodsField) {
		this.goodsField = goodsField;
	}

	public List getFieldPluginList() {
		return fieldPluginList;
	}

	public void setFieldPluginList(List fieldPluginList) {
		this.fieldPluginList = fieldPluginList;
	}

	public Integer getFieldid() {
		return fieldid;
	}

	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer[] getSorts() {
		return sorts;
	}

	public void setSorts(Integer[] sorts) {
		this.sorts = sorts;
	}

	public GoodsFieldPluginBundle getGoodsFieldPluginBundle() {
		return goodsFieldPluginBundle;
	}

	public void setGoodsFieldPluginBundle(
			GoodsFieldPluginBundle goodsFieldPluginBundle) {
		this.goodsFieldPluginBundle = goodsFieldPluginBundle;
	}

	public List<GoodsField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<GoodsField> fieldList) {
		this.fieldList = fieldList;
	}

}
