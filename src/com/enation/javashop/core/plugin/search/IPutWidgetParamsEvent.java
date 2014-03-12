package com.enation.javashop.core.plugin.search;

import java.util.Map;

/**
 * 向挂件参数压入值的事件接口
 * @author kingapex
 *
 */
public interface IPutWidgetParamsEvent {
	
	
	/**
	 * 向挂件页面中传递数据
	 * @param params 向其中压入的参数会被传递到挂件页面中
	 * @param urlFragment
	 */
	public void putParams(Map<String,Object> params,String urlFragment);
	
}
