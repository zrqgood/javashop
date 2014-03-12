<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Logi.js"></script>
 <div class="grid">

	<div class="toolbar">
 
		<ul>
			<li><a href="logi!add_logi.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage" >

	<grid:header>
	<grid:cell  width="20px"><input type="checkbox"  id="toggleChk" /></grid:cell> 
	<grid:cell>公司名称</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="logi">
	 <grid:cell><input type="checkbox" name="id" value="${logi.id }" /></grid:cell>
     <grid:cell>${logi.name} </grid:cell> 
     <grid:cell align="center"> <a href="logi!edit_logi.do?cid=${logi.id }" ><img class="modify" src="images/transparent.gif"/></a></grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<DIV style="clear:both;margin-top:5px;"></DIV>
</div>
 
 

