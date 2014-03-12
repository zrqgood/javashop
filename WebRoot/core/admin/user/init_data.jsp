<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<style>
.code_img{
height:22px;
vertical-align:middle;
width:60px;
}
</style>
<div class="input" >
	<div style="color:red">提示：初始化数据会删除当前站点所有数据，请谨慎操作</div>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th ><img class="code_img" id="code_img" src="../../../validcode.do?vtype=initdata"></th>
       <td><input type="text" name="vcode" id="vcode" style="width:80px" />请输入图片中字符以确认
	   </td>
     </tr>
   
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="button" id="initBtn" type="button"  value="初始化数据" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
</div>
<script>
function doInit(){
	var vcode  = $("#vcode").val();
	if($.trim(vcode)==''){
		alert("请输入验证码以确认初始化");
		return ;
	}	
	
	$.Loading.show('正在初始化，请稍侯...'); 
	$.ajax({
		url:'userSite!initData.do?ajax=yes',
		data:'vcode='+vcode,
		type:'POST',
		dataType:'json',
		success:function(result){
			$.Loading.hide();
			if(result.result==1){
				alert("初始化成功，请重新启动以便请除缓存！");
			}else{
				alert(result.message);
			}
		},
		error:function(e){
			$.Loading.hide();
			alert("error:"+e);
		}
	});
	
}
$(function(){
	$("#initBtn").click(function(){
		doInit();
	});
})
</script>