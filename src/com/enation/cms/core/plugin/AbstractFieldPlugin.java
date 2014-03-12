package com.enation.cms.core.plugin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.service.impl.DataFieldManager;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.plugin.AutoRegisterPlugin;

public abstract class AbstractFieldPlugin extends AutoRegisterPlugin implements
		IFieldSaveEvent, IFieldDispalyEvent,IFieldValueShowEvent {

	/**
	 * 下面两个方法废弃
	 */
	 
	public void register() {

	}

 
	public void perform(Object... params) {

	}
	public abstract String onDisplay(DataField field, Object value);
	public abstract String getAuthor();
	public abstract String getId();
	public abstract String getName();
	public abstract String getType() ;
	public abstract String getVersion();
	
	
	/**
	 * 定义是否有选择值<br>
	 * 如下拉框或单选按钮类控件是有选择值控件
	 * @return
	 */
	public abstract int getHaveSelectValue();
	 
	
	
	/**
	 * 数据保存事件默认响应<br>
	 * 逻辑为以name为字段为字段名，值为request.getParameter(fieldname);
	 */
	public void onSave(Map article, DataField field) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		article.put(field.getEnglish_name(),request.getParameter(field.getEnglish_name()));
	}
	
 
	
	/**
	 * 数据显示默认响应事件<br>
	 * 逻辑为直接返回字段值<br>
	 * 如果为null返回空串
	 */
	public Object onShow(DataField field, Object value) {
		if(value!=null)
		return value.toString();
		else return "";
	}
	
	/**
	 * 根据字段类型，提供字段校验的html
	 * @param field 
	 * @return
	 */
	protected String  wrappValidHtml(DataField field ){
		
		StringBuffer html  = new StringBuffer();
		if(field.getIs_validate() ==1 ){
			html.append(" required=\"true\" " );
		}
 
		return html.toString();
	}
	
	/**
	 * 当前版本cms只支持varchar(255) 和text两种数据类型
	 * 定义默认的数据类型，为1(varchar(255))
	 * 子类可覆写此方法返回2为text型。
	 * @return
	 
	public int getDataType(){
		
		return 1;
	}
	*/
	
	/**
	 * 返回字段的数据类型
	 * 在创建字段时会掉用此方法
	 * @see DataFieldManager#add(DataField) 
	 * @see DataFieldManager#edit(DataField)
	 * @since 2.3 
	 */
	public String getDataType(){
		
		return "varchar(255)";
	}
}
