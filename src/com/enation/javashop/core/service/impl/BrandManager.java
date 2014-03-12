package com.enation.javashop.core.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.database.Page;
import com.enation.framework.database.StringMapper;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Brand;
import com.enation.javashop.core.model.mapper.BrandMapper;
import com.enation.javashop.core.service.IBrandManager;
import com.enation.javashop.core.service.IGoodsCatManager;

public class BrandManager extends BaseSupport<Brand> implements IBrandManager {
 
	private IGoodsCatManager goodsCatManager;
	
	/**
	 * 分页读取品牌
	 * 
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	
	public Page list(String order, int page, int pageSize) {
		order = order == null ? " brand_id desc" : order;
		String sql = "select * from brand where disabled=0";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	/**
	 * 分页读取回收站列表
	 * 
	 * @param order
	 * @param page
	 * @param pageSize
	 * @return
	 */
	
	public Page listTrash(String order, int page, int pageSize) {
		order = order == null ? " brand_id desc" : order;
		String sql = "select * from brand where disabled=1";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	/**
	 * 将回收站中的品牌还原
	 * 
	 * @param bid
	 */
	
	public void revert(String bid) {
		if (bid == null || bid.equals(""))
			return;

		String sql = "update brand set disabled=0  where brand_id in (" + bid
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	public boolean checkUsed(String ids){
		if (ids == null || ids.equals("")) return false;
		String sql  ="select count(0) from goods where brand_id in (" + ids + ")";;
		int count  = this.baseDaoSupport.queryForInt(sql);
		if(count>0)
			return true;
		else
			return false;
	}
	
	
	/**
	 * 将品牌放入回收站
	 * 
	 * @param bid
	 */
	
	public void delete(String bid) {
		if (bid == null || bid.equals(""))
			return;
		String sql = "update brand set disabled=1  where brand_id in (" + bid
				+ ")";
		this.baseDaoSupport.execute(sql);
	}

	/**
	 * 品牌删除,真正的删除。
	 * 
	 * @param bid
	 * @param files
	 */
	
	public void clean(String bid) {
		if (bid == null || bid.equals(""))
			return;
		String[] bids = bid.split(",");
		// 删除附件
		for (int i = 0; i < bids.length; i++) {
			int brand_id = Integer.parseInt(bids[i].trim());
			Brand brand = this.get(brand_id);
			if (brand != null) {
				String f = brand.getLogo();
				if (f != null && !f.trim().equals("")) {
					File file = new File(StringUtil.getRootPath() + "/" + f);
					file.delete();
				}
			}
		}

		String sql = "delete  from  brand   where brand_id in (" + bid + ")";
		this.baseDaoSupport.execute(sql);
	}

	private String getThumbpath(String file) {
		String fStr = "";
		if (!file.trim().equals("")) {
			String[] arr = file.split("/");
			fStr = "/" + arr[0] + "/" + arr[1] + "/thumb/" + arr[2];
		}
		return fStr;
	}

	/**
	 * 读取所有品牌
	 * 
	 * @return
	 */
	
	public List<Brand> list() {
		String sql = "select * from brand where disabled=0";
		List list = this.baseDaoSupport.queryForList(sql,new BrandMapper());
		return list;
	}

	
	public List<Brand> listByTypeId(Integer typeid){
		String sql ="select b.* from "+this.getTableName("type_brand")+" tb inner join "+this.getTableName("brand")+" b  on    b.brand_id = tb.brand_id where tb.type_id=? and b.disabled=0";
		List list = this.daoSupport.queryForList(sql,new BrandMapper(),typeid);
		return list;
	}
	
	

	/**
	 * 读取品牌详细
	 * 会将本地文件存储的图片地址前缀替换为静态资源服务器地址。
	 * @param brand_id
	 * @return
	 */
	
	public Brand get(Integer brand_id) {
		String sql = "select * from brand where brand_id=?";
		Brand brand = this.baseDaoSupport.queryForObject(sql, Brand.class,
				brand_id);// .queryForMap(sql, brand_id);
		String logo = brand.getLogo();
		if(logo!=null){
			logo  =UploadUtil.replacePath(logo);
		}
		brand.setLogo(logo);
		return brand;
	}

	/**
	 * 分页读取某个品牌下的商品
	 * 
	 * @param brand_id
	 * @return
	 */
	
	public Page getGoods(Integer brand_id, int pageNo, int pageSize) {
		String sql = "select * from goods where brand_id=? and disabled=0";
		Page page = this.baseDaoSupport.queryForPage(sql, pageNo, pageSize,
				brand_id);
		return page;

	}

	
	public void add(Brand brand) {		
		if(brand.getFile()!=null && brand.getFileFileName()!=null){
			brand.setLogo( UploadUtil.upload(brand.getFile(), brand.getFileFileName(), "brand") );
		}
		this.baseDaoSupport.insert("brand", brand);

	}
	
 
	
	private void deleteOldLogo(String logo){
		if(!logo.equals("http://static.enationsfot.com")){
			logo  =UploadUtil.replacePath(logo);
			FileUtil.delete(logo);
		}
		
	}  
	
	
	public void update(Brand brand) {
		
		if(brand.getLogo()!=null && "".equals(brand.getLogo())){
			this.deleteOldLogo(brand.getLogo());
		} 
		if(brand.getFile()!=null && brand.getFileFileName()!=null){
			brand.setLogo( UploadUtil.upload(brand.getFile(), brand.getFileFileName(), "brand") );
		}
		this.baseDaoSupport.update("brand", brand, "brand_id="
				+ brand.getBrand_id());
	}

	
	public List listByCatId(Integer catid) {
		String sql ="select b.* from brand b ,type_brand tb,goods_cat c where tb.brand_id=b.brand_id and c.type_id=tb.type_id and c.cat_id=?";
		return this.baseDaoSupport.queryForList(sql, Brand.class, catid);
	}
	
	public List groupByCat(){
		//取得商品分类的第一级列表
		List<Map> listCat = this.baseDaoSupport.queryForList("select * from goods_cat where parent_id = 0 order by cat_order");
		for(Map map:listCat){
			List list = this.baseDaoSupport.queryForList("select type_id from goods_cat where cat_path like '" + map.get("cat_path").toString() + "%'", new StringMapper());
			String types = StringUtil.listToString(list, ",");
			List listid = this.baseDaoSupport.queryForList("select brand_id from type_brand where type_id in (" + types + ")", new StringMapper());
			String ids = StringUtil.listToString(listid, ",");
			List<Brand> listBrand = this.baseDaoSupport.queryForList("select * from brand where brand_id in (" + ids + ")", Brand.class);
			map.put("listBrand", listBrand);
		}
		return listCat;
	}

	public boolean checkname(String name,Integer brandid) {
		if(name!=null)name=name.trim();
		String sql ="select count(0) from brand where name=? and brand_id!=?";
		if(brandid==null) brandid=0;
		
		int count =this.baseDaoSupport.queryForInt(sql, name,brandid);
		if(count>0)
			return true;
		else
			return false;
	}

	public IGoodsCatManager getGoodsCatManager() {
		return goodsCatManager;
	}

	public void setGoodsCatManager(IGoodsCatManager goodsCatManager) {
		this.goodsCatManager = goodsCatManager;
	}

}
