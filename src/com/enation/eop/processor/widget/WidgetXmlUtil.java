package com.enation.eop.processor.widget;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 挂件xml参数解析工具
 * @author kingapex
 * 2010-2-4下午04:11:25
 */
public class WidgetXmlUtil {

	private  WidgetXmlUtil(){}
	
	
	
	/**
	 * 返回的Map规则：
	 * key为挂件id
	 * value为挂件参数Map
	 * @param path
	 * @return
	 */
	public static Map<String, Map<String, Map<String,String>>> parse(String path){
		try {
 
		    Document document = paseParamDoc(path);
		    return parsedoc(document);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load ["+path +"] widget file error" );
		} 	 
	}
	
	/**
	 * 将一个页面挂件json参数格式转为Map格式
	 * @param paramJson
	 * @return
	 */
    public static  List<Map<String,String>>   jsonToMapList(String paramJson){
    	 JSONArray tempArray= JSONArray.fromObject(paramJson);
    	 List<Map<String,String>> paramList = new ArrayList<Map<String,String>>();
    	 Object[] paramArray = tempArray.toArray();
    	 for(Object param: paramArray){
    		 JSONObject paramObj = JSONObject.fromObject(param);
    		 paramList.add( (Map<String,String>) JSONObject.toBean(paramObj,Map.class));
    	 }
    	 
    	 return paramList;
    }
    
    
    /**
     * 将一个params转为Json字串
     * @param params
     * @return
     */
    public static String mapToJson(Map<String,Map<String,String>> params){
    	if(params==null)
    		return "[]";
    		
    	Set<String> widgetIdSet = params.keySet();
    	List<Map<String,String>> mapList= new ArrayList<Map<String,String>>();
    	for(String widgetId: widgetIdSet){
    		Map<String,String>  widgetParams=  params.get(widgetId);
    		widgetParams.put("id", widgetId);
    		mapList.add(widgetParams);
    	}
    	JSONArray array= JSONArray.fromObject(mapList);
    	
    	return array.toString();
    }
	
    
    /**
     * 保存挂件参数
     * @param path
     * @param pageId
     * @param params
     */
	public  static void save(String path,String pageId,List<Map<String,String>> params){
		
		try {
			
		    Document document =  paseParamDoc(path);
		    
		    //根据参数Map解析出新的page结点
		    Node newPageNode  = createPageNode(document,pageId,params);
		    
		    Node widgets  = document.getFirstChild();
		    NodeList nodeList  = widgets.getChildNodes(); //子元素为page结点
		    for(int i=0;i<nodeList.getLength();i++){
		    	Node page= nodeList.item(i);
		    	if(page.getNodeType() == Node.ELEMENT_NODE){
		    		Element pageEl= (Element) page;
		    		String id=pageEl.getAttribute("id");
		    		if(id.equals(pageId)){
		    			widgets.replaceChild(newPageNode, page);
		    		}
		    	}
		    }
		    
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer t = tfactory.newTransformer();
			t.setOutputProperty("encoding", "UTF-8");
			t.setOutputProperty(OutputKeys.INDENT,"yes");
			t.setOutputProperty(OutputKeys.METHOD,"xml");
			
			FileOutputStream stream = new FileOutputStream( new File(path));
			DOMSource source = new DOMSource(document);
			t.transform(source, new StreamResult(stream));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("save ["+path +"] widget file error" );
		} 	
	}
	
	/**
	 * 将一个map挂件参数转换为node结点
	 * @param doc
	 * @param pageId
	 * @param params
	 * @return
	 */
	private static Node createPageNode(Document doc,String pageId,List<Map<String,String>> params){
		
		//创建一个新的page结点
		Element pageEl = doc.createElement("page");
		pageEl.setAttribute("id", pageId);
		
		//通过参数list创建新的 widget结点，并添加为新的page子结点
		for(Map<String,String> paramMap:params){
			Element widgetEl= doc.createElement("widget");
			widgetEl.setAttribute("id", paramMap.get("id"));
			Set<String> paramSet =paramMap.keySet();
			for(String name:paramSet){
				
				if(!"id".equals(name)){
					Element paramEl = doc.createElement(name);
					paramEl.setTextContent(paramMap.get(name));								
					widgetEl.appendChild(paramEl);
				}
				
			}
			pageEl.appendChild(widgetEl);
		}		
		
		return pageEl;
	}
	
	
	
	private static Map<String,Map<String,Map<String,String>>> parsedoc(Document doc){
		Map<String,Map<String,Map<String,String>>>  params= new LinkedHashMap<String,Map<String,Map<String,String>>>();
		Node widgets = doc.getFirstChild();
		if(widgets==null) throw new RuntimeException("widget xml error[page node is null]");
		NodeList nodeList  = widgets.getChildNodes(); //子元素为page结点
		for(int i=0;i<nodeList.getLength();i++){
			Node page= nodeList.item(i);
			if(page.getNodeType() == Node.ELEMENT_NODE){
				Map<String, Map<String, String>>  widgetParams = parse(page);
				params.put( ((Element)page).getAttribute("id")  , widgetParams);
				
			}
		}
		return params;
	}
	
	
	/**
	 * 将一个page结点转 为Map
	 * @param document
	 * @return
	 */
	private  static  Map<String, Map<String, String>> parse(Node page){
		
		Map<String, Map<String, String>> params = new LinkedHashMap<String, Map<String,String>>();
		 
		if(page==null) throw new RuntimeException("widget xml error[page node is null]");
		
		NodeList nodeList  = page.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element widgetEl = (Element)node;
				String main = widgetEl.getAttribute("main");

				Map<String,String> param = parae(widgetEl);
				if("yes".equals(main)){ //如果是主挂件，标识id为main
					params.put("main", param);
				}else	{	
					params.put(widgetEl.getAttribute("id"), param);
				}
				
			}
		}
		
		return params;
	}
	
	
	/**
	 * 将一个参数 widget 结点元素转为参数Map
	 * @param element
	 * @return
	 */
	private static  Map<String,String> parae(Element element){
		
		NodeList nodeList  = element.getChildNodes();
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put("widgetid", element.getAttribute("id"));
		
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element attr = (Element)node;
				String name = attr.getNodeName();
				String value = attr.getTextContent();
				param.put(name, value);
				
			}
		}
		
		return param;
	}
	
	/**
	 * 给定一个挂件xml地址，解析为document
	 * @param path
	 * @return
	 */
	private static Document paseParamDoc(String path){
		try {
		    DocumentBuilderFactory factory = 
		    DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(path);
		    return document;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load ["+path +"] widget file error" );
		} 	 
	}
		
}
