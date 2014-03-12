package com.enation.eop.sdk.utils;

import com.enation.eop.processor.Widget;

public class WidgetUtil {
	
	public static  String toHtml(Widget widget, String content) {

		StringBuffer htmlBuffer = new StringBuffer();

		// 以下是符合《挂件规范.doc》中的挂件html片段
		htmlBuffer.append("<div ");

		htmlBuffer.append("class=\"widget\"");
		htmlBuffer.append(" ");
		

		htmlBuffer.append("eop_type=\"widget\"");
		htmlBuffer.append(" ");
		
		//在2.1版本的架构中去掉了widgetId属性
		/*htmlBuffer.append("id=\"widget");
		htmlBuffer.append(widget.getWidgetId());
		htmlBuffer.append("\"");
		htmlBuffer.append(" ");*/

		htmlBuffer.append("appId=\"");
		htmlBuffer.append(widget.getApp().getId());
		htmlBuffer.append("\"");
		htmlBuffer.append(" ");
		
		htmlBuffer.append("widgetType=\"");
		htmlBuffer.append(widget.getWidgetType());
		htmlBuffer.append("\"");
		htmlBuffer.append(" ");
		
		htmlBuffer.append(">");

		htmlBuffer.append(content); // 填充上挂件的内容

		htmlBuffer.append("</div>");

		return htmlBuffer.toString();

	}
}
