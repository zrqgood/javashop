<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style>
.input table th{
width:80px
}
</style>
<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="addBtn">新增</a></li>
			<li><a href="javascript:;" id="saveBtn">保存修改</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
	
	<form method="POST" id="editform">
	
	<grid:grid  from="uriList">
	
		<grid:header>
			<grid:cell width="50">id</grid:cell> 
			<grid:cell width="250">uri</grid:cell> 
			<grid:cell width="200">path</grid:cell> 
			<grid:cell width="150">页面名称</grid:cell>
			<grid:cell width="100">积分</grid:cell>
			 <grid:cell>操作</grid:cell>
		</grid:header>
	
	  <grid:body item="uri">
	        <grid:cell>${uri.id}<input type="hidden" value="${uri.id}"  name="ids" /> </grid:cell>
	        <grid:cell><input type="text" name="uri" value="${uri.uri }" style="width:200px" /> </grid:cell>
	        <grid:cell><input type="text" name="path" value="${uri.path}" style="width:150px" /> </grid:cell> 
	        <grid:cell><input type="text" name="pagename" value="${uri.pagename}" style="width:150px" /></grid:cell> 
	        <grid:cell><input type="text" name="point" value="${uri.point}" style="width:100px" /></grid:cell>
	        <grid:cell>
	        <a href="javascript:;" uriid="${uri.id }"><img class="delete" src="images/transparent.gif" ></a>
	        &nbsp;&nbsp;
	        <a href="javascript:;"><img class="modify" src="images/transparent.gif"  uriid="${uri.id }"></a>
	        </grid:cell>
	  </grid:body> 
	  
	</grid:grid>  
	</form>	 
	<div style="clear:both;padding-top:5px;"></div>
</div>

<div id="adddlg">
</div>
<div id="editdlg">
</div>

<script type="text/javascript">
var ThemeUri = {
	init:function(){
		var self  = this;
		Eop.Dialog.init({id:"adddlg",modal:false,title:"新建映射"});
		Eop.Dialog.init({id:"editdlg",modal:false,title:"修改映射"});
		
		$("#addBtn").click(function(){
			$("#adddlg").load("themeUri!add.do?ajax=yes",function(){
				$("#adddlg .submitBtn").click(function(){
					self.add();
				});		
			});
			Eop.Dialog.open("adddlg");
		});

		$(".modify").click(function(){
			var id  = $(this).attr("uriid");
			$("#editdlg").load("themeUri!edit.do?ajax=yes&id="+id,function(){
				$("#editdlg .submitBtn").click(function(){
					self.edit();
				});		
			});
			Eop.Dialog.open("editdlg");			
		});


		$("#saveBtn").click(function(){
			self.save();
		});
		$(".delete").parent().click(function(){
			
			if( !confirm("确定删除此映射吗?") ){
				return ;
			}
			
			var id = $(this).attr("uriid");
			$.Loading.show('正在删除，请稍侯...');
			$.ajax({
				url:'themeUri!delete.do?id='+id+'&ajax=yes',
				type:'get',
				dataType:'json',
				success:function(result){
					$.Loading.hide();
					if(result.result==1){
						alert("删除成功");
						location.reload();
					}else{
						alert(result.message);
					}						
				},
				error:function(){
					alert("删除出现错误");
				}
				
			});
		});
	},
	add:function(){
		$.Loading.show('正在添加，请稍侯...');
		var options = {
				url : "themeUri!saveAdd.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					Eop.Dialog.close("adddlg");
					$.Loading.hide();
					alert("添加成功");
					if(result.result==1){
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

			$('#adddlg form').ajaxSubmit(options);			
	},
	save:function(){
		if( !this.checkEdit() ){
			return ;
		}
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url : "themeUri!batchEdit.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
					alert("保存成功");
					if(result.result==1){
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

			$('#editform').ajaxSubmit(options);
	},
	edit:function(){
		$.Loading.show('正在修改，请稍侯...');
		var options = {
				url : "themeUri!saveEdit.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					Eop.Dialog.close("editdlg");
					$.Loading.hide();
					
					if(result.result==1){
						alert("修改成功");
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

			$('#editdlg form').ajaxSubmit(options);			
	},
	checkEdit:function(){
		var result = true;
		$("[name='uri']").each(function(){
			if( $.trim( $(this).val() )=='' ){
				result = false;
			}
		});
		if(!result){ alert("uri不能为空"); return result; }	 

		$("[name='path']").each(function(){
			if( $.trim( $(this).val() )=='' ){
				result = false;
			}
		});
		if(!result){ alert("path不能为空"); return result; }

		$("[name='pagename']").each(function(){
			if( $.trim( $(this).val() )=='' ){
				result = false;
			}
		});
		if(!result){ alert("页面名称不能为空"); return result; }
		
		$("[name='point']").each(function(){
			var point  = $.trim( $(this).val() );
			
			if( point=='' || parseInt(point) != point ){
				result = false;
			}
		});
		if(!result){ alert("积分不能为空，且必须是数字"); return result; }		

		return true;	
	}
		
};
$(function(){
	ThemeUri.init();
});
</script>
