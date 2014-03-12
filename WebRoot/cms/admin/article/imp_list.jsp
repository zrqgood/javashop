<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="imp_list">
<form action="data!importdata.do" method="post" id="importform">
<%@ include file="/commons/taglibs.jsp"%>
<c:set var="fieldList" value="${fieldList}" />

<div class="input">

<table class="form-table">
	<tr>
		<th id="catbox">
		<select name="siteid" id="siteparent"/>
	     </th>
		<td>
		 <input type="button" name="filterBtn" id="filterBtn" value="确定"/>
		
		</td>
	</tr>
</table>	

</div>


<div class="grid">
<input type="hidden" name="catid" value="${catid }"/>
<div id="gridbox">
<grid:grid  from="webpage" ajax="yes">

	<grid:header> 
	<grid:cell width="50px">选择</grid:cell>
	
	<c:forEach items="${fieldList}" var="field">
		<th style="width:100px">${field.china_name}</th> 
	</c:forEach>
		<th >添加时间</th>
	</grid:header> 

  <grid:body item="article">
  		<grid:cell>${article.id }<input type="checkbox" name="ids" value="${article.id }" /> </grid:cell>
  		<html:field></html:field>
  		<td><fmt:formatDate value="${article.add_time}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
  </grid:body>   
  
</grid:grid>  
</div>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	  <input name="button" type="button" value=" 确    定   " id="okBtn" class="submitBtn" />
	   </td>
	   </tr>
	 </table>
	</div>
<div style="clear:both;padding-top:5px;"></div>
<script>
$(function(){
	$("#siteparent").selectTree("${ctx}/core/admin/multiSite!listJson.do");
	//$("#siteparent").val(${siteid});
	
	$("#filterBtn").click(function(){

		$.ajax({
			 type: "GET",
			 url: "data!implist.do?ajax=yes&catid=${catid }&siteid="+$("#siteparent").val(),
			 dataType:'html',
			 success: function(result){
				 $("#imp_list").empty().append(result);
		     },
		     error:function(){
				alert("数据列表获取失败");
			 }
		});
	});
	$("#okBtn").click(function(){
		var options = {
				url :"data!importdata.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
					Eop.Dialog.close("import_selected");
					location.reload();
				},
				error : function(e) {
					alert("出错啦:(");
					Eop.Dialog.close("import_selected");
 				}
 		};
		$("#importform").ajaxSubmit(options);
	});
});
</script>
</div>
</form>	
</div>
