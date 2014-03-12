package com.enation.javashop.core.service.impl;

import java.util.List;
import java.util.Map;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Gnotify;
import com.enation.javashop.core.model.Product;
import com.enation.javashop.core.service.IGnotifyManager;
import com.enation.javashop.core.service.IGoodsManager;
import com.enation.javashop.core.service.IProductManager;

public class GnotifyManager extends BaseSupport implements IGnotifyManager {
	 
	
	public Page pageGnotify(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select a.*, b.image_default image,b.store store, b.name name, b.price price, b.mktprice mktprice,b.cat_id cat_id from "+ this.getTableName("gnotify")+" a left join "+ this.getTableName("goods")+" b on b.goods_id = a.goods_id";
		sql += " and a.member_id = " + member.getMember_id();
 		Page webpage = this.daoSupport.queryForPage(sql, pageNo, pageSize);
		return webpage;
	}

	
	public void deleteGnotify(int gnotify_id) {
		this.baseDaoSupport.execute("delete from gnotify where gnotify_id = ?", gnotify_id);
	}
	
	public void addGnotify(int goodsid){
		IUserService userService = UserServiceFactory.getUserService();
		Member member = null;
		if(userService!=null){
			member = userService.getCurrentMember();
		}
		Gnotify gnotify = new Gnotify();
		gnotify.setCreate_time(System.currentTimeMillis());
		gnotify.setGoods_id(goodsid);
		if(member!=null){
			gnotify.setMember_id(member.getMember_id());
			gnotify.setEmail(member.getEmail());
		}
		this.baseDaoSupport.insert("gnotify", gnotify);
		
	}

 


 

}
