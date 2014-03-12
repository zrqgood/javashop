<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>

<div class="grid">
	
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell sort="payment_id" width="250px">支付单号</grid:cell> 
	<grid:cell >支付金额</grid:cell>
	<grid:cell sort="order_id">订单号</grid:cell>
	<grid:cell >支付方式</grid:cell>
	<grid:cell sort="member_name">会员用户名</grid:cell>
	<grid:cell>操作</grid:cell> 
	</grid:header>

  <grid:body item="payment">
        <grid:cell>${payment.payment_id } </grid:cell>
        <grid:cell><fmt:formatNumber value="${payment.money }" type="currency" pattern="￥.00"/></grid:cell>
        <grid:cell>${payment.sn} </grid:cell>
        <grid:cell>${payment.pay_method} </grid:cell>
        <grid:cell>${payment.member_name} </grid:cell>
        <grid:cell><a href="orderReport!paymentDetail.do?id=${payment.payment_id }">查看详细</a></grid:cell>
  </grid:body>  
  
</grid:grid>  
</form>	
</div>


