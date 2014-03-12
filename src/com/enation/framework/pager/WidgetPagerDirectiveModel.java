package com.enation.framework.pager;

import java.io.IOException;
import java.util.Map;

import com.enation.framework.pager.impl.SimplePageHtmlBuilder;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class WidgetPagerDirectiveModel implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] arg2,
			TemplateDirectiveBody arg3) throws TemplateException, IOException {
		String pageno = params.get("pageno").toString();
		String pagesize = params.get("pagesize").toString();
		String totalcount =params.get("totalcount").toString();
		int _pageNum = Integer.valueOf(pageno);
		int _totalCount = Integer.valueOf(totalcount);
		int _pageSize = Integer.valueOf(pagesize);
		SimplePageHtmlBuilder pageHtmlBuilder = new SimplePageHtmlBuilder(_pageNum, _totalCount, _pageSize);
		String html = pageHtmlBuilder.buildPageHtml();
		env.getOut().write(html);
	}

}
