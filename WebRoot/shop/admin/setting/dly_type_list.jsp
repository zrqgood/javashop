<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/DlyType.js"></script>
 <div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="dlyType!add_type.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage" >

	<grid:header>
	<grid:cell  width="20px"><input type="checkbox" id="toggleChk" /></grid:cell> 
	<grid:cell>配送方式</grid:cell> 
	<grid:cell>支持保价</grid:cell>
	<grid:cell>支持货到付款</grid:cell>
	<grid:cell>操作</grid:cell>	
	</grid:header> 
 
  <grid:body item="type">
	 <grid:cell><input type="checkbox" name="id" value="${type.type_id }" /></grid:cell>
     <grid:cell>${type.name} </grid:cell> 
     <grid:cell><c:if test="${type.protect==1}">是</c:if><c:if test="${type.protect==0}">否</c:if></grid:cell> 
     <grid:cell><c:if test="${type.has_cod}">是</c:if><c:if test="${!type.has_cod}">否</c:if></grid:cell> 
     <grid:cell align="center"> <a href="dlyType!edit.do?type_id=${type.type_id }" ><img class="modify" src="images/transparent.gif"/></a> </grid:cell> 
  </grid:body>
  
</grid:grid>  
</form>	
<DIV style="clear:both;margin-top:5px;"></DIV>
</div>