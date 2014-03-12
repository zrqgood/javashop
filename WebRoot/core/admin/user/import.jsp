<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input" >
<form class="validate"   enctype="multipart/form-data">
	<div style="color:red">提示：导入解决方案会可能会覆盖当前站点的数据</div>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >选择导入文件</th>
       <td><input type="file" name="zip"    dataType="string" required="true" />.zip格式
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
var backupsuccess=true;
var backupname;
function doImport(){
	
	$.Loading.show('正在导入，请稍侯...'); 
	
	$("form").ajaxSubmit({
		url:"userSite!imported.do?ajax=yes",
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.result==1){
			 	alert("导入成功");
			 	$.Loading.hide();
			 	$(this)[0].rest();
			}else{
				$.Loading.hide();
				alert("导入失败，点击确定还原您的站点");
				restore();
			}
			
		},
		error:function(){
			$.Loading.hide();
			alert("导入错误");
		}
	});	
}

function restore(){
	$.Loading.show('正在还原站点...'); 
	$.ajax({
		url:'userSite!restore.do?ajax=yes',
		data:"name="+backupname,
		dataType:"json",
		success:function(result){
			$.Loading.hide();
			if(result.result==1){
			 	alert('站点还原成功');
			}else{
				alert('站点还原失败');
			}
		},error:function(){
			$.Loading.hide();
			alert('站点还原失败');
		}
	});	
	
}

/**
 * 备份当前站点
 */
function backup(){
	$.Loading.show('正在创建还原点...'); 
	$.ajax({
		url:'userSite!backup.do?ajax=yes',
		dataType:"json",
		success:function(result){
			$.Loading.hide();
			if(result.result==1){
				backupname =result.name;
				doImport();
			}else{
				backupsuccess=false; //标记备份失败
				if( confirm("创建还原点失败，如果导入失败将无法恢复您的数据，您还要继续导入吗？") ){
					doImport();
				}
			}
		},error:function(){
			$.Loading.hide();
			backupsuccess=false; //标记备份失败
			if( confirm("创建还原点失败，如果导入失败将无法恢复您的数据，您还要继续导入吗？") ){
				doImport();
			}
		}
	});	
}



$(function(){
	$(".submitBtn").click(function(){
		backup();
	});
})
</script>