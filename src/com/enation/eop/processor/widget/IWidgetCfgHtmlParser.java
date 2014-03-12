package com.enation.eop.processor.widget;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.core.Request;
import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.StringResponse;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * 挂件设置html解析器接口 
 * @author kingapex
 * 2010-1-29上午10:06:41
 */
public interface IWidgetCfgHtmlParser  {
	
	/**
	 * 有挂件id为编辑
	 * 挂件id为null为新建
	 * @param widgetId
	 * @return
	 */
	public String pase(Map<String,String> params);

}
