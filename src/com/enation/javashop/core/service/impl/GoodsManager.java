package com.enation.javashop.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.model.MemberLv;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.Goods;
import com.enation.javashop.core.model.GoodsLvPrice;
import com.enation.javashop.core.model.support.GoodsEditDTO;
import com.enation.javashop.core.plugin.goods.GoodsPluginBundle;
import com.enation.javashop.core.service.IGoodsCatManager;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IMemberLvManager;
import com.enation.javashop.core.service.IMemberPriceManager;
import com.enation.javashop.core.service.IPackageProductManager;

/**
 * Saas式Goods业务管理
 * @author kingapex
 * 2010-1-13下午12:07:07
 */
public class GoodsManager extends BaseSupport<Goods> implements IGoodsManager {
	
	private GoodsPluginBundle goodsPluginBundle;
	private IPackageProductManager packageProductManager;
	private IGoodsCatManager goodsCatManager;
	private IMemberPriceManager memberPriceManager;
	private IMemberLvManager memberLvManager;	
	//private IDaoSupport<Goods> daoSupport;
	/**
	 * 添加商品，同时激发各种事件
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	public void add(Goods goods) {
		Map goodsMap = po2Map(goods);
		
		//触发商品添加前事件
		goodsPluginBundle.onBeforeAdd(goodsMap);
		 

	     goodsMap.put("disabled", 0);
	     goodsMap.put("create_time", System.currentTimeMillis());
	     goodsMap.put("view_count",0);
	     goodsMap.put("buy_count",0);
	     goodsMap.put("last_modify",System.currentTimeMillis());
			
			
	     this.baseDaoSupport.insert("goods", goodsMap);
	     Integer goods_id  = this.baseDaoSupport.getLastId("goods");
	 	     
	     goods.setGoods_id(goods_id);
	     goodsMap.put("goods_id", goods_id); 
	     
	   //  addGoodsCount(goods.getCat_id());
	     //触发商品添加后事件
	     goodsPluginBundle.onAfterAdd(goodsMap);
		   
	}
	
	
//	private void addGoodsCount(Integer catid){
//	     
//	     //更新商品分类的商品数+1
//	     Cat cat  = this.goodsCatManager.getById( catid);
//	     String catpath  = cat.getCat_path();
//	     catpath = catpath.replace('|', ',');
//	     
//	     this.baseDaoSupport.execute("update goods_cat set goods_count=goods_count+1 where cat_id in ("+catpath+")");
//	    		
//	}
//	

 
	
	
	/**
	 * 修改商品同时激发各种事件
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	public void edit(Goods goods) {
		if(logger.isDebugEnabled()){
			 logger.debug("开始保存商品数据...");
		}		
		Map goodsMap = this.po2Map(goods);
		this.goodsPluginBundle.onBeforeEdit(goodsMap);
		this.baseDaoSupport.update("goods", goodsMap, "goods_id=" + goods.getGoods_id());
		this.goodsPluginBundle.onAfterEdit(goodsMap);
		if(logger.isDebugEnabled()){
			 logger.debug("保存商品数据完成.");
		}	
	}




	/**
	 * 得到修改商品时的数据
	 * @param goods_id
	 * @return
	 */
	public GoodsEditDTO getGoodsEditData(Integer goods_id){
		GoodsEditDTO editDTO = new GoodsEditDTO();
		String sql ="select * from goods  where goods_id=?";
		Map goods  = this.baseDaoSupport.queryForMap(sql, goods_id);
		
		//将本地存储的图片替换为静态资源服务器地址
		String image_file =(String)goods.get("image_file");
		if(!StringUtil.isEmpty(image_file)){
			image_file  =UploadUtil.replacePath(image_file); 
			goods.put("image_file", image_file);
		}
		String intro = (String)goods.get("intro");
		if(intro!=null){
			intro  =UploadUtil.replacePath(intro); 
			goods.put("intro", intro);
		}
		
		List<String> htmlList  = 	goodsPluginBundle.onFillEditInputData(goods);
		editDTO.setGoods(goods);
		editDTO.setHtmlList(htmlList);
		
		return editDTO;
	}
	
	/**
	 * 读取一个商品的详细<br/>
	 * 处理由库中读取的默认图片和所有图片路径:<br>
	 * 如果是以本地文件形式存储，则将前缀替换为静态资源服务器地址。
	 */
	
