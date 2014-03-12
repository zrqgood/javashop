<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid" %>
<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="#">添加</a></li>
			<li><a href="#">修改</a></li>
			<li><a href="#">删除</a></li>
		</ul>
	</div>
	<grid:grid from="webPage">
		<grid:header>
			<grid:cell sort="id" width="50px">id</grid:cell>
			<grid:cell sort="username" width="80px">用户名</grid:cell>
			<grid:cell sort="address">地址</grid:cell>
		</grid:header>
		<grid:body item="user">
			<grid:cell><input type="checkbox" name="id" value="${user.id }" />${user.id }</grid:cell>
			<grid:cell>&nbsp;${user.username } </grid:cell>
			<grid:cell>&nbsp;${user.address } <a href="javascript:deleteUser();" >delete</a></grid:cell>
		</grid:body>
	</grid:grid>
</div>