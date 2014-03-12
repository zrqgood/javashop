<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input">
	<form method="post" action="payCfg!save.do" class="validate" name="theForm" id="theForm"  >
		<input type="hidden" name="paymentId" value="${paymentId }" />
		<table cellspacing="1" cellpadding="3" width="100%" class="form-table" id="pluginTable">
			<tbody>
				<tr id="first">
					<th>支付方式：</th>
					<td>
						<select name="type" id="pluginList">
							<option value="">请选择要添加的支付方式</option>
							<c:forEach items="${pluginList}" var="plugin">
							<option value="${plugin.id }">${plugin.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="name">
					<th>支付方式名称：</th>
					<td>
						<input type="text" name="name"  id="payment_name" value="${name }" />
					</td>
				</tr>
				<tr id="last">
					<th>介绍：</th>
					<td>
						<textarea name="biref" id="biref">${biref}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="submitlist" align="center">
			<table>
				<tr>
					<td><input name="submit" type="submit" value=" 确　定 " class="submitBtn" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script>
function cleanTr(){
	$("#pluginTable>tbody>tr").each(function(){
		if($(this).attr("id")!="first" &&$(this).attr("id")!="last"   &&$(this).attr("id")!="name"  ){
				$(this).remove();
		}
	});
}

function loadHtml(paymentid,pluginid,name){
	 
 	if(pluginid && pluginid!=''){
 		
	$.ajax({
		url:"payCfg!getPluginHtml.do?ajax=yes&pluginId="+pluginid+"&paymentId="+paymentid,
		success:function(html){
			cleanTr(); 
			$("#pluginTable tr[id=name]").after(html);
			$("#payment_name").val(name);
			
		},
		error:function(){
			alert("出错了");
		} 
	});
 	}	
}
$(function(){
	$('#biref').ckeditor();
	$("form.validate").validate();
	$("#pluginList").change(function(){
		var pluginid = $(this).val();
		var name = $(this).find("option[selected]").text();
		loadHtml("",pluginid,name);
	});
	loadHtml('${paymentId}','${type}','${name}');

});
</script>