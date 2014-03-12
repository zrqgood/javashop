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
		<grid:cell sort="cat_id"  width="100px">分类</grid:cell> 
		<grid:cell sort="price">销售价格</grid:cell>
		<grid:cell sort="store">库存</grid:cell>
 
	</grid:header>


  <grid:body item="goods" >
  
  		<grid:cell><input type="checkbox" name="goodsid" value="${goods.goods_id }" />${goods.goods_id }</grid:cell>
        <grid:cell>&nbsp;${goods.sn } </grid:cell>
        <grid:cell>&nbsp;<span name="goodsname">${goods.name }</span></grid:cell> 
        <grid:cell>&nbsp;${goods.cat_name} </grid:cell> 
        <grid:cell>&nbsp;<fmt:formatNumber value="${goods.price}" type="currency" pattern="￥.00"/> </grid:cell> 
        <grid:cell>&nbsp;${goods.store} </grid:cell> 
         
  </grid:body>
  
</grid:grid>
</div>

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="btn" type="button" value=" 确    定   "  class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	

</form>
