<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<script type="text/javascript">
$(function(){
	$(".btn").click(function(){
		//alert("http://"+$(this).attr("adomain") + "/eop/admin");
		top.location.href="http://"+$(this).attr("adomain") + "admin";
		});
});
</script>
<div class="grid">
<div class="toolbar">
<ul>
	<li><a href="userSite!add.do">添加</a></li>
	<li><a href="#">删除</a></li>
	<li>
	<form action="userSite.do" method="post" id="theForm" name="theForm">
	<label>关键字：</label> <input id="search" name="search" type="text"
		value="${search }"></input> <input type="submit"
		style="margin-left: 5px;" value="查询" /></form>
	</li>
</ul>
</div>
<grid:grid from="webpage">
	<grid:header>
		<grid:cell sort="id" width="50px">id</grid:cell>
		<grid:cell width="110px">网站Logo</grid:cell>
		<grid:cell sort="sitename" width="200px">站点名称</grid:cell>
		<grid:cell>站点域名</grid:cell>
		<grid:cell>操作</grid:cell>
	</grid:header>
	<grid:body item="userSite">
		<grid:cell>
			<input type="checkbox" name="id" value="${userSite.id }" />${userSite.id }</grid:cell>
		<grid:cell>&nbsp;
		<c:if test="${userSite.logofile!=null }">
				<img src="${userSite.logofile }" width="100" height="56" />
			</c:if>
		</grid:cell>
		<grid:cell>&nbsp;${userSite.sitename } </grid:cell>
		
		<grid:cell><ul style="width:90%">
			<c:forEach var="item" items="${userSite.eopSiteDomainList }">
				<li style="width:100%;float:left">${item.domain } &nbsp;
				</li>
			</c:forEach></ul>
		</grid:cell>
		<grid:cell>&nbsp;
		<!-- 
		<c:if test="${defaultsiteid != userSite.id }">
		<a href="userSite!delete.do?id=${userSite.id }" onclick="return confirm('您确定要删除当前记录吗？')">
			<img class="delete" src="../images/transparent.gif" > 
		</a></c:if>
				&nbsp;
				 -->
		<a	href="userSite!edit.do?id=${userSite.id }"> 
		 <img class="modify" src="../images/transparent.gif" > 
		</a>
		&nbsp;
		<c:if test="${defaultsiteid == userSite.id }">默认站点</c:if>
				
		</grid:cell>
	</grid:body>
</grid:grid></div>