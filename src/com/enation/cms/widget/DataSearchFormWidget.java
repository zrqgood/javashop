package com.enation.cms.widget;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.enation.cms.core.model.DataField;
import com.enation.cms.core.plugin.ArticlePluginBundle;
import com.enation.cms.core.plugin.IFieldDispalyEvent;
import com.enation.cms.core.service.IDataFieldManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.utils.FreeMarkerUtil;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.plugin.IPlugin;
import com.enation.framework.util.StringUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 搜索表单挂件
 * @author kingapex
 * 2010-7-15下午09:55:05
 */
public class DataSearchFormWidget extends AbstractWidget {

	private IDataFieldManager dataFieldManager;
	private ArticlePluginBundle articlePluginBundle;
	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		
		String page  = params.get("formpage");
		String folder  = params.get("folder");
		Integer modelid = Integer.valueOf(params.get("modelid"));
		
		String html =this.getFormHtml(modelid, page, folder,params);
		this.putData("html", html);
		
	}

	private String getFormHtml(Integer modelid,String page,String folder,Map<String,String> params){
		

		
		Map data  = new HashMap();
		HttpServletRequest  request  = ThreadContextHolder.getHttpRequest();
		String inputnames= params.get("inputnames");
		if( !StringUtil.isEmpty(inputnames) ){
			String[] namear= inputnames.split(",");
			for(String name:namear){
				String value = request.getParameter(name);
				data.put(name, value);
			}
		}
		
		data.put("modelid", modelid);
		List<DataField> fieldList  = dataFieldManager.list(modelid);
		for(DataField field: fieldList){
			IPlugin plugin = articlePluginBundle.findPlugin(field.getShow_form());
			if(plugin!=null){
				if(plugin instanceof IFieldDispalyEvent){
					String inpuhtml =((IFieldDispalyEvent)plugin).onDisplay(field, null);
					data.put(field.getEnglish_name()+"_input", inpuhtml);
				}
			}
		}
		
		
		EopSite site  = EopContext.getContext().getCurrentSite();
		String contextPath  = EopContext.getContext().getContextPath();
		if(StringUtil.isEmpty(folder)){
			folder =(contextPath+"/themes/"+site.getThemepath());
		}else{
			folder = (contextPath+"/themes/"+site.getThemepath()+"/"+folder);
		}				
		try{
			Configuration cfg = FreeMarkerUtil.getFolderCfg(EopSetting.EOP_PATH +folder);
			Template temp = cfg.getTemplate(page+".html");
			ByteOutputStream stream = new ByteOutputStream();
			
			Writer out = new OutputStreamWriter(stream);
			temp.process(data, out);
			
			out.flush();
			String html = stream.toString();		
			
			return html;
		}
		catch(Exception e){
			this.logger.error(e.getMessage(), e);
			return "挂件解析出错"+e.getMessage();
		}
		
	}

	public IDataFieldManager getDataFieldManager() {
		return dataFieldManager;
	}

	public void setDataFieldManager(IDataFieldManager dataFieldManager) {
		this.dataFieldManager = dataFieldManager;
	}

	public ArticlePluginBundle getArticlePluginBundle() {
		return articlePluginBundle;
	}

	public void setArticlePluginBundle(ArticlePluginBundle articlePluginBundle) {
		this.articlePluginBundle = articlePluginBundle;
	}
	
	
}
