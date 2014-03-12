<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<div class="input">
<form   method="post" id="domainForm">
<table  class="form-table">
	<tr>
		<th><label class="text">域名：</label><input type="hidden" name="eopSiteDomain.siteid" id="siteid" value="${id }" /></th>
		<td ><input type="text" name="eopSiteDomain.domain"  dataType="string" required="true" /></td>
	</tr><input type="hidden" name="eopSiteDomain.domaintype" value="0" /><tr>
		<th><label class="text" >状态：</label></th>
		<td><select name="eopSiteDomain.status" id="eopSiteDomain.status">
			<option value="0"
				<c:if test="${eopSiteDomain.status==0 }"> selected</c:if>>已开启</option>
			<option value="1"
				<c:if test="${eopSiteDomain.status==1 }"> selected</c:if>>已关闭</option>
		</select></td>
	</tr>
	<tr>
		<td colspan=2><input type="reset" value="重置" class="btn" />&nbsp;<input
			type="button" value="新增" id="add_btn" class="btn"/></td>
	</tr>
</table>
</form>
</div>