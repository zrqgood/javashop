<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${staticserver }/js/admin/jqModal1.1.3.1.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqDnR.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<script type="text/javascript" src="js/Selector.js"></script>
<script type="text/javascript" src="js/Bind.js"></script>
<div class="input">

<form action="bind!editSave.do" method="post"  class="validate">
<input type="hidden" name="bind.goods_id" value="${goods_id }"/>
<input type="hidden" name="bind.sn" value="${bind.sn }"/>
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="form-table">
	<tr>
		<td class="label" style="width: 100px">捆绑商品名称：</td>
		<td><input type="text" required="true" name="bind.name" value="${bind.name }"></td>
	</tr>
</table>
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="form-table">
	<tr>
		<td class="label" style="width: 100px">是否上架销售：</td>
		<td><label><input type="radio" checked="checked"
			value="1" name="bind.market_enable" <c:if test="${bind.market_enable == 1 }">checked</c:if>>是</label> &nbsp; &nbsp; <label><input
			type="radio" value="0" name="bind.market_enable" <c:if test="${bind.market_enable != 1 }">checked</c:if>>否</label></td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">库存：</td>
		<td><input type="text"  required="true" dataType="int" name="bind.store" value="${bind.store }"></td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">重量：</td>
		<td><input type="text" name="bind.weight" value="${bind.weight }" dataType="int" readonly="readonly" id="bind_weight">克(g)</td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">原价格：</td>
		<td><input type="text" name="bind.mktprice" readonly="readonly" id="bind_mktprice" value="${bind.mktprice }"></input></td>
	</tr>
	<tr>
		<td class="label" style="width: 100px">捆绑销售价：</td>
		<td><input type="text" name="bind.price"   readonly="readonly" dataType="float" value="${bind.price }">
		</td>
	</tr>
</table>
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="form-table">
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
					<tbody class="adjunctitem_body" >
					<c:forEach items="${packageList}" var="p">
					<tr class="product_item_${p.product_id }">
						<td><a href='javascript:;'><img src="images/transparent.gif" class='delete'></a><input type='hidden' name='product_id' value='${p.product_id }' /></td>
						<td>${p.name }</td>
						<td><input type='text' name='pkgnum' value='${p.pkgnum }' mkt_price='${p.price }' weight='${p.weight }'/></td>
					</tr>
					</c:forEach>
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
</form>
</div>

<div id="product_item_input">
	<div class="grid">
	<grid:grid  from="productPage">

	<grid:header>
	<grid:cell>&nbsp;</grid:cell>
	<grid:cell>货号</grid:cell> 
	<grid:cell>商品名称</grid:cell> 	
	<grid:cell  width="100px">销售价格</grid:cell> 
	<grid:cell>库存</grid:cell>	
	<grid:cell  width="100px">商品描述</grid:cell> 	
	</grid:header>

  <grid:body item="product">
  	 <grid:cell><input type="checkbox" value="${product.product_id}" name="product_id" productname="${product.name}" productprice="${product.price }" productweight="${product.weight }"/> </grid:cell>
     <grid:cell >${product.sn}</grid:cell>
     <grid:cell >${product.name}</grid:cell>
     <grid:cell >${product.price }</grid:cell>
     <grid:cell >${product.store } </grid:cell> 	
     <grid:cell >${product.specs }  </grid:cell>      
  </grid:body>  
</grid:grid>
	
</div>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	
</div>

<script type="text/javascript">
$(function(){
	$("form.validate").validate();
	Bind.init();
});
</script>
