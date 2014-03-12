<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="division">
	<div class="grid">
		<table cellspacing="0" cellpadding="0" border="0" width="100%" class="finderInform">
			<thead>
				<tr>
					<th>序号</th>
					<th>时间</th>
					<th>操作人</th>
					<th>操作内容</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logList}" var="log">   
				<tr>
					<td>${log.log_id }</td>
					<td><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${log.op_time}"></html:dateformat> </td>
					<td>${log.op_name }</td>
					<td>${log.message }</td>
				</tr>
				</c:forEach>          
			</tbody>
		</table>
	</div>
</div>