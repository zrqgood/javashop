<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<form id="serchform">
<div class="input">
<table class="form-table">
	<tr>
		<th>
		<select name="sType">
			<option value="sn">货号</option>
			<option value="name">商品名称</option>
		</select>
		：</th>
		<td><input type="text" name="keyword"  value="${keyword }"/><input type="button" name="searchBtn" id="searchBtn" value="搜索"/></td>
	</tr>
</table>		
</div>

<div style="clear:both;padding-top:5px;"></div>

<div class="grid" id="goodslist">

<grid:grid  from="webpage"  ajax="yes" >

	<grid:header>
		<grid:cell  width="50px"><input type="checkbox" id="toggleChk"/></grid:cell> 
		<grid:cell sort="sn" width="80px">货号</grid:cell> 
		<grid:cell sort="name" width="250px">商品名称</grid:cell>
		<grid:cell sort="price">销售价格</grid:cell>
		<grid:cell sort="store">库存</grid:cell>
		<grid:cell >物品描述</grid:cell>
	</grid:header>


  <grid:body item="product" >
  
  		<grid:cell><input type="checkbox" name="productid" value="${product.product_id }" />${product.product_id }</grid:cell>
        <grid:cell>&nbsp;${product.sn } </grid:cell>
        <grid:cell>&nbsp;<span name="goodsname">${product.name }</span></grid:cell> 
        <grid:cell>&nbsp;<fmt:formatNumber value="${product.price}" type="currency" pattern="￥.00"/> </grid:cell> 
        <grid:cell>&nbsp;${product.store} </grid:cell> 
        <grid:cell>&nbsp;${product.specs} </grid:cell> 
         
  </grid:body>
  
</grid:grid>
</div>
</form>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="saveBtn" type="button" value=" 确    定   "  class="submitBtn" />
   </td>
   </tr>
 </table>
</div>