<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/Theme.js"></script>
<div class="sitetheme">
	<div class="template Border Background" >
		<div class="depict">
			<h3>当前后台使用的主题</h3>
		</div>
		<img src="${previewpath }"/>
		<div class="theme">
			<h3>${adminTheme.themename }</h3>
			<span>作者：${adminTheme.author }</span>
			<span>版本：${adminTheme.version }</span>
		</div>
	</div>
	<div class="all-themes Border Background" >
		<span>其它后台主题</span>
		<c:forEach var="item" items="${listTheme }">
			<div class="item">
				<img class="ImgBorder ImgBackground" src="${previewBasePath }${item.path}/preview.png" />
				<span>${item.themename }</span>
				<span>作者：${item.author }</span>
				<span>版本：${item.version }</span>
				<a class="ButtonBackground ButtonBorder" themeid="${item.id }" href="javascript:;" >使用此主题</a>
			</div>
		</c:forEach>
	</div>
</div>