package com.enation.javashop.core.plugin.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.enation.javashop.core.model.AllocationItem;
import com.enation.javashop.core.model.OrderItem;

/**
 * 订单项 配货事件
 * 处理某个货物项配货
 * @author kingapex
 *
 */
public interface IOrderAllocationItemEvent {
	
	
	 	/**
	 	 * 获取配货时的html
	 	 * @param item  某个订单项
	 	 * @return 配货的html
	 	 */
		public String getAllocationStoreHtml(OrderItem item ); 
	
		
		/**
		 * 显示配货情况的html
		 * @param item
		 * @return
		 */
		public String getAllocationViewHtml(OrderItem item);
		
		
		
		/**
		 * 订单配货事件，传递配货单
		 * @param allocationItem 某个配货项
		 * 
		 */
		public void onAllocation(AllocationItem allocationItem);
		
		
		
		/**
		 * 在显示配货单时，过滤结果集 
		 * @param colValues
		 * @param rs
		 */
		public void filterAlloViewItem(Map colValues,ResultSet rs)throws SQLException;
		
		
		
		
		
		
		
		/**
		 * 定义事件是否被执行 
		 * @param catid 商品分类id
		 * @return
		 */
		public boolean canBeExecute(int catid);
		
		
}