	public Map get(Integer goods_id) {
		String sql ="select g.*,b.name as brand_name from "+this.getTableName("goods")+" g left join "+this.getTableName("brand")+" b on g.brand_id=b.brand_id ";
		sql += "  where goods_id=?";
		Map goods  = this.daoSupport.queryForMap(sql, goods_id);
		
		/**
		 * ======================
		 * 对商品图片的处理
		 * ======================
		 */
		String image_file = (String)goods.get("image_file");
		if(image_file!=null){
			image_file  =UploadUtil.replacePath(image_file); 
			goods.put("image_file", image_file);
		}
		
		String image_default = (String)goods.get("image_default");
		if(image_default!=null){
			image_default= UploadUtil.replacePath(image_default); 
			goods.put("image_default", image_default);
		}
		
		
		
		/**
		 * ======================
		 * 对会员价格的处理
		 * ======================
		 */
		if(  goods.get("have_spec")== null || ((Integer)goods.get("have_spec")).intValue() ==0 ){
			/****************计算会员价格******************/
			IUserService userService = UserServiceFactory.getUserService();
			Member member = userService.getCurrentMember();
			List<GoodsLvPrice> memPriceList = new ArrayList<GoodsLvPrice>();
			double discount =1; //默认是原价,防止无会员级别时出错
			if(member!=null ){
				memPriceList  = this.memberPriceManager.listPriceByGid( (Integer) goods.get("goods_id")  );
				MemberLv lv =this.memberLvManager.get(member.getLv_id());
				
				if( lv!=null &&  lv.getDiscount()!=null)
					discount = lv.getDiscount()/100.00;
				
				Double price  = ((BigDecimal)goods.get("price")).doubleValue() *discount;
				for(GoodsLvPrice lvPrice:memPriceList){
					if( lvPrice.getGoodsid() == ( (Integer) goods.get("goods_id")  ).intValue() && lvPrice.getLvid()== member.getLv_id().intValue()){
						price = lvPrice.getPrice();
					} 
				}
					
				goods.put("price",price);
			}
		}
		
		return goods;
	}

	public void getNavdata(Map goods){
		//lzf 2011-08-29 add,lzy modified 2011-10-04
		int catid = (Integer)goods.get("cat_id");
		List list = goodsCatManager.getNavpath(catid);
		goods.put("navdata", list);
		//lzf add end 
	}
	
	private String getListSql(int disabled){
		String sql = "select g.*,b.name as brand_name ,t.name as type_name,c.name as cat_name from "
	          +this.getTableName("goods")
            +" g left join "+this.getTableName("goods_cat")
            +" c on g.cat_id=c.cat_id left join "+this.getTableName("brand")
            +" b on g.brand_id = b.brand_id and b.disabled=0 left join "+this.getTableName("goods_type")
            +" t on g.type_id =t.type_id " 
		    +" where g.goods_type = 'normal' and g.disabled="+disabled;

		return sql;
	}
	
	/**
	 * 取得捆绑商品列表
	 * @param disabled
	 * @return
	 */
	private String getBindListSql(int disabled){
		String sql = "select g.*,b.name as brand_name ,t.name as type_name,c.name as cat_name from "
	          +this.getTableName("goods")
            +" g left join "+this.getTableName("goods_cat")
            +" c on g.cat_id=c.cat_id left join "+this.getTableName("brand")
            +" b on g.brand_id = b.brand_id left join "+this.getTableName("goods_type")
            +" t on g.type_id =t.type_id"
	    +" where g.goods_type = 'bind' and g.disabled="+disabled;
		return sql;
	}
	
	
	/**
	 * 后台搜索商品
	 * @param params 通过map的方式传递搜索参数
	 * @param page
	 * @param pageSize
	 * @return 
	 */
	public Page searchGoods(Integer catid,String name,String sn,Integer market_enable,Integer[] tagid,String order,int page,int pageSize){
		
		String sql = getListSql(0);
		
		if(order==null){
			order="goods_id desc";
		}
		
		if(name!=null && !name.equals("")){
			sql+="  and g.name like '%"+ name +"%'" ;
		}
		
		if(sn!=null && !sn.equals("")){
			sql+="   and g.sn = '"+ sn+ "'" ;
		}
		
		if(market_enable!=null){
			sql+=" and g.market_enable="+ market_enable;
		}
		
		if(catid!=null){
			Cat cat  =this.goodsCatManager.getById(catid);
			sql += " and  g.cat_id in(";
			sql += "select c.cat_id from " + this.getTableName("goods_cat")
					+ " c where c.cat_path like '" + cat.getCat_path() + "%')  ";
		}
		
		if(tagid!=null && tagid.length>0){
			String tagidstr = StringUtil.arrayToString(tagid, ",");
			sql+=" and g.goods_id in(select rel_id from "+this.getTableName("tag_rel")+" where tag_id in("+tagidstr+"))";
		}
		
		sql += " order by g."+ order ;
		
		Page webpage  = this.daoSupport.queryForPage(sql, page, pageSize);
		
		return webpage;
	}
	
