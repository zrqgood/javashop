<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>	
<div>
		<grid:grid from="webpage" ajax="yes">
			<grid:header>
				<th style="width:80px">选择</th>
				<th>标题</th>
			 
			</grid:header> 	
	  		<grid:body item="article">
	  			<grid:cell> <input type="checkbox" name="dataid" value="${article.id }"/> </grid:cell>
	  			<td class="title">${article.title }</td> 
  			 
			</grid:body>
		</grid:grid>
</div>		