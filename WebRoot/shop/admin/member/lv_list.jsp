<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Member.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="member!add_lv.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<!--  <li><a href="member!trash_list_lv.do">回收站</a></li> -->
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell  width="20px"><input type="checkbox" id="toggleChk" /></grid:cell> 
	<grid:cell  width="100px">id</grid:cell> 
	<grid:cell>等级名称</grid:cell> 
	<grid:cell  width="100px">默认等级</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="lv">
	 <grid:cell><input type="checkbox" name="id" value="${lv.lv_id}" /></grid:cell>
     <grid:cell>${lv.lv_id} </grid:cell>
     <grid:cell>${lv.name} </grid:cell> 
     <grid:cell><c:if test="${lv.default_lv}">是</c:if> <c:if test="${!lv.default_lv}">否</c:if></grid:cell> 
     <grid:cell> <a  href="member!edit_lv.do?lv_id=${lv.lv_id }" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  

</form>	
<DIV style="clear:both;margin-top:5px;"></DIV>
</div>



 

