<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" >
loadScript("js/Goods.js");
</script>
<div class="grid">

	<div class="toolbar">
	<ul>
	<li><a href="javascript:;" id="revertBtn">还原</a></li>
	<li><a href="javascript:;" id="cleanBtn">清除</a></li>
	</ul>
	<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage" >

	<grid:header>
		<grid:cell  width="50px"><input type="checkbox" id="toggleChk"/></grid:cell> 
		<grid:cell sort="sn" width="50px">货号</grid:cell> 
		<grid:cell sort="name" width="100px">商品名称</grid:cell>
		<grid:cell sort="cat_id"  width="100px">分类</grid:cell> 
		<grid:cell sort="price">销售价格</grid:cell>
		<grid:cell sort="store">库存</grid:cell>
		<grid:cell sort="market_enable">上架</grid:cell>
		<grid:cell sort="brand_id">品牌</grid:cell>
		<grid:cell sort="last_modify">上架时间</grid:cell>
		<grid:cell sort="type_id">类型</grid:cell>
		
	</grid:header>


  <grid:body item="goods" >
  		<grid:cell><input type="checkbox" name="id" value="${goods.goods_id }" />${goods.goods_id }</grid:cell>
        <grid:cell>&nbsp;${goods.sn } </grid:cell>
        <grid:cell>&nbsp;${goods.name } </grid:cell> 
        <grid:cell>&nbsp;${goods.cat_name} </grid:cell> 
        <grid:cell>&nbsp;<fmt:formatNumber value="${goods.price}" type="currency" pattern="￥.00"/> </grid:cell> 
        <grid:cell>&nbsp;${goods.store} </grid:cell> 
        <grid:cell><c:if test="${goods.market_enable==0}" >否</c:if> <c:if test="${goods.market_enable==1}" >是</c:if></grid:cell>
        <grid:cell>&nbsp;${goods.brand_name} </grid:cell> 
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${goods.last_modify}"></html:dateformat> </grid:cell> 
        <grid:cell>&nbsp;${goods.type_name} </grid:cell> 
  </grid:body> 
  
  
</grid:grid>
</form>  
</div>
