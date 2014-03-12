package com.enation.eop.resource.impl;

import java.util.List;

import com.enation.eop.resource.IBorderManager;
import com.enation.eop.resource.model.Border;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.IDBRouter;
import com.enation.framework.database.IDaoSupport;

/**
 * saas式的边框管理 
 * @author kingapex
 * 2010-1-28下午05:41:09
 */
public class BorderManagerImpl extends BaseSupport<Border>  implements IBorderManager {
 
	public void clean() {
		this.baseDaoSupport.execute("truncate table border");
	}
	
	
	public void add(Border border) {
		this.baseDaoSupport.insert("border", border);
	}

	
	public void delete(Integer id) {
		this.baseDaoSupport.execute("delete from border where id=?", id);
	}

	
	public List<Border> list() {
		String sql  ="select * from  border";
		List<Border> list = this.baseDaoSupport.queryForList(sql, Border.class);
		return list;
	}

	
	public void update(Border border) {
		this.baseDaoSupport.update("border", border, "id="+border.getId());
	}

 
	
}
