package com.enation.cms.core.action;

import java.util.List;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.ArticlePluginBundle;
import com.enation.cms.core.service.IDataFieldManager;
import com.enation.framework.action.WWAction;

/**
 * 模型字段管理
 * @author kingapex
 * 2010-7-2下午05:17:48
 */
public class DataFieldAction extends WWAction {

	private IDataFieldManager dataFieldManager;
	private ArticlePluginBundle articlePluginBundle;
	private DataField dataField;
	private List fieldPluginList;
	private Integer fieldid;
	private Integer modelid;
	private boolean isEdit;
	private Integer[] ids;
	private Integer[] sorts;
	public String add(){
		this.isEdit = false;
		fieldPluginList = this.articlePluginBundle.getPlugins();
		return "input";
	}
	
	public String edit(){
		this.isEdit = true;
		this.dataField = this.dataFieldManager.get(fieldid);
		fieldPluginList = this.articlePluginBundle.getPlugins();		
		return "input";
	}
	
	public String saveAdd(){
		try{
			fieldid  = this.dataFieldManager.add(dataField);
			this.json ="{result:1,fieldid:"+fieldid+",message:'字段添加成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'字段添加出错'}";
			
		}
		return this.JSON_MESSAGE;
	}
	
	public String saveEdit(){
		try{
			 this.dataFieldManager.edit(dataField);
			this.json ="{result:1,message:'字段修改成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'字段修改出错'}";
			
		}
		return this.JSON_MESSAGE;		
	}
	
	public String delete(){
		try{
			 this.dataFieldManager.delete(fieldid);
			this.json ="{result:1,message:'字段删除成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'字段删除出错'}";
			
		}
		return this.JSON_MESSAGE;
	}

	public String saveSort(){
		try{
			 this.dataFieldManager.saveSort(ids, sorts);
			this.json ="{result:1,message:'排序更新成功'}";
		}catch(RuntimeException e){
			this.logger.error(e.getMessage(), e);
			this.json="{result:0,message:'"+e.getMessage()+"'}";
			
		}		
		return this.JSON_MESSAGE;
	}
	public IDataFieldManager getDataFieldManager() {
		return dataFieldManager;
	}

	public void setDataFieldManager(IDataFieldManager dataFieldManager) {
		this.dataFieldManager = dataFieldManager;
	}

	public DataField getDataField() {
		return dataField;
	}

	public void setDataField(DataField dataField) {
		this.dataField = dataField;
	}

	public Integer getFieldid() {
		return fieldid;
	}

	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}

	public Integer getModelid() {
		return modelid;
	}

	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}

	public ArticlePluginBundle getArticlePluginBundle() {
		return articlePluginBundle;
	}

	public void setArticlePluginBundle(ArticlePluginBundle articlePluginBundle) {
		this.articlePluginBundle = articlePluginBundle;
	}

	public List getFieldPluginList() {
		return fieldPluginList;
	}

	public void setFieldPluginList(List fieldPluginList) {
		this.fieldPluginList = fieldPluginList;
	}
	
	public void setIsEdit(boolean isEdit){
		this.isEdit = isEdit;
	}
	
	public boolean getIsEdit(){
		return this.isEdit;
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
	
}
