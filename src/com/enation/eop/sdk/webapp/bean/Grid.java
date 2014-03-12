package com.enation.eop.sdk.webapp.bean;

import java.util.List;
import java.util.Map;
import java.util.Iterator;

import com.enation.framework.database.Page;
/*
 * 数据列表对象基类，派生类支持如下特性
 * 1. grid taglib - Server
 * 2. datatable   - Browser
 */
public abstract class Grid {
	//	datatable 参数开始
	private int iDisplayStart;
	private int iDisplayLength;
	private String iSortCol_0;
	private int iSortingCols;
	private String sSearch;
	private String sEcho;
	//	datatable 参数结束

	private String json;
	
//	public String getJson() {
//		if(json==null){
//			Page page = getWebpage();
//			List<Map<String,Object>> list = (List)page.getData();
//			json = "{";
//			json += "'sEcho': " + (sEcho==null ? "1" : sEcho) + "," ;
//			json += "'iTotalRecords': " + page.getTotalCount() + ",";
//			json += "'iTotalDisplayRecords': " + page.getTotalCount() + ",";
//			json += "'aaData': [ ";
//			for(int i=0;i<list.size();i++) {
//				Map map = list.get(i);
//				Iterator it = map.entrySet().iterator();
//				String record = "[";
//				while (it.hasNext())
//				{
//					Map.Entry entry = (Map.Entry) it.next() ;
//					record += "'" + entry.getValue() + "',";
//				}
//				record = record.substring(0,record.length()-1) + "],";
//				json += record;
//			}
//			json = json.substring(0,json.length()-1);
//			json += "]}";
//		}
//			
//		return json;
//	}
	public void setJson(String json) {
		this.json = json;
	}

	private Page webpage;
	
	public Page getWebpage() {
		if(webpage==null)
			webpage = execute(getPageNo(),getPageSize(),getOrder()); 
		return webpage;
	}
	public void setWebpage(Page webpage) {
		this.webpage = webpage;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(String iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public int getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	/*
	 *	以下为参数转换代码，方便派生类调用
	 */
	public int getPageNo(){
		if(getiDisplayLength()==0){
			return 1;
		}
		return getiDisplayStart() / getiDisplayLength() + 1;
	}
	public int getPageSize(){
		if(getiDisplayLength()==0)
			return 10;
		
		return getiDisplayLength();
	}
	public String getOrder(){
		return "";
	}
	
	public abstract Page execute(int pageNo,int pageSize,String order);
}