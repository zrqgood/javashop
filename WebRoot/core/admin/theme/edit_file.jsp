<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<div class="toolbar">
<ul>
	<li><a href="themeFile!list.do?themeid=${themeid }&folderName=${folderName}&type=${type}">返回</a></li>
</ul>
<div style="clear: both;"></div>
</div>
<div class="input">
<form  action="themeFile!edit.do" method="post" class="validate">
	<input type="hidden" name="name" value="${name }" />
	<input type="hidden" name="themeid" value="${themeid }" />
	<input type="hidden" name="folderName" value="${folderName }" />
	<input type="hidden" name="type" value="${type }" />
	<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
		<tr>
			<th><label class="text">名称：</label></th>
			<td>${name }</td>
		</tr>
		<tr>
			<th><label class="text">内容：</label></th>
			<td>
			<textarea style="width:95%;height:400px" id="content" name="content"><c:out value="${content}" escapeXml="true"></c:out></textarea>
			</td> 
		</tr>
	</table>
	
	<div class="submitlist" align="center">
		<table>
			<tr>
				<td><input type="submit" name="submit" value="提交更改" class="submitBtn" /></td>
			</tr>
		</table>
	</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
//	$('#content' ).ckeditor( );	
});
</script>