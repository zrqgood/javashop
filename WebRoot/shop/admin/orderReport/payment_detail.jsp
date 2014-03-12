<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<div class="infoPanel" style="">
<div class="infoContent" container="true"
	style="visibility: visible; opacity: 1;">
<div class="input">
<table class="form-table">
	<tr>
		<th>序号：</th>
		<td>${payment.payment_id }</td>
	</tr>
	<tr>
		<th>订单号：</th>
		<td>${payment.order_id }</td>
	</tr>
	<tr>
		<th>支付方式：</th>
		<td>${payment.pay_method}</td>
	</tr>
	<tr>
		<th>生成时间：</th>
		<td><html:dateformat pattern="yyyy-MM-dd" time="${payment.create_time }"></html:dateformat></td>
	</tr>
	<tr>
		<th>收款方式：</th>
		<td><c:if test="${payment.pay_type=='online' }">在线支付</c:if><c:if test="${payment.pay_type=='offline' }">线下支付</c:if><c:if test="${payment.pay_type=='deposit' }">预存款支付</c:if><c:if test="${payment.pay_type=='recharge' }">预存款充值</c:if></td>
	</tr>
	<tr>
		<th>收款帐号：</th>
		<td>${payment.account }</td>
	</tr>
	<tr>
		<th>收款银行：</th>
		<td>${payment.bank }</td>
	</tr>
	<tr>
		<th>付款人：</th>
		<td>${payment.pay_user }</td>
	</tr>
	<tr>
		<th>付款金额：</th>
		<td><fmt:formatNumber value="${payment.money }" type="currency" pattern="￥.00"/></td>
	</tr>
</table>

<table cellspacing="0" cellpadding="0" border="0" style="margin: 0pt;"
	class="tableAction">
	<tbody>
		<tr>
			<td>备注：${payment.remark }</td>
		</tr>
	</tbody>
</table>

</div>
</div>
</div>