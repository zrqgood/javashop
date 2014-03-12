<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<script type="text/javascript"  >//var OrderStatus={};OrderStatus.ORDER_CANCEL_SHIP=-2;OrderStatus.ORDER_CANCEL_PAY=-1;OrderStatus.ORDER_NOT_PAY=0;OrderStatus.ORDER_PAY=1;OrderStatus.ORDER_SHIP=2;OrderStatus.ORDER_COMPLETE=3;OrderStatus.ORDER_CANCELLATION=4;OrderStatus.PAY_NO=0;OrderStatus.PAY_YES=1;OrderStatus.PAY_CANCEL=2;OrderStatus.PAY_PARTIAL_REFUND=3;OrderStatus.PAY_PARTIAL_PAYED=4;OrderStatus.SHIP_NO=0;OrderStatus.SHIP_YES=1;OrderStatus.SHIP_CANCEL=2;OrderStatus.SHIP_PARTIAL_SHIPED=4;OrderStatus.SHIP_PARTIAL_CANCEL=3; var OrderDetail={orderid:undefined,orderStatus:undefined,payStatus:undefined,shipStatus:undefined,init:function(a,c,b,d){this.orderStatus=c;this.payStatus=b;this.shipStatus=d;Eop.Dialog.init({id:"order_dialog",modal:false,title:"\u8ba2\u5355\u64cd\u4f5c",width:"750px"});this.orderid=a;new Tab(".order_detail");this.bindFlowEvent();this.bindTabEvent();this.showBase()},bindTabEvent:function(){var a=this;$("#base").click(function(){a.showBase()});$("#items").click(function(){a.showItems()});$("#payLog").click(function(){a.showPayLog()}); $("#shipLog").click(function(){a.showShipLog()});$("#pmt").click(function(){a.showPmt()});$("#log").click(function(){a.showLog()});$("#remark").click(function(){a.showRemark()})},bindFlowEvent:function(){var a=this;$("#pay").unbind("click");$("#pay").bind("click",function(){Eop.Dialog.open("order_dialog");$("#order_dialog .con").load(basePath+"payment!showPayDialog.do?ajax=yes&orderId="+a.orderid,function(){$("#order_dialog .submitBtn").unbind("click");$("#order_dialog .submitBtn").bind("click",function(){a.pay()})})}); $("#refund").unbind("click");$("#refund").bind("click",function(){Eop.Dialog.open("order_dialog");$("#order_dialog .con").load(basePath+"payment!showRefundDialog.do?ajax=yes&orderId="+a.orderid,function(){$("#order_dialog .submitBtn").unbind("click");$("#order_dialog .submitBtn").bind("click",function(){a.refund()})})});$("#shipping").unbind("click");$("#shipping").bind("click",function(){Eop.Dialog.open("order_dialog");$("#order_dialog .con").load(basePath+"ship!showShipDialog.do?ajax=yes&orderId="+ a.orderid,function(){initCity();$("#order_dialog .submitBtn").unbind("click");$("#order_dialog .submitBtn").bind("click",function(){a.ship()})})});$("#returned").unbind("click");$("#returned").bind("click",function(){Eop.Dialog.open("order_dialog");$("#order_dialog .con").load(basePath+"ship!showReturnDialog.do?ajax=yes&orderId="+a.orderid,function(){initCity();$("#order_dialog .submitBtn").unbind("click");$("#order_dialog .submitBtn").bind("click",function(){a.returned()})})});$("#complete").unbind("click"); $("#complete").bind("click",function(){confirm("\u5b8c\u6210 \u64cd\u4f5c\u4f1a\u4f7f\u8be5\u8ba2\u5355\u5f52\u6863\u4e14\u4e0d\u5141\u8bb8\u518d\u505a\u4efb\u4f55\u64cd\u4f5c\uff0c\u786e\u5b9a\u8981\u6267\u884c\u5417\uff1f")&&a.complete(a.orderid)});$("#cancel").unbind("click");$("#cancel").bind("click",function(){confirm("\u4f5c\u5e9f\u64cd\u4f5c\u4f1a\u4f7f\u8be5\u8ba2\u5355\u5f52\u6863\u4e14\u4e0d\u5141\u8bb8\u518d\u505a\u4efb\u4f55\u64cd\u4f5c\uff0c\u786e\u5b9a\u8981\u6267\u884c\u5417\uff1f")&& a.cancel(a.orderid)});this.initBtnStatus()},initBtnStatus:function(){this.payStatus==OrderStatus.PAY_NO||this.payStatus==OrderStatus.PAY_PARTIAL_PAYED?$("#pay").attr("disabled",false):$("#pay").attr("disabled",true);this.payStatus==OrderStatus.PAY_YES||this.payStatus==OrderStatus.PAY_PARTIAL_REFUND||this.payStatus==OrderStatus.PAY_PARTIAL_PAYED?$("#refund").attr("disabled",false):$("#refund").attr("disabled",true);this.shipStatus==OrderStatus.SHIP_NO||this.shipStatus==OrderStatus.SHIP_PARTIAL_SHIPED? $("#shipping").attr("disabled",false):$("#shipping").attr("disabled",true);this.shipStatus==OrderStatus.SHIP_YES||this.shipStatus==OrderStatus.SHIP_PARTIAL_SHIPED||this.shipStatus==OrderStatus.SHIP_PARTIAL_CANCEL?$("#returned").attr("disabled",false):$("#returned").attr("disabled",true);if(this.orderStatus==OrderStatus.ORDER_COMPLETE||this.orderStatus==OrderStatus.ORDER_CANCELLATION)$(".toolbar input").attr("disabled",true);else{$("#cancel").attr("disabled",false);$("#complete").attr("disabled",false)}}, pay:function(){var a=this,c={url:basePath+"payment!pay.do?ajax=yes",type:"post",dataType:"json",success:function(b){if(b.result==1){alert(b.message);Eop.Dialog.close("order_dialog");a.payStatus=b.payStatus;a.bindFlowEvent()}b.result==0&&alert(b.message)},error:function(){alert("\u51fa\u9519\u4e86:(")}};$("#order_form").ajaxSubmit(c)},refund:function(){var a=this,c={url:basePath+"payment!cancel_pay.do?ajax=yes",type:"post",dataType:"json",success:function(b){if(b.result==1){alert(b.message);Eop.Dialog.close("order_dialog"); a.payStatus=b.payStatus;a.bindFlowEvent()}b.result==0&&alert(b.message)},error:function(){alert("\u51fa\u9519\u4e86:(")}};$("#order_form").ajaxSubmit(c)},ship:function(){var a=this,c={url:basePath+"ship!ship.do?ajax=yes",type:"post",dataType:"json",success:function(b){if(b.result==1){alert(b.message);Eop.Dialog.close("order_dialog");a.shipStatus=b.shipStatus;a.bindFlowEvent()}b.result==0&&alert(b.message)},error:function(){alert("\u51fa\u9519\u4e86:(")}};$("#order_form").ajaxSubmit(c)},returned:function(){var a= this,c={url:basePath+"ship!returned.do?ajax=yes",type:"post",dataType:"json",success:function(b){if(b.result==1){alert(b.message);Eop.Dialog.close("order_dialog");a.shipStatus=b.shipStatus;a.bindFlowEvent()}b.result==0&&alert(b.message)},error:function(){alert("\u51fa\u9519\u4e86:(")}};$("#order_form").ajaxSubmit(c)},complete:function(a){var c=this;$.ajax({url:basePath+"order!complete.do?ajax=yes&orderId="+a,dataType:"json",success:function(b){if(b.result==1){alert(b.message);c.orderStatus=b.orderStatus; c.bindFlowEvent()}b.result==0&&alert(b.message)},error:function(){alert("\u51fa\u9519\u4e86:(")}})},cancel:function(a){var c=this;$.ajax({url:basePath+"order!cancel.do?ajax=yes&orderId="+a,dataType:"json",success:function(b){if(b.result==1){alert(b.message);c.orderStatus=b.orderStatus;c.bindFlowEvent()}b.result==0&&alert(b.message)},error:function(){alert("\u51fa\u9519\u4e86:(")}})},saveRemark:function(a){var c=this;$("#remark_form").ajaxSubmit({url:basePath+"order!saveRemark.do?ajax=yes",dataType:"json", type:"POST",success:function(b){b.result==1&&alert(b.message);if(b.result==0){alert(b.message);c.bindFlowEvent();c.showRemark(a)}},error:function(){alert("\u51fa\u9519\u4e86:(")}})},showBase:function(){var a=this;$("#baseTab").load(basePath+"order!base.do?ajax=yes&orderId="+this.orderid+"&rmd="+(new Date).getTime(),function(){$("#editPriceBtn").click(function(){a.showpriceinput($(this))})})},showpriceinput:function(a){var c=this,b=$("#orderid").val(),d=$("#order_price_span"),f=d.html().replace("\uffe5", ""),e=$("<input type='text' style='width:60px' value='"+f+"' id='price_input' name='price' />");d.empty();d.append(e);a.html("\u786e\u5b9a");a.unbind("click");a.bind("click",function(){c.savePrice(e.val(),b)})},savePrice:function(a,c){var b=this;$.ajax({url:basePath+"order!savePrice.do?ajax=yes",data:"price="+a+"&orderId="+c,type:"POST",dataType:"json",success:function(d){if(d.result==1){$("#order_price_span").html("\uffe5"+a);$("#editPriceBtn").html("\u4fee\u6539");$("#editPriceBtn").unbind("click"); $("#editPriceBtn").bind("click",function(){b.showpriceinput($(this))})}else alert("\u4fdd\u5b58\u8ba2\u5355\u4ef7\u683c\u51fa\u9519")},error:function(d){alert("\u4fdd\u5b58\u8ba2\u5355\u4ef7\u683c\u51fa\u9519"+d)}})},showItems:function(){$("#itemsTab").load(basePath+"order!items.do?ajax=yes&orderId="+this.orderid)},showPayLog:function(){$("#payLogTab").load(basePath+"order!payLog.do?ajax=yes&orderId="+this.orderid)},showShipLog:function(){$("#shipLogTab").load(basePath+"order!shipLog.do?ajax=yes&orderId="+ this.orderid)},showPmt:function(){$("#pmtTab").load(basePath+"order!pmt.do?ajax=yes&orderId="+this.orderid)},showLog:function(){$("#logTab").load(basePath+"order!log.do?ajax=yes&orderId="+this.orderid)},showRemark:function(){$("#remarkTab").load(basePath+"order!remark.do?ajax=yes&orderId="+this.orderid);$("#save_remark").unbind("click");$("#save_remark").bind("click",function(){this.saveRemark(this.orderid)})}};</script>
<script type="text/javascript" src="../shop/admin/js/OrderDetail.js">
</script>
<style>
<!--
.division {
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
line-height:150%;
margin:10px;
padding:5px;
white-space:normal;
}
.division table{margin:0;padding:0} 
.orderdetails_basic th {
color:#336699;
text-align:left;
white-space:nowrap;
}

