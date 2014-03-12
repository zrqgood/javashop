<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
	
<div class="input">
<%@ include file="account_header.jsp" %>
<%@ include file="info_header.jsp" %>
<form method="post" action="userDetail!save.do" name="theForm"
	id="theForm">
<table class="form-table">
	<tr>
		<th> <input type="hidden"
			name="eopUserDetail.userid" id="eopUserDetail.userid"
			value="${userid }" /><label class="text">经营范围：</label></th>
		<td><input type="text" name="eopUserDetail.bussinessscope"
			id="eopUserDetail.bussinessscope"
			value="${eopUserDetail.bussinessscope }" 
			dataType="string" required="true" style="width:260px;" /></td>
	</tr>
	<tr>
		<th><label class="text">注册地：</label></th>
		<td><input type="text" name="eopUserDetail.regaddress"
			id="eopUserDetail.regaddress" value="${eopUserDetail.regaddress }"
			 dataType="string" required="true" style="width:260px;" /></td>
	</tr>
	<tr>
		<th><label class="text">注册日期：</label></th>
		<td><input type="text" name="eopUserDetail.regdate"
			id="eopUserDetail.regdate" value="${eopUserDetail.regdate }"
			 dataType="string" required="true" style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">公司规模：</label></th>
		<td><select name="eopUserDetail.corpscope"
			id="eopUserDetail.corpscope">
			<option value="0"
				<c:if test="${eopUserDetail.corpscope==0 }"> selected</c:if> >未知</option>
			<option value="1"
				<c:if test="${eopUserDetail.corpscope==1 }"> selected</c:if> >10人以下</option>
			<option value="2"
				<c:if test="${eopUserDetail.corpscope==2 }"> selected</c:if> >11-50人</option>
			<option value="3"
				<c:if test="${eopUserDetail.corpscope==3 }"> selected</c:if> >51-100人</option>
			<option value="4"
				<c:if test="${eopUserDetail.corpscope==4 }"> selected</c:if> >101-500人</option>
			<option value="5"
				<c:if test="${eopUserDetail.corpscope==5 }"> selected</c:if> >501-1000人</option>
			<option value="6"
				<c:if test="${eopUserDetail.corpscope==6 }"> selected</c:if> >1000人以上</option>
		</select></td>
	</tr>
	<tr>
		<th><label class="text">公司简介：</label></th>
		<td><fck:editor basePath="${ctx}/editor/fckeditor/" startupFocus="false"
							id="eopUserDetail.corpdescript" width="98%" height="300" toolbarSet="Default">
						${eopUserDetail.corpdescript}</fck:editor></td>
	</tr>
 
</table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>  
</form>
</div>