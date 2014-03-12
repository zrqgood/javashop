<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input">
	<form class="validate" >
		<div style="color:red">提示：此操作会重新所有商品图片，不可恢复，请谨慎操作。</div>
		<div class="submitlist" align="center">
			<table>
				<tr>
					<td>共有商品相册：${total }个，生成从
						<input id="istart" type="text" name="start" value="1" style="width:35px"></input> -
						<input id="iend" type="text" name="end" value="${total}" style="width:35px"></input>个
					</td>
				</tr>
				<tr>
					<td>
						<input name="button" type="button"  value="生成" class="submitBtn"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script>
function doCreate(){

	var istart = $("#istart").val();
	var iend = $("#iend").val();
	if(istart * 1 != istart || iend * 1 != iend || istart * 1 > iend * 1){
		alert("输入有误，请检查！");
		return false;
	}
		
	$.Loading.show('正在生成，请稍侯...'); 

	$("form").ajaxSubmit({
		url:"gallery!recreate.do?ajax=yes",
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.result==1){
			 	alert("生成成功");
			 	$.Loading.hide();
			}else{
				$.Loading.hide();
				alert(result.message);
			}
			
		},
		error:function(){
			$.Loading.hide();
			alert("生成错误");
		}
	});	
}

$(function(){
	$(".submitBtn").click(function(){
		doCreate();
	});
})
</script>