.division th {
background:none repeat scroll 0 0 #E2E8EB;
border-right:1px solid #CCCCCC;
font-size:14px;
text-align:right;
white-space:nowrap;
width:140px;
}
.division th, .division td {
border-color:#FFFFFF #DBE2E7 #DDDDDD #FFFFFF;
border-left:1px solid #FFFFFF;
border-right:1px solid #DBE2E7;
border-style:solid;
border-width:1px;
padding:5px;
vertical-align:top;
}

.tableform {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#DDDDDD #BEC6CE #BEC6CE #DDDDDD;
border-style:solid;
border-width:1px;
margin:10px;
padding:5px;
}

h5  {
font-size:1em;
font-weight:bold;
}
h1, h2, h3, h4, h5, h6 {
clear:both;
color:#111111;
margin:0.5em 0;
}

#order_dialog .con{
		background:none repeat scroll 0 0 #FFFFFF;
		overflow-x:hidden;
		overflow-y:auto;
		height: 400px;   visibility: visible; opacity: 1; position: relative;
}
 
-->
</style>
	<div class="toolbar">
		 <input type="button" id="pay" value="支付" />
		 <input type="button" id="refund" value="退款" />
		 <input type="button" id="shipping" value="发货" />
		 
		 <input type="button" id="returned" value="退货" />
		 <input type="button" id="changed" value="换货" />
		 
		 
		 <input type="button" id="complete" value="完成" />
		 <input type="button" id="cancel" value="作废" />
		 &nbsp;&nbsp;<span class="help_icon" helpid="order_opration"></span>
		<div style="clear:both"></div>
	</div>
	
