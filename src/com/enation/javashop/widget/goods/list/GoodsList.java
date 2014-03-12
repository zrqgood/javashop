package com.enation.javashop.widget.goods.list;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.app.base.core.service.ISettingService;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.util.RequestUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.Tag;
import com.enation.javashop.core.model.support.GoodsView;
import com.enation.javashop.core.service.IGoodsTypeManager;
import com.enation.javashop.core.service.ITagManager;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 商品列表挂件   
 * @author kingapex
 *
 */
public class GoodsList extends AbstractWidget {
	
	private GoodsDataProvider goodsDataProvider;
	private ITagManager tagManager;
	private IGoodsTypeManager goodsTypeManager;
	private ISettingService settingService ;
	
	/**
	 * 返回url中包含的参数，以“-”为开始，“.”为结束
	 * 例如goodslist-1-2-3,2-4.html
	 * @param index
	 */
	protected String getSplitParam(int index){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String url = RequestUtil.getRequestUrl(httpRequest);
		String[] params = url.split("-");
		url = params[params.length-1];
		int pos = url.indexOf('.');
		if(pos>0)
			params[params.length-1] = url.substring(0,pos);
		if(params.length>index)
			return params[index];
		return null;
	}
	
	
	protected void  display(Map<String, String> params) {
		String  widgetid  = "widget_"+params.get("widgetid");
		this.putData("widgetid", widgetid);
		String settingJson = params.get("setting");
		
		String catid = params.get("catid");
		
		Settings  setting = (Settings)JSONObject.toBean( JSONObject.fromObject(settingJson),Settings.class );
		this.putData("setting", null);
		if(setting!=null){
			//处理商品图片大小
			if(setting.getThumbnail_pic_width()== null){
				String defaultWidth =this.settingService.getSetting("photo", "thumbnail_pic_width");
				defaultWidth = defaultWidth== null?"100":defaultWidth;
				setting.setThumbnail_pic_width(defaultWidth+"px");
			}
		
			if(setting.getThumbnail_pic_height()== null){
				String defaultHeight =this.settingService.getSetting("photo", "thumbnail_pic_height");
				defaultHeight = defaultHeight== null?"100":defaultHeight;
				setting.setThumbnail_pic_height(defaultHeight+"px");
			}
			
			//处理列宽
			setting.setColumnwidth( Integer.valueOf(99/Integer.valueOf(setting.getColumnnum())) );
		 
			this.putData("setting", setting);
			
			String distype = setting.getType();
			
			if(distype==null ||"default".equals(distype)){
				String termJson = params.get("term");
				this.putData("goodsList", null);
				Term term =(Term)JSONObject.toBean( JSONObject.fromObject(termJson),Term.class );
				
				
				if(term!=null){
				 
					if(!StringUtil.isEmpty(catid))
					{
						term.setCatid(catid);
					}
					 List goodsList =goodsDataProvider.list(term, setting.getGoodsNum());
					 this.putAttrText(goodsList);
					 this.putData("goodsList", goodsList);
					 
				}else{
					//	如果没有指定term参数，尝试从url读取并传入页面
					//	url形如：goodslist-1,2-3.html
					term = new Term();
					term.setCatid(getSplitParam(1));
					term.setTagid(getSplitParam(2));
					if(!StringUtil.isEmpty(catid))
					{
						term.setCatid(catid);
					}
					 List goodsList =goodsDataProvider.list(term, setting.getGoodsNum());
					 this.putAttrText(goodsList);
					 this.putData("goodsList", goodsList);
					params.put("catid", term.getCatid());
					params.put("tagid", term.getTagid());
					this.putData("catid", term.getCatid());
					this.putData("tagid", term.getTagid());
				}
			}
			
			if(distype==null ||"morediv".equals(distype)){
				String tabsjson=  params.get("tabs");
				JSONArray configArray= JSONArray.fromObject(tabsjson);
				Collection configList  =JSONArray.toCollection(configArray,Tab.class);
				Iterator<Tab> tabIterator= configList.iterator();
				int i=0;
				while(tabIterator.hasNext()){
					Tab tab  =	tabIterator.next();
					if(i==0)tab.setSelected(true);
					else tab.setSelected(false);
					
					tab.setId( widgetid+ "_"+ i);
					
					List goodsList  = this.goodsDataProvider.list(tab.getTerm() ,setting.getGoodsNum() );
					tab.setGoodsList(goodsList);
					i++;
				}
				this.putData("tabList", configList);
				this.setPageName("MoreDivList");
			}
			
		}
		
//		 
//		 
//		 if("morediv".equals(params.get("distype"))){
//			String config_str=  params.get("conifg");
//			JSONArray configArray= JSONArray.fromObject(config_str);
//			Collection configList  =JSONArray.toCollection(configArray,Map.class);
//			this.putData("tabList", configList);
//			this.setPageName("MoreDivList");
//		 }
		
	}
	
 
	
