<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/commons/taglibs.jsp"%>
    <div class="tableform">
<table cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<td style="vertical-align: top;">
			<div class="tableform">
			<h4>收款单据列表</h4>
			<div class="division">
			<table cellspacing="0" cellpadding="0" border="0" width="100%"
				class="gridlist">
				<col class="Coldate">
				<col class="Colamount">
				<col>
				<col>
				<thead>
					<tr>
						<th>单据日期</th>
						<th>支付金额</th>
						<th>支付方式</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${payLogList }" var="payment">
					<tr>
						<td><html:dateformat pattern="yy-MM-dd hh:mm:ss" time="${payment.create_time}" /></td>
						<td><fmt:formatNumber value="${payment.money }" type="currency" pattern="￥.00"/></td>
						<td>
							<c:if test="${payment.pay_type == 'online' }">在线支付</c:if>
							<c:if test="${payment.pay_type == 'offline' }">线下支付</c:if>
							<c:if test="${payment.pay_type == 'deposit' }">预存款支付</c:if>
						</td>
						<td>
							<c:if test="${payment.status == 1 }">成功</c:if>
							<c:if test="${payment.status == 2 }">进行中</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
			</td>
			<td style="vertical-align: top;">
			<div class="tableform">
			<h4>退款单据列表</h4>
			<div class="division">
			<table cellspacing="0" cellpadding="0" border="0" width="100%"
				class="gridlist">
				<col class="Coldate">
				<col class="Colamount">
				<thead>
					<tr>
						<th>单据日期</th>
						<th>退款金额</th>
						<th>退款方式</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${refundList }" var="payment">
					<tr>
						<td><html:dateformat pattern="yy-MM-dd hh:mm:ss" time="${payment.create_time}" /></td>
						<td><fmt:formatNumber value="${payment.money }" type="currency" pattern="￥.00"/></td>
						<td>
							<c:if test="${payment.pay_type == 'online' }">在线支付</c:if>
							<c:if test="${payment.pay_type == 'offline' }">线下支付</c:if>
							<c:if test="${payment.pay_type == 'deposit' }">预存款支付</c:if>
						</td>
						<td>
							<c:if test="${payment.status == 1 }">成功</c:if>
							<c:if test="${payment.status == 2 }">进行中</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>