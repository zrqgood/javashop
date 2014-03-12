package com.enation.javashop.widget.goods.search;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.utils.UrlUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class SearchUrlDirectiveModel implements TemplateDirectiveModel {

	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String type  = params.get("type").toString();
		String value  = params.get("value").toString();
	 	
		
		HttpServletRequest request  =ThreadContextHolder.getHttpRequest(); 
		String url = request.getServletPath();
		String encode = request.getCharacterEncoding();
		if(!"UTF-8".equals(encode)){ //如果非UTF-8编码则进行编码
			url=StringUtil.toUTF8(url);
		}
		url= UrlUtils.getParamStr(url);
		
		if("all".equals(value)){
			if( "brand".equals(type) ){
				url= UrlUtils.getExParamUrl(url, type);
			}else if("prop".equals(type)){
				String index =params.get("index").toString();
			   url = UrlUtils.getPropExSelf(Integer.valueOf(index), url);
			}
		}else{
			if(!type.equals("prop") )
				url= UrlUtils.getExParamUrl(url, type);
			url= UrlUtils.appendParamValue(url, type, value);
		}
		env.getOut().write("search-"+url+".html");
		
	}

}
