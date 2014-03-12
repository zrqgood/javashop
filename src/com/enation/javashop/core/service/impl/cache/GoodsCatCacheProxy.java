package com.enation.javashop.core.service.impl.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.framework.cache.AbstractCacheProxy;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.service.IGoodsCatManager;

/**
 * 商品分类缓存代理
 * @author kingapex
 * 2010-5-25上午10:52:51
 */
public class GoodsCatCacheProxy extends AbstractCacheProxy<List<Cat> > implements
		IGoodsCatManager {

	private IGoodsCatManager goodsCatManager;
	
	public static final String CACHE_KEY= "goods_cat" ;
	public GoodsCatCacheProxy(IGoodsCatManager goodsCatManager) {
		super(CACHE_KEY);
		this.goodsCatManager = goodsCatManager;
	}
	
	
	private void cleanCache(){
		EopSite site  = EopContext.getContext().getCurrentSite();
		this.cache.remove(CACHE_KEY+"_"+site.getUserid() +"_"+ site.getId()+"_0") ;
	}
	
	

	
	public int delete(int catId) {
		int r  =this.goodsCatManager.delete(catId);
		if(r == 0){
			this.cleanCache();
		}
		return r;
	}

	
	public Cat getById(int catId) {
		return goodsCatManager.getById(catId);
	}

	
	public List<Cat> listAllChildren(Integer catId) {
		EopSite site  = EopContext.getContext().getCurrentSite();
		List<Cat> catList  = this.cache.get(CACHE_KEY+"_"+site.getUserid() +"_"+ site.getId()+"_"+catId);
		if(catList == null){
			catList  = this.goodsCatManager.listAllChildren(catId);
			this.cache.put(CACHE_KEY+"_"+site.getUserid() +"_"+ site.getId()+"_"+catId, catList);
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load goods cat from database");
			}			
		} else{
			if(this.logger.isDebugEnabled()){
				this.logger.debug("load goods cat from cache");
			}
		}
		return catList;
	}

	
	public List<Cat> listChildren(Integer catId) {
		return this.goodsCatManager.listChildren(catId);
	}

	
	public void saveAdd(Cat cat) {
		this.goodsCatManager.saveAdd(cat);
		this.cleanCache();
	}

	
	public void saveSort(int[] catIds, int[] catSorts) {
		this.goodsCatManager.saveSort(catIds, catSorts);
		this.cleanCache();
	}

	
	public void update(Cat cat) {
		this.goodsCatManager.update(cat);
		this.cleanCache();
	}


	public boolean checkname(String name,Integer catid) {
		return this.goodsCatManager.checkname(name,catid);
	}


	public List getNavpath(int catId) {
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("name", "首页");
		map.put("value", "0");
		list.add(map);
		Cat cat = getById(catId);
		String path = cat.getCat_path();
		path = path.substring(2, path.length()-1);
		path = path.replace("|", ",");
		String[] ids = path.split(",");
		for(String id:ids){
			Cat pcat = getById(StringUtil.toInt(id));
			Map pmap = new HashMap();
			pmap.put("name", pcat.getName());
			pmap.put("value", id);
			list.add(pmap);
		}
		return list;
	}
	
	public static void main(String[] args){
		String path = "0|1|2|3|4|";
		path = path.substring(2,path.length()-1);
		path = path.replace("|", ",");
		System.out.println(path);
	}

}
