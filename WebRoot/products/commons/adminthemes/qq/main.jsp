<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>QQEmail</title>
<link href="${context}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dialog.css"  rel="stylesheet" type="text/css" />

<script type="text/javascript" src="menu"></script>

<script type="text/javascript" src="${staticserver }/js/common/jquery-1.3.2.js"></script>

<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.draggable.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.resizable.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.shadow.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/dialog/jquery.dialog.js"></script>

<script type="text/javascript" src="${staticserver }/js/common/jquery.loading.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/jquery-menu.js"></script>
<script type="text/javascript" src="${context}/js/index.js"></script> 
<script type="text/javascript">
	$(function(){
		$(".sysmenu").dropMenu();
	});
</script>
</head>
<body>
<table width="100%" height="100%" cellspacing="0" cellpadding="0"  >
<tbody>
<tr><td valign="top"  height="66">
<div class="top">
	<div class="sysmenu"><ul></ul></div>
	<div class="logo"><img src="logo.gif" /></div>
	 <div class="divusermenu">
   <div class="userd"><span>oyk</span> 您好！</div>
	   <div class="usermenu">
	<ul id="toptab">
	</ul>
		</div>
   </div>
 
</div>
</td></tr>
<tr><td valign="top"  height="11">
<div class="topline"><div class="topline_left"></div></div>
</td></tr>
<tr><td height="100%" valign="top">
<div class="content">
<table  height="100%" cellspacing="0" cellpadding="0">
	<tbody>
	  <tr><td valign="top" height="100%">
			<div class="cleft" style="">
			<table   cellspacing="0" cellpadding="0">
			<tbody><tr><td valign="top" >
				<div id="leftMenus"> </div>
				</td></tr>
				<!--<tr><td valign="top" height="100%"  >
				 <div  class="leftMenus3">&nbsp;</div>
				 </td></tr>-->
				 <tr><td valign="top" height="13">
			     <div  class="leftMenus2"></div>
				 </td></tr></tbody></table>
			</div>
	 </td><td valign="top"  width="100%">
			<div class="cclient">
				<div id="header"></div>
				<div id="dispanel"></div>
			</div>
	 </td> </tr></tbody></table>
</div>
</td></tr></tbody></table> 
</body>
</html>