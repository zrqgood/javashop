<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>

<div class="grid">
	
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell sort="delivery_id" width="250px">发货单号</grid:cell> 
	<grid:cell >物流费用</grid:cell>
	<grid:cell sort="order_id">订单号</grid:cell>
	<grid:cell >收货人</grid:cell>
	<grid:cell >是否保价</grid:cell>
	<grid:cell sort="member_name">会员用户名</grid:cell>
	<grid:cell>操作</grid:cell> 
	</grid:header>

  <grid:body item="delivery">
        <grid:cell>${delivery.delivery_id } </grid:cell>
        <grid:cell><fmt:formatNumber value="${delivery.money }" type="currency" pattern="￥.00"/> </grid:cell>
        <grid:cell>${delivery.sn} </grid:cell>
        <grid:cell>${delivery.ship_name} </grid:cell>
        <grid:cell><c:if test="${delivery.is_protect == 1}">是</c:if><c:if test="${delivery.is_protect == 0}">否</c:if> </grid:cell>
        <grid:cell>${delivery.member_name} </grid:cell>
        <grid:cell><a href="orderReport!shippingDetail.do?id=${delivery.delivery_id }">查看详细</a></grid:cell>
  </grid:body>  
  
</grid:grid>  
</form>	
</div>


