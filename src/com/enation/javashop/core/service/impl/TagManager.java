package com.enation.javashop.core.service.impl;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.service.ITagManager;

/**
 * Saas式的标签管理
 * @author kingapex
 * 2010-1-18上午10:57:35
 */
public class TagManager extends BaseSupport<Tag> implements ITagManager {
	
	private IDaoSupport  daoSupport;
	private JdbcTemplate jdbcTemplate;
	
	public boolean checkname(String name,Integer tagid){
		if(name!=null)name=name.trim();
		if(tagid==null) tagid=0;
		String sql ="select count(0) from tags where tag_name=? and tag_id!=?";
		int count  = this.baseDaoSupport.queryForInt(sql, name,tagid);
		if(count>0)
			return true;
		else
			return false;
	}
	
	
	public void add(Tag tag) {
		tag.setRel_count(0);
		this.baseDaoSupport.insert("tags", tag);
		
	}

	public boolean checkJoinGoods(Integer[] tagids) {
		if(tagids==null ) return false;
		String ids =StringUtil.implode(",", tagids);
		String sql ="select count(0)  from tag_rel where tag_id in("+ids+")";	 
		int count  = this.baseDaoSupport.queryForInt(sql);
		if(count>0)
			return true;
		else
			return false;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void delete(Integer[] tagids) {
		String ids =StringUtil.implode(",", tagids);
		if(ids==null || ids.equals("")){return ;}
		//删除标签，同时删除标签的引用对照表
		this.baseDaoSupport.execute("delete from tags where tag_id in("+ids+")");
		this.daoSupport.execute("delete from "+this.getTableName("tag_rel")+" where tag_id in("+ids+")");
	}

	
	public Page list(int pageNo, int pageSize) {
		 
		return this.baseDaoSupport.queryForPage("select * from tags", pageNo, pageSize);
	}

	
	public void update(Tag tag) {

		this.baseDaoSupport.update("tags", tag, "tag_id="+tag.getTag_id());
		
	}

	public IDaoSupport<Tag> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<Tag> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public Tag getById(Integer tagid) {
		String sql  ="select * from tags where tag_id=?";
		Tag tag = this.baseDaoSupport.queryForObject(sql, Tag.class, tagid);
		return tag;
	}

	
	public List<Tag> list() {
		String sql  ="select * from tags";
		return this.baseDaoSupport.queryForList(sql,Tag.class);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void saveRels(Integer relid, Integer[] tagids) {
		//清空原有引用
		String sql = "delete from "+ this.getTableName("tag_rel") +" where rel_id=?";
		this.daoSupport.execute(sql, relid);
		if(tagids!=null ){
			
			//重新对照新的引用
			sql ="insert into " + this.getTableName("tag_rel") +"(tag_id,rel_id)values(?,?)";
			for(Integer tagid:tagids){
				this.daoSupport.execute(sql, tagid,relid);
			}
		}
	}

	
	public List<Integer> list(Integer relid) {
		String sql= "select tag_id from " + this.getTableName("tag_rel") + " where rel_id="+relid;
		List<Integer> tagids = this.jdbcTemplate.queryForList(sql, Integer.class);
		return tagids;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	
	 
}
