<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仿DEDE后台模板</title>

<script type="text/javascript" src="menu"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.3.2.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.draggable.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.resizable.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.shadow.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.dialog.js"></script>
<link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dialog.css"  rel="stylesheet" type="text/css" />

<link href="${context}/css/dmenu.css"  rel="stylesheet" type="text/css" />
<link href="${context}/css/shadow.css"  rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${staticserver }/js/common/jquery.loading.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>


<script type="text/javascript" src="${staticserver }/js/common/jquery.shadow.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery.dmenu.js"></script>

<script type="text/javascript" src="${context}/js/index.js"></script>
<script type="text/javascript">
$(function(){
	$('ul.dmenu').dmenu();
});
</script>

</head>
<body>
<div id="head">
	<div class="top">
		<div class="logo"><a href="#"><img src="${context}/images/logo.gif"/></a></div>
		<div id="sysmenu">
			<ul class="dmenu"></ul>
		</div>
	</div>
	<div class="topnav">
		<div class="top_menu">
			<div class="menuact">
				<a href="#">隐藏菜单</a>
				<a href="#">功能地图</a>
			</div>
		</div>
		<div class="top_link">
				<a href="#">官方论坛</a>
				<a href="#">在线帮助</a>
		</div>
	</div>
</div>

	<div id="left">
		<div id="appmenu">
			<ul>
			</ul>
		</div>
		<div id="extmenu">
			<ul>
			</ul>
			<div class="extmenu_foot"></div>
		</div>
	</div>
	<div id="right">
		<div id="header">
			<div id="nav_title">基本信息</div>
		</div>	
		<div id="right_content"></div>
	</div>
</body>
</html>