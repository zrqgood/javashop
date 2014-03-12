package com.enation.eop.sdk.webapp.taglib.html.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enation.eop.sdk.webapp.plugin.AbstractPluginsBundle;
import com.enation.eop.sdk.webapp.taglib.html.GridCellTaglib;
import com.enation.eop.sdk.webapp.taglib.html.GridTaglib;

public class GridCellProvider extends AbstractPluginsBundle{
	
	private String sort;

	private String sortDefault;

	private String order;

	private int isSort;

	private String style;

	private String width;

	private String height;

	private String align;
	
	private String clazz;  // add by lzf

	private GridCellTaglib celltaglib;
	private String plugin_type;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public GridCellProvider(){
		
	}
	
	public void initCellProvider(GridCellTaglib _celltaglib) {
		celltaglib=_celltaglib;
		sort = celltaglib.getSort();
		sortDefault = celltaglib.getSortDefault();
	
		isSort = celltaglib.getIsSort();
		style = celltaglib.getStyle();
		width = celltaglib.getWidth();
		height = celltaglib.getHeight();
		align = celltaglib.getAlign();
		request = celltaglib.getRequest(); 
		response = celltaglib.getResponse();
		order = request.getParameter("order");
		clazz = celltaglib.getClazz();
		this.plugin_type = celltaglib.getPlugin_type();
	}
	
	public String getStartHtml(){
		if(order ==null) isSort = 0; else isSort = 1;
		
		order=order==null || order.equals("") ? sort + " desc" : order;
		String str = "";
		String parentName = celltaglib.getParent().getClass().getName();
		
		if(parentName!=null && parentName.endsWith("GridHeaderTaglib")){
			str="<th ";
			if(width!=null){
				str+="width='" + width +"'";
			}
			
			if(height!=null){
				str+="height='" + height +"'";
			}	
			
			if(this.align!=null){
				str+="align='" + this.align +"'";
			}
		} else {
			str= "<td ";
			if(this.align!=null){
				str+="align='" + this.align +"'";
			}
			
		}
		
		if(clazz!=null){
			str+=" class='" + clazz +"'";
		}
		
		if(this.style!=null){
			str+=" style='" + this.style+"'";
		}
		str +=">";

		init();
		if(this.sort!=null)
			str+=this.getSortOpStart();		
		
		return str;		
	}
	
	private void init(){
		GridTaglib tag = (GridTaglib)celltaglib.getParent().getParent();
	}

	/**
	 * 判断此cell是否正在排序
	 * @return
	 */
	private int getCanSort(){
		if(isSort==0){
			if(this.sortDefault!=null && this.sortDefault.equals("yes")){
				return 1;
			} else {
				return 0;
			}
		}
		
		String regEx = "(\\s+)";
		Pattern p = Pattern.compile(regEx);
		String[] op  = p.split(this.order);
		
		
		if(op[0].equals(sort)){
			return 1;
		}
		
		return 0;
	}
	
	/**
	 * 生成操作串的href
	 * @return
	 */
	private String getUrl() {
		String contextPath = request.getContextPath();
		String queryString  = request.getQueryString();

		String  servletPath =null;
		 
		servletPath = request.getServletPath();
	 
		
		queryString = queryString==null?"" :queryString; 
		String regEx = "(&||\\?)order=.*";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(queryString);
		queryString = m.replaceAll("");		
		
		queryString = queryString.equals("")? "?" : "?" + queryString + "&";

		return  contextPath+servletPath+queryString;
	}
	
	/**
	 * 
	 * 得到排序开始操作的字串
	 * 将排序的起连接字串分开
	 * 因为是点击cell中的字排序
	 * 
	 * @return
	 */
	private String getSortOpStart(){

		StringBuffer buffer = new StringBuffer();
		
		if(order.endsWith("asc")){
			buffer.append("<a href=\"");
			
			buffer.append(getUrl()) ;			
			buffer.append("order=");
			buffer.append(this.sort);
			buffer.append(" desc");
			buffer.append("\"");
			
			if(getCanSort()==1)
				buffer.append(" class=\"desc\"");
			
			buffer.append(">");
			
		}
		if(order.endsWith("desc")){
			
			buffer.append("<a href=\"");
			
			buffer.append(getUrl()) ;			
			buffer.append("order=");
			buffer.append(this.sort);
			buffer.append(" asc");
			buffer.append("\"");
			
			if(getCanSort()==1)
				buffer.append(" class=\"asc\"");
			
			buffer.append(">");

		}
		
		return buffer.toString();
	}
	
	/**
	 * 
	 * 得到排序结速操作的字串
	 * 将排序的起连接字串分开
	 * 因为是点击cell中的字排序
	 * 
	 * @return
	 */	
	private String getSortOpEnd(){
		StringBuffer buffer =  new StringBuffer();
		
		buffer.append("</a>");
//		if(order.endsWith("asc")){
//			if( getCanSort()==1){
//				buffer.append("<span class=\"desc\"></span>");
//			}
//			buffer.append("</a>");
//		}
//		if(order.endsWith("desc")){
//			if(  getCanSort() ==1){
//				buffer.append("<span class=\"asc\"></span>");
//			}
//			buffer.append("</a>");
//		}
		
		return buffer.toString();
	}

	/**
	 * 这个标记的结束串
	 */
	public String getEndHtml() {
		
		String str = "";
		if(this.sort!=null){
			if(getCanSort() ==1)
				str+= this.getSortOpEnd();
			else
				str+="</a>";
		}
		
		int isTh=0;
		
		String parentName = celltaglib.getParent().getClass().getName();
		if(parentName!=null && parentName.endsWith("GridHeaderTaglib")){
			str+="</th>";
			isTh=1;
		}else{
			str+= "</td>";
		}
		StringBuffer buffer = new StringBuffer();
		if(plugin_type!=null)
			this.performPlugins("grid_cell_plugin",this.plugin_type, this.request,response,buffer,isTh);
		str+=buffer.toString(); 
		
		return str;
	}
}
