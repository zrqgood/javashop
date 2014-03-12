package com.enation.app.base.core.service.impl;

import java.util.List;

import com.enation.app.base.core.model.FriendsLink;
import com.enation.app.base.core.model.FriendsLinkMapper;
import com.enation.app.base.core.service.IFriendsLinkManager;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.UploadUtil;

/**
 * 友情链接
 * 
 * @author lzf<br/>
 *         2010-4-8 下午06:20:29<br/>
 *         version 1.0<br/>
 */
public class FriendsLinkManager extends BaseSupport<FriendsLink> implements
		IFriendsLinkManager {

	
	public void add(FriendsLink friendsLink) {
		this.baseDaoSupport.insert("friends_link", friendsLink);

	}

	
	public void delete(String id) {
		this.baseDaoSupport.execute("delete from friends_link where link_id in (" + id + ")");

	}

	
	public List listLink() {
		
		return this.baseDaoSupport.queryForList("select * from friends_link order by sort",new FriendsLinkMapper());
	}

	
	public void update(FriendsLink friendsLink) {
		this.baseDaoSupport.update("friends_link", friendsLink, "link_id = " + friendsLink.getLink_id());

	}

	
	public FriendsLink get(int link_id) {
		FriendsLink friendsLink = this.baseDaoSupport.queryForObject("select * from friends_link where link_id = ?", FriendsLink.class, link_id);
		String logo  = friendsLink.getLogo();
		if(logo!=null) {
			logo  =UploadUtil.replacePath(logo);
			friendsLink.setLogo(logo);
		}
		return  friendsLink;
	}

}