	/**
	 * 后台搜索商品
	 * @param params 通过map的方式传递搜索参数
	 * @param page
	 * @param pageSize
	 * @return 
	 */
	public Page searchBindGoods(String name,String sn,String order,int page,int pageSize){
		
		String sql = getBindListSql(0);
		
		if(order==null){
			order="goods_id desc";
		}
		
		if(name!=null && !name.equals("")){
			sql+="  and g.name like '%"+ name +"%'" ;
		}
		
		if(sn!=null && !sn.equals("")){
			sql+="   and g.sn = '"+ sn+ "'" ;
		}
		
		sql += " order by g."+ order ;
		Page webpage  = this.daoSupport.queryForPage(sql, page, pageSize);
		
		List<Map> list = (List<Map>) (webpage.getResult());
		
		for(Map map:list){
			List productList = packageProductManager.list(Integer.valueOf(map.get("goods_id").toString()));
			productList = productList == null ? new ArrayList() : productList;
			map.put("productList", productList);
		}
		
		return webpage;
	}
	
	
	/**
	 * 读取商品回收站列表
	 * @param name
	 * @param sn
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page pageTrash(String name,String sn,String order,int page,int pageSize){
		
		String sql = getListSql(1);
		if(order==null){
			order="goods_id desc";
		}
		
		if(name!=null && !name.equals("")){
			sql+="  and g.name like '%"+ name +"%'" ;
		}
		
		if(sn!=null && !sn.equals("")){
			sql+="   and g.sn = '"+ sn+ "'" ;
		}
		
		sql += " order by g."+ order ;
 
		Page webpage  = this.daoSupport.queryForPage(sql, page, pageSize);
		
		return webpage;
	}
	
	


	/**
	 * 批量将商品放入回收站
	 * @param ids
	 */
	public void delete(Integer[] ids){
		if(ids==null) return ;

		String id_str = StringUtil.arrayToString(ids, ",");
		String sql = "update  goods set disabled=1  where goods_id in ("+ id_str +")";
		this.baseDaoSupport.execute(sql);
	}
	
	
	
	
	/**
	 * 还原
	 * @param ids
	 */
	public void  revert(Integer[] ids){
		if(ids==null) return ;
		String id_str = StringUtil.arrayToString(ids, ",");
		String sql = "update  goods set disabled=0  where goods_id in ("+id_str+")";
		this.baseDaoSupport.execute(sql);
	}
	
	/**
	 * 清除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void clean(Integer[] ids){
		if(ids==null) return ;
		this.goodsPluginBundle.onGoodsDelete(ids);
		
		String id_str = StringUtil.arrayToString(ids, ",");
		String sql ="delete  from goods  where goods_id in ("+id_str+")";
		this.baseDaoSupport.execute(sql);
	 
	}
	

	
	public List list(Integer[] ids) {
		if(ids==null || ids.length==0) return new ArrayList();
		String idstr = StringUtil.arrayToString(ids, ",");
		String sql ="select * from goods where goods_id in("+idstr+")";
		return this.baseDaoSupport.queryForList(sql);
	}
	
	public List<String> fillAddInputData(){
		return goodsPluginBundle.onFillAddInputData();
	}



	public GoodsPluginBundle getGoodsPluginBundle() {
		return goodsPluginBundle;
	}



	public void setGoodsPluginBundle(GoodsPluginBundle goodsPluginBundle) {
		this.goodsPluginBundle = goodsPluginBundle;
	}
	
	/**
	 * 将po对象中有属性和值转换成map
	 * 
	 * @param po
	 * @return
	 */
	protected Map po2Map(Object po) {
		Map poMap = new HashMap();
		Map map = new HashMap();
		try {
			map = BeanUtils.describe(po);
		} catch (Exception ex) {
		}
		Object[] keyArray = map.keySet().toArray();
		for (int i = 0; i < keyArray.length; i++) {
			String str = keyArray[i].toString();
			if (str != null && !str.equals("class")) {
				if (map.get(str) != null) {
					poMap.put(str, map.get(str));
				}
			}
		}
		return poMap;
	}


	public IPackageProductManager getPackageProductManager() {
		return packageProductManager;
	}


