<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid"%>
<link href="../css/style.css" rel="stylesheet" type="text/css" />

<div class="sitetheme">
	<div class="template Border Background" >
		<div class="depict">
			<h3>当前使用的风格</h3>此模板是您前台正在使用的模板
		</div>
		<img src="${previewpath }"/>
		<div class="theme">
			<h3>${theme.themename }</h3>
			<span>作者：${theme.author }</span>
			<span>版本：${theme.version }</span>
		</div>
	</div>
	<input type="button" id="newBtn" value="新建模板" onclick="javascript:location.href='siteTheme!add.do'" style="margin-top:10px"/>
	<div class="all-themes Border Background">
		<span>其它前台风格</span>
		<c:forEach var="item" items="${listTheme }">
			<div class="item">
				<img class="ImgBorder ImgBackground" src="${previewBasePath }${item.path}/preview.png" />
				<a class="ButtonBackground ButtonBorder"   href="siteTheme!change.do?themeid=${item.id }">使用此风格</a>
				<a class="ButtonBackground ButtonBorder" href="${ctx }/core/admin/themeFile!list.do?themeid=${item.id }&folderName=/&type=file">文件管理</a>
				<a class="ButtonBackground ButtonBorder" href="${ctx }/core/admin/themeFile!list.do?themeid=${item.id }&folderName=/&type=style">样式管理</a>
				<span>${item.themename }</span>
				<span>作者：${item.author }</span>
				<span>版本：${item.version }</span>
			</div>
		</c:forEach>
	</div>
</div>