<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/FreeOfferCategory.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="freeOfferCategory!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="freeOfferCategory!trash_list.do">回收站</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell sort="cat_name" width="250px">分类名称</grid:cell> 
	<grid:cell sort="publishable">发布</grid:cell>
	<grid:cell sort="sorder">排序</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="freeOfferCategory">
  		<grid:cell><input type="checkbox" name="id" value="${freeOfferCategory.cat_id }" />${freeOfferCategory.cat_id } </grid:cell>
        <grid:cell>${freeOfferCategory.cat_name } </grid:cell>
        <grid:cell><c:if test="${freeOfferCategory.publishable == 0}">是</c:if><c:if test="${freeOfferCategory.publishable == 1}">否</c:if> </grid:cell>
        <grid:cell>${freeOfferCategory.sorder}</grid:cell>  
        <grid:cell> <a href="freeOfferCategory!edit.do?cat_id=${freeOfferCategory.cat_id }" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>