	public void setPackageProductManager(
			IPackageProductManager packageProductManager) {
		this.packageProductManager = packageProductManager;
	}


	
	public Goods getGoods(Integer goods_id) {
		Goods goods = this.baseDaoSupport.queryForObject("select * from goods where goods_id=?", Goods.class, goods_id);
		return goods;
	}


	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}


	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void batchEdit() {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String[] ids = request.getParameterValues("goodsidArray");
		String[] names = request.getParameterValues("name");
		String[] prices =  request.getParameterValues("price");
		String[] cats =  request.getParameterValues("catidArray");
		String[] market_enable =  request.getParameterValues("market_enables");
		String[] store =  request.getParameterValues("store");
		String[] sord =  request.getParameterValues("sord");
		
		String sql ="";
		
	 
		for(int i=0;i<ids.length;i++){
			sql ="";
			 if(names!=null && names.length>0){
				 if(!StringUtil.isEmpty(names[i])){
					 if(!sql.equals("")) sql+=",";
					 sql+=" name='"+ names[i] +"'"; 
				 }
			 }
			 
			 if(prices!=null && prices.length>0){
				 if(!StringUtil.isEmpty(prices[i])){
					 if(!sql.equals("")) sql+=",";
					 sql+=" price=" + prices[i];
				 }
			 }
			 if(cats!=null && cats.length>0){
				 if(!StringUtil.isEmpty(cats[i])){
					 if(!sql.equals("")) sql+=",";
					 sql+=" cat_id=" + cats[i];
				 }
			 }			 
			 if(store!=null && store.length>0){
				 if(!StringUtil.isEmpty(store[i])){
					 if(!sql.equals("")) sql+=",";
					 sql+=" store=" + store[i];
				 }
			 }
			 if(market_enable!=null && market_enable.length>0){
				 if(!StringUtil.isEmpty(market_enable[i])){
					 if(!sql.equals("")) sql+=",";
					 sql+=" market_enable=" + market_enable[i];
				 }
			 }	
			 if(sord!=null && sord.length>0){
				 if(!StringUtil.isEmpty(sord[i])){
					 if(!sql.equals("")) sql+=",";
					 sql+=" sord=" + sord[i];
				 }
			 }	
			 sql= "update  goods set "+sql +" where goods_id=?";
			 this.baseDaoSupport.execute(sql, ids[i]);
			  
		}
	}


	public Map census() {
		//计算上架商品总数
		String sql  ="select count(0) from goods where market_enable=1 and  disabled = 0";
		int salecount = this.baseDaoSupport.queryForInt(sql);
		
		//计算下架商品总数
		sql  ="select count(0) from goods where market_enable=0 and  disabled = 0";
		int unsalecount = this.baseDaoSupport.queryForInt(sql);
		
		//计算回收站总数
		sql  ="select count(0) from goods where market_enable=0 and  disabled = 1";
		int disabledcount = this.baseDaoSupport.queryForInt(sql);
		
		//读取商品评论数
		sql ="select count(0) from comments where   for_comment_id is null  and commenttype='goods' and object_type='discuss'";
		int discusscount = this.baseDaoSupport.queryForInt(sql);
		
		//读取商品评论数
		sql ="select count(0) from comments where for_comment_id is null  and  commenttype='goods' and object_type='ask'";
		int askcount = this.baseDaoSupport.queryForInt(sql);		
		
		
		
		Map<String,Integer> map = new HashMap<String, Integer>(2);
		map.put("salecount", salecount);
		map.put("unsalecount", unsalecount);
		map.put("disabledcount", disabledcount);
		map.put("allcount", unsalecount+salecount);
		map.put("discuss", discusscount);
		map.put("ask", askcount);
		return map;
	}

	public List<Map> list() {
		String sql = "select * from goods where disabled = 0";
		return this.baseDaoSupport.queryForList(sql);
	}

	public IMemberPriceManager getMemberPriceManager() {
		return memberPriceManager;
	}


	public void setMemberPriceManager(IMemberPriceManager memberPriceManager) {
		this.memberPriceManager = memberPriceManager;
	}


	public IMemberLvManager getMemberLvManager() {
		return memberLvManager;
	}


	public void setMemberLvManager(IMemberLvManager memberLvManager) {
		this.memberLvManager = memberLvManager;
	}


	@Override
	public void updateField(String filedname, Object value, Integer goodsid) {
		this.baseDaoSupport.execute("update goods set "+filedname +"=? where goods_id=?", value,goodsid);
	}


	@Override
	public List listByCat(Integer catid) {
		String sql =getListSql(0);
		if(catid.intValue()!=0){
			Cat cat  =this.goodsCatManager.getById(catid);
			sql += " and  g.cat_id in(";
			sql += "select c.cat_id from " + this.getTableName("goods_cat")
					+ " c where c.cat_path like '" + cat.getCat_path() + "%')  ";
		}
		return this.daoSupport.queryForList(sql);
	}


	@Override
	public List listByTag(Integer[] tagid) {
		
		String sql =getListSql(0);
		if(tagid!=null && tagid.length>0){
			String tagidstr = StringUtil.arrayToString(tagid, ",");
			sql+=" and g.goods_id in(select rel_id from "+this.getTableName("tag_rel")+" where tag_id in("+tagidstr+"))";
		}
		return this.daoSupport.queryForList(sql);
	}

 }
