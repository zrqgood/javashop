package com.enation.javashop.core.service.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.GoodsLvPrice;
import com.enation.javashop.core.plugin.search.GoodsDataFilterBundle;
import com.enation.javashop.core.plugin.search.GoodsSearchPluginBundle;
import com.enation.javashop.core.plugin.search.IGoodsDataFilter;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.IGoodsSortFilter;
import com.enation.javashop.core.plugin.search.IMultiSelector;
import com.enation.javashop.core.plugin.search.IPutWidgetParamsEvent;
import com.enation.javashop.core.plugin.search.SearchSelector;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsSearchManager2;
import com.enation.javashop.core.utils.UrlUtils;

public class GoodsSearchManager2 extends BaseSupport implements IGoodsSearchManager2{
 
	private GoodsSearchPluginBundle goodsSearchPluginBundle;
	private GoodsDataFilterBundle goodsDataFilterBundle;
	
	private IGoodsCatManager goodsCatManager ;
	
	public Map<String,Object> getSelector(String url) {
		
		/**
		 * 如果按类别搜索,则查询此类别,并传递给过虑器
		 * 如果未按类别搜索,则传递null
		 */
		Cat cat  =getCat(url);
		
		Map selectorMap = new HashMap<String ,String>();
		List<IGoodsSearchFilter > filterList  = goodsSearchPluginBundle.getPluginList();
		for(IGoodsSearchFilter filter:filterList){
			
			

			String urlFragment = UrlUtils.getParamStringValue(url, filter.getFilterId());
			String exSelfurl = UrlUtils.getExParamUrl(url, filter.getFilterId()).replaceAll(".html", ""); //获取排除此过滤器的url
			
			List<SearchSelector> selectorList= filter.createSelectorList(cat,exSelfurl, urlFragment);
			
			if(selectorList!=null)
				selectorMap.put(filter.getFilterId(), selectorList);
			
			
			/**
			 * 如果实现了多过滤选择器，调用多选择器生成接口
			 */
			if(filter instanceof IMultiSelector){
				Map multiSelector =  ((IMultiSelector)filter).createMultiSelector(cat, exSelfurl, urlFragment);
				if(multiSelector!=null)
					selectorMap.put(filter.getFilterId(), multiSelector);
			}
		}
		return selectorMap;
	}
	
	
	/**
	 * 调用插件向挂件参数中压入参数
	 */
	public void putParams(Map<String,Object> params,String url){
		List<IGoodsSearchFilter > filterList  = goodsSearchPluginBundle.getPluginList();
		for(IGoodsSearchFilter filter:filterList){
			if(filter instanceof IPutWidgetParamsEvent){
				String urlFragment = UrlUtils.getParamStringValue(url, filter.getFilterId());
				IPutWidgetParamsEvent event = (IPutWidgetParamsEvent)filter;
				event.putParams(params, urlFragment);
			}
		}
	}
	
	/**
	 * 返回当前搜索的分类
	 * @param url
	 * @return
	 */
	private Cat getCat(String url){
		
		Cat cat  = null;
		String catidstr = UrlUtils.getParamStringValue(url,"cat");
		if(!StringUtil.isEmpty(catidstr) && !"0".equals(catidstr)){
			Integer catid= Integer.valueOf(catidstr);
			cat = goodsCatManager.getById(catid); 
		}
		
		return cat;
	}
	
	public Page search(int pageNo,int pageSize ,String url) {
		List list  = this.list(pageNo,pageSize,url);
		int count = this.count(url);
		Page webPage = new Page(0, count, pageSize, list);
		return webPage;
	}
	
