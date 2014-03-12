<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
 
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px">id</grid:cell>
	<grid:cell >订单号</grid:cell> 
	<grid:cell >金额</grid:cell> 
	<grid:cell >抬头</grid:cell>
	<grid:cell >内容</grid:cell>
	<grid:cell >状态</grid:cell>
	<grid:cell >申请时间</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="invoice">
  		<grid:cell>${invoice.id}</grid:cell>
        <grid:cell>${invoice.ordersn} </grid:cell>
        <grid:cell>${invoice.amount}</grid:cell> 
        <grid:cell>${invoice.title}</grid:cell> 
        <grid:cell>${invoice.content}</grid:cell> 
        <grid:cell>${invoice.stateStr}</grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${invoice.add_time*1000}"></html:dateformat></grid:cell> 
         <grid:cell>
         
         <a href="invoice!delete.do?id=${invoice.id}" >删除</a>&nbsp;&nbsp;
         
         <c:if test="${invoice.state==0}">
         	<a href="javascript:;" invoiceid="${invoice.id}" class="agree" >同意</a>
         	&nbsp;&nbsp;
         	<a href="javascript:;" invoiceid="${invoice.id}"  class="refuse" >拒绝</a>
		</c:if>
         
         </grid:cell>
   </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>


<div id="refusedlg"  >
<form  id="refuseform"  method="post">
<input type="hidden"  name="id" id="id"/>
 <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
	 <th>
	 	<label class="text">拒绝理由：</label>
	 </th>
	 <td><textarea style="width:300px;height:200px" id="refuse_reson"  name="reson"></textarea>
	 </td>
	 </tr>
	<tr><td align="center"><input type="button" value="确定" id="okBtn" /></td></tr>
	
</table>
</form>
</div>

<script type="text/javascript">
<!--
var Invoice={
	init:function(){
		var self  = this;
		Eop.Dialog.init({id:"refusedlg",modal:false,title:"索取发票",width:"450px"});

		//拒绝 
		$(".refuse").click(function(){
			$("#id").val($(this).attr("invoiceid")   );
			$("#refuse_reson").val('');
			Eop.Dialog.open("refusedlg");
			$("#okBtn").unbind("click").bind('click',function(){
				self.refuse();
			});
		});

		//同意
		$(".agree").click(function(){
		 	self.agree($(this).attr("invoiceid"));
		});

		
	}	,
	refuse:function(){
		var self= this;
		var options = {
				url:'invoice!refuse.do?ajax=yes',
				type:"post",
				dataType:"json",
				success: function(responseText) { 
					if(responseText.result==1){
						
						Eop.Dialog.close("refusedlg");
						alert("操作成功");
						location.reload();
					 
					}
					if(responseText.result==0){
						alert(responseText.message);
					}						
				},
				error:function(){
					alert("出错了:(");
				}
		};
		$('#refuseform').ajaxSubmit(options); 
	},
	agree:function(id){

		if(!confirm("确认同意此发票申请吗?") ){ return}
		
		$.ajax({
			url:'invoice!agree.do?ajax=yes&id='+id,
			type:"post",
			dataType:"json",
			success: function(responseText) { 
				if(responseText.result==1){
					
					alert("操作成功");
					location.reload();
				}
				if(responseText.result==0){
					alert(responseText.message);
				}						
			},
			error:function(){
				alert("出错了:(");
			}
		});
	}
};

$(function(){
	Invoice.init();
});

//-->
</script>

