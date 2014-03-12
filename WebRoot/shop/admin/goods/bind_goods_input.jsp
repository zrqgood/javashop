<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input">

<form method="post" name="theForm" action="goods!saveBindAdd.do" id="theForm" class="validate">

<div style="display: block;" class="goods_input">
<table class="form-table">
	<tr>
		<th>商品名称：</th>
		<td><input type="hidden" name="goods.goods_type" value="bind"/><input type="text" name="goods.name" id="goods_name"
			class="input_text" dataType="string" required="true"
			 /></td>
	</tr>
	<tr>
		<th>货号：</th>
		<td><input type="text" name="goods.sn" class="input_text" dataType="string" required="true"
			 /></td>
	</tr>
	<tr>
		<th>是否上架销售：</th>
		<td><input type="radio" name="goods.market_enable" value="1"
			checked /> 是&nbsp;&nbsp; <input type="radio"
			name="goods.market_enable" value="0" /> 否</td>
	</tr>


	<tr>
		<th>市场价格：</th>
		<td><input type="text" name="goods.mktprice" id="mktprice"
			class="input_text" dataType="float"/>
		</td>
	</tr>


</table>
</form>
<script type="text/javascript">
$("form.validate").validate();
</script>
</div>