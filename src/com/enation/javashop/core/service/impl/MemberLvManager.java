package com.enation.javashop.core.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.enation.app.base.core.model.MemberLv;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.service.IMemberLvManager;
 

/**
 * 会员级别管理
 * @author kingapex
 *2010-4-29下午11:04:43
 */
public class MemberLvManager extends BaseSupport<MemberLv> implements IMemberLvManager{

	
	public void add(MemberLv lv) {
		this.baseDaoSupport.insert("member_lv", lv);
	}

	
	public void edit(MemberLv lv) {
		this.baseDaoSupport.update("member_lv", lv, "lv_id=" + lv.getLv_id());
	}

	
	public Integer getDefaultLv() {
		String sql  ="select * from member_lv where default_lv=1";
		List<MemberLv> lvList  = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		if(lvList==null || lvList.isEmpty()){
			return null;
		}
		
		return lvList.get(0).getLv_id();
	}

	
	public Page list(String order, int page, int pageSize) {
		order = order == null ? " lv_id desc" : order;
		String sql = "select * from member_lv ";
		sql += " order by  " + order;
		Page webpage = this.baseDaoSupport.queryForPage(sql, page, pageSize);
		return webpage;
	}

	
	public MemberLv get(Integer lvId) {
		if(lvId!=null&&lvId!=0){
			String sql = "select * from member_lv where lv_id=?";
			MemberLv lv = this.baseDaoSupport.queryForObject(sql, MemberLv.class,
				lvId);
			return lv;
		}else{
			return null;
		}
	}

	
	public void delete(String id) {
		if (id == null || id.equals(""))
			return;
		String sql = "delete from member_lv where lv_id in (" + id + ")";
		this.baseDaoSupport.execute(sql);
	}

	
	public List<MemberLv> list() {
		String sql = "select * from member_lv order by lv_id";
		List lvlist = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		return lvlist;
	}

	
	public List<MemberLv> list(String ids) {
		
		if( StringUtil.isEmpty(ids)) return new ArrayList();
		
		String sql = "select * from member_lv where  lv_id in("+ids+") order by lv_id";
		List lvlist = this.baseDaoSupport.queryForList(sql, MemberLv.class);
		return lvlist;
		 
	}


	public MemberLv getByPoint(int point) {
		String sql = "select * from member_lv where  point<=? order by point desc";
		List<MemberLv> list =this.baseDaoSupport.queryForList(sql, MemberLv.class,point);
		if(list==null || list.isEmpty())
		return null;
		else
			return list.get(0);
	}

	 

	 

}
