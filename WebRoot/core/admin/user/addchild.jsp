<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<div class="input">
<form action="multiSite!addSave.do" method="post" name="theForm"
	id="theForm" >
<table  class="form-table">
	<tr>
		<th><label class="text">网站名称：</label></th>
		<td><input type="text" name="site.name" 
			dataType="string" required="true" /></td>
	</tr>
	<tr>
       <th><label class="text">父站点：</label></th>
       <td><select name="site.parentid" id="siteparent"/></td>
     </tr>
     <tr>
       <th><label class="text">指定站点域名：</label></th>
       <td><input type="text" name="site.domain" id="sitedomain"  /></td>
     </tr>
      <tr>
       <th><label class="text">指定站点模板：</label></th>
       <td>
       <c:forEach var="item" items="${themeList }">
			<div class="item">
				<img class="ImgBorder ImgBackground" src="${previewBasePath }${item.path}/preview.png" />
				<span>${item.themename }</span>
				<span>作者：${item.author }</span>
				<span>版本：${item.version }</span>
				<span><input type="radio" name="site.themeid" value="${item.id }" /></span>
			</div>
		</c:forEach>
       </td>
     </tr>
	<tr>
		<td colspan=2><input type="reset" value="重置" class="btn"/>&nbsp;<input
			type="submit" value="新增" class="btn"/></td>
	</tr>
</table>
</form>
<script>
$(function(){
	$("#siteparent").selectTree("multiSite!listJson.do");
	
});
</script>
</div>