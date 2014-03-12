package com.enation.javashop.core.action.backend;

import java.util.List;
import java.util.Map;

import com.enation.framework.action.WWAction;
import com.enation.framework.plugin.page.JspPageTabs;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.support.GoodsEditDTO;
import com.enation.javashop.core.plugin.field.GoodsField;
import com.enation.javashop.core.plugin.field.GoodsFieldPluginBundle;
import com.enation.javashop.core.plugin.goods.GoodsPluginBundle;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsFieldManager;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IProductManager;
import com.enation.javashop.core.service.ITagManager;
import com.enation.javashop.core.service.impl.TagManager;

public class GoodsAction extends WWAction {
	protected String name;
	protected String sn;
	protected String order;
	private Integer catid;
	protected Integer[] id;

	protected Goods goods;
	protected Map goodsView;
	protected Integer goods_id;
	protected List catList; // 所有商品分类
	protected IGoodsCatManager goodsCatManager;
	protected IGoodsManager goodsManager;
	private IProductManager productManager; 
	protected Boolean is_edit;
	protected String actionName;
	protected Integer market_enable;
	private Integer[] tagids;
	private List tagList;
	
	private List<GoodsField> fieldList;
	private GoodsPluginBundle goodsPluginBundle;
	private IGoodsFieldManager goodsFieldManager;
	private GoodsFieldPluginBundle goodsFieldPluginBundle;
	private ITagManager tagManager;
	protected Map tabs;
	
	public Map getTabs(){
		return this.tabs;
	}
	
	public static final String GOODS_ADD_PAGE_ID= "goods_add";
	

	
	//商品列表
	public String list(){		
//		if(name!=null ) name = StringUtil.toUTF8(name);
//		if(sn!=null ) sn = StringUtil.toUTF8(sn);
		tagList  = this.tagManager.list();
		this.webpage = goodsManager.searchGoods(catid,name,sn,market_enable,tagids,order, this.getPage(),this.getPageSize());
		is_edit =is_edit == null?Boolean.FALSE:Boolean.TRUE;
		if(!is_edit)
			return "list";
		else
			return "edit_list";
	}

	public String batchEdit(){
		
		try{
			this.goodsManager.batchEdit();
			this.json ="{result:1}";
		}catch(RuntimeException e ){
			e.printStackTrace();
			this.json = "{result:0}";
		}
		
		return this.JSON_MESSAGE;
	}
	
	public String getCatTree(){
		this.catList = this.goodsCatManager.listAllChildren(0);
		return "cat_tree";
	}
	
	//商品回收站列表
	public String trash_list(){
		this.webpage = this.goodsManager.pageTrash(name, sn, order, this.getPage(), this.getPageSize());
		return "trash_list";
	}
	
	//删除
	public String delete(){
		try{
			this.goodsManager.delete(id);
			this.json = "{'result':0,'message':'删除成功'}";
		 }catch(RuntimeException e){
			 this.json="{'result':1,'message':'删除失败'}";
		 }
		return this.JSON_MESSAGE;
	}
	
	//还原 
	public String revert(){
		try{
			this.goodsManager.revert(id);
			this.json = "{'result':0,'message':'清除成功'}";
		 }catch(RuntimeException e){
			 this.json="{'result':1,'message':'清除失败'}";
		 }
		return this.JSON_MESSAGE;
	}
	
	//清除
	public String clean(){
		try{
			this.goodsManager.clean(id);
			this.json = "{'result':0,'message':'清除成功'}";
		 }catch(RuntimeException e){
			 e.printStackTrace();
			 this.json="{'result':1,'message':'清除失败'}";
		 }
		return this.JSON_MESSAGE;
	}
	
	
	
	public String selector_list_ajax(){
		
		return "selector";
	}
	

	private List<String> tagHtmlList ;
	/**
	 * 到商品添加页
	 */
	public String add() {
		actionName = "goods!saveAdd.do";
		is_edit =false;
		this.pageId = GOODS_ADD_PAGE_ID;
		this.tabs = JspPageTabs.getTabs("goods");
		tagHtmlList = goodsManager.fillAddInputData();
		
		return this.INPUT;
	}
	
