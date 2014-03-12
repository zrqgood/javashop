package com.enation.eop.processor.facade;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.processor.Processor;
import com.enation.eop.processor.core.Response;
import com.enation.eop.processor.core.StringResponse;
import com.enation.eop.processor.facade.support.LocalWidgetPaser;
import com.enation.eop.processor.facade.support.widget.WidgetHtmlGetter;
import com.enation.eop.processor.widget.IWidgetHtmlGetter;
import com.enation.eop.processor.widget.IWidgetPaser;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;

/**
 * 挂件处理器<br/>
 * 处理挂件异步读取,如在编辑模板时挂件的创建和挂件更新
 * @author kingapex
 * @version 1.0
 * @created 22-十月-2009 16:36:13
 */
public class WidgetProcessor   implements Processor {


	
	/**
	 * 
	 * @param model
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		
		/*
		 * request中有挂件数据形成需要的参数
		 */
	    Map<String,String> widgetParams = RequestUtil.paramToMap(httpRequest);
	    
	    
		/*
		 * 调用挂件获取接口
		 * 传递给上述挂件参数 
		 */
		IWidgetPaser widgetPaser = new LocalWidgetPaser();
		String content =widgetPaser.pase(widgetParams);
		Response response = new StringResponse();
		//content = StringUtil.compressHtml(content);
		response.setContent(content);
		return response;
		
		/*Processor processor  =null;
		Widget widget =WidgetFactory.build(httpRequest);
	 
		String act  = httpRequest.getParameter("act");
		if("delete".equals(act)){
			processor =new WidgetDeleter(widget);
		}else{
			processor =new WidgetGeter(widget);
		}
		
		
		mode = widget.getApp().getDeployment();
		
		Response response  = processor.process(mode, httpResponse, httpRequest);
		
		return response;*/
	}

}