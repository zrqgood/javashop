package com.enation.javashop.core.action.backend;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.service.ITagManager;

/**
 * 标签action
 * @author kingapex
 * 2010-7-14上午11:54:15
 */
public class TagAction extends WWAction {
	
	private ITagManager tagManager;
	private Tag tag;
	private Integer[] tag_ids;
	private Integer tag_id;
	
	public String checkJoinGoods(){
		if(this.tagManager.checkJoinGoods(tag_ids)){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String checkname(){
		if( this.tagManager.checkname(tag.getTag_name(), tag.getTag_id()) ){
			this.json="{result:1}";
		}else{
			this.json="{result:0}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String add(){
		
		return "add";
	}
	
	public String edit(){
		 tag = this.tagManager.getById(tag_id);
		return "edit";
	}
	
	
	//添加标签
	public String saveAdd(){
		this.tagManager.add(tag);
		this.msgs.add("标签添加成功");
		this.urls.put("标签列表", "tag!list.do");
		return this.MESSAGE;
	}
	
	
	//保存修改
	public String saveEdit(){
		
		this.tagManager.update(tag);
 
		this.msgs.add("标签修改成功");
		this.urls.put("标签列表", "tag!list.do");
		
		return this.MESSAGE;
	}
	
	public String delete(){
		
	 	this.tagManager.delete(tag_ids);
		json = "{'result':0,'message':'删除成功'}";
		
		return this.JSON_MESSAGE	;	
	}
	
	public String list(){
		this.webpage = this.tagManager.list(this.getPage(), this.getPageSize());
		return "list";
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Integer[] getTag_ids() {
		return tag_ids;
	}

	public void setTag_ids(Integer[] tagIds) {
		tag_ids = tagIds;
	}

	public Integer getTag_id() {
		return tag_id;
	}

	public void setTag_id(Integer tagId) {
		tag_id = tagId;
	}
	
	
	
}
