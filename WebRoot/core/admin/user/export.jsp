<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="input" id="exportbox">
<form class="validate"  >

   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >名称</th>
       <td><input type="text" name="name"    dataType="string" required="true"/>请输入英文字母
	   </td>
     </tr>
     <tr> 
       <th >选项</th>
       <td> <input type="radio" id="all" value="1"   name="exprt_opt" checked="true" />导出为解决方案&nbsp;&nbsp; <input type="radio" id="custom" name="exprt_opt" value="0" />自定义
	   </td>
     </tr>
     
     <tr id="customtr" style="display:none"> 
       <th >自定义</th>
       <td id="custombox"> 
       		<input type="checkbox"  name="exportData" value="1" checked="true" />数据&nbsp;&nbsp; 
       		<input type="checkbox" name="exportAttr" value="1" checked="true"  />附件&nbsp;&nbsp; 
            <input type="checkbox" name="exportTheme" value="1" checked="true"  />模板&nbsp;&nbsp; 
            <input type="checkbox" name="exportProfile" value="1" checked="true"  />配置文件&nbsp;&nbsp; 
	   </td>
     </tr>
          
   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="button" type="button"  value="导出" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
   </form>
   
</div>

<div class="input" id="downloadbox" style="display:none">
<h1>导出完成！</h1>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >zip下载:</th>
       <td><a href="" id="dl_link">点击此处下载</a>
	   </td>
     </tr>
     </table>
</div>

<script>
function doExport(){
	$.Loading.show('正在导出，请稍侯...'); 
	$("form").ajaxSubmit({
		url:"userSite!export.do?ajax=yes",
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.result==1){
				$("#dl_link").attr("href",result.path);
				$("#exportbox").hide();
				$("#downloadbox").show();
			}else{
				alert(result.message);
			}
			$.Loading.hide();
		},
		error:function(){
			$.Loading.hide();
			alert("导出错误");
		}
	});	
}
$(function(){
	$("form.validate").validate();
	$("#all").click(function(){
		if($(this).attr("checked")){
			$("#customtr").hide();
			$("#custombox>input").attr("checked",true);

		}
	});

	$("#custom").click(function(){
		if($(this).attr("checked")){
			$("#customtr").show();
			$("#custombox>input").attr("checked",false);
		}
	});	

	$(".submitBtn").click(function(){
		doExport();
	});
});
</script>