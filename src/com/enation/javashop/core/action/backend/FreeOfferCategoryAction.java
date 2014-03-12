package com.enation.javashop.core.action.backend;

import com.enation.framework.action.WWAction;
import com.enation.javashop.core.model.FreeOfferCategory;
import com.enation.javashop.core.service.IFreeOfferCategoryManager;

/**
 * 赠品分类
 * 
 * @author 李志富 lzf<br/>
 *         2010-1-18 上午10:43:25<br/>
 *         version 1.0<br/>
 * <br/>
 */
public class FreeOfferCategoryAction extends WWAction {

	private String name;
	private String order;
	private FreeOfferCategory freeOfferCategory;
	private Integer cat_id;
	private String id;

	private IFreeOfferCategoryManager freeOfferCategoryManager;

	public String list() throws Exception {
		this.webpage = freeOfferCategoryManager.searchFreeOfferCategory(name, order, this
				.getPage(), this.getPageSize());
		return "list";
	}

	public String trash_list() {

		this.webpage = this.freeOfferCategoryManager.pageTrash(name, order, this
				.getPage(), this.getPageSize());
		return "trash_list";
	}

	public String add() {

		return "add";
	}

	public String edit() {
		freeOfferCategory = this.freeOfferCategoryManager.get(cat_id);
		return "edit";
	}

	public String save() {
		
		freeOfferCategory.setDisabled(0);
		freeOfferCategoryManager.saveAdd(freeOfferCategory);
		this.msgs.add("赠品分类添加成功");
		this.urls.put("赠品分类列表", "freeOfferCategory!list.do");
		return this.MESSAGE;
	}

	//	
	public String saveEdit() {
		
		freeOfferCategoryManager.update(freeOfferCategory);
		this.msgs.add("赠品分类修改成功");
		this.urls.put("赠品分类列表", "freeOfferCategory!list.do");
		return this.MESSAGE;
	}

	/**
	 * 将品牌放入回收站
	 * 
	 * @return
	 */
	public String delete() {
		try {
			this.freeOfferCategoryManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'删除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 将品牌从回收站中还原
	 * 
	 * @return
	 */
	public String revert() {
		try {
			freeOfferCategoryManager.revert(id);
			this.json = "{'result':0,'message':'还原成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'还原失败'}";
		}
		return this.JSON_MESSAGE;
	}

	/**
	 * 清空回收站中的品牌
	 * 
	 * @return
	 */
	public String clean() {
		try {
			freeOfferCategoryManager.clean(id);
			this.json = "{'result':0,'message':'清除成功'}";
		} catch (RuntimeException e) {
			this.json = "{'result':1,'message':'清除失败'}";
		}
		return this.JSON_MESSAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FreeOfferCategory getFreeOfferCategory() {
		return freeOfferCategory;
	}

	public void setFreeOfferCategory(FreeOfferCategory freeOfferCategory) {
		this.freeOfferCategory = freeOfferCategory;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer catId) {
		cat_id = catId;
	}

	public IFreeOfferCategoryManager getFreeOfferCategoryManager() {
		return freeOfferCategoryManager;
	}

	public void setFreeOfferCategoryManager(
			IFreeOfferCategoryManager freeOfferCategoryManager) {
		this.freeOfferCategoryManager = freeOfferCategoryManager;
	}

	

}
