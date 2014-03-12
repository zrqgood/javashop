<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String ctx = request.getContextPath(); %>
<style>
.dialog .tableform {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#DDDDDD #BEC6CE #BEC6CE #DDDDDD;
border-style:solid;
border-width:1px;
margin:10px;
padding:5px;
}

.dialog .note  {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#CCCCCC;
color:#666666;
}
.dialog .error, .notice, .note, .success {
border:2px solid #DDDDDD;
font-size:1em;
margin-bottom:1em;
padding:0.8em;
}
</style>
<script type="text/javascript">
var Eop=Eop||{};
Eop.Upload={
	 upload:function(){
		var options ={
				url:"<%=ctx%>/eop/upload!upload.${ext}?ajax=1",
				dataType:"json",
				success:function(data){
					if(Eop.Upload.uploadOk && typeof Eop.Upload.uploadOk == 'function'){
						Eop.Upload.uploadOk(data);
					}else{
						uploadOk(data);
					}					
				},
				error:function(){
					alert("o,error.");
				}
				
			};
	
			$('#uploadForm').ajaxSubmit(options);		
	}
};
$(function(){
	$("#imgFromLocal").click(function(){
		if(this.checked){
			$("#imgViewLocal").show();
			$("#imgViewNet").hide();
		}
	});

	$("#imgFromNet").click(function(){
		if(this.checked){
			$("#imgViewLocal").hide();
			$("#imgViewNet").show();
		}
	});

	$("#okBtn").click(function(){
		if($("#imgFromLocal").attr("checked")){
			Eop.Upload.upload();
		}else if($("#imgFromNet").attr("checked")){
			if(Eop.Upload.uploadOk && typeof Eop.Upload.uploadOk == 'function'){
				Eop.Upload.uploadOk({result:0,path:$("#imgViewUrl").val()});
			}else{
				uploadOk({result:0,path:$("#imgViewUrl").val()});
			}
		}
	});
	
	

	$("#cancelBtn").click(function(){
		Eop.Dialog.close();	
	});
});
</script>	

<div class="headContent" style="">
<div class="tableform mainHead">
<h4>选择上传图片的方式</h4>
<div id="imgFrom">
	<input type="radio" checked id="imgFromLocal" name="from">
	<label for="imgFromLocal">上传图片</label>&nbsp;&nbsp;&nbsp;&nbsp;
	
	<input type="radio" id="imgFromNet" name="from">
	<label for="imgFromNet">网络图片地址</label>&nbsp;&nbsp;&nbsp;&nbsp;
</div>

</div>
</div>


<div>
<form enctype="multipart/form-data" method="post" id="uploadForm">
<input type="hidden" name="subFolder" value="${subFolder }">
<input type="hidden" name="createThumb" value="${createThumb }">
<input type="hidden" name="width" value="${width }">
<input type="hidden" name="height" value="${height }">
	<div class="tableform" id="imgViewLocal">
		
		<h4>从您的电脑中挑选一张图片：</h4>
		<input type="file" name="file">
	</div>

</form>
	<div class="tableform" id="imgViewNet" style="display: none;">
	
	<h4>输入一张网络图片的网址：</h4>
	<input type="text" value="http://" id="imgViewUrl" style="width: 80%;">
	<div class="note" id="imgViewUrlPreivew">复制网络上的一张图片路径到上面的输入框<br>
	.例如:"http://www.example.com/images/pic.jpg"</div>
	</div>
 


<div class="footContent" style="">

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="btn" type="button" id="okBtn" value=" 确    定   "  class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	

</div>
</div>
