<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="../shop/admin/js/Order.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="revertBtn">还原</a></li>
			<li><a href="javascript:;" id="cleanBtn">清除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
		<grid:cell sort="sn" width="150px">订单号</grid:cell> 
		<grid:cell width="150px">下单日期</grid:cell> 
		<grid:cell  width="100px">订单总额</grid:cell>
		<grid:cell >收货人</grid:cell> 
		<grid:cell >付款状态</grid:cell> 
		<grid:cell >发货状态</grid:cell>
		<grid:cell >配送方式</grid:cell>
		<grid:cell >支付方式</grid:cell>
		
	</grid:header>

  <grid:body item="order">
  		<grid:cell><input type="checkbox" name="id" value="${order.order_id }" /></grid:cell>
        <grid:cell><a href="order!detail.do?orderId=${order.order_id }"> ${order.sn } </a></grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${order.create_time}"></html:dateformat> </grid:cell> 
        <grid:cell><fmt:formatNumber value="${order.order_amount}" type="currency" pattern="￥.00"/></grid:cell> 
        <grid:cell>${order.ship_name}</grid:cell> 
        <grid:cell>${order.payStatus}</grid:cell> 
        <grid:cell>${order.shipStatus}</grid:cell> 
        <grid:cell>${order.shipping_type}</grid:cell> 
        <grid:cell>${order.payment_name}</grid:cell> 
        
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>

