<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="image/x-icon" href="${ico}" rel="icon" />
<link type="image/x-icon" href="${ico}" rel="bookmark" />
<script type="text/javascript" src="menu"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.3.2.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery-form-2.33.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery.validate.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery.loading.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqModal1.1.3.1.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqDnR.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dimensions.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Grid.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery.dmenu.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/Utils.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery-menu.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor-3.4/kindeditor.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/swfupload.js "></script>
<script type="text/javascript" src="${staticserver }/js/admin/swfupload.queue.js"></script>
<link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dialog.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dmenu.css"  rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${context}/js/index.js"></script> 
<script type="text/javascript">
	$(function(){
		$(".sysmenu").dropMenu();
	});
</script>
</head>
<body>
<div class="top">
	<div class="sysmenu"><ul></ul></div>
	<div class="logo"><img src="${logo}" /></div>
	<ul id="toptab">
	</ul>
</div>

<div class="content">
	<div class="cleft">
		<div id="leftMenus">
		</div>
	</div>
	<div class="cclient">
		<div id="header"></div>
		<div id="dispanel"></div>
	</div>
</div>
 	
</body>
</html>