<div style="display: block;" class="order_detail">
	<div class="tab-bar" style="position: relative;">
	<ul class="tab">
		<li id="base" class="active">基本信息</li>
		<li id="items">商品</li> 
		<li id="payLog">收退款记录</li>
		<li id="shipLog">发退货记录</li>
		<li id="pmt">优惠方案</li>
		<li id="log">订单日志</li>
		<li id="remark">订单附言</li>
	</ul>
	</div>
	
	<div class="tab-page">
		<div id="baseTab"></div>
		<div id="itemsTab"></div>
		<div id="payLogTab"></div>
		<div id="shipLogTab"></div>
		<div id="pmtTab"></div>
		<div id="logTab"></div>
		<div id="remarkTab"></div>
	</div>
</div>
 

<div id="order_dialog">
<form id="order_form">
<input type="hidden" id="orderid" name="orderId" value="${orderId }" />
<div class="con">

</div>
</form>
<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn">提交</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>

 
<script>

$(function(){
	OrderDetail.init(${orderId},${ord.status},${ord.pay_status},${ord.ship_status});
});

function initCity(){
	$("#address_city_id").hide();
	$("#address_region_id").hide();
	$("#address_province_id").empty();
	$("<option value='-1'>请选择...</option>").appendTo($("#address_province_id"));
	
	<c:forEach items="${provinceList}" var="province" >
		$("<option value='${(province.region_id)}' >${province.local_name}</option>").appendTo($("#address_province_id"));
	</c:forEach>
	
	$("#address_province_id").change(function(){
		$("#address_province").val($("#address_province_id option:selected").text());
		$("#address_city_id").empty();
		$("#address_city_id").hide();
		$("#address_region_id").empty();
		$("#address_region_id").hide();
		$.ajax({
			method:"get",
			url:"../shop/area!list_city.do?province_id=" + $("#address_province_id").attr("value"),
			dataType:"html",
			success:function(result){
				$("#address_city_id").show();
				$(result).appendTo($("#address_city_id"));
			},
			error:function(){
				alert("异步失败");
			}
		});
	});
	$("#address_city_id").change(function(){
		$("#address_city").val($("#address_city_id option:selected").text());
		$("#address_region_id").empty();
		$("#address_region_id").hide();
		$.ajax({
			method:"get",
			url:"../shop/area!list_region.do?city_id=" + $("#address_city_id").attr("value"),
			dataType:"html",
			success:function(result){
				$("#address_region_id").show();
				$(result).appendTo($("#address_region_id"));
			},
			error:function(){
				alert("异步失败");
			}
		});
	});
	$("#address_region_id").change(function(){
		$("#address_region").val($("#address_region_id option:selected").text());
	});
}
</script>