<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>

<div class="grid">

	<div class="toolbar">
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell  width="150px">订单号&nbsp;&nbsp;<span class="help_icon" helpid="order_showdetail"></span></grid:cell>
		<grid:cell >申请日期</grid:cell> 
		<grid:cell  width="70px">类型</grid:cell> 
		<grid:cell   >状态</grid:cell> 
		<grid:cell  width="70px">联系人</grid:cell>
		<grid:cell >联系电话</grid:cell> 
		<grid:cell>操作</grid:cell> 
	</grid:header>

  <grid:body item="order">
        <grid:cell>${order.ordersn}</grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${order.add_time*1000}"></html:dateformat></grid:cell> 
         <grid:cell>
         
         <c:if test="${order.type==0}"> 退货</c:if>
         <c:if test="${order.type==1}"> 换货</c:if>
         <c:if test="${order.type==2}"> 返修</c:if>
         
         </grid:cell>
        <grid:cell>${order.stateStr}</grid:cell>
        <grid:cell> ${order.linkman } </grid:cell> 
        <grid:cell>${order.linktel}</grid:cell> 
        <grid:cell>
        
         <a href="order!returnDetail.do?orderId=${order.id }">详细 </a>&nbsp;&nbsp;
        
        <c:if test="${order.state==0}"> 
	         <a href="javascript:;" orderid="${order.id }" class="refuse">拒绝 </a>&nbsp;&nbsp;
	         <a href="javascript:;" orderid="${order.id }" class="agree">同意 </a>&nbsp;&nbsp;

         </c:if>
         <c:if test="${order.state==1}"> 
	           
	         <c:if test="${order.type==1}">
	         	<a href="order!detail.do?orderId=${order.orderid }" >退货</a>
	         </c:if>
	         <c:if test="${order.type==2 || order.type==3}">
	         	<a href="order!detail.do?orderId=${order.orderid }" >换货</a>
	         </c:if>
         </c:if>
         </grid:cell> 
 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>

<div id="refusedlg"  >
<form  id="refuseform"  method="post">
<input type="hidden"  name="orderId" id="orderid"/>
 <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
	 <th>
	 	<label class="text">拒绝理由：</label>
	 </th>
	 <td><textarea style="width:300px;height:200px" id="refuse_reson"  name="refuse_reson"></textarea>
	 </td>
	 </tr>
	<tr><td align="center"><input type="button" value="确定" id="okBtn" /></td></tr>
	
</table>
</form>
</div>

<script type="text/javascript">
<!--
var OrderReturns={
	init:function(){
		var self  = this;
		Eop.Dialog.init({id:"refusedlg",modal:false,title:"订单操作",width:"450px"});

		//拒绝 
		$(".refuse").click(function(){
			$("#orderid").val($(this).attr("orderid")  );
			$("#refuse_reson").val('');
			Eop.Dialog.open("refusedlg");
			$("#okBtn").unbind("click").bind('click',function(){
				self.refuse();
			});
		});

		//同意
		$(".agree").click(function(){
		 	self.agree($(this).attr("orderid"));
		});

		
	}	,
	refuse:function(){
		var self= this;
		var options = {
				url:'order!refuse.do?ajax=yes',
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

		if(!confirm("确认同意此订单的退换货申请吗?") ){ return}
		
		$.ajax({
			url:'order!agree.do?ajax=yes&orderId='+id,
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
	OrderReturns.init();
});

//-->
</script>
