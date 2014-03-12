package com.enation.javashop.core.action.backend;

import java.util.List;

import net.sf.json.JSONArray;

import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IFreeOfferManager;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IProductManager;
import com.enation.javashop.core.service.ITagManager;

/**
 * 商品/货品/赠品选择器
 * @author kingapex
 *2010-4-22上午09:34:57
 */
public class SelectorAction extends WWAction {
	private IGoodsManager goodsManager;
	private IProductManager productManager;
	private IFreeOfferManager freeOfferManager;
	
	
	private String sType;
	private String keyword;
	private String order;
	
	private Integer catid;
	protected List catList; // 所有商品分类
	protected IGoodsCatManager goodsCatManager;
	
	private Integer[] goodsid;
	private Integer[] productid;
	private Integer[] giftid;
	
	private Integer[] tagid;
	private List tagList;
	private ITagManager tagManager;
	/**
	 * 商品选择器搜索商品
	 * @return
	 */
	public String goods(){ 
		if(keyword!=null) keyword= StringUtil.toUTF8(keyword);
		String name= "name".equals(sType)?keyword:null;
		String sn= "sn".equals(sType)?keyword:null;
		this.webpage = this.goodsManager.searchGoods(null,name, sn, null,null,order, this.getPage(), 5);
		return "goods";
	}
	
	public String cat(){
		this.catList = this.goodsCatManager.listAllChildren(0);
		return "cat";
	}
	
	public String tag(){
		this.tagList = this.tagManager.list();
		return "tag";
	}
	
	
	/**
	 * 货品选择器搜索货品
	 * @return
	 */
	public String product(){ 
		if(keyword!=null) keyword= StringUtil.toUTF8(keyword);
		String name= "name".equals(sType)?keyword:null;
		String sn= "sn".equals(sType)?keyword:null;
		this.webpage = productManager.list(name,sn,this.getPage(), 5, order);
		return "product";
	}
	
	

	/**
	 * 赠品选择器搜索赠品
	 * @return
	 */
	public String gift(){
		if(keyword!=null) keyword= StringUtil.toUTF8(keyword);
		this.webpage = this.freeOfferManager.list(keyword, order, this.getPage(), 3);
		return "gift";
	}
	
	
	/**
	 * 根据一批商品id查询商品列表，并成json数组。
	 * 一般供选择完成后形成商品列表使用
	 * @return
	 */
	public  String listGoods(){
		List goodsList  = this.goodsManager.list(goodsid);
		 this.json = JSONArray.fromObject(goodsList).toString();
		 
		return this.JSON_MESSAGE;
	}
	
	public String listGoodsByCat(){
		List goodsList  = this.goodsManager.listByCat(catid);
		 this.json = JSONArray.fromObject(goodsList).toString();
		 
		return this.JSON_MESSAGE;
	}
	
	public String listGoodsByTag(){
		List goodsList  = this.goodsManager.listByTag(tagid);
		 this.json = JSONArray.fromObject(goodsList).toString();
		 
		return this.JSON_MESSAGE;
	}
	
	/**
	 * 根据一批商品id查询货品列表，并成json数组。
	 * 一般供选择完成后形成货品列表使用
	 * @return
	 */
	public String listProduct(){
		List productlist  = this.productManager.list(productid);
		this.json = JSONArray.fromObject(productlist).toString();
		return this.JSON_MESSAGE;
	}
	
	/**
	 * 根据一批赠品id读取赠品列有，并形成json数组
	 *  一般供选择完成后形成赠 品列表使用
	 * @return
	 */
	public String listGift(){
		List  giftList  = this.freeOfferManager.list(this.giftid);
		this.json = JSONArray.fromObject(giftList).toString();
		return this.JSON_MESSAGE;
	}
	
	
	
	public IGoodsManager getGoodsManager() {
		return goodsManager;
	}

	public void setGoodsManager(IGoodsManager goodsManager) {
		this.goodsManager = goodsManager;
	}

	public IProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(IProductManager productManager) {
		this.productManager = productManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public Integer[] getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer[] goodsid) {
		this.goodsid = goodsid;
	}


	public IFreeOfferManager getFreeOfferManager() {
		return freeOfferManager;
	}


	public void setFreeOfferManager(IFreeOfferManager freeOfferManager) {
		this.freeOfferManager = freeOfferManager;
	}


	public Integer[] getGiftid() {
		return giftid;
	}


	public void setGiftid(Integer[] giftid) {
		this.giftid = giftid;
	}


	public Integer[] getProductid() {
		return productid;
	}


	public void setProductid(Integer[] productid) {
		this.productid = productid;
	}


	public Integer getCatid() {
		return catid;
	}


	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public List getCatList() {
		return catList;
	}

	public void setCatList(List catList) {
		this.catList = catList;
	}

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	public Integer[] getTagid() {
		return tagid;
	}

	public void setTagid(Integer[] tagid) {
		this.tagid = tagid;
	}

	public List getTagList() {
		return tagList;
	}

	public void setTagList(List tagList) {
		this.tagList = tagList;
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}
	 
}