	public List list(int pageNo,int pageSize,String url){
		
		StringBuffer sql  =new StringBuffer();
		sql.append( "select g.* from ");
		sql.append(this.getTableName("goods"));
		sql.append(" g where g.goods_type = 'normal' and g.disabled=0 and market_enable=1 ");
		
		
		/***
		 * --------------------------
		 * 过滤搜索条件
		 * -------------------------
		 */
		this.filterTerm(sql,url);
	 
		
		RowMapper mapper = new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Map<String,Object> goodsMap  = new HashMap<String,Object>();
				
				goodsMap.put("name", rs.getString("name"));
				goodsMap.put("goods_id", rs.getInt("goods_id"));
				goodsMap.put("image_default", rs.getString("image_default"));
				goodsMap.put("mktprice", rs.getDouble("mktprice"));
				goodsMap.put("price", rs.getDouble("price"));
				goodsMap.put("create_time", rs.getLong("create_time"));
				goodsMap.put("last_modify",rs.getLong("last_modify") );
				goodsMap.put("type_id", rs.getInt("type_id"));
				goodsMap.put("store",rs.getInt("store") );
				goodsMap.put("have_spec", rs.getInt("have_spec"));
				goodsMap.put("sn",rs.getString("sn") );
				goodsMap.put("intro",rs.getString("intro") );
				goodsMap.put("cat_id",rs.getInt("cat_id" ));
				
				filterGoods(goodsMap,rs);
				
				return goodsMap;
			}

		};

		List goodslist = this.daoSupport.queryForList(sql.toString(), pageNo, pageSize, mapper); 
	//	System.out.println(sql);
		return goodslist;
	}
	
	
	
	
	/**
	 * 过滤商品数据
	 * @param goods
	 * @param rs
	 */
	private final void filterGoods(Map<String,Object> goods,ResultSet rs){
		List<IGoodsDataFilter > filterList  = goodsDataFilterBundle.getPluginList();
		for(IGoodsDataFilter filter:filterList){
				((IGoodsDataFilter)filter).filter(goods,rs);
		}
	}
	
	
	
	
	/**
	 * 应用会员价
	 */
	private void applyMemPrice(List<Map>   proList,List<GoodsLvPrice> memPriceList,double discount ){
		for(Map pro : proList ){
			double price  =( (BigDecimal )pro.get("price")).doubleValue() *  discount;
			for(GoodsLvPrice lvPrice:memPriceList){
				if( ((Integer)pro.get("product_id")) .intValue() == lvPrice.getProductid() ){
					price = lvPrice.getPrice();
				}
			}
			 
			pro.put("price", price);
		}
	}
	
	/**
	 * 计算搜索结果数量
	 * @param url
	 * @return
	 */
	private int count(String url){
		StringBuffer sql = new StringBuffer("select count(0) from " + this.getTableName("goods")
		+ " g where g.disabled=0 and market_enable=1 ");
		this.filterTerm(sql, url);
		return this.daoSupport.queryForInt(sql.toString());
	}
	
	
	/**
	 *过滤搜索条件 
	 * @param sql 要加条件的sql语句
	 */
	private void filterTerm(StringBuffer sql,String url){
		/**
		 * 如果按类别搜索,则查询此类别,并传递给过虑器
		 * 如果未按类别搜索,则传递null
		 */
		Cat cat  =getCat(url);
		
		
		//准备一个空的排序字串,单独交给IGoodsSortFilter(商品排序过滤器执行)
		StringBuffer sortStr = new StringBuffer();
		
		
		List<IGoodsSearchFilter > filterList  = goodsSearchPluginBundle.getPluginList();
		for(IGoodsSearchFilter filter:filterList){
			
			String urlFragment = UrlUtils.getParamStringValue(url, filter.getFilterId());
			if(filter instanceof IGoodsSortFilter){
				filter.filter(sortStr, cat, urlFragment); //商品排序过滤器执行独立的排序字串
			}else{
				filter.filter(sql,cat, urlFragment);
			}
		}
		
		sql.append(sortStr); //连接上排序字串
		
		
	}

	public GoodsSearchPluginBundle getGoodsSearchPluginBundle() {
		return goodsSearchPluginBundle;
	}

	public void setGoodsSearchPluginBundle(
			GoodsSearchPluginBundle goodsSearchPluginBundle) {
		this.goodsSearchPluginBundle = goodsSearchPluginBundle;
	}


	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}


	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}


	public GoodsDataFilterBundle getGoodsDataFilterBundle() {
		return goodsDataFilterBundle;
	}


	public void setGoodsDataFilterBundle(GoodsDataFilterBundle goodsDataFilterBundle) {
		this.goodsDataFilterBundle = goodsDataFilterBundle;
	}


	
	
}
