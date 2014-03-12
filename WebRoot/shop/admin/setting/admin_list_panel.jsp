<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<sql:datasource id="adminlist" type="list">
	<sql:append>
select * from js_administrator 
 </sql:append>
</sql:datasource>

<form action="" id="admin_from" method="post">
	<div class="grid-div" id="gridDiv">

		<div class="toolbar">
			<span class="action-span-btn"><a href="admin_add.jsp"><img src="../images/icon_add.gif" border="0" />添加</a>
			</span>
			<span class="action-span-btn"><a href="javascript:del();"><img src="../images/icon_drop.gif" border="0" />删除</a>
			</span>
			<div style="clear:both"></div>
		</div>

		<grid:grid from="adminlist" type="list" ajax="yes">

			<grid:header>
			<grid:cell  width="20px"><input type="checkbox" onclick="javascript:Utils.chk_ctl_all('id',this.checked);" /></grid:cell> 
				<grid:cell>用户名</grid:cell>
				<grid:cell>姓名</grid:cell>
				<grid:cell>最后登录时间</grid:cell>
				<grid:cell>状态</grid:cell>
				<grid:cell>操作</grid:cell>
			</grid:header>

			<grid:body item="admin">
				<grid:cell><input type="checkbox" name="id" value="${admin.id }" /></grid:cell>
				<grid:cell>${admin.uname}</grid:cell>
				<grid:cell>${admin.name}</grid:cell>
				<grid:cell>
					<html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${admin.last_login}"></html:dateformat>
				</grid:cell>
				<grid:cell><c:if test="${admin.status==1}" >启用</c:if><c:if test="${admin.status==0}" >禁用</c:if>
				</grid:cell>
				 <grid:cell align="left"> <a href="admin_edit.jsp?id=${admin.id }" ><img src="../images/icon_edit.gif" border="0"/></a> </grid:cell> 
			</grid:body>

		</grid:grid>

	</div>
</form>
