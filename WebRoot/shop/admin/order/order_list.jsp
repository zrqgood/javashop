<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" href="css/admin.zcss" type="text/css" media="screen, projection">
<script type="text/javascript" src="../shop/admin/js/Order.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="order!trash_list.do">回收站</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell width="30px"><input type="checkbox" id="toggleChk" /></grid:cell>
		<grid:cell sort="sn" width="110px">订单号&nbsp;&nbsp;<span class="help_icon" helpid="order_showdetail"></span></grid:cell>
		<grid:cell width="60px">下单日期</grid:cell> 
		<grid:cell sort="status" width="70px">订单状态</grid:cell> 
		<grid:cell  width="70px">订单总额</grid:cell>
		<grid:cell >收货人</grid:cell> 
		<grid:cell >付款状态</grid:cell> 
		<grid:cell >发货状态</grid:cell>
		<grid:cell >配送方式</grid:cell>
		<grid:cell >支付方式</grid:cell>
		<grid:cell>操作</grid:cell> 
		<grid:cell>打印</grid:cell>
	</grid:header>

  <grid:body item="order">
  		<grid:cell><input type="checkbox" name="id" value="${order.order_id }" /></grid:cell>
        <grid:cell><c:if test="${order.status<3}"><B></c:if><a href="order!detail.do?orderId=${order.order_id }"> ${order.sn } </a><c:if test="${order.status<3}"></B></c:if></grid:cell>
        <grid:cell><c:if test="${order.status<3}"><B></c:if><html:dateformat pattern="yyyy-MM-dd" time="${order.create_time}"></html:dateformat> <c:if test="${order.status<3}"></B></c:if></grid:cell> 
        <grid:cell><c:if test="${order.status<3}"><B></c:if>${order.orderStatus}<c:if test="${order.status<3}"></B></c:if></grid:cell>
        <grid:cell><c:if test="${order.status<3}"><B></c:if><fmt:formatNumber value="${order.order_amount}" type="currency" pattern="￥.00"/><c:if test="${order.status<3}"></B></c:if></grid:cell> 
        <grid:cell><c:if test="${order.status<3}"><B></c:if>${order.ship_name}<c:if test="${order.status<3}"></B></c:if></grid:cell> 
        <grid:cell><c:if test="${order.status<3}"><B></c:if>${order.payStatus}<c:if test="${order.status<3}"></B></c:if></grid:cell> 
        <grid:cell><c:if test="${order.status<3}"><B></c:if>${order.shipStatus}<c:if test="${order.status<3}"></B></c:if></grid:cell> 
        <grid:cell><c:if test="${order.status<3}"><B></c:if>${order.shipping_type}<c:if test="${order.status<3}"></B></c:if></grid:cell> 
        <grid:cell><c:if test="${order.status<3}"><B></c:if>${order.payment_name}<c:if test="${order.status<3}"></B></c:if></grid:cell>
        <grid:cell><a href="order!detail.do?orderId=${order.order_id }" title="订单详细"><img class="modify" src="images/transparent.gif" ></a></grid:cell> 
        <grid:cell>
        	<button title="购物清单打印" class="p_prted" id="orderprntBtn" onclick="javascript:window.open('../shop/admin/orderPrint!order_prnt.do?orderId=${order.order_id }'); return false;" >购</button>
        	<button title="配货单打印" class="p_prted" id="deliveryprntBtn" onclick="javascript:window.open('../shop/admin/orderPrint!delivery_prnt.do?orderId=${order.order_id }');return false;" >配</button>
        	<button title="联合打印" class="p_prted" id="globalprntBtn" onclick="javascript:window.open('../shop/admin/orderPrint!global_prnt.do?orderId=${order.order_id }');return false;" >合</button>
        	<button title="快递单打印" class="p_prted" id="shipprntBtn" onclick="javascript:window.open('../shop/admin/orderPrint!ship_prnt_step1.do?orderId=${order.order_id }');return false;" >递</button>
        </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>

