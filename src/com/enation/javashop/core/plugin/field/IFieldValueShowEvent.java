package com.enation.javashop.core.plugin.field;


/**
 * 商品字段显示事件
 * @author kingapex
 *
 */
public interface IFieldValueShowEvent {
 
		
		/**
		 * 字段显示事件字义
		 * @param field 字段实体
		 * @param value 数据库中保存的字段值
		 * @return 处理后的字段值
		 */
		public Object onShow(GoodsField field,Object value);
 

}
