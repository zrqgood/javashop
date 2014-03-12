<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/AdColumn.js"></script>
<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="adColumn!add.do">新增广告位</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">
	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell width="250px">标题</grid:cell> 
	<grid:cell>广告类型</grid:cell>
	<grid:cell >宽度</grid:cell>
	<grid:cell >高度</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>
  <grid:body item="adColumn">
  		<grid:cell><input type="checkbox" name="id" value="${adColumn.acid }" />${adColumn.acid } </grid:cell>
        <grid:cell>${adColumn.cname }</grid:cell>
        <grid:cell><c:if test="${adColumn.atype == 0 }">图片</c:if><c:if test="${adColumn.atype == 1 }">Flash</c:if></grid:cell>
        <grid:cell>${adColumn.width} </grid:cell>
        <grid:cell>${adColumn.height }</grid:cell>  
        <grid:cell><a href="adColumn!edit.do?acid=${adColumn.acid}" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>
</grid:grid>  
</form>	
</div>