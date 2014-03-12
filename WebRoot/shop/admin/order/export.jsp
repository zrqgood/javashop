<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input" >
<form class="validate" >
 
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >下单日期：</th>
       <td>
       由
       <input type="text" name="start" readonly="true"  class="dateinput"  />
     至  
          <input type="text" name="end" readonly="true"  class="dateinput"   />
        
	   </td>
     </tr>
   
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="button" type="button" id="exportBtn" value="导出" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
   </form>
</div>
<script>
 
function doImport(){
	
	$.Loading.show('正在导出，请稍侯...'); 
	
	$("form").ajaxSubmit({
		url:"order!export.do?ajax=yes",
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.result==1){
			 	alert("导出成功");
			 	window.open(result.url);
			 	$.Loading.hide();
			}else{
				$.Loading.hide();
				alert("导出失败");
			 
			}
			
		},
		error:function(){
			$.Loading.hide();
			alert("导出错误");
		}
	});	
}
 
 

$(function(){
	$("#exportBtn").click(function(){
		doImport();
	});
})
</script>