	private void putAttrText(List<GoodsView> goodsList){
		if(goodsList==null || goodsList.size()==0) return ;
		GoodsView tempgoods = goodsList.get(0);
		List<Attribute> attrList =goodsTypeManager.getAttrListByTypeId(tempgoods.getType_id());
		
		for(GoodsView goods:goodsList){
			putGoodsAttrText(goods,attrList);
		}

	}
	
	private void putGoodsAttrText(GoodsView goods,List<Attribute> attrList){
		int i=1;
		
		for(Attribute attr: attrList){
			String value =""+goods.getPropMap().get("p"+i);
			attr.setValue(value);
			goods.getPropMap().put("p"+i+"_text", attr.getValStr()); //为商品取出属性的字符值
			i++;
		}
	}
	
	protected void config(Map<String, String> params) {
		List<Tag> tagList =tagManager.list();
		this.putData("tagList", tagList);
	}
	
	
	public GoodsDataProvider getGoodsDataProvider() {
		return goodsDataProvider;
	}





	public void setGoodsDataProvider(GoodsDataProvider goodsDataProvider) {
		this.goodsDataProvider = goodsDataProvider;
	}





	public void test(){
		 Configuration cfg = new Configuration();
		 cfg.setClassForTemplateLoading(getClass()  , "");
		 cfg.setObjectWrapper(new DefaultObjectWrapper());  
 		 Map<String, Object> goods = new HashMap<String, Object>();
//		 goods.put("name", "kingapex");
		 List list= new ArrayList();
		 list.add("1");
		 list.add("2");
		 list.add("3");
		 goods.put("nlist", list);
		 
		 try {
			Template temp = cfg.getTemplate("goodslist.html");
			ByteOutputStream stream = new ByteOutputStream();
			Writer out = new OutputStreamWriter(stream);
			temp.process(goods, out);
			out.flush();  
			String name =this.getClass().getName();
			name = name.substring(name.lastIndexOf('.')+1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	

	
	public void setTagManager(ITagManager tagManager) {
		this.tagManager = tagManager;
	}

	public static void main(String[] args){
//		GoodsList goodsList = new GoodsList();
//		goodsList.process(null);
		
		
		 
		String config_str=  "[{ title:'类别1',term:{tag_id:1}},{ title:'类别2',term:{tag_id:1}}]";
		JSONArray array= JSONArray.fromObject(config_str);
		Collection list  =JSONArray.toCollection(array,Map.class);
		
		Iterator<Map> iterator = list.iterator();
		while(iterator.hasNext()){
			Map<String,String> map = iterator.next();
		}
		
	}


	public ISettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(ISettingService settingService) {
		this.settingService = settingService;
	}

	public ITagManager getTagManager() {
		return tagManager;
	}

	public IGoodsTypeManager getGoodsTypeManager() {
		return goodsTypeManager;
	}

	public void setGoodsTypeManager(IGoodsTypeManager goodsTypeManager) {
		this.goodsTypeManager = goodsTypeManager;
	}
	
	
}
