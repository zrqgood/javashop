<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ArtiFactory</title>
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
<tr><td valign="top" height="81">
<div class="top">
	<div class="sysmenu"><ul></ul></div>
	<div class="logo"><a href="#">artifactory</a></div>
	 <div class="divusermenu">
	   <div class="usermenu">
	<ul id="toptab">
	 </ul>
	 </div>
   </div>
 
</div>
</td></tr>
<tr><td height="100%" valign="top">
<div class="content">
<table  height="100%" cellspacing="0" cellpadding="0">
	<tbody>
	  <tr><td valign="top" height="100%">
			<div class="cleft"  >
			  <div class="leftMenus">
			   <div class="leftMenus1">
			    <div class="leftMenus2">
			      <div id="leftMenus"></div>
				  
				 </div>
				</div>
			 </div>
			</div>
	 </td><td valign="top"  width="100%">
			<div class="cclient">
				<div id="header"></div>
				<div id="dispanel"></div>
			</div>
	 </td> </tr></tbody></table>
</div>
</td></tr>
<tr><td valign="top"  height="23">
<div class="footer">

<p class="copywrites">
<span>@ Copyright 2009</span>
<a href="#">JFrog Ltd.</a>
<span id="id85">
<span content="#" name="footer"></span>
</span>
</p>
<p class="version">Artifactory 2.1.3 (rev. 9204)</p>

</div>
</td></tr>
</tbody></table> 
</body>
</html>