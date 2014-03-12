package com.enation.javashop.core.plugin.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.enation.framework.plugin.AutoRegisterPluginsBundle;
import com.enation.framework.plugin.IPlugin;
import com.enation.javashop.core.model.Allocation;
import com.enation.javashop.core.model.AllocationItem;
import com.enation.javashop.core.model.Delivery;
import com.enation.javashop.core.model.DeliveryItem;
import com.enation.javashop.core.model.Order;

/**
 * 订单插件桩
 * @author kingapex
 *
 */
public class OrderPluginBundle extends AutoRegisterPluginsBundle {

	@Override
	public String getName() {
		
		return "订单插件桩";
	}
	public List<IPlugin> getPluginList(){
		
		return this.plugins;
	}
	
	
	public void onCreate(Order order,String sessionid){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderCreateEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onCreate 开始...");
						}
						IOrderCreateEvent event = (IOrderCreateEvent) plugin;
						event.onOrderCreate(order, sessionid);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onCreate 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[创建]事件错误", e);
			throw e;
		}
	}
	
	public void onFilter(Integer orderid,List<Map> itemList){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderItemFilter) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onFilter 开始...");
						}
						IOrderItemFilter event = (IOrderItemFilter) plugin;
						event.filter(orderid,itemList);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onFilter 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[filter]事件错误", e);
			throw e;
		}
	}	
	
	
	
	
	/**
	 * 激发订单发货事件
	 * @param orderid
	 * @param itemList
	 */
	public void onShip(Delivery delivery,List<DeliveryItem> itemList){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderShipEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onShip 开始...");
						}
						IOrderShipEvent event = (IOrderShipEvent) plugin;
						event.ship(delivery, itemList);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onShip 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[ship]事件错误", e);
			throw e;
		}
	}	
	
	
	/**
	 * 激发订单项发货事件
	 * @param deliveryItem
	 * @param allocationItem
	 */
	public void onItemShip(DeliveryItem deliveryItem,AllocationItem allocationItem){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderShipEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onItemShip 开始...");
						}
						IOrderShipEvent event = (IOrderShipEvent) plugin;
						if(event.canBeExecute(allocationItem.getCat_id() )){
							event.itemShip(deliveryItem, allocationItem);
						}
						
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onItemShip 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[onItemShip]事件错误", e);
			throw e;
		}
	}	
	
	
	
	public void onReturned(Delivery delivery,List<DeliveryItem> itemList){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderShipEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onReturned 开始...");
						}
						IOrderShipEvent event = (IOrderShipEvent) plugin;
						event.returned(delivery, itemList);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onReturned 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[returned]事件错误", e);
			
			throw e;
		}
	}	
	
	
	/**
	 * 激发订单删除事件
	 * @param orderId
	 */
	public void onDelete(Integer[] orderId){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderDeleteEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onDelete 开始...");
						}
						IOrderDeleteEvent event = (IOrderDeleteEvent) plugin;
						event.delete(orderId);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onDelete 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[delete]事件错误", e);
			throw e;
		}
	}
	
	
	/**
	 * 激发订单项配货事件
	 * @param orderId
	 */
	public void onAllocationItem(AllocationItem allocationItem){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderAllocationItemEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onAllocationItem 开始...");
						}
						IOrderAllocationItemEvent event = (IOrderAllocationItemEvent) plugin;
						if(event.canBeExecute(allocationItem.getCat_id())){
							 event.onAllocation(allocationItem);
						}
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onAllocationItem 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
				this.loger.error("调用订单插件[onAllocationItem]事件错误", e);
			
			throw e;
		}
	}	
	
	
	/**
	 * 激发订单配货事件
	 * @param orderId
	 */
	public void onAllocation(Allocation allocation){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderAllocationEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onAllocation 开始...");
						}
						IOrderAllocationEvent event = (IOrderAllocationEvent) plugin;
						event.onAllocation(allocation);
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " onAllocation 结束.");
						}
					} 
				}
			}
		}catch(RuntimeException  e){
				this.loger.error("调用订单插件[onAllocation]事件错误", e);
			
			throw e;
		}
	}		
	
	
	
	/**
	 * 获取某个订单项的各库房的库存情况
	 * @param item
	 * @return
	 */
/*	public String getAllocationHtml(OrderItem item){
		String  html = null;
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderAllocationItemEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " getAllocationHtml 开始...");
						}
						IOrderAllocationItemEvent event = (IOrderAllocationItemEvent) plugin;
						if( event.canBeExecute(item.getCat_id()) ){
							html = ((IOrderAllocationItemEvent) plugin).getAllocationStoreHtml(item);
						} 
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " getAllocationHtml 结束.");
						}
					} 
				}
			}
			
			return html;
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[getAllocationHtml]事件错误", e);
			
			throw e;
		}
	} 
	*/
	/**
	 * 获取配货情况html
	 * @param item
	 * @return
	 */
/*	public String getAllocationViewHtml(OrderItem item){
		String  html = null;
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderAllocationItemEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " getAllocationViewHtml 开始...");
						}
						IOrderAllocationItemEvent event = (IOrderAllocationItemEvent) plugin;
						if( event.canBeExecute(item.getCat_id()) ){
							html = ((IOrderAllocationItemEvent) plugin).getAllocationViewHtml(item);
						} 
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " getAllocationViewHtml 结束.");
						}
					} 
				}
			}
			
			return html;
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[getAllocationViewHtml]事件错误", e);
			throw e;
		}
		
	}*/
	
	
	
	/**
	 * 显示配货单时过滤相应项
	 * @param catid
	 * @param values
	 * @param rs
	 * @throws SQLException 
	 */
	public void filterAlloItem(int catid,Map values,ResultSet rs) throws SQLException{
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderAllocationItemEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " filterAlloViewItem 开始...");
						}
						IOrderAllocationItemEvent event = (IOrderAllocationItemEvent) plugin;
						if( event.canBeExecute(catid) ){
						  ((IOrderAllocationItemEvent) plugin).filterAlloViewItem(values, rs);
						} 
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " filterAlloViewItem 结束.");
						}
					} 
				}
			}
			
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[filterAlloViewItem]事件错误", e);
			throw e;
		}
		
	}
	
	
	public void onPay(Order order ,boolean isOnline){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderPayEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " pay 开始...");
						}
						    IOrderPayEvent event = (IOrderPayEvent) plugin;
						   event.pay(order, isOnline);
						 
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " pay 结束.");
						}
					} 
				}
			} 
			
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[pay]事件错误", e);
			throw e;
		}		
	}
	
	public void onCanel(Order order){
		try{
			if (plugins != null) {
				for (IPlugin plugin : plugins) {
					if (plugin instanceof IOrderCanelEvent) {
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " canel 开始...");
						}
						    IOrderCanelEvent event = (IOrderCanelEvent) plugin;
						   event.canel(order);
						 
						if (loger.isDebugEnabled()) {
							loger.debug("调用插件 : " + plugin.getName() + " canel 结束.");
						}
					} 
				}
			} 
			
		}catch(RuntimeException  e){
			if(this.loger.isErrorEnabled())
			this.loger.error("调用订单插件[canel]事件错误", e);
			throw e;
		}		
	}
	
	
	public static void main(String[] args){
		OrderPluginBundle  bundle = new OrderPluginBundle();
		bundle.onCreate(null,null);
	}

	
}


