package com.enation.cms.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.cms.core.model.DataCat;
import com.enation.cms.core.service.ArticleCatRuntimeException;
import com.enation.eop.sdk.database.BaseSupport;

/**
 * 文章类别管理
 * @author kingapex
 * 2010-7-5上午07:53:56
 */
public class DataCatManager extends BaseSupport<DataCat> implements
		com.enation.cms.core.service.IDataCatManager {

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(DataCat cat) {
		
//		判断文章分类的父类id不能同自己的id一样
        if(cat.getParent_id() == null)
        	cat.setParent_id(0);
        else{
        	if(cat.getCat_id() != null && cat.getParent_id() == cat.getCat_id() )
        		throw new ArticleCatRuntimeException(2);//文章分类的父类id不能同自己的id一样
        		
        }
        
//      判断文章分类不能同名
        if(cat.getName() != null){
        	String sql = "select count(0) from data_cat where name = '"+ cat.getName()+"' and parent_id="+cat.getParent_id();
		    int count = this.baseDaoSupport.queryForInt(sql);	
		    if(count > 0)
		    	throw new ArticleCatRuntimeException(1);//文章分类不能同名
        }
		this.baseDaoSupport.insert("data_cat", cat);
		int cat_id = this.baseDaoSupport.getLastId("data_cat");

		String sql = "";

		if (cat.getParent_id() != null && cat.getParent_id().intValue() != 0) {
			sql = "select * from data_cat where cat_id=?";
			DataCat parent = (DataCat) this.baseDaoSupport.queryForObject(sql,
					DataCat.class, cat.getParent_id());
			if(parent != null){
				cat.setCat_path(parent.getCat_path() + cat_id+"|");
			}
		} else {
			cat.setCat_path(cat.getParent_id()+"|" + cat_id+"|");
		}
       
		//更新类别path和url
		sql = "update data_cat set cat_path='" + cat.getCat_path()
			 +"',url='newslist-"+cat_id+"-1.html' where cat_id=" + cat_id;
		this.baseDaoSupport.execute(sql);
	}

	
	public int delete(Integer catid) {

	       //获取某类别下的子类别数
			String sql = "select count(0) from data_cat where parent_id = ?";
			int count = this.baseDaoSupport.queryForInt(sql, catid);
 
			if (count > 0 )  {
				return 1;  
			}

			sql = "delete from data_cat where cat_id=" + catid;
			this.baseDaoSupport.execute(sql);
			return 0;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(DataCat cat) {
		
		//判断文章分类的父类id不能同自己的id一样
		 if(cat.getParent_id() == null)
	        	cat.setParent_id(0);
	        else{
	        	if(cat.getCat_id() != null && cat.getParent_id() == cat.getCat_id() )
	        		throw new ArticleCatRuntimeException(2);//文章分类的父类id不能同自己的id一样
	        		
	        }
//	      判断文章分类不能同名
		if(cat.getName() != null){
        	String sql = "select count(0) from data_cat where cat_id != "+cat.getCat_id()+" and name = '"+ cat.getName()+"' and parent_id="+cat.getParent_id();
		    int count = this.baseDaoSupport.queryForInt(sql);	
		    if(count > 0)
		    	throw new ArticleCatRuntimeException(1);//文章分类不能同名
        }

		if (cat.getParent_id() != null && cat.getParent_id().intValue() != 0) {
			String sql = "select * from data_cat where cat_id=?";
			DataCat parent = (DataCat) this.baseDaoSupport.queryForObject(sql,
					DataCat.class, cat.getParent_id());
			if(parent != null){
			   cat.setCat_path(parent.getCat_path() + cat.getCat_id()+"|");
			}
		} else {
			cat.setCat_path(cat.getParent_id() +"|"+ cat.getCat_id()+"|");
		}

		HashMap map = new HashMap();
		map.put("name", cat.getName());
		map.put("parent_id", cat.getParent_id());
		map.put("cat_order", cat.getCat_order());
		map.put("cat_path", cat.getCat_path());
		map.put("url", cat.getUrl());
		map.put("model_id", cat.getModel_id());
		map.put("tositemap", cat.getTositemap());
		map.put("detail_url", cat.getDetail_url());
		this.baseDaoSupport.update("data_cat", map, "cat_id=" + cat.getCat_id());
		

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveSort(int[] cat_ids, int[] cat_sorts) {
		String sql = "";
		if (cat_ids != null && cat_sorts != null && cat_ids.length == cat_sorts.length) {
			for (int i = 0; i < cat_ids.length; i++) {
				sql = "update data_cat set cat_order=" + cat_sorts[i]
						+ " where cat_id=" + cat_ids[i];
				this.baseDaoSupport.execute(sql);
			}
		}

	}
	
	
	
	public DataCat get(Integer catid) {
		
		return (DataCat) this.baseDaoSupport.queryForObject(
				"select * from data_cat where cat_id=?",
				DataCat.class, catid);
	}

	
	public List<DataCat> listAllChildren(Integer parentid) {
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找"+parentid+"的子 ");
		}
		String sql = "select * from  data_cat  order by parent_id,cat_order" ;
		List<DataCat> allCatList = this.baseDaoSupport.queryForList(sql, DataCat.class);
		List<DataCat> topCatList  = new ArrayList<DataCat>();
		for(DataCat cat :allCatList){
			if(cat.getParent_id().compareTo(parentid)==0){
				if(this.logger.isDebugEnabled()){
					this.logger.debug("发现子["+cat.getName()+"-"+cat.getCat_id() +"]");
				}
				List<DataCat> children = this.getChildren(allCatList, cat.getCat_id());
				cat.setChildren(children);
				topCatList.add(cat);
			}
		}
		return topCatList;			 
	}

	
	private List<DataCat> getChildren(List<DataCat> catList ,Integer parentid){
		if(this.logger.isDebugEnabled()){
			this.logger.debug("查找["+parentid+"]的子");
		}
		List<DataCat> children =new ArrayList<DataCat>();
		for(DataCat cat :catList){
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


	public List<DataCat> getParents(Integer catid) {
		DataCat cat  = this.get(catid);
		String path = cat.getCat_path();
		path  = path.replaceAll("\\|", ",")+"-1";
		return this.baseDaoSupport.queryForList("select * from data_cat where cat_id in("+ path+")", DataCat.class);
	}
	

}
