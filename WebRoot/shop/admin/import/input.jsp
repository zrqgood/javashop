<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input" >
<form class="validate"   enctype="multipart/form-data">
	<div style="color:red">提示：导入会覆盖当前站点的数据</div>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >输入导入配置文件路径</th>
       <td><input type="text" name="path"    dataType="string" required="true" /> 
	   </td>
     </tr>
   
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="button" type="button"  value="导入" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
   </form>
</div>
<script>
 
function doImport(){
	
	$.Loading.show('正在导入，请稍侯...'); 
	
	$("form").ajaxSubmit({
		url:"goodsImport!imported.do?ajax=yes",
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.result==1){
			 	alert("导入成功");
			 	$.Loading.hide();
			}else{
				$.Loading.hide();
				alert("导入失败");
			 
			}
			
		},
		error:function(){
			$.Loading.hide();
			alert("导入错误");
		}
	});	
}
 
 

$(function(){
	$(".submitBtn").click(function(){
		doImport();
	});
})
</script>