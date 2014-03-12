package com.enation.javashop.plugin.standard.cart;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.javashop.core.model.GroupBuy;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.plugin.cart.ICartItemFilter;
import com.enation.javashop.core.service.IGroupBuyManager;

/**
 * 团购购物车过滤器
 * @author kingapex
 *
 */
public class GroupBuyCartPlugin extends AutoRegisterPlugin implements
		ICartItemFilter {

	private IGroupBuyManager groupBuyManager;


	@Override
	public void filter(List<CartItem> itemlist, String sessionid) {
		List<GroupBuy> groupBuyList  = groupBuyManager.listEnable();
		for(CartItem item: itemlist){
			for(GroupBuy groupbuy:groupBuyList){
				if( item.getGoods_id().intValue() ==  groupbuy.getGoodsid() ){
					item.setCoupPrice( groupbuy.getPrice() );
				}
			}
		}
		
	}
	

	@Override
	public String getAuthor() {
		
		return "kingapex";
	}

	@Override
	public String getId() {
		
		return "groupBuyCartPlugin";
	}

	@Override
	public String getName() {
		
		return "团购购物车过滤器";
	}

	@Override
	public String getType() {
		
		return "cart";
	}

	@Override
	public String getVersion() {
		
		return "1.0";
	}

	@Override
	public void perform(Object... params) {
		

	}
	@Override
	public void register() {

	}
	public IGroupBuyManager getGroupBuyManager() {
		return groupBuyManager;
	}

	public void setGroupBuyManager(IGroupBuyManager groupBuyManager) {
		this.groupBuyManager = groupBuyManager;
	}

}
