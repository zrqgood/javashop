package com.enation.eop.processor.facade.support;

import java.util.Map;

import com.enation.eop.processor.core.EopException;
import com.enation.eop.processor.widget.IWidgetPaser;
import com.enation.eop.sdk.widget.IWidget;
import com.enation.framework.context.spring.SpringContextHolder;

/**
 * 本地挂件解析器     
 * @author kingapex
 * 2010-2-8下午03:56:17
 */
public class LocalWidgetPaser implements IWidgetPaser {

	 
	
	public String pase(Map<String, String> params) {
		if(params==null) throw new EopException("挂件参数不能为空");
		
		String widgetType = params.get("type");
		if(widgetType==null) throw new EopException("挂件类型不能为空");
		
	 	//System.out.println("processor "+ widgetType+"["+params.get("widgetid")+"]");
		try{
			IWidget widget =SpringContextHolder.getBean(widgetType);
			
			String content;
			if(widget==null) content=("widget["+widgetType+"]not found");
			else  {   
				content= widget.process(params); //解析挂件内容
				widget.update(params); //执行挂件更新操作
			}

			return content;
		}catch(Exception e){
			e.printStackTrace();
			return "widget["+widgetType+"]pase error ";
		}		
		
	}

}