	/**
	 * 到商品添加页
	 */
	public String addBind() {
		actionName = "goods!saveBindAdd.do";
		return "bind_goods_input";
	}
	
	
	/**
	 * 到商品修改页面
	 */
	public String  edit(){
		//先将商品更新为下架
		this.goodsManager.updateField("market_enable", 0, goods_id);
		actionName = "goods!saveEdit.do";
		is_edit =true;
		this.pageId = GOODS_ADD_PAGE_ID;
		this.tabs = JspPageTabs.getTabs("goods");
		catList = goodsCatManager.listAllChildren(0);
		GoodsEditDTO editDTO = this.goodsManager.getGoodsEditData(goods_id);
		goodsView = editDTO.getGoods();
		this.tagHtmlList = editDTO.getHtmlList();
		
		
		fieldList= this.goodsFieldManager.list(Integer.valueOf(""+ editDTO.getGoods().get("type_id")));
		for(GoodsField field:fieldList){
			String html = this.goodsFieldPluginBundle.onDisplay(field, editDTO.getGoods());
			field.setInputHtml(html);
		}
		
		return this.INPUT;
	}
	

	/**
	 * 保存商品添加
	 * @return
	 */
	public String saveAdd() {

		try{
			goodsManager.add(goods) ;
			this.msgs.add("商品添加成功");
			this.urls.put("商品列表", "goods!list.do");
		}catch(RuntimeException e) {
			e.printStackTrace();
			this.msgs.add(e.getMessage());
		}
		
		return this.MESSAGE;
	}
	 
	
	public String saveEdit(){
		try{
			goodsManager.edit(goods);
			this.msgs.add("商品修改成功");
			this.urls.put("商品列表", "goods!list.do");
			
		}catch(RuntimeException e) {
			e.printStackTrace();
			this.msgs.add(e.getMessage());
		}
		return this.MESSAGE;
	}
	
	
	public String updateMarketEnable(){
		try{
			this.goodsManager.updateField("market_enable", 1, goods_id);
			this.showSuccessJson("更新上架状态成功");
		}catch(RuntimeException e){
			this.showErrorJson("更新上架状态失败");
		}
		return this.JSON_MESSAGE;
	}
	
	
	public  String selector(){
		
		return "selector";
	}

	public List getCatList() {
		return catList;
	}

	public void setCatList(List catList) {
		this.catList = catList;
	}

 

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}


	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}



	public Goods getGoods() {
		return goods;
	}


	public Integer getGoods_id() {
		return goods_id;
	}


	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}


	public Map getGoodsView() {
		return goodsView;
	}


	public void setGoodsView(Map goodsView) {
		this.goodsView = goodsView;
	}


	public Boolean getIs_edit() {
		return is_edit;
	}


	public void setIs_edit(Boolean is_edit) {
		this.is_edit = is_edit;
	}


	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName;
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}


	public Integer[] getId() {
		return id;
	}


	public void setId(Integer[] id) {
		this.id = id;
	}

	public GoodsPluginBundle getGoodsPluginBundle() {
		return goodsPluginBundle;
	}

	public void setGoodsPluginBundle(GoodsPluginBundle goodsPluginBundle) {
		this.goodsPluginBundle = goodsPluginBundle;
	}

	public List<String> getTagHtmlList() {
		return tagHtmlList;
	}

	public void setTagHtmlList(List<String> tagHtmlList) {
		this.tagHtmlList = tagHtmlList;
	}

	public Integer getCatid() {
		return catid;
	}

	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public Integer getMarket_enable() {
		return market_enable;
	}

	public void setMarket_enable(Integer marketEnable) {
		market_enable = marketEnable;
	}

	public IGoodsFieldManager getGoodsFieldManager() {
		return goodsFieldManager;
	}

	public void setGoodsFieldManager(IGoodsFieldManager goodsFieldManager) {
		this.goodsFieldManager = goodsFieldManager;
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

	public Integer[] getTagids() {
		return tagids;
	}

	public void setTagids(Integer[] tagids) {
		this.tagids = tagids;
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

	public List getTagList() {
		return tagList;
	}

	public void setTagList(List tagList) {
		this.tagList = tagList;
	}
			
}
