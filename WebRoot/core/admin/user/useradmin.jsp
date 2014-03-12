<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<script type="text/javascript" src="../js/PasswordOperator.js"></script>

<div class="grid">
<div class="toolbar">
<ul>
	<li><a href="userAdmin!add.do">添加</a></li>
</ul>
</div>
<grid:grid from="userList">
	<grid:header>
	<grid:cell width="50px">id</grid:cell>
		<grid:cell width="110px">用户名</grid:cell>
		<grid:cell width="200px">姓名</grid:cell>
		<grid:cell>状态</grid:cell>
		<grid:cell width="180px">操作</grid:cell>
	</grid:header>
	<grid:body item="userAdmin">
		<grid:cell>
			${userAdmin.userid}
		</grid:cell>
		<grid:cell>&nbsp;${userAdmin.username }
		</grid:cell>
		<grid:cell>&nbsp;${userAdmin.realname } </grid:cell>
		<grid:cell> 
		 <c:if test="${userAdmin.state==1}">启用</c:if>
		 <c:if test="${userAdmin.state==0}">禁用</c:if>
		</grid:cell>
		<grid:cell>&nbsp; 
			
			<a href="userAdmin!edit.do?id=${userAdmin.userid }"><img class="modify"  src="../images/transparent.gif"></a>
			 &nbsp;<a  href="userAdmin!delete.do?id=${userAdmin.userid }" onclick="javascript:return confirm('确认删除此管理员吗？')"><img class="delete" src="../images/transparent.gif"></a>
		
		</grid:cell>
	</grid:body>
</grid:grid></div>