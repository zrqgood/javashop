package com.enation.javashop.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.model.mapper.CatMapper;
import com.enation.javashop.core.service.IGoodsCatManager;

public class GoodsCatManager extends BaseSupport<Cat> implements IGoodsCatManager {
 
	public boolean checkname(String name,Integer catid){
		if(name!=null)name=name.trim();
		String sql ="select count(0) from goods_cat where name=? and cat_id!=?";
		if(catid==null){
			catid=0;
		}
		
		int count  = this.baseDaoSupport.queryForInt(sql, name,catid);
		if(count>0) return true;
		else 		return false;
	}
	
	public int delete(int catId) {
		String sql =  "select count(0) from goods_cat where parent_id = ?";
		int count = this.baseDaoSupport.queryForInt(sql,  catId );
		if (count > 0) {
			return 1; // 有子类别
		}

		sql =  "select count(0) from goods where cat_id = ?";
		count = this.baseDaoSupport.queryForInt(sql,  catId );
		if (count > 0) {
			return 2; // 有子类别
		}
		sql =  "delete from  goods_cat   where cat_id=?";
		this.baseDaoSupport.execute(sql,  catId );

		return 0;
	}

	/**
	 * 获取类别详细，将图片加上静态资源服务器地址
	 */
	public Cat getById(int catId) {
		String sql = "select * from goods_cat  where cat_id=?";
		Cat cat =baseDaoSupport.queryForObject(sql, Cat.class, catId);
		String image = cat.getImage();
		if(image!=null){
			image  =UploadUtil.replacePath(image); 
			cat.setImage(image);
		}
		return cat;
	}
	

	
	public List listChildren(Integer catId) {
		 String sql  ="select c.*,'' type_name from goods_cat c where parent_id=?";
		return this.baseDaoSupport.queryForList(sql,new CatMapper(), catId);
	}
	
	
	
	public List<Cat> listAllChildren(Integer catId) {

		String tableName =this.getTableName("goods_cat");
		String sql = "select c.*,t.name as type_name  from  "
				+ tableName
				+ " c  left join "+this.getTableName("goods_type")+" t on c.type_id = t.type_id  "
				+ " order by parent_id,cat_order";// this.findSql("all_cat_list");

		List<Cat> allCatList = daoSupport.queryForList(sql, new CatMapper());
		List<Cat> topCatList  = new ArrayList<Cat>();
		if(catId.intValue()!=0){
			Cat cat = this.getById(catId);
			topCatList.add(cat);
		}
		for(Cat cat :allCatList){
			if(cat.getParent_id().compareTo(catId)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug("发现子["+cat.getName()+"-"+cat.getCat_id() +"]"+cat.getImage());
				}
				List<Cat> children = this.getChildren(allCatList, cat.getCat_id());
				cat.setChildren(children);
				topCatList.add(cat);
			}
		}
		return topCatList;
	}
 
	
	private List<Cat> getChildren(List<Cat> catList ,Integer parentid){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找["+parentid+"]的子");
		}
		List<Cat> children =new ArrayList<Cat>();
		for(Cat cat :catList){
			if(cat.getParent_id().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug(cat.getName()+"-"+cat.getCat_id()+"是子");
				}
			 	cat.setChildren(this.getChildren(catList, cat.getCat_id()));
				children.add(cat);
			}
		}
		return children;
	}
	

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAdd(Cat cat) {
		 
		baseDaoSupport.insert("goods_cat", cat);
		int cat_id = baseDaoSupport.getLastId("goods_cat");
		String sql = "";
		//判断是否是顶级类似别，如果parentid为空或为0则为顶级类似别
		//注意末尾都要加|，以防止查询子孙时出错
		if (cat.getParent_id() != null && cat.getParent_id().intValue() != 0) { //不是顶级类别，有父
			sql = "select * from goods_cat  where cat_id=?";
			Cat parent = baseDaoSupport.queryForObject(sql, Cat.class, cat
					.getParent_id());
			cat.setCat_path(parent.getCat_path()  + cat_id+"|"); 
		} else {//是顶级类别
			cat.setCat_path(cat.getParent_id() + "|" + cat_id+"|");
		}

		sql = "update goods_cat set  cat_path=? where  cat_id=?";
		baseDaoSupport.execute(sql, new Object[] { cat.getCat_path(), cat_id });

	}

	
	public void update(Cat cat) {
		checkIsOwner(cat.getCat_id());
		// 如果有父类别，根据父的path更新这个类别的path信息
		if (cat.getParent_id() != null && cat.getParent_id().intValue() != 0) {

			String sql = "select * from goods_cat where cat_id=?";
			Cat parent = baseDaoSupport.queryForObject(sql, Cat.class, cat
					.getParent_id());
			cat.setCat_path(parent.getCat_path() + cat.getCat_id()+"|");
		} else {
			// 顶级类别，直接更新为parentid|catid
			cat.setCat_path(cat.getParent_id() + "|" + cat.getCat_id()+"|");
		}

		HashMap map = new HashMap();
		map.put("name", cat.getName());
		map.put("parent_id", cat.getParent_id());
		map.put("cat_order", cat.getCat_order());
		map.put("type_id", cat.getType_id());
		map.put("cat_path", cat.getCat_path());
		map.put("list_show", cat.getList_show());
		map.put("image", StringUtil.isEmpty(cat.getImage().trim())?null:cat.getImage());
		baseDaoSupport.update("goods_cat", map, "cat_id=" + cat.getCat_id());
	}

	
	protected void checkIsOwner(Integer catId) {
//		String sql = "select userid from  goods_cat  where cat_id=?";
//		int userid = saasDaoSupport.queryForInt(sql, catId);
//		super.checkIsOwner(userid);
	}

	

	/**
	 * 保存分类排序
	 * 
	 * @param cat_ids
	 * @param cat_sorts
	 */
	public void saveSort(int[] cat_ids, int[] cat_sorts) {
		String sql = "";
		if (cat_ids != null) {
			for (int i = 0; i < cat_ids.length; i++) {
			    sql= "update  goods_cat  set cat_order=? where cat_id=?" ;
			    baseDaoSupport.execute(sql,  cat_sorts[i], cat_ids[i] );
			}
		}
	}

	public List getNavpath(int catId) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
}
