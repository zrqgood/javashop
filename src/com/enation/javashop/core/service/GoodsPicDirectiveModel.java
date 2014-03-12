package com.enation.javashop.core.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.util.DateUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.utils.GoodsUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class GoodsPicDirectiveModel implements TemplateDirectiveModel {

	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String pic  = params.get("pic").toString();
		if(StringUtil.isEmpty(pic)) pic ="http://"+EopSetting.IMG_SERVER_DOMAIN+"/images/no_picture.jpg";
		if(pic.startsWith("fs:")){
			pic  = UploadUtil.replacePath(pic);
		}
		String postfix=   params.get("postfix").toString() ;
		env.getOut().write(UploadUtil.getThumbPath(pic, postfix));

	}

}
