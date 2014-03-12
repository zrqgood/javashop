<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Role.js"></script>
<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="role!add.do">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="roleList">

	<grid:header>
	<grid:cell sort="name" width="250px">角色名称</grid:cell> 
	<grid:cell sort="url">描述</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="role">
        <grid:cell>${role.rolename } </grid:cell>
        <grid:cell>${role.rolememo} </grid:cell> 
        <grid:cell> 
       		 <a  href="role!edit.do?roleid=${role.roleid }" ><img class="modify" src="images/transparent.gif" ></a> 
 &nbsp;&nbsp;<a  href="role!delete.do?roleid=${role.roleid }" onclick="javascript:return confirm('确认删除此角色吗？删除后不可恢复');" ><img class="delete" src="images/transparent.gif" ></a> 
        </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>