/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.javashop.widget.goods.search;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 商品搜索结果页分页
 * @author kingapex
 *2010-4-27下午01:11:10
 */
public class SearchPagerDirectiveModel implements TemplateDirectiveModel {

	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
	 
		String total  = params.get("total").toString();
		String pagesize  = params.get("pagesize").toString();
		String pageNo=  params.get("pageno").toString();
		PagerHtmlBuilder pagerHtmlBuilder = new PagerHtmlBuilder(Integer.valueOf(pageNo), Integer.valueOf(total), Integer.valueOf(pagesize));
		String page_html = pagerHtmlBuilder.buildPageHtml();
		env.getOut().write(page_html);
	}

}
