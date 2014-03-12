package com.enation.eop.processor.core.freemarker;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.enation.framework.util.DateUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DateFormateDirectiveModel implements TemplateDirectiveModel {

	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Long date_l = Long.valueOf( params.get("date").toString());
		String pattern=  params.get("pattern").toString();
		env.getOut().write(DateUtil.toString(new Date(date_l), pattern));

	}

}
