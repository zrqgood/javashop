package com.enation.app.base.core.action;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.service.IAdColumnManager;
import com.enation.framework.action.WWAction;

/**
 * @author lzf
 * 2010-3-2 上午09:46:08
 * version 1.0
 */
public class AdColumnAction extends WWAction {
	
	private IAdColumnManager adColumnManager;
	private AdColumn adColumn;
	private Long acid;
	private String id;
	
	public String list() {
		this.webpage = this.adColumnManager.pageAdvPos(this.getPage(), this.getPageSize());
		return "list";
	}
	
	public String detail(){
		adColumn = this.adColumnManager.getADcolumnDetail(acid);
		return "detail";
	}
	
	public String delete(){
		try {
			this.adColumnManager.delAdcs(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}
	
	public String add(){
		return "add";
	}
	
	public String addSave() {
		this.adColumnManager.addAdvc(adColumn);
		this.msgs.add("新增广告位成功");
		this.urls.put("广告位列表", "adColumn!list.do");
		return this.MESSAGE;
	}
	
	public String edit(){
		adColumn = this.adColumnManager.getADcolumnDetail(acid);
		return "edit";
	}
	
	public String editSave(){
		this.adColumnManager.updateAdvc(adColumn);
		this.msgs.add("修改广告位成功");
		this.urls.put("广告位列表", "adColumn!list.do");
		return this.MESSAGE;
	}

	public IAdColumnManager getAdColumnManager() {
		return adColumnManager;
	}

	public void setAdColumnManager(IAdColumnManager adColumnManager) {
		this.adColumnManager = adColumnManager;
	}

	public AdColumn getAdColumn() {
		return adColumn;
	}

	public void setAdColumn(AdColumn adColumn) {
		this.adColumn = adColumn;
	}

	public Long getAcid() {
		return acid;
	}

	public void setAcid(Long acid) {
		this.acid = acid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
