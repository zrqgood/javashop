<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${staticserver }/js/admin/jqModal1.1.3.1.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqDnR.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<script type="text/javascript" src="js/Selector.js"></script>
<script type="text/javascript" src="js/Bind.js"></script>
<div class="input"> 

<form action="bind!addSave.do" method="post"  class="validate" >
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="form-table">
	<tr>
		<td class="label" style="width: 100px">捆绑商品名称：</td>
		<td><input type="text" required="true" name="bind.name"></td>
	</tr>
</table>
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="form-table">
	<tr>
		<td class="label" style="width: 100px">是否上架销售：</td>
		<td><label><input type="radio" checked="checked"
			value="1" name="bind.market_enable">是</label> &nbsp; &nbsp; <label><input
			type="radio" value="0" name="bind.market_enable">否</label></td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">库存：</td>
		<td><input type="text"  required="true" name="bind.store"dataType="int" ></td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">重量：</td>
		<td><input type="text" name="bind.weight" readonly="readonly"  dataType="int" id="bind_weight">克(g)</td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">原价格：</td>
		<td><input type="text" name="bind.mktprice" readonly="readonly" id="bind_mktprice"></input></td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">捆绑销售价：</td>
		<td><input type="text"   required="true" dataType="float"  name="bind.price">
		</td>
	</tr>
</table>
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%" class="form-table">
	<tbody>
		<tr>
			<td class="label" style="width: 100px">商品选择：</td>
			<td>
				<div class="grid">
					<table  cellspacing="0" cellpadding="0" class="adjunct_grid">
					<thead>
						<tr>
							<th style="text-align:left"><input type="button" value="添加" id="adj_item_addbtn" />	</th>
							<th style="text-align:left">商品名称</th>
							<th style="text-align:left">数量	</th>
						</tr>
					</thead>
					<tbody class="adjunctitem_body">
					
					</tbody>			
				</table>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	
</div>
</form>
</div>

<div id="product_item_input">
</div>

<script type="text/javascript">

$(function(){
	Bind.init();
	$("form.validate").validate();
});
</script>
