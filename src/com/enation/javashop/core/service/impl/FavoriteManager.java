package com.enation.javashop.core.service.impl;

import java.util.List;

import com.enation.app.base.core.model.Member;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.user.IUserService;
import com.enation.eop.sdk.user.UserServiceFactory;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.javashop.core.model.Favorite;
import com.enation.javashop.core.service.IFavoriteManager;

/**
 * 我的收藏
 * 
 * @author lzf<br/>
 *         2010-3-24 下午02:54:26<br/>
 *         version 1.0<br/>
 */
public class FavoriteManager extends BaseSupport implements IFavoriteManager {

	
	public Page list(int pageNo, int pageSize) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		String sql = "select g.*, f.favorite_id from " + this.getTableName("favorite")
				+ " f left join " + this.getTableName("goods")
				+ " g on g.goods_id = f.goods_id";
		sql += " where f.member_id = ?";
		Page page = this.daoSupport.queryForPage(sql, pageNo, pageSize, member
				.getMember_id());
		return page;
	}

	
	public void delete(int favorite_id) {
		this.baseDaoSupport.execute("delete from favorite where favorite_id = ?", favorite_id);
	}

	
	public void add(Integer goodsid) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		Favorite favorite = new Favorite();
		favorite.setGoods_id(goodsid);
		favorite.setMember_id(member.getMember_id());
		this.baseDaoSupport.insert("favorite", favorite);
	}

	
	public List list( ) {
		IUserService userService = UserServiceFactory.getUserService();
		Member member = userService.getCurrentMember();
		
		return this.baseDaoSupport.queryForList("select * from favorite where member_id=?", Favorite.class, member.getMember_id());
	}

}
