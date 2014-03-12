<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
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
-->
</style>
<div class="infoPanel">
<div class="infoContent" container="true"
	style="visibility: visible; opacity: 1;">
<div class="input">
<table class="form-table">
	<tr>
		<th>发货单号：</th><td>${delivery.delivery_id }</td>
		<th>订单号:</th><td>${delivery.sn }</td>
		<th>配送方式:</th><td>${delivery.ship_type }</td>
	</tr>
	<tr>
		<th>操作员:</th><td>${delivery.op_name }</td>
		<th>会员:</th><td>${delivery.member_name }</td>
		<th>收货人:</th><td>${delivery.ship_name }</td>
	</tr>
	<tr>
		<th>收货地区:</th><td>${delivery.province }-${delivery.city }-${delivery.region }</td>
		<th>收货地址:</th><td>${delivery.ship_addr }</td>
		<th>邮编:</th><td>${delivery.ship_zip }</td>
	</tr>
	<tr>
		<th>电话:</th><td>${delivery.ship_tel }</td>
		<th>手机:</th><td>${delivery.ship_mobile }</td>
		<th>物流公司:</th><td>${delivery.logi_name }</td>
	</tr>
	<tr>
		<th>物流费用:</th><td><fmt:formatNumber value="${delivery.money }" type="currency" pattern="￥.00"/></td>
		<th>物流单号:</th><td>${delivery.logi_no }</td>
		<th>生成时间:</th><td><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${delivery.create_time}"></html:dateformat></td>
	</tr>
</table>
<hr class="space">
<h4>发货明细:</h4>
<div class="division">
<div class="grid">
<grid:grid  from="listDeliveryItem">
	<grid:header>
		<grid:cell>货号</grid:cell>
		<grid:cell>商品名称</grid:cell>
		<grid:cell>发货量</grid:cell>
	</grid:header>
	<grid:body item="item">
		<grid:cell>${item.sn }</grid:cell>
		<grid:cell>${item.name }</grid:cell>
		<grid:cell>${item.num }</grid:cell>
	</grid:body>
</grid:grid>
</div>
</div>
<table cellspacing="0" cellpadding="0" border="0" style="margin: 0pt;"
	class="tableAction">
	<tbody>
		<tr>
			<td>备注：${delivery.remark }</td>
		</tr>
	</tbody>
</table>

</div>
</div>
</div>