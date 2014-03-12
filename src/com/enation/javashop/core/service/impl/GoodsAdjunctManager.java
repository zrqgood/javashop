package com.enation.javashop.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.database.BaseSupport;
import com.enation.javashop.core.model.GoodsAdjunct;
import com.enation.javashop.core.model.GoodsComplex;
import com.enation.javashop.core.service.IGoodsAdjunctManager;

public class GoodsAdjunctManager extends BaseSupport implements
		IGoodsAdjunctManager {

	
	public List<Map> list(int goods_id) {
		String sql = "select * from goods_adjunct where goods_id = ?";
		List list = this.baseDaoSupport.queryForList(sql, goods_id);
		return list;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(int goods_id, List<GoodsAdjunct> list) {
		//删除原有
		String deleteSql = "delete from goods_adjunct where goods_id = ?";
		this.baseDaoSupport.execute(deleteSql, goods_id);
		
		//依次加入
		for(GoodsAdjunct adjunct:list){
			this.add(adjunct);
		}
	}
	
	private void add(GoodsAdjunct adjunct) {
		this.baseDaoSupport.insert("goods_adjunct", adjunct);
	}

}
