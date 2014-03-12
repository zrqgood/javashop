<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/commons/taglibs.jsp"%>
<style>
<!--
.division th{font-weight:normal;}
-->
</style>    
<div class="tableform">
<table cellspacing="10" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<td style="vertical-align: top;">
			<h4>发货单据列表</h4>
			<div class="division">
			<table cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<col class="Coldate">
				<thead>
					<tr>
						<th>建立日期</th>
						<th>发货单号</th>
						<th>物流单号</th>
						<th>收件人</th>
						<th>配送方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shipLogList }" var="ship">
					<tr>
						<th><html:dateformat pattern="yy-MM-dd hh:mm:ss" time="${ship.create_time}" /></th>
						<th>${ship.delivery_id }</th>
						<th>${ship.logi_no }</th>
						<th>${ship.ship_name }</th>
						<th>${ship.ship_type }</th>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</td>
			<td style="vertical-align: top;">
			<h4>退货单据列表</h4>
			<div class="division">
			<table cellspacing="0" cellpadding="0" border="0" width="100%"
				class="gridlist">
				<col class="Coldate">
				<thead>
					<tr>
						<th>建立日期</th>
						<th>退货单号</th>
						<th>物流单号</th>
						<th>退货人</th>
						<th>配送方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reshipLogList }" var="ship">
					<tr>
						<th><html:dateformat pattern="yy-MM-dd hh:mm:ss" time="${ship.create_time}" /></th>
						<th>${ship.delivery_id }</th>
						<th>${ship.logi_no }</th>
						<th>${ship.ship_name }</th>
						<th>${ship.ship_type }</th>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</td>
		</tr>


		<tr>
			<td style="vertical-align: top;">
			<h4>换货单据列表</h4>
			<div class="division">
			<table cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<col class="Coldate">
				<thead>
					<tr>
						<th>建立日期</th>
						<th>换货单号</th>
						<th>物流单号</th>
						<th>收件人</th>
						<th>配送方式</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${chshipLogList }" var="ship">
					<tr>
						<th><html:dateformat pattern="yy-MM-dd hh:mm:ss" time="${ship.create_time}" /></th>
						<th>${ship.delivery_id }</th>
						<th>${ship.logi_no }</th>
						<th>${ship.ship_name }</th>
						<th>${ship.ship_type }</th>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</td>
			<td style="vertical-align: top;">

			</td>
		</tr>
		
				
	</tbody>
</table>
</div>