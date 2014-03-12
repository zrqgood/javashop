<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style>

.spec_pic {
border:1px solid #3366CC;
padding:1px;
vertical-align:middle;
}

</style>
<c:if test="${specView.spec_id==null}">
<form method="post" action="spec!saveAdd.do" class="validate" name="theForm" id="theForm">
</c:if>
<c:if test="${specView.spec_id!=null}">
<form method="post" action="spec!saveEdit.do" class="validate" name="theForm" id="theForm">
<input type="hidden" name="spec.spec_id" value="${specView.spec_id}" />
</c:if>

<div class="input">

<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<th><label class="text">规格名称：</label></th>
		<td><input type="text" name="spec.spec_name" maxlength="60"
			value="${specView.spec_name }" dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">规格备注：</label></th>
		<td><input type="text" name="spec.spec_memo" maxlength="60" value="${specView.spec_memo}"/></td>
	</tr>
	<tr>
		<th><label class="text">显示类型：</label></th>
		<td><label><input type="radio" checked value="0" name="spec.spec_type">文字</label>
			<label><input type="radio" value="1" name="spec.spec_type">图片</label>
		</td>
	</tr>

</table>
</div>
<div class="grid">
	<div class="toolbar">
	
		<ul>
			<li><a id="addBtn" href="javascript:;">添加规格值</a></li>
			<li>&nbsp;&nbsp;<span class="help_icon" helpid="spec_add"></span></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>

	
<grid:grid from="valueList" pager="no">

	<grid:header>
		<grid:cell >规格值名称</grid:cell>
		<grid:cell >&nbsp;</grid:cell>
		<grid:cell>操作</grid:cell>
	</grid:header>

	<grid:body item="val">
		<grid:cell><input type="text" name="valueArray" value="${val.spec_value }" /> </grid:cell>
		<grid:cell>
		
		<img height="30" width="30" src="${val.spec_image }" class="spec_pic">
		<span class="specImage">
		<a href="javascript:;" class="sysbtn upload">上传图片</a>
		<input type="hidden" name="imageArray"  value="${val.spec_image }"/>
		</span> </grid:cell>
		<grid:cell> <a  href="javascript:;"><img class="delete" src="${staticserver }/images/transparent.gif" ></a> </grid:cell>
  
	</grid:body>
  
</grid:grid>

</div>
<div class="submitlist" align="center">
 <table >
 <tr><td align="center">
  <input name="submit" type="submit"  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
</form>
<table id="temp" style="display:none">
<tr>
	<td><input type="text" name="valueArray"  /> </td>
	<td>
	<img height="30" width="30" src="../shop/admin/spec/image/spec_def.gif" class="spec_pic"><span class="specImage">
	
		<a href="javascript:;" class="sysbtn upload">上传图片</a>
		<input type="hidden" name="imageArray" />
</span> </td>
	<td> <a  href="javascript:;"><img class="delete" src="${staticserver }/images/transparent.gif" ></a> </td>
</tr>
</table>

<div id="upload"></div>

<script type="text/javascript">

function uploadOk(data){
	if(data.result==1){
		Spec.uploadOk(data.path);
		
	}else{
		alert(data.message);
	}
	Eop.Dialog.close("upload");	
}
var Spec={
	imageInput:undefined,
	init:function(){
		var self= this;
		$(".input input[value=${specView.spec_type}]").attr("checked",true);
		$("#addBtn").click(function(){
			self.addRow();
		});

		$("input[value=1]").click(function(){
			$("span.specImage").parent().children().show();
		});

		$("input[value=0]").click(function(){
			$("span.specImage").parent().children().hide();
		});

		$("input[value=${specView.spec_type}]").click();
		Eop.Dialog.init({id:'upload', width:500,height:200,title:'上传规格图片'});
		this.bindEvent();
	},
	bindEvent:function(){
		var self = this;
		$(".grid table .delete").click(function(){
			self.deleteRow($(this));
		});

		$(".grid .sysbtn.upload").click(function(){
			Eop.Dialog.open("upload");
			$("#upload").load('${ctx}/eop/upload.${ext}?subFolder=spec');
			self.imageInput = $(this).siblings("input");
		});
	}
	,
	addRow:function(){
		var tr =$("#temp tr").clone();
		if($("input[value=0]").attr("checked")){ 
	 
			tr.find("span.specImage").parent().children().hide();
		}
		var body  = $(".grid table:first>tbody");
		$(".grid table:first>tbody").append(tr);
		this.bindEvent(); 
	},
	deleteRow:function(link){
		link.parent().parent().parent().remove();
	},
	uploadOk:function(path){
		this.imageInput.parent().parent().children("img.spec_pic").attr("src",path);
		this.imageInput.val(path);
	}
};
$(function(){
	 
	$("form.validate").validate();
	$("#theForm").submit(function(){
		var result =(  $("#theForm").attr("validate")=='true'  );
		if(!result){
			return false;
		}
		result = $("form [name='valueArray']").size()!=0;
		if(!result){
			alert("请添加规格值");
			$("#theForm").attr("validate","false");
			return false;
		}
		
		$("form [name='valueArray']").each(function(){
			
			if(  $.trim( $(this).val() )=='' ){
				result = false;
			}
	 
		});
		if(!result){
			alert("请填写规格值");
			$("#theForm").attr("validate","false");
			return false;
		}

		
		
		$("#theForm").attr("validate","true");
		return true;		
	});
	 
	Spec.init();
});

 
</script>

