<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="division">
<div class="grid">
<grid:grid  from="listOrder" >
	<grid:header>
		<grid:cell>订单号</grid:cell>
		<grid:cell>订单状态</grid:cell>
		<grid:cell>支付状态</grid:cell>
		<grid:cell>发货状态</grid:cell>
		<grid:cell>总金额</grid:cell>
		<grid:cell>下单日期</grid:cell>
	</grid:header>
	
	<grid:body item="order">
		<grid:cell><a href="javascript:;" order_id="${order.order_id }">${order.sn }</a></grid:cell>
		<grid:cell>${order.orderStatus}</grid:cell>
		<grid:cell>${order.payStatus}</grid:cell>
		<grid:cell>${order.shipStatus }</grid:cell>
		<grid:cell><fmt:formatNumber value="${order.order_amount }" type="currency" /></grid:cell>
		<grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${order.create_time }" /></grid:cell>
	</grid:body>

</grid:grid>
</div>
</div>