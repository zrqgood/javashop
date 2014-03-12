package com.enation.app.base.core.action;

import java.util.List;

import com.enation.eop.resource.IIndexItemManager;
import com.enation.eop.resource.model.IndexItem;
import com.enation.framework.action.WWAction;

/**
 * 后台首页
 * @author kingapex
 * 2010-10-12下午04:53:52
 */
public class IndexAction extends WWAction {

	  private IIndexItemManager indexItemManager;
	  private List<IndexItem> itemList;
	  
      public String execute(){
    	  itemList  =  indexItemManager.list();
    	  return "index";
      }
      
            
	public IIndexItemManager getIndexItemManager() {
		return indexItemManager;
	}
	public void setIndexItemManager(IIndexItemManager indexItemManager) {
		this.indexItemManager = indexItemManager;
	}
	public List<IndexItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<IndexItem> itemList) {
		this.itemList = itemList;
	}

}
