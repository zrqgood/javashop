package com.enation.javashop.core.plugin.cart;

import java.util.List;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;
import com.enation.javashop.core.model.Cart;
import com.enation.javashop.core.model.support.CartItem;
import com.enation.javashop.core.model.support.OrderPrice;

public class CartPluginBundle extends AutoRegisterPluginsBundle {
	
	public OrderPrice coutPrice(OrderPrice orderpice){
		try{
			
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof ICountPriceEvent ) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + "cart.countPrice开始...");
						}
						ICountPriceEvent event = (ICountPriceEvent) plugin;
						orderpice =event.countPrice(orderpice);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " cart.countPrice结束.");
						}
					}else{
						if (loger.isDebugEnabled()) {
							loger.debug(" no,skip...");
						}
					}
				}
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
			 
		}
		 
		return orderpice;
	}
	 
	public void filterList(List<CartItem> itemList,String sessionid){
		try{
			
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof ICartItemFilter) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + "cart.add开始...");
						}
						ICartItemFilter event = (ICartItemFilter) plugin;
						event.filter(itemList, sessionid);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " cart.add结束.");
						}
					}else{
						if (loger.isDebugEnabled()) {
							loger.debug(" no,skip...");
						}
					}
				}
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			 
		}
	}
	
	public void onAdd(Cart cart){
		try{
	
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof ICartAddEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + "cart.add开始...");
						}
						ICartAddEvent event = (ICartAddEvent) plugin;
						event.add(cart);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " cart.add结束.");
						}
					}else{
						if (loger.isDebugEnabled()) {
							loger.debug(" no,skip...");
						}
					}
				}
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			 
		}
	}
	
	
	public void onDelete(String sessionid,Integer cartid){
		try{
			
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof ICartDeleteEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + "cart.delete开始...");
						}
						ICartDeleteEvent event = (ICartDeleteEvent) plugin;
						event.delete(sessionid, cartid);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " cart.delete结束.");
						}
					}else{
						if (loger.isDebugEnabled()) {
							loger.debug(" no,skip...");
						}
					}
				}
			}
			 
			
		}catch(Exception e){
			e.printStackTrace();
			 
		}
		
		
	}
	
	
	@Override
	public String getName() {
		return "购物车插件桩";
	}
	
}
