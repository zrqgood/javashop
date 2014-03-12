package com.enation.javashop.plugin.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.Cat;
import com.enation.javashop.core.plugin.search.IGoodsSearchFilter;
import com.enation.javashop.core.plugin.search.SearchSelector;

/**
 * 价格搜索过虑器<br/>
 * 由themes下加载 price_filter.xml 过滤条件
 *  
 * @author kingapex
 *
 */
public class PriceSearchFilter extends AutoRegisterPlugin implements
		IGoodsSearchFilter {

	private static Map<String,List<Price>> priceMap;
	
	public List<SearchSelector> createSelectorList(Cat cat, String url,
			String urlFragment) {
		if(cat==null) return null;
		
		String currMin = "";
		String currMax = "";
		
		if(!StringUtil.isEmpty( urlFragment ) ){
			
			String[] price = urlFragment.split("-");
			if(price!=null && price.length>=1){
				currMin  = price[0];
			}
			if(price!=null && price.length>=2){
				currMax  = price[1];
			}
			
		}
		
		List<SearchSelector>  list= new ArrayList<SearchSelector>();
		
		/**
		 * ------------------------ 生成'全部'价格的选择器 -----------------------
		 */
		SearchSelector allselector = new SearchSelector();
		allselector.setName("全部");
		allselector.setUrl(url+".html");
		if (StringUtil.isEmpty(urlFragment)) {
			allselector.setSelected(true);
		} else {
			allselector.setSelected(false);
		}
		list.add(allselector);
		
		
		 Map<String,List<Price>> pMap  =this.getPriceMap();
		 List<Price> priceList  = pMap.get(cat.getType_id().toString());
		 if(priceList==null) return  null;
		 
		 for(Price price :priceList){
			 
			 String max = price.getMax();
			 String min = price.getMin();
			 
			 min = min==null ?"":min;
			 max = max==null ?"":max;
			 
			 String text  = price.getText();
			 
			 
			 SearchSelector selector = new SearchSelector();
			 selector.setName(text);
			 String priceUrl  = min+"-"+max;
			 selector.setUrl(url+"price{"+priceUrl+"}.html");
			 
			 if(currMin.equals(min)  && currMax.equals( max ) ){
				 selector.setSelected(true);
			 }
			 
			 
			 list.add(selector);
			 
		 }
		 
		return list;
	}

	
	public void filter(StringBuffer sql, Cat cat, String urlFragment) {
		if(cat==null) return ;
 
		
		if(!StringUtil.isEmpty( urlFragment ) ){
			
			String[] price = urlFragment.split("-");
			if(price!=null && price.length>=1 && !StringUtil.isEmpty(price[0])){
				sql.append(" and  g.price>="+price[0]);				
			}
			if(price!=null && price.length>=2 && !StringUtil.isEmpty(price[1])){
				sql.append(" and g.price<"+price[1]);				
			}
			
		}
	}

	
	
	
	private  static Map<String,List<Price>> getPriceMap(){
		if(priceMap!=null) return priceMap;
		String xmlFile =EopSetting.EOP_PATH + EopContext.getContext().getContextPath() +"/themes/price_filter.xml";
		
		try {
			
		    DocumentBuilderFactory factory = 
		    DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(xmlFile);
		    
		     priceMap = new HashMap<String, List<Price>>();
		    
		    NodeList typeList  = document.getElementsByTagName("goodstype");
		    for( int i = 0;i<typeList.getLength();i++){
		    	
		    	List<Price> priceList  = new ArrayList<Price>();
		    	
		    	Element typeNode  = (Element) typeList.item(i);
		    	NodeList priceNodeList  = typeNode.getElementsByTagName("price");
		    	for (int j=0;j<priceNodeList.getLength();j++){
		    		
		    		
		    		Element priceEl  = (Element)priceNodeList.item(j);
		    		String text = priceEl.getTextContent();
		    		String minPrice  = priceEl.getAttribute("min");
		    		String maxPrice  = priceEl.getAttribute("max");
		    		Price price  = new Price();
		    		price.setText(text);
		    		
		    		if(!StringUtil.isEmpty(minPrice)){
		    			price.setMin(  minPrice  );
		    		}
		    		if(!StringUtil.isEmpty(maxPrice)){
		    			price.setMax(  maxPrice  );
		    		}

		    		priceList.add(price);
		    		
		    	}
		    	
		    	priceMap.put(typeNode.getAttribute("id"), priceList);
		    	
		    }
		    
		    return priceMap;
		}
		catch (Exception e) {
			 
			e.printStackTrace();
			throw new RuntimeException("load  price_filter.xml   error" );
		} 	 
		 
	}
	
	public String getFilterId() {
		
		return "price";
	}

	
	public String getAuthor() {
		
		return "kingapex";
	}

	
	public String getId() {
		
		return "priceSearchFilter";
	}

	
	public String getName() {
		
		return "价格搜索过虑器";
	}

	
	public String getType() {
		
		return "searchFilter";
	}

	
	public String getVersion() {
		
		return "1.0";
	}

	
	public void perform(Object... params) {
		

	}
	
	public void register() {
		

	}

}
