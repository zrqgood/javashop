<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<table>
<thead>
	<tr>
		<td width="200px">域名</td>
		<td>操作</td>
	</tr>
</thead>	
<tbody>
	<c:forEach var="item" items="${siteDomainList }">
		<tr>
			<td>${item.domain }</td>
			<td domainid="${item.id }" statusid="${item.status }">&nbsp;
			<a class="domainDel" href="javascript:;"><img class="delete" src="../images/transparent.gif" > </a>&nbsp;</td>
		</tr>
	</c:forEach>
	</tbody>
</table>