<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
<grid:grid  from="orderStatList">

	<grid:header>
	<grid:cell>支付方式</grid:cell>
	<grid:cell>订单数量</grid:cell> 
	<grid:cell >订单金额</grid:cell> 
	</grid:header>

  <grid:body item="order">
  		<grid:cell>${order.payment_name}</grid:cell>
        <grid:cell>${order.num}</grid:cell>
        <grid:cell>${order.amount}</grid:cell> 
  </grid:body>  
  
</grid:grid>  
<div style="clear:both;padding-top:5px;"></div>
</div>