package com.enation.eop.processor.core.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class RepeatDirective implements TemplateDirectiveModel {

	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer count = Integer.valueOf( params.get("count").toString());
		for(int i=0;i<count.intValue();i++){
			 body.render(env.getOut());
		}
	}

}
