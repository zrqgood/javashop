<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../core/common/commonlibs.jsp"%>    
<span>请选择一个编制好的菜单：</span>
<select id="menu_id" name="menu_id" eop_type="widget_params">
	<option value="0">全部</option>
	<c:forEach var="item" items="${menuList}">
		<option value="${item.id}">${item.title }</option>
	</c:forEach>
</select>