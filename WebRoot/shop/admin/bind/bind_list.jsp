<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/BindList.js"></script>

<form action="" id="bind_form" method="post">

<div class="grid" id="gridDiv">


	<div class="toolbar">
	<ul><li><a href="bind!add.do">添加捆绑商品</a> </li></ul>
	<div style="clear:both"></div>
	</div>
	
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="350px">商品名称</grid:cell> 
	<grid:cell   >捆绑原价格</grid:cell> 	
	<grid:cell  width="100px">捆绑销售价</grid:cell> 
	<grid:cell  width="100px">删除</grid:cell> 	
	<grid:cell  width="100px">编辑</grid:cell> 
	</grid:header>

  <grid:body item="bind">
  
     <grid:cell >${bind.name }</grid:cell>
     <grid:cell >${bind.mktprice }</grid:cell>
     <grid:cell >${bind.price }</grid:cell>
     <grid:cell align="center"> 
     	<a href="javascript:;"><img class="delete" goods_id="${bind.goods_id }" src="images/transparent.gif" > </a>
     </grid:cell>      
     <grid:cell align="center"> <a href="bind!edit.do?goods_id=${bind.goods_id }"><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
       
       
  </grid:body>  
  
</grid:grid>  
</div>

</